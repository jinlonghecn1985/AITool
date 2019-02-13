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

import com.hnjing.ai.model.dao.RegulationsMapper;
import com.hnjing.ai.model.entity.Regulations;
import com.hnjing.ai.service.RegulationsService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: Regulations
 * @Description: 项目流程话术服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Service("regulationsService")
@Transactional(readOnly=true)
public class  RegulationsServiceImpl implements RegulationsService {	
	private static final Logger logger = LoggerFactory.getLogger(RegulationsServiceImpl.class);
	
	@Autowired
    private RegulationsMapper regulationsMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addRegulations
	 * @Description:添加项目流程话术
	 * @param regulations 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Regulations addRegulations(Regulations regulations){
		int ret = regulationsMapper.addRegulations(regulations);
		if(ret>0){
			return regulations;
		}
		return null;
	}
	
	/**
	 * @Title modifyRegulations
	 * @Description:修改项目流程话术
	 * @param regulations 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyRegulations(Regulations regulations){
		return regulationsMapper.modifyRegulations(regulations);
	}
	
	/**
	 * @Title: dropRegulationsByRegulationsId
	 * @Description:删除项目流程话术
	 * @param regulationsId 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropRegulationsByRegulationsId(Integer regulationsId){
		return regulationsMapper.dropRegulationsByRegulationsId(regulationsId);
	}
	
	/**
	 * @Title: queryRegulationsByRegulationsId
	 * @Description:根据实体标识查询项目流程话术
	 * @param regulationsId 实体标识
	 * @return Regulations
	 */
	@Override
	public Regulations queryRegulationsByRegulationsId(Integer regulationsId){
		return regulationsMapper.queryRegulationsByRegulationsId(regulationsId);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryRegulationsForPage
	 * @Description: 根据项目流程话术属性与分页信息分页查询项目流程话术信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param regulations 实体
	 * @return List<Regulations>
	 */
	@Override
	public Map<String, Object> queryRegulationsForPage(Integer pagenum, Integer pagesize, String sort, Regulations regulations){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, Regulations.class);
		List<Regulations> entityList = regulationsMapper.queryRegulationsForPage(pageBounds, regulations);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, Regulations.class);
		}
		
		PageList<Regulations> pagelist = (PageList<Regulations>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryRegulationsByProperty
	 * @Description:根据属性查询项目流程话术
	 * @return List<Regulations>
	 */
	@Override
	public List<Regulations> queryRegulationsByProperty(Map<String, Object> map){
		return regulationsMapper.queryRegulationsByProperty(map);
	}
	
	@Override
	public HSSFWorkbook exportByProperty(Map<String, Object> map) {
		String[] title = {"项目名称 ", "话术内容", "话术名称", "画像代码", "肯定识别词", "否定识别词", "中立识别词", "识别顺序", "肯定识别词匹配量", "否定识别词匹配量", "中立识别词匹配量", "创建时间", "修订时间"};
		String[][] data = null;
		List<Regulations> info = queryRegulationsByProperty(map);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][12];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = "";
				data[j][1] = ""+info.get(j).getContent();
				data[j][2] = ""+info.get(j).getRegulations();				
				data[j][3] = "";
				data[j][4] = ""+info.get(j).getAsYes();		
				data[j][5] = ""+info.get(j).getAsNo();					
				data[j][6] = ""+info.get(j).getAsOther();
				data[j][7] = ""+info.get(j).getAsYesNum();
				data[j][8] = ""+info.get(j).getAsNoNum();
				data[j][9] = ""+info.get(j).getAsOtherNum();
				
				data[j][10] = info.get(j).getGmtCreated()!=null?DateUtil.DateToString(info.get(j).getGmtCreated()):"";
				data[j][11] = info.get(j).getGmtModify()!=null?DateUtil.DateToString(info.get(j).getGmtModify()):"";
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("话术策略详情", title, data, null);
	}

	/*
	 * @Title: dropRegulationsByProjectId
	 * @Description: 
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.RegulationsService#dropRegulationsByProjectId(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer dropRegulationsByProjectId(Integer projectId) {
		return regulationsMapper.dropRegulationsByProjectId(projectId);
	}



}
