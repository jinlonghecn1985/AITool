package com.hnjing.ai.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.ai.model.entity.Assessment;

/**
 * @ClassName: Assessment
 * @Description: 沟通分析服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public interface AssessmentService {

    /**
	 * @Title: addAssessment
	 * @Description:添加沟通分析
	 * @param assessment 实体
	 * @return Integer
	 */
	Assessment addAssessment(Assessment assessment);
	
	/**
	 * @Title modifyAssessment
	 * @Description:修改沟通分析
	 * @param assessment 实体
	 * @return Integer
	 */
	Integer modifyAssessment(Assessment assessment);
	
	/**
	 * @Title: dropAssessmentByCid
	 * @Description:删除沟通分析
	 * @param cid 实体标识
	 * @return Integer
	 */
	Integer dropAssessmentByCid(Integer cid);
	
	/** 
	* @Title: dropAssessmentByProjectId 
	* @Description: 删除沟通分析
	* @param projectId 项目标识
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer dropAssessmentByProjectId(Integer projectId);
	
	/**
	 * @Title: queryAssessmentByCid
	 * @Description:根据实体标识查询沟通分析
	 * @param cid 实体标识
	 * @return Assessment
	 */
	Assessment queryAssessmentByCid(Integer cid);
	 
	/**
	 * @Title: queryAssessmentForPage
	 * @Description: 根据沟通分析属性与分页信息分页查询沟通分析信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param assessment 实体
	 * @return List<Assessment>
	 */
	Map<String, Object> queryAssessmentForPage(Integer pagenum, Integer pagesize, String sort, Assessment assessment);
	 
	 /**
	 * @Title: queryAssessmentByProperty
	 * @Description:根据属性查询沟通分析
	 * @return List<Assessment>
	 */
	 List<Assessment> queryAssessmentByProperty(Map<String, Object> map);

	/** 
	* @Title: exportByProperty 
	* @Description: 导出数据
	* @param transBean2Map
	* @return  
	* HSSFWorkbook    返回类型 
	* @throws 
	*/
	HSSFWorkbook exportByProperty(Map<String, Object> map);	 
	
	/** 
	* @Title: exportRegulationQuestionByPid 
	* @Description: 导出指定计划策略和问题的匹配情况
	* @param projectId
	* @return  
	* HSSFWorkbook    返回类型 
	* @throws 
	*/
	HSSFWorkbook exportRegulationQuestionByPid(Integer projectId);	
	
	
	/** 
	* @Title: queryAssessmentKeyword 
	* @Description: 查询分析结果中命中词汇情况
	* @param projectId
	* @return  
	* List<Map<String,String>>    返回类型 
	* @throws 
	*/
	List<Map<String, String>> queryAssessmentKeyword(Integer projectId);

	/** 
	* @Title: exportAssessmentKeyword 
	* @Description: 导出分析结果用词匹配情况
	* @param projectId
	* @return  
	* HSSFWorkbook    返回类型 
	* @throws 
	*/
	HSSFWorkbook exportAssessmentKeyword(Integer projectId);
	
	/** 
	* @Title: queryAssessentTotal 
	* @Description: 查询分析总体情况
	* @param projectId
	* @return  
	* List<Map<String,String>>    返回类型 
	* @throws 
	*/
	List<Map<String, String>> queryAssessentTotal(Integer projectId);
	 
}
