package com.hnjing.ai.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: Communicate
 * @Description: 沟通详情实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public class Communicate implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer cid;	//tb_communicate:cid  自增标识  

	private Integer projectId;	//tb_communicate:project_id  归属项目  

	@Length(min=0, max=16, message="{org.hibernate.validator.constraints.Length.message}")
	private String useCode;	//tb_communicate:use_code  画像代码  

	@Length(min=0, max=16, message="{org.hibernate.validator.constraints.Length.message}")
	private String callFrom;	//tb_communicate:call_from  主叫号码  

	@Length(min=0, max=16, message="{org.hibernate.validator.constraints.Length.message}")
	@Pattern(regexp="((\\d{3,4}-)[0-9]{7,8}(-[0-9]{0,4})?)", message="{javax.validation.constraints.Pattern.message}")
	private String phone;	//tb_communicate:phone  联系电话  

	@Length(min=0, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String name;	//tb_communicate:name  联系人  

	@Length(min=0, max=128, message="{org.hibernate.validator.constraints.Length.message}")
	private String company;	//tb_communicate:company  公司名称  

	private Date callDate;	//tb_communicate:call_date  呼叫时间  

	private Integer callLength;	//tb_communicate:call_length  拨打时长  

	private Integer callTimes;	//tb_communicate:call_times  呼叫次数  

	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String userType;	//tb_communicate:user_type  用户类型  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String note;	//tb_communicate:note    

	private String content;	//tb_communicate:content  沟通内容  

	@Length(min=0, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	private Date gmtCreated;	//tb_communicate:gmt_created  创建时间  

	@Length(min=0, max=255, message="{org.hibernate.validator.constraints.Length.message}")
	private Date gmtModify;	//tb_communicate:gmt_modify  修订时间  


	/**
	* @DatabasetableColumnName: tb_communicate:cid
	* @Description: 获取属性        自增标识
	* @return: Integer
	*/
	public Integer getCid(){
		return cid;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:cid
	* @Description: 设置属性        自增标识
	* @return: Integer
	*/
	public void setCid(Integer cid){
		this.cid = cid;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:project_id
	* @Description: 获取属性        归属项目
	* @return: Integer
	*/
	public Integer getProjectId(){
		return projectId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:project_id
	* @Description: 设置属性        归属项目
	* @return: Integer
	*/
	public void setProjectId(Integer projectId){
		this.projectId = projectId;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:use_code
	* @Description: 获取属性        画像代码
	* @return: String
	*/
	public String getUseCode(){
		return useCode;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:use_code
	* @Description: 设置属性        画像代码
	* @return: String
	*/
	public void setUseCode(String useCode){
		this.useCode = useCode;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:call_from
	* @Description: 获取属性        主叫号码
	* @return: String
	*/
	public String getCallFrom(){
		return callFrom;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:call_from
	* @Description: 设置属性        主叫号码
	* @return: String
	*/
	public void setCallFrom(String callFrom){
		this.callFrom = callFrom;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:phone
	* @Description: 获取属性        联系电话
	* @return: String
	*/
	public String getPhone(){
		return phone;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:phone
	* @Description: 设置属性        联系电话
	* @return: String
	*/
	public void setPhone(String phone){
		this.phone = phone;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:name
	* @Description: 获取属性        联系人
	* @return: String
	*/
	public String getName(){
		return name;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:name
	* @Description: 设置属性        联系人
	* @return: String
	*/
	public void setName(String name){
		this.name = name;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:company
	* @Description: 获取属性        公司名称
	* @return: String
	*/
	public String getCompany(){
		return company;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:company
	* @Description: 设置属性        公司名称
	* @return: String
	*/
	public void setCompany(String company){
		this.company = company;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:call_date
	* @Description: 获取属性        呼叫时间
	* @return: Date
	*/
	public Date getCallDate(){
		return callDate;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:call_date
	* @Description: 设置属性        呼叫时间
	* @return: Date
	*/
	public void setCallDate(Date callDate){
		this.callDate = callDate;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:call_length
	* @Description: 获取属性        拨打时长
	* @return: Integer
	*/
	public Integer getCallLength(){
		return callLength;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:call_length
	* @Description: 设置属性        拨打时长
	* @return: Integer
	*/
	public void setCallLength(Integer callLength){
		this.callLength = callLength;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:call_times
	* @Description: 获取属性        呼叫次数
	* @return: Date
	*/
	public Integer getCallTimes(){
		return callTimes;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:call_times
	* @Description: 设置属性        呼叫次数
	* @return: Date
	*/
	public void setCallTimes(Integer callTimes){
		this.callTimes = callTimes;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:user_type
	* @Description: 获取属性        用户类型
	* @return: String
	*/
	public String getUserType(){
		return userType;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:user_type
	* @Description: 设置属性        用户类型
	* @return: String
	*/
	public void setUserType(String userType){
		this.userType = userType;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:note
	* @Description: 获取属性        
	* @return: String
	*/
	public String getNote(){
		return note;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:note
	* @Description: 设置属性        
	* @return: String
	*/
	public void setNote(String note){
		this.note = note;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:content
	* @Description: 获取属性        沟通内容
	* @return: java.lang.Object
	*/
	public String getContent(){
		return content;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:content
	* @Description: 设置属性        沟通内容
	* @return: java.lang.Object
	*/
	public void setContent(String content){
		this.content = content;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:gmt_created
	* @Description: 获取属性        创建时间
	* @return: String
	*/
	public Date getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:gmt_created
	* @Description: 设置属性        创建时间
	* @return: String
	*/
	public void setGmtCreated(Date gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_communicate:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: String
	*/
	public Date getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_communicate:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: String
	*/
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;	
	}	
	
	
	
	
}

