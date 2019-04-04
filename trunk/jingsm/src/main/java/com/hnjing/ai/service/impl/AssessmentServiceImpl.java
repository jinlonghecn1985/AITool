package com.hnjing.ai.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjing.ai.model.dao.AssessmentMapper;
import com.hnjing.ai.model.entity.Assessment;
import com.hnjing.ai.service.AssessmentService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: Assessment
 * @Description: 沟通分析服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Service("assessmentService")
@Transactional(readOnly=true)
public class  AssessmentServiceImpl implements AssessmentService {	
	private static final Logger logger = LoggerFactory.getLogger(AssessmentServiceImpl.class);
	
	@Autowired
    private AssessmentMapper assessmentMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addAssessment
	 * @Description:添加沟通分析
	 * @param assessment 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Assessment addAssessment(Assessment assessment){
		int ret = assessmentMapper.addAssessment(assessment);
		if(ret>0){
			return assessment;
		}
		return null;
	}
	
	/**
	 * @Title modifyAssessment
	 * @Description:修改沟通分析
	 * @param assessment 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyAssessment(Assessment assessment){
		return assessmentMapper.modifyAssessment(assessment);
	}
	
	/**
	 * @Title: dropAssessmentByCid
	 * @Description:删除沟通分析
	 * @param cid 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropAssessmentByCid(Integer cid){
		return assessmentMapper.dropAssessmentByCid(cid);
	}
	
	/**
	 * @Title: queryAssessmentByCid
	 * @Description:根据实体标识查询沟通分析
	 * @param cid 实体标识
	 * @return Assessment
	 */
	@Override
	public Assessment queryAssessmentByCid(Integer cid){
		return assessmentMapper.queryAssessmentByCid(cid);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryAssessmentForPage
	 * @Description: 根据沟通分析属性与分页信息分页查询沟通分析信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param assessment 实体
	 * @return List<Assessment>
	 */
	@Override
	public Map<String, Object> queryAssessmentForPage(Integer pagenum, Integer pagesize, String sort, Assessment assessment){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, Assessment.class);
		List<Assessment> entityList = assessmentMapper.queryAssessmentForPage(pageBounds, assessment);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, Assessment.class);
		}
		
		PageList<Assessment> pagelist = (PageList<Assessment>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryAssessmentByProperty
	 * @Description:根据属性查询沟通分析
	 * @return List<Assessment>
	 */
	@Override
	public List<Assessment> queryAssessmentByProperty(Map<String, Object> map){
		return assessmentMapper.queryAssessmentByProperty(map);
	}

	/*
	 * @Title: dropAssessmentByProjectId
	 * @Description: 
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.AssessmentService#dropAssessmentByProjectId(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer dropAssessmentByProjectId(Integer projectId) {
		return assessmentMapper.dropAssessmentByProjectId(projectId);
	}

	/*
	 * @Title: exportByProperty
	 * @Description: TODO
	 * @param @param map
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param map
	 * @return
	 * @see com.hnjing.ai.service.AssessmentService#exportByProperty(java.util.Map)
	 */ 
	@Override
	public HSSFWorkbook exportByProperty(Map<String, Object> map) {
		String[] title = {"项目名称 ", "联系人", "沟通顺序", "AI问题", "客户回答", "判定", "命中词"};
		String[][] data = null;
		List<Assessment> info = queryAssessmentByProperty(map);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][7];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = "";
				data[j][1] = "";
				data[j][2] = ""+info.get(j).getFlag();				
				data[j][3] = info.get(j).getQuestion();
				data[j][4] = info.get(j).getAnswer();		//0否定 1肯定 2中立
				data[j][5] = info.get(j).getAssessment()==2?"肯定":(info.get(j).getAssessment()==1?"否定":"中立");					
				data[j][6] = info.get(j).getKeyword()==null?"":info.get(j).getKeyword();
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("分析结果详情", title, data, null);
	}

	/*
	 * @Title: exportRegulationQuestionByPid
	 * @Description: 策略和问题的匹配情况
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.AssessmentService#exportRegulationQuestionByPid(java.lang.Integer)
	 */ 
	@Override
	public HSSFWorkbook exportRegulationQuestionByPid(Integer projectId) {
		String[] title = {"策略内容", "AI问题"};
		String[][] data = null;
		List<HashMap<String, String>> info = assessmentMapper.queryRegulationQuestionByPid(projectId);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][2];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).get("content");
				data[j][1] = info.get(j).get("question");
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("计划策略和问题的匹配情况", title, data, null);
	}

	/*
	 * @Title: queryAssessmentKeyword
	 * @Description: 
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.AssessmentService#queryAssessmentKeyword(java.lang.Integer)
	 */ 
	@Override
	public List<Map<String, String>> queryAssessmentKeyword(Integer projectId) {
		return assessmentMapper.queryAssessmentKeyword(projectId);
	}

	/*
	 * @Title: exportAssessmentKeyword
	 * @Description: TODO
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.AssessmentService#exportAssessmentKeyword(java.lang.Integer)
	 */ 
	@Override
	public HSSFWorkbook exportAssessmentKeyword(Integer projectId) {
		String[] title = {"问题", "关键词", "数量"};
		String[][] data = null;
		List<Map<String, String>> info = queryAssessmentKeyword(projectId);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][3];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).get("question");
				data[j][1] = info.get(j).get("keyword");
				data[j][2] = info.get(j).get("ct");
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("问题与关键词匹配情况", title, data, null);
	}

	/*
	 * @Title: queryAssessentTotal
	 * @Description: TODO
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.AssessmentService#queryAssessentTotal(java.lang.Integer)
	 */ 
	@Override
	public List<Map<String, String>> queryAssessentTotal(Integer projectId) {
		return assessmentMapper.queryAssessentTotal(projectId);
	}



}
