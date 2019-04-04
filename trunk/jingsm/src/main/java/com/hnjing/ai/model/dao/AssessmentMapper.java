package com.hnjing.ai.model.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ai.model.entity.Assessment;

/**
 * @ClassName: AssessmentMapper
 * @Description: 沟通分析映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Mapper
public interface AssessmentMapper {

	/**
	 * @Title: addAssessment
	 * @Description:添加沟通分析
	 * @param assessment 实体
	 * @return Integer
	 */
	Integer addAssessment(Assessment assessment);
	
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
	* @Title: queryRegulationQuestionByPid 
	* @Description: 查询指定计划策略和问题的匹配情况 
	* @param projectId
	* @return  
	* List<HashMap>    返回类型 
	* @throws 
	*/
	List<HashMap<String, String>> queryRegulationQuestionByPid(Integer projectId);
	 
	/**
	 * @Title: queryAssessmentForPage
	 * @Description: 根据沟通分析属性与分页信息分页查询沟通分析信息
	 * @param pageBounds 分页信息
	 * @param assessment 实体
	 * @return List<Assessment>
	 */
	List<Assessment> queryAssessmentForPage(PageBounds pageBounds, @Param("assessment") Assessment assessment);
	 
	 /**
	  * @Title: queryAssessmentByProperty
	  * @Description:根据属性查询沟通分析
	  * @return List<Assessment>
	  */
	 List<Assessment> queryAssessmentByProperty(@Param("assessment") Map<String, Object> map);
	 
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
	* @Title: queryAssessentTotal 
	* @Description: 查询分析总体情况
	* @param projectId
	* @return  
	* List<Map<String,String>>    返回类型 
	* @throws 
	*/
	List<Map<String, String>> queryAssessentTotal(Integer projectId);
	 
}
