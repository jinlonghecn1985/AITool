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

import com.hnjing.ai.model.dao.ProjectMapper;
import com.hnjing.ai.model.entity.Project;
import com.hnjing.ai.service.ProjectService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: Project
 * @Description: AI项目服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Service("projectService")
@Transactional(readOnly=true)
public class  ProjectServiceImpl implements ProjectService {	
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
    private ProjectMapper projectMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addProject
	 * @Description:添加AI项目
	 * @param project 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Project addProject(Project project){
		int ret = projectMapper.addProject(project);
		if(ret>0){
			return project;
		}
		return null;
	}
	
	/**
	 * @Title modifyProject
	 * @Description:修改AI项目
	 * @param project 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyProject(Project project){
		return projectMapper.modifyProject(project);
	}
	
	/**
	 * @Title: dropProjectByProjectId
	 * @Description:删除AI项目
	 * @param projectId 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropProjectByProjectId(Integer projectId){
		return projectMapper.dropProjectByProjectId(projectId);
	}
	
	/**
	 * @Title: queryProjectByProjectId
	 * @Description:根据实体标识查询AI项目
	 * @param projectId 实体标识
	 * @return Project
	 */
	@Override
	public Project queryProjectByProjectId(Integer projectId){
		return projectMapper.queryProjectByProjectId(projectId);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryProjectForPage
	 * @Description: 根据AI项目属性与分页信息分页查询AI项目信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param project 实体
	 * @return List<Project>
	 */
	@Override
	public Map<String, Object> queryProjectForPage(Integer pagenum, Integer pagesize, String sort, Project project){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, Project.class);
		List<Project> entityList = projectMapper.queryProjectForPage(pageBounds, project);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, Project.class);
		}
		
		PageList<Project> pagelist = (PageList<Project>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryProjectByProperty
	 * @Description:根据属性查询AI项目
	 * @return List<Project>
	 */
	@Override
	public List<Project> queryProjectByProperty(Map<String, Object> map){
		return projectMapper.queryProjectByProperty(map);
	}

	/*
	 * @Title: exportByProperty
	 * @Description: 
	 * @param @param map
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param map
	 * @return
	 * @see com.hnjing.ai.service.ProjectService#exportByProperty(java.util.Map)
	 */ 
	@Override
	public HSSFWorkbook exportByProperty(Map<String, Object> map) {
		String[] title = {"项目名称 ", "外呼总量", "接通总量", "肯定交互", "否定交互", "中立交互", "统计代码", "创建时间", "修订时间"};
		String[][] data = null;
		List<Project> info = queryProjectByProperty(map);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][9];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).getProjectName();
				data[j][1] = ""+info.get(j).getTotalNum();
				data[j][2] = ""+info.get(j).getConnectedNum();				
				data[j][3] = ""+info.get(j).getSureNum();
				data[j][4] = ""+info.get(j).getNegativeNum();		
				data[j][5] = ""+info.get(j).getNeutralNum();					
				data[j][6] = ""+info.get(j).getSCode();
				data[j][7] = info.get(j).getGmtCreated()!=null?DateUtil.DateToString(info.get(j).getGmtCreated()):"";
				data[j][8] = info.get(j).getGmtModify()!=null?DateUtil.DateToString(info.get(j).getGmtModify()):"";
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("外呼计划详情", title, data, null);
	}


}
