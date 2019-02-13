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
	 
}
