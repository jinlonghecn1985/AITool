package com.hnjing.ai.model.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: Contacts
 * @Description: 项目联系人信息实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public class Contacts implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer contactsId;	//tb_contacts:contacts_id  联系人标识  

	private Integer projectId;	//tb_contacts:project_id  项目标识  

	private Integer status;	//tb_contacts:status  状态 1已呼  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String contacts;	//tb_contacts:contacts  联系人  

	@Length(min=0, max=16, message="{org.hibernate.validator.constraints.Length.message}")
	@Pattern(regexp="((\\d{3,4}-)[0-9]{7,8}(-[0-9]{0,4})?)", message="{javax.validation.constraints.Pattern.message}")
	private String phone;	//tb_contacts:phone  联系电话  

	@Length(min=0, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String company;	//tb_contacts:company  公司名称  

	private java.sql.Timestamp gmtCreated;	//tb_contacts:gmt_created  创建时间  

	private java.sql.Timestamp gmtModify;	//tb_contacts:gmt_modify  修订时间  


	/**
	* @DatabasetableColumnName: tb_contacts:contacts_id
	* @Description: 获取属性        联系人标识
	* @return: Integer
	*/
	public Integer getContactsId(){
		return contactsId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:contacts_id
	* @Description: 设置属性        联系人标识
	* @return: Integer
	*/
	public void setContactsId(Integer contactsId){
		this.contactsId = contactsId;	
	}	
	/**
	* @DatabasetableColumnName: tb_contacts:project_id
	* @Description: 获取属性        项目标识
	* @return: Integer
	*/
	public Integer getProjectId(){
		return projectId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:project_id
	* @Description: 设置属性        项目标识
	* @return: Integer
	*/
	public void setProjectId(Integer projectId){
		this.projectId = projectId;	
	}	
	/**
	* @DatabasetableColumnName: tb_contacts:status
	* @Description: 获取属性        状态 1已呼
	* @return: Integer
	*/
	public Integer getStatus(){
		return status;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:status
	* @Description: 设置属性        状态 1已呼
	* @return: Integer
	*/
	public void setStatus(Integer status){
		this.status = status;	
	}	
	/**
	* @DatabasetableColumnName: tb_contacts:contacts
	* @Description: 获取属性        联系人
	* @return: String
	*/
	public String getContacts(){
		return contacts;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:contacts
	* @Description: 设置属性        联系人
	* @return: String
	*/
	public void setContacts(String contacts){
		this.contacts = contacts;	
	}	
	/**
	* @DatabasetableColumnName: tb_contacts:phone
	* @Description: 获取属性        联系电话
	* @return: String
	*/
	public String getPhone(){
		return phone;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:phone
	* @Description: 设置属性        联系电话
	* @return: String
	*/
	public void setPhone(String phone){
		this.phone = phone;	
	}	
	/**
	* @DatabasetableColumnName: tb_contacts:company
	* @Description: 获取属性        公司名称
	* @return: String
	*/
	public String getCompany(){
		return company;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:company
	* @Description: 设置属性        公司名称
	* @return: String
	*/
	public void setCompany(String company){
		this.company = company;	
	}	
	/**
	* @DatabasetableColumnName: tb_contacts:gmt_created
	* @Description: 获取属性        创建时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:gmt_created
	* @Description: 设置属性        创建时间
	* @return: java.sql.Timestamp
	*/
	public void setGmtCreated(java.sql.Timestamp gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_contacts:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_contacts:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: java.sql.Timestamp
	*/
	public void setGmtModify(java.sql.Timestamp gmtModify){
		this.gmtModify = gmtModify;	
	}	
	
	
	
	
}

