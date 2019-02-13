package com.hnjing.ai.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.ai.model.entity.Project;

/**
 * @ClassName: Project
 * @Description: AI项目服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public interface ProjectService {

    /**
	 * @Title: addProject
	 * @Description:添加AI项目
	 * @param project 实体
	 * @return Integer
	 */
	Project addProject(Project project);
	
	/**
	 * @Title modifyProject
	 * @Description:修改AI项目
	 * @param project 实体
	 * @return Integer
	 */
	Integer modifyProject(Project project);
	
	/**
	 * @Title: dropProjectByProjectId
	 * @Description:删除AI项目
	 * @param projectId 实体标识
	 * @return Integer
	 */
	Integer dropProjectByProjectId(Integer projectId);
	
	/**
	 * @Title: queryProjectByProjectId
	 * @Description:根据实体标识查询AI项目
	 * @param projectId 实体标识
	 * @return Project
	 */
	Project queryProjectByProjectId(Integer projectId);
	 
	/**
	 * @Title: queryProjectForPage
	 * @Description: 根据AI项目属性与分页信息分页查询AI项目信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param project 实体
	 * @return List<Project>
	 */
	Map<String, Object> queryProjectForPage(Integer pagenum, Integer pagesize, String sort, Project project);
	 
	 /**
	 * @Title: queryProjectByProperty
	 * @Description:根据属性查询AI项目
	 * @return List<Project>
	 */
	 List<Project> queryProjectByProperty(Map<String, Object> map);

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
