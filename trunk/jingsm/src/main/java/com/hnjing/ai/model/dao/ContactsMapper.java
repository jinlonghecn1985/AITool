package com.hnjing.ai.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ai.model.entity.Contacts;

/**
 * @ClassName: ContactsMapper
 * @Description: 项目联系人信息映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Mapper
public interface ContactsMapper {

	/**
	 * @Title: addContacts
	 * @Description:添加项目联系人信息
	 * @param contacts 实体
	 * @return Integer
	 */
	Integer addContacts(Contacts contacts);
	
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
	 * @param pageBounds 分页信息
	 * @param contacts 实体
	 * @return List<Contacts>
	 */
	List<Contacts> queryContactsForPage(PageBounds pageBounds, @Param("contacts") Contacts contacts);
	 
	 /**
	  * @Title: queryContactsByProperty
	  * @Description:根据属性查询项目联系人信息
	  * @return List<Contacts>
	  */
	 List<Contacts> queryContactsByProperty(@Param("contacts") Map<String, Object> map);

	/** 
	* @Title: dropContactsByProjectId 
	* @Description: 
	* @param projectId
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer dropContactsByProjectId(Integer projectId);
	 
	 
	 
}
