package com.hnjing.ai.model.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: Regulations
 * @Description: 项目流程话术实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public class Regulations implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer regulationsId;	//tb_regulations:regulations_id  话术标识  

	private Integer projectId;	//tb_regulations:project_id  项目标识  

	@Length(min=0, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String regulations;	//tb_regulations:regulations  话术  

	@Length(min=0, max=512, message="{org.hibernate.validator.constraints.Length.message}")
	private String content;	//tb_regulations:content  内容 
	
	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String uscode;
	
	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String usorder;

	private Integer asNum;	//tb_regulations:as_num  回复总量  

	@Length(min=0, max=512, message="{org.hibernate.validator.constraints.Length.message}")
	private String asYes;	//tb_regulations:as_yes  肯定用词  

	private Integer asYesNum;	//tb_regulations:as_yes_num  肯定回复总量  

	@Length(min=0, max=512, message="{org.hibernate.validator.constraints.Length.message}")
	private String asNo;	//tb_regulations:as_no  否定用词  

	private Integer asNoNum;	//tb_regulations:as_no_num  否定回复总量  

	@Length(min=0, max=512, message="{org.hibernate.validator.constraints.Length.message}")
	private String asOther;	//tb_regulations:as_other  其它用词  

	private Integer asOtherNum;	//tb_regulations:as_other_num  其它用词总量  

	private java.sql.Timestamp gmtCreated;	//tb_regulations:gmt_created  创建时间  

	private java.sql.Timestamp gmtModify;	//tb_regulations:gmt_modify  修订时间  


	/**
	* @DatabasetableColumnName: tb_regulations:regulations_id
	* @Description: 获取属性        话术标识
	* @return: Integer
	*/
	public Integer getRegulationsId(){
		return regulationsId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:regulations_id
	* @Description: 设置属性        话术标识
	* @return: Integer
	*/
	public void setRegulationsId(Integer regulationsId){
		this.regulationsId = regulationsId;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:project_id
	* @Description: 获取属性        项目标识
	* @return: Integer
	*/
	public Integer getProjectId(){
		return projectId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:project_id
	* @Description: 设置属性        项目标识
	* @return: Integer
	*/
	public void setProjectId(Integer projectId){
		this.projectId = projectId;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:regulations
	* @Description: 获取属性        话术
	* @return: String
	*/
	public String getRegulations(){
		return regulations;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:regulations
	* @Description: 设置属性        话术
	* @return: String
	*/
	public void setRegulations(String regulations){
		this.regulations = regulations;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:content
	* @Description: 获取属性        内容
	* @return: String
	*/
	public String getContent(){
		return content;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:content
	* @Description: 设置属性        内容
	* @return: String
	*/
	public void setContent(String content){
		this.content = content;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:as_num
	* @Description: 获取属性        回复总量
	* @return: Integer
	*/
	public Integer getAsNum(){
		return asNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:as_num
	* @Description: 设置属性        回复总量
	* @return: Integer
	*/
	public void setAsNum(Integer asNum){
		this.asNum = asNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:as_yes
	* @Description: 获取属性        肯定用词
	* @return: String
	*/
	public String getAsYes(){
		return asYes;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:as_yes
	* @Description: 设置属性        肯定用词
	* @return: String
	*/
	public void setAsYes(String asYes){
		this.asYes = asYes;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:as_yes_num
	* @Description: 获取属性        肯定回复总量
	* @return: Integer
	*/
	public Integer getAsYesNum(){
		return asYesNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:as_yes_num
	* @Description: 设置属性        肯定回复总量
	* @return: Integer
	*/
	public void setAsYesNum(Integer asYesNum){
		this.asYesNum = asYesNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:as_no
	* @Description: 获取属性        否定用词
	* @return: String
	*/
	public String getAsNo(){
		return asNo;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:as_no
	* @Description: 设置属性        否定用词
	* @return: String
	*/
	public void setAsNo(String asNo){
		this.asNo = asNo;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:as_no_num
	* @Description: 获取属性        否定回复总量
	* @return: Integer
	*/
	public Integer getAsNoNum(){
		return asNoNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:as_no_num
	* @Description: 设置属性        否定回复总量
	* @return: Integer
	*/
	public void setAsNoNum(Integer asNoNum){
		this.asNoNum = asNoNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:as_other
	* @Description: 获取属性        其它用词
	* @return: String
	*/
	public String getAsOther(){
		return asOther;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:as_other
	* @Description: 设置属性        其它用词
	* @return: String
	*/
	public void setAsOther(String asOther){
		this.asOther = asOther;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:as_other_num
	* @Description: 获取属性        其它用词总量
	* @return: Integer
	*/
	public Integer getAsOtherNum(){
		return asOtherNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:as_other_num
	* @Description: 设置属性        其它用词总量
	* @return: Integer
	*/
	public void setAsOtherNum(Integer asOtherNum){
		this.asOtherNum = asOtherNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:gmt_created
	* @Description: 获取属性        创建时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:gmt_created
	* @Description: 设置属性        创建时间
	* @return: java.sql.Timestamp
	*/
	public void setGmtCreated(java.sql.Timestamp gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_regulations:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_regulations:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: java.sql.Timestamp
	*/
	public void setGmtModify(java.sql.Timestamp gmtModify){
		this.gmtModify = gmtModify;	
	}

	/**
	 * @return the uscode
	 */
	public String getUscode() {
		return uscode;
	}

	/**
	 * @param uscode the uscode to set
	 */
	public void setUscode(String uscode) {
		this.uscode = uscode;
	}

	/**
	 * @return the usorder
	 */
	public String getUsorder() {
		return usorder;
	}

	/**
	 * @param usorder the usorder to set
	 */
	public void setUsorder(String usorder) {
		this.usorder = usorder;
	}	
	
	
	
	
}

