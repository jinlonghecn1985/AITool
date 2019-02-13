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

import com.hnjing.ai.model.dao.ContactsMapper;
import com.hnjing.ai.model.entity.Contacts;
import com.hnjing.ai.service.ContactsService;
import com.hnjing.utils.Constant;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.file.office.ExcelWriteUtil;
import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.utils.paginator.domain.PageList;
import com.hnjing.utils.paginator.domain.PageService;

/**
 * @ClassName: Contacts
 * @Description: 项目联系人信息服务实现类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Service("contactsService")
@Transactional(readOnly=true)
public class  ContactsServiceImpl implements ContactsService {	
	private static final Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);
	
	@Autowired
    private ContactsMapper contactsMapper;   
    
	@Autowired
	private PageService pageService; // 分页器
	
	
	/**
	 * @Title: addContacts
	 * @Description:添加项目联系人信息
	 * @param contacts 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Contacts addContacts(Contacts contacts){
		int ret = contactsMapper.addContacts(contacts);
		if(ret>0){
			return contacts;
		}
		return null;
	}
	
	/**
	 * @Title modifyContacts
	 * @Description:修改项目联系人信息
	 * @param contacts 实体
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer modifyContacts(Contacts contacts){
		return contactsMapper.modifyContacts(contacts);
	}
	
	/**
	 * @Title: dropContactsByContactsId
	 * @Description:删除项目联系人信息
	 * @param contactsId 实体标识
	 * @return Integer
	 */
	@Override
	@Transactional(readOnly = false)
	public Integer dropContactsByContactsId(Integer contactsId){
		return contactsMapper.dropContactsByContactsId(contactsId);
	}
	
	/**
	 * @Title: queryContactsByContactsId
	 * @Description:根据实体标识查询项目联系人信息
	 * @param contactsId 实体标识
	 * @return Contacts
	 */
	@Override
	public Contacts queryContactsByContactsId(Integer contactsId){
		return contactsMapper.queryContactsByContactsId(contactsId);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 
	/**
	 * @Title: queryContactsForPage
	 * @Description: 根据项目联系人信息属性与分页信息分页查询项目联系人信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param contacts 实体
	 * @return List<Contacts>
	 */
	@Override
	public Map<String, Object> queryContactsForPage(Integer pagenum, Integer pagesize, String sort, Contacts contacts){
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		PageBounds pageBounds = pageService.getPageBounds(pagenum, pagesize, null, true, false);
		pageBounds.setOrdersByJson(sort, Contacts.class);
		List<Contacts> entityList = contactsMapper.queryContactsForPage(pageBounds, contacts);
		if(null!=sort && sort.length()>0){
			pageBounds.setOrdersByJson(sort, Contacts.class);
		}
		
		PageList<Contacts> pagelist = (PageList<Contacts>) entityList;
		returnMap.put(Constant.PAGELIST, entityList);
		returnMap.put(Constant.PAGINATOR, pagelist.getPaginator());
		
		return returnMap;
	}
	 
	/**
	 * @Title: queryContactsByProperty
	 * @Description:根据属性查询项目联系人信息
	 * @return List<Contacts>
	 */
	@Override
	public List<Contacts> queryContactsByProperty(Map<String, Object> map){
		return contactsMapper.queryContactsByProperty(map);
	}
	
	@Override
	public HSSFWorkbook exportByProperty(Map<String, Object> map) {
		String[] title = {"项目名称 ", "外呼状态", "联系人", "联系号码", "公司名称", "创建时间", "修订时间"};
		String[][] data = null;
		List<Contacts> info = queryContactsByProperty(map);
		if(info!=null && info.size()>0) {
			data = new String[info.size()][7];
			for(int j=0; j<info.size(); j++) {
				data[j][0] = "";
				data[j][1] = ""+info.get(j).getStatus();
				data[j][2] = ""+info.get(j).getContacts();				
				data[j][3] = ""+info.get(j).getPhone();
				data[j][4] = ""+info.get(j).getCompany();	
				data[j][5] = info.get(j).getGmtCreated()!=null?DateUtil.DateToString(info.get(j).getGmtCreated()):"";
				data[j][6] = info.get(j).getGmtModify()!=null?DateUtil.DateToString(info.get(j).getGmtModify()):"";
			}
		}
		return ExcelWriteUtil.getHSSFWorkbook("外呼计划详情", title, data, null);
	}

	/*
	 * @Title: dropContactsByProjectId
	 * @Description: TODO
	 * @param @param projectId
	 * @param @return    参数  
	 * @author Jinlong He
	 * @param projectId
	 * @return
	 * @see com.hnjing.ai.service.ContactsService#dropContactsByProjectId(java.lang.Integer)
	 */ 
	@Override
	@Transactional(readOnly = false)
	public Integer dropContactsByProjectId(Integer projectId) {
		return contactsMapper.dropContactsByProjectId(projectId);
	}



}
