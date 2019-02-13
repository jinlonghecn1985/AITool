package com.hnjing.ai.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ai.model.entity.Project;

/**
 * @ClassName: ProjectMapper
 * @Description: AI项目映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Mapper
public interface ProjectMapper {

	/**
	 * @Title: addProject
	 * @Description:添加AI项目
	 * @param project 实体
	 * @return Integer
	 */
	Integer addProject(Project project);
	
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
	 * @param pageBounds 分页信息
	 * @param project 实体
	 * @return List<Project>
	 */
	List<Project> queryProjectForPage(PageBounds pageBounds, @Param("project") Project project);
	 
	 /**
	  * @Title: queryProjectByProperty
	  * @Description:根据属性查询AI项目
	  * @return List<Project>
	  */
	 List<Project> queryProjectByProperty(@Param("project") Map<String, Object> map);
	 
	 
	 
}
