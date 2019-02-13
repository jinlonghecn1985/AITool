/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: AIController.java
 * @Prject: AITool
 * @Package: com.hnjing.ai.controller
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月26日 下午4:44:02
 * @version: V1.0  
 */
package com.hnjing.ai.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnjing.ai.bo.RgeulationsBo;
import com.hnjing.ai.model.entity.Assessment;
import com.hnjing.ai.model.entity.Communicate;
import com.hnjing.ai.model.entity.Project;
import com.hnjing.ai.model.entity.Regulations;
import com.hnjing.ai.service.AssessmentService;
import com.hnjing.ai.service.CommunicateService;
import com.hnjing.ai.service.ProjectService;
import com.hnjing.ai.service.RegulationsService;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.config.web.exception.ParameterException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: AIController
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2019年1月26日 下午4:44:02
 */
@RestController
@Api(description="AI管理接口")
public class AIController {
	
	private static String lineSeparator = System.getProperty("line.separator");
	private static final Logger logger = LoggerFactory.getLogger(AIController.class);
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private RegulationsService regulationsService;
	
	@Autowired
	private CommunicateService communicateService;

	@ApiOperation(value = "查询 根据沟通分析标识查询沟通分析信息", notes = "根据沟通分析标识查询沟通分析信息")
	@RequestMapping(value = "/ai/{projectId:.+}", method = RequestMethod.GET)
	public Object analysisAi(HttpServletResponse response, @PathVariable Integer projectId) {
		// NO1 条件验证
		Project tempProject = projectService.queryProjectByProjectId(projectId);
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		List<Regulations> regulationsList = regulationsService.queryRegulationsByProperty(map);
		if(regulationsList==null || regulationsList.size()==0) {
			throw new ParameterException("Regulations", "项目没有配置相应话术识别规则，请先导入规则！");
		}
		logger.debug(" regulationsList:"+regulationsList.size());
		List<Communicate> communicateList = communicateService.queryCommunicateByProperty(map);
		if(communicateList==null || communicateList.size()==0) {
			throw new ParameterException("communicate", "项目没有导入相应沟通记录，请先导入沟通记录！");
		}
		logger.debug(" communicateList:"+communicateList.size());
		// NO2 数据准备
		assessmentService.dropAssessmentByProjectId(projectId); //清空已有分析数据
		Map<String, RgeulationsBo> rgeulationsMap = new HashMap<String, RgeulationsBo>();		
		for(Regulations reg : regulationsList) {
			RgeulationsBo rb = new RgeulationsBo();
			rb.setRegulations(reg);
			rgeulationsMap.put(reg.getContent(), rb);
			
		}
		int yesCount = 0;
		int noCount = 0;
		int otherCount = 0;
		// NO3 数据处理
		for(Communicate commu : communicateList) {
			int falg = 1;
			if(commu.getContent()!=null) {
				processComm(rgeulationsMap, projectId, commu, yesCount, noCount, otherCount, falg);
			}
//			if(commu.getUseCode()!=null) {
				commu.setNote(""+falg);
				communicateService.modifyCommunicate(commu);
//			}
		}
		tempProject.setSureNum(yesCount);
		tempProject.setNegativeNum(noCount);
		tempProject.setNeutralNum(otherCount);
		tempProject.setProjectName(null);
		tempProject.setTotalNum(null);
		tempProject.setNote(null);
		projectService.modifyProject(tempProject);		
		return tempProject;
	}
	
	private void processComm(Map<String, RgeulationsBo> rgeulationsMap, Integer projectId, Communicate commu, int yesCount, int noCount, int otherCount, int falg) {
		commu.setUseCode(null);
//		logger.debug("    process:"+commu.getContent());
		String[] contents = commu.getContent().split(lineSeparator);				
		Assessment assessment = null;
		for(String c : contents) {
			String line = c.replace("\n", ""); //问题合并
//			logger.debug("    process:"+line);
			if(line.startsWith("AI：")) {
				//判断是否问题
//				logger.debug("     setQuestion:"+line);
				assessment = new Assessment();
				assessment.setProjectId(projectId);
				assessment.setCid(commu.getCid());
				assessment.setQuestion(line.substring(4, line.length()-1).replaceAll("\\pP" , ""));	
				assessment.setFlag(falg++);
			}else {
				if(assessment==null) {
					assessment = new Assessment();
					assessment.setProjectId(projectId);
					assessment.setCid(commu.getCid());
					assessment.setQuestion("fii");
				}
				//回答
				otherCount++;
//				logger.debug("     setAnswer:"+line);
				
				assessment.setAssessment(9);
				assessment.setAnswer(line.substring(4, line.length()-1));
								
				if(rgeulationsMap.containsKey(assessment.getQuestion())) {
					RgeulationsBo rb = rgeulationsMap.get(assessment.getQuestion());
					assessment.setRid(rb.getRegulations().getRegulationsId()); //匹配到策略
					String checkOrder = "213";
					if(rb.getRegulations()!=null && rb.getRegulations().getUsorder()!=null && rb.getRegulations().getUsorder().trim().length()==3) {
						checkOrder = rb.getRegulations().getUsorder().trim();
					}
					boolean hasKey = false;  	//有命中到KEY
					String yno = "9";
					for(int i=0; i<checkOrder.length(); i++) {
						String source = checkOrder.substring(i, i+1);						
						if(!hasKey && "1".equals(source)) {
							hasKey = distinguishData(assessment, rb.getKeyWordsYes());
							if(hasKey) {yesCount++;otherCount--;yno="2";}
							//commu.setUseCode(commu.getUseCode()==null?"Y":(commu.getUseCode()+"Y"));
						}else if(!hasKey && "2".equals(source)) {
							hasKey = distinguishData(assessment, rb.getKeyWordsNo());
							if(hasKey) {noCount++;otherCount--;yno="1";}
							//commu.setUseCode(commu.getUseCode()==null?"N":(commu.getUseCode()+"N"));
						}else if(!hasKey && "3".equals(source)) {
							hasKey = distinguishData(assessment, rb.getKeyWordsOther());
							if(hasKey) {yno = "0";}
							//commu.setUseCode(commu.getUseCode()==null?"O":(commu.getUseCode()+"O"));
						}	
						assessment.setAssessment(Integer.parseInt(yno));
					}	
					if(hasKey && yno.length()>0 && rb.getRegulations().getUscode()!=null ) {
						commu.setUseCode(commu.getUseCode()==null?(rb.getRegulations().getUscode()+yno):(commu.getUseCode()+rb.getRegulations().getUscode()+yno));
					}
//					logger.debug("     save:"+JsonUtil.object2json(commu));
				}else {
					logger.debug("     nonono:");
				}
				
				
				assessmentService.addAssessment(assessment);
				assessment = null; //
			}
		}
	}
	

	
	
	private boolean distinguishData(Assessment assessment, String[] words) {  
		String a = "";
		for(int i=0; i<words.length; i++) {
			a+=(words[i]+",");
		}
		
//		logger.debug("     assessment :"+assessment.getAnswer()+" \r\n "+a);
		if(assessment!=null && assessment.getAnswer()!=null && words!=null && words.length>0) {
			for(String key: words) {
//				logger.debug("     ******************key:"+key);
				if(key!=null && key.length()>0 && assessment.getAnswer().contains(key)) {
//					logger.debug("     *********OK:"+key+" ="+assessment.getAnswer());
					assessment.setKeyword(key);
					return true;
				}
			}
		}	
		return false;
	}
	
	
}
