package com.hnjing.ai.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hnjing.ai.model.entity.Assessment;
import com.hnjing.ai.model.entity.Project;
import com.hnjing.ai.service.AssessmentService;
import com.hnjing.ai.service.ProjectService;
import com.hnjing.config.validation.BeanValidator;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.utils.ClassUtil;
import com.hnjing.utils.HttpTool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: AssessmentController
 * @Description: 沟通分析HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@RestController
@Api(description="沟通分析")
public class AssessmentController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private ProjectService projectService;

	
	@ApiOperation(value = "新增 添加沟通分析信息", notes = "添加沟通分析信息")
	@RequestMapping(value = "/assessment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addAssessment(HttpServletResponse response,
			@ApiParam(value = "assessment") @RequestBody Assessment assessment) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		assessment.setCid(null);
		assessmentService.addAssessment(assessment);
		return assessment;
	}
	
	
	@ApiOperation(value = "更新 根据沟通分析标识更新沟通分析信息", notes = "根据沟通分析标识更新沟通分析信息")
	@RequestMapping(value = "/assessment/{cid:.+}", method = RequestMethod.PUT)
	public Object modifyAssessmentById(HttpServletResponse response,
			@PathVariable Integer cid,
			@ApiParam(value = "assessment", required = true) @RequestBody Assessment assessment
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Assessment tempAssessment = assessmentService.queryAssessmentByCid(cid);
		assessment.setCid(cid);
		if(null == tempAssessment){
			throw new NotFoundException("沟通分析");
		}
		return assessmentService.modifyAssessment(assessment);
	}

	@ApiOperation(value = "删除 根据沟通分析标识删除沟通分析信息", notes = "根据沟通分析标识删除沟通分析信息")
	@RequestMapping(value = "/assessment/{cid:.+}", method = RequestMethod.DELETE)
	public Object dropAssessmentByCid(HttpServletResponse response, @PathVariable Integer cid) {
		Assessment assessment = assessmentService.queryAssessmentByCid(cid);
		if(null == assessment){
			throw new NotFoundException("沟通分析");
		}
		return assessmentService.dropAssessmentByCid(cid);
	}
	
	@ApiOperation(value = "查询 根据沟通分析标识查询沟通分析信息", notes = "根据沟通分析标识查询沟通分析信息")
	@RequestMapping(value = "/assessment/{cid:.+}", method = RequestMethod.GET)
	public Object queryAssessmentById(HttpServletResponse response,
			@PathVariable Integer cid) {
		Assessment assessment = assessmentService.queryAssessmentByCid(cid);
		if(null == assessment){
			throw new NotFoundException("沟通分析");
		}
		return assessment;
	}
	
	@ApiOperation(value = "查询 根据沟通分析属性查询沟通分析信息列表", notes = "根据沟通分析属性查询沟通分析信息列表")
	@RequestMapping(value = "/assessment", method = RequestMethod.GET)
	public Object queryAssessmentList(HttpServletResponse response,
			Assessment assessment) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return assessmentService.queryAssessmentByProperty(ClassUtil.transBean2Map(assessment, false));
	}
	
	@ApiOperation(value = "查询分页 根据沟通分析属性分页查询沟通分析信息列表", notes = "根据沟通分析属性分页查询沟通分析信息列表")
	@RequestMapping(value = "/assessments", method = RequestMethod.GET)
	public Object queryAssessmentPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, Assessment assessment) {				
		return assessmentService.queryAssessmentForPage(pagenum, pagesize, sort, assessment);
	}
	
	
	@ApiOperation(value = "导出数据", notes = "导出数据")
	@RequestMapping(value = "/assessments/export", method = RequestMethod.GET)
	public void exportDataList(HttpServletResponse response,
			Assessment assessment) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		if(assessment==null || assessment.getProjectId()==null) {
			throw new ParameterException("projectId", "找不到必传参数，计划标识 ！");
		}
		Project tempProject = projectService.queryProjectByProjectId(assessment.getProjectId());
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		String fileName = "assessment_item_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = assessmentService.exportByProperty(ClassUtil.transBean2Map(assessment, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}
	
	@ApiOperation(value = "导出指定计划策略和问题的匹配情况 ", notes = "导出数据")
	@RequestMapping(value = "/assessments/export2/{projectId:.+}", method = RequestMethod.GET)
	public void exportRegulationDataList(HttpServletResponse response,
			@PathVariable Integer projectId) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		Project tempProject = projectService.queryProjectByProjectId(projectId);
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		String fileName = "ass_reg_item_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = assessmentService.exportRegulationQuestionByPid(projectId);
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}
	
	@ApiOperation(value = "分析结果匹配情况", notes = "分析结果匹配情况")
	@RequestMapping(value = "/assessments/keyword/{projectId:.+}", method = RequestMethod.GET)
	public List<Map<String, String>> queryAssessmentKeyword(HttpServletResponse response,
			@PathVariable Integer projectId) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		Project tempProject = projectService.queryProjectByProjectId(projectId);
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}		
		return assessmentService.queryAssessentTotal(projectId);
	}
	
	@ApiOperation(value = "导出分析结果用词匹配情况 ", notes = "导出数据")
	@RequestMapping(value = "/assessments/export3/{projectId:.+}", method = RequestMethod.GET)
	public void exportKeywordDataList(HttpServletResponse response,
			@PathVariable Integer projectId) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		Project tempProject = projectService.queryProjectByProjectId(projectId);
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		String fileName = "ass_keyword_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = assessmentService.exportAssessmentKeyword(projectId);
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

}
