package com.hnjing.ai.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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

import com.hnjing.ai.model.entity.Project;
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
 * @ClassName: ProjectController
 * @Description: AI项目HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@RestController
@Api(description="AI项目")
public class ProjectController{

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private ProjectService projectService;

	
	@ApiOperation(value = "新增 添加AI项目信息", notes = "添加AI项目信息")
	@RequestMapping(value = "/project", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addProject(HttpServletResponse response,
			@ApiParam(value = "project") @RequestBody Project project) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		project.setProjectId(null);
		
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("projectNameOnly", project.getProjectName());
		List<Project> list = projectService.queryProjectByProperty(query);
		if(list!=null && list.size()>0) {
			throw new ParameterException("projectName", "项目名称有重,请重新填空！");
		}		
		projectService.addProject(project);
		return project;
	}
	
	
	@ApiOperation(value = "更新 根据AI项目标识更新AI项目信息", notes = "根据AI项目标识更新AI项目信息")
	@RequestMapping(value = "/project/{projectId:.+}", method = RequestMethod.PUT)
	public Object modifyProjectById(HttpServletResponse response,
			@PathVariable Integer projectId,
			@ApiParam(value = "project", required = true) @RequestBody Project project
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Project tempProject = projectService.queryProjectByProjectId(projectId);
		project.setProjectId(projectId);
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		if(!tempProject.getProjectName().equals(project.getProjectName())) {
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("projectNameOnly", project.getProjectName());
			query.put("projectIdNot", project.getProjectId());
			List<Project> list = projectService.queryProjectByProperty(query);
			if(list!=null && list.size()>0) {
				throw new ParameterException("projectName", "项目名称有重,请重新填空！");
			}
		}
		return projectService.modifyProject(project);
	}

	@ApiOperation(value = "删除 根据AI项目标识删除AI项目信息", notes = "根据AI项目标识删除AI项目信息")
	@RequestMapping(value = "/project/{projectId:.+}", method = RequestMethod.DELETE)
	public Object dropProjectByProjectId(HttpServletResponse response, @PathVariable Integer projectId) {
		Project project = projectService.queryProjectByProjectId(projectId);
		if(null == project){
			throw new NotFoundException("AI项目");
		}
		return projectService.dropProjectByProjectId(projectId);
	}
	
	@ApiOperation(value = "查询 根据AI项目标识查询AI项目信息", notes = "根据AI项目标识查询AI项目信息")
	@RequestMapping(value = "/project/{projectId:.+}", method = RequestMethod.GET)
	public Object queryProjectById(HttpServletResponse response,
			@PathVariable Integer projectId) {
		Project project = projectService.queryProjectByProjectId(projectId);
		if(null == project){
			throw new NotFoundException("AI项目");
		}
		return project;
	}
	
	@ApiOperation(value = "查询 根据AI项目属性查询AI项目信息列表", notes = "根据AI项目属性查询AI项目信息列表")
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public Object queryProjectList(HttpServletResponse response,
			Project project) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return projectService.queryProjectByProperty(ClassUtil.transBean2Map(project, false));
	}
	
	@ApiOperation(value = "查询分页 根据AI项目属性分页查询AI项目信息列表", notes = "根据AI项目属性分页查询AI项目信息列表")
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public Object queryProjectPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, Project project) {				
		return projectService.queryProjectForPage(pagenum, pagesize, sort, project);
	}
	
	@ApiOperation(value = "导出数据", notes = "导出数据")
	@RequestMapping(value = "/projects/export", method = RequestMethod.GET)
	public void exportDataList(HttpServletResponse response,
			Project project) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		String fileName = "project_item_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = projectService.exportByProperty(ClassUtil.transBean2Map(project, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

}
