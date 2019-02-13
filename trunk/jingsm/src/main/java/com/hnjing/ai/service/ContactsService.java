package com.hnjing.ai.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.ai.model.entity.Contacts;

/**
 * @ClassName: Contacts
 * @Description: 项目联系人信息服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public interface ContactsService {

    /**
	 * @Title: addContacts
	 * @Description:添加项目联系人信息
	 * @param contacts 实体
	 * @return Integer
	 */
	Contacts addContacts(Contacts contacts);
	
	/**
	 * @Title modifyContacts
	 * @Description:修改项目联系人信息
	 * @param contacts 实体
	 * @return Integer
	 */
	Integer modifyContacts(Contacts contacts);
	
	/**
	 * @Title: dropContactsByContactsId
	 * @Description:删除项目联系人信息
	 * @param contactsId 实体标识
	 * @return Integer
	 */
	Integer dropContactsByContactsId(Integer contactsId);
	
	Integer dropContactsByProjectId(Integer projectId);
	
	/**
	 * @Title: queryContactsByContactsId
	 * @Description:根据实体标识查询项目联系人信息
	 * @param contactsId 实体标识
	 * @return Contacts
	 */
	Contacts queryContactsByContactsId(Integer contactsId);
	 
	/**
	 * @Title: queryContactsForPage
	 * @Description: 根据项目联系人信息属性与分页信息分页查询项目联系人信息信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param contacts 实体
	 * @return List<Contacts>
	 */
	Map<String, Object> queryContactsForPage(Integer pagenum, Integer pagesize, String sort, Contacts contacts);
	 
	 /**
	 * @Title: queryContactsByProperty
	 * @Description:根据属性查询项目联系人信息
	 * @return List<Contacts>
	 */
	 List<Contacts> queryContactsByProperty(Map<String, Object> map);	 
	 
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
