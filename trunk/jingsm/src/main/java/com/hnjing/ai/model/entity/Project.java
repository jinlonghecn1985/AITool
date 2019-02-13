package com.hnjing.ai.model.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName: Project
 * @Description: AI项目实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer projectId;	//tb_project:project_id  项目标识  

	@NotBlank(message = "{org.hibernate.validator.constraints.NotBlank.message}")
	@Length(min=1, max=64, message="{org.hibernate.validator.constraints.Length.message}")
	private String projectName;	//tb_project:project_name  项目名称  

	private Integer totalNum;	//tb_project:total_num  总量  

	private Integer connectedNum;	//tb_project:connected_num  接通数量  

	private Integer sureNum;	//tb_project:sure_num  肯定数量  

	private Integer negativeNum;	//tb_project:negative_num  否定数量  

	private Integer neutralNum;	//tb_project:neutral_num  中立数量  

	@Length(min=0, max=8, message="{org.hibernate.validator.constraints.Length.message}")
	private String sCode;	//tb_project:s_code  统计代码  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String note;	//tb_project:note  备注  

	private java.sql.Timestamp gmtCreated;	//tb_project:gmt_created  创建时间  

	private java.sql.Timestamp gmtModify;	//tb_project:gmt_modify  修订时间  


	/**
	* @DatabasetableColumnName: tb_project:project_id
	* @Description: 获取属性        项目标识
	* @return: Integer
	*/
	public Integer getProjectId(){
		return projectId;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:project_id
	* @Description: 设置属性        项目标识
	* @return: Integer
	*/
	public void setProjectId(Integer projectId){
		this.projectId = projectId;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:project_name
	* @Description: 获取属性        项目名称
	* @return: String
	*/
	public String getProjectName(){
		return projectName;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:project_name
	* @Description: 设置属性        项目名称
	* @return: String
	*/
	public void setProjectName(String projectName){
		this.projectName = projectName;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:total_num
	* @Description: 获取属性        总量
	* @return: Integer
	*/
	public Integer getTotalNum(){
		return totalNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:total_num
	* @Description: 设置属性        总量
	* @return: Integer
	*/
	public void setTotalNum(Integer totalNum){
		this.totalNum = totalNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:connected_num
	* @Description: 获取属性        接通数量
	* @return: Integer
	*/
	public Integer getConnectedNum(){
		return connectedNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:connected_num
	* @Description: 设置属性        接通数量
	* @return: Integer
	*/
	public void setConnectedNum(Integer connectedNum){
		this.connectedNum = connectedNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:sure_num
	* @Description: 获取属性        肯定数量
	* @return: Integer
	*/
	public Integer getSureNum(){
		return sureNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:sure_num
	* @Description: 设置属性        肯定数量
	* @return: Integer
	*/
	public void setSureNum(Integer sureNum){
		this.sureNum = sureNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:negative_num
	* @Description: 获取属性        否定数量
	* @return: Integer
	*/
	public Integer getNegativeNum(){
		return negativeNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:negative_num
	* @Description: 设置属性        否定数量
	* @return: Integer
	*/
	public void setNegativeNum(Integer negativeNum){
		this.negativeNum = negativeNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:neutral_num
	* @Description: 获取属性        中立数量
	* @return: Integer
	*/
	public Integer getNeutralNum(){
		return neutralNum;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:neutral_num
	* @Description: 设置属性        中立数量
	* @return: Integer
	*/
	public void setNeutralNum(Integer neutralNum){
		this.neutralNum = neutralNum;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:s_code
	* @Description: 获取属性        统计代码
	* @return: String
	*/
	public String getSCode(){
		return sCode;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:s_code
	* @Description: 设置属性        统计代码
	* @return: String
	*/
	public void setSCode(String sCode){
		this.sCode = sCode;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:note
	* @Description: 获取属性        备注
	* @return: String
	*/
	public String getNote(){
		return note;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:note
	* @Description: 设置属性        备注
	* @return: String
	*/
	public void setNote(String note){
		this.note = note;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:gmt_created
	* @Description: 获取属性        创建时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getGmtCreated(){
		return gmtCreated;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:gmt_created
	* @Description: 设置属性        创建时间
	* @return: java.sql.Timestamp
	*/
	public void setGmtCreated(java.sql.Timestamp gmtCreated){
		this.gmtCreated = gmtCreated;	
	}	
	/**
	* @DatabasetableColumnName: tb_project:gmt_modify
	* @Description: 获取属性        修订时间
	* @return: java.sql.Timestamp
	*/
	public java.sql.Timestamp getGmtModify(){
		return gmtModify;	
	}
	
	/**
	* @DatabasetableColumnName: tb_project:gmt_modify
	* @Description: 设置属性        修订时间
	* @return: java.sql.Timestamp
	*/
	public void setGmtModify(java.sql.Timestamp gmtModify){
		this.gmtModify = gmtModify;	
	}	
	
	
	
	
}

