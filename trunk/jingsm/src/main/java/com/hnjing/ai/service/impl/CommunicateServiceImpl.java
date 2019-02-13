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

import com.hnjing.ai.model.dao.CommunicateMapper;
import com.hnjing.ai.model.entity.Communicate;
import com.hnjing.ai.service.CommunicateService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: Communicate
 * @Description: 沟通详情服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Service("communicateService")
@Transactional(readOnly=true)
public class  CommunicateServiceImpl implements CommunicateService {	
	private static final Logger logger = LoggerFactory.getLogger(CommunicateServiceImpl.class);
	
	@Autowired
    private CommunicateMapper communicateMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addCommunicate
	 * @Description:添加沟通详情
	 * @param communicate 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Communicate addCommunicate(Communicate communicate){
		int ret = communicateMapper.addCommunicate(communicate);
		if(ret>0){
			return communicate;
		}
		return null;
	}
	
	/**
	 * @Title modifyCommunicate
	 * @Description:修改沟通详情
	 * @param communicate 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyCommunicate(Communicate communicate){
		return communicateMapper.modifyCommunicate(communicate);
	}
	
	/**
	 * @Title: dropCommunicateByCid
	 * @Description:删除沟通详情
	 * @param cid 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropCommunicateByCid(Integer cid){
		return communicateMapper.dropCommunicateByCid(cid);
	}
	
	/**
	 * @Title: queryCommunicateByCid
	 * @Description:根据实体标识查询沟通详情
	 * @param cid 实体标识
	 * @return Communicate
	 */
	@Override
	public Communicate queryCommunicateByCid(Integer cid){
		return communicateMapper.queryCommunicateByCid(cid);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryCommunicateForPage
	 * @Description: 根据沟通详情属性与分页信息分页查询沟通详情信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param communicate 实体
	 * @return List<Communicate>
	 */
	@Override
	public Map<String, Object> queryCommunicateForPage(Integer pagenum, Integer pagesize, String sort, Communicate communicate){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, Communicate.class);
		List<Communicate> entityList = communicateMapper.queryCommunicateForPage(pageBounds, communicate);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, Communicate.class);
		}
		
		PageList<Communicate> pagelist = (PageList<Communicate>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryCommunicateByProperty
	 * @Description:根据属性查询沟通详情
	 * @return List<Communicate>
	 */
	@Override
	public List<Communicate> queryCommunicateByProperty(Map<String, Object> map){
		return communicateMapper.queryCommunicateByProperty(map);
	}

	/*
	 * @Title: exportByProperty
	 * @Description: TODO
	 * @param @param communicate
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param communicate
	 * @return
	 * @see com.hnjing.ai.service.CommunicateService#exportByProperty(java.util.Map)
	 */ 
	@Override
	public HSSFWorkbook exportByProperty(Map<String, Object> communicate) {
		String[] title = {"分析代码", "主叫号码", "被叫号码", "姓名", "客户公司	", "拨打时间", "通话时长", "拨打次数", "客户类型", "创建时间", "修订时间", "内容"};
		String[][] data = null;
		List<Communicate> info = queryCommunicateByProperty(communicate);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][12];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = info.get(j).getUseCode();
				data[j][1] = info.get(j).getCallFrom();
				data[j][2] = info.get(j).getPhone();				
				data[j][3] = info.get(j).getName();
				data[j][4] = info.get(j).getCompany();		
				data[j][5] = info.get(j).getCallDate()!=null?DateUtil.DateToString(info.get(j).getCallDate()):"";
				data[j][6] = ""+info.get(j).getCallLength();
				data[j][7] = ""+info.get(j).getCallTimes();
				data[j][8] = info.get(j).getUserType();
				data[j][9] = DateUtil.DateToString(info.get(j).getGmtCreated());
				data[j][10] = DateUtil.DateToString(info.get(j).getGmtModify());
				data[j][11] = info.get(j).getContent();
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("沟通详情", title, data, null);
	}

	/*
	 * @Title: dropCommunicateByProjectId
	 * @Description: 
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.CommunicateService#dropCommunicateByProjectId(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer dropCommunicateByProjectId(Integer projectId) {
		return communicateMapper.dropCommunicateByProjectId(projectId);
	}


}
