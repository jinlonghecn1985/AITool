package com.hnjing.ai.model.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: Assessment
 * @Description: 沟通分析实体类
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public class Assessment implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer cid;	//tb_assessment:cid  沟通标识 
	
	private Integer rid;

	private Integer flag;	//tb_assessment:flag  沟通顺序 
	
	private Integer projectId;	//tb_communicate:project_id  归属项目 

	private String phone;
	
	@Length(min=0, max=512, message="{org.hibernate.validator.constraints.Length.message}")
	private String question;	//tb_assessment:question  问题  

	@Length(min=0, max=256, message="{org.hibernate.validator.constraints.Length.message}")
	private String answer;	//tb_assessment:answer  回答  

	@Length(min=0, max=32, message="{org.hibernate.validator.constraints.Length.message}")
	private String keyword;	//tb_assessment:keyword  关键词  

	private Integer assessment;	//tb_assessment:assessment  评估 0中立  1否定 2 肯定


	/**
	* @DatabasetableColumnName: tb_assessment:cid
	* @Description: 获取属性        沟通标识
	* @return: Integer
	*/
	public Integer getCid(){
		return cid;	
	}
	
	/**
	* @DatabasetableColumnName: tb_assessment:cid
	* @Description: 设置属性        沟通标识
	* @return: Integer
	*/
	public void setCid(Integer cid){
		this.cid = cid;	
	}	
	/**
	* @DatabasetableColumnName: tb_assessment:flag
	* @Description: 获取属性        沟通顺序
	* @return: Integer
	*/
	public Integer getFlag(){
		return flag;	
	}
	
	/**
	* @DatabasetableColumnName: tb_assessment:flag
	* @Description: 设置属性        沟通顺序
	* @return: Integer
	*/
	public void setFlag(Integer flag){
		this.flag = flag;	
	}	
	/**
	* @DatabasetableColumnName: tb_assessment:question
	* @Description: 获取属性        问题
	* @return: String
	*/
	public String getQuestion(){
		return question;	
	}
	
	/**
	* @DatabasetableColumnName: tb_assessment:question
	* @Description: 设置属性        问题
	* @return: String
	*/
	public void setQuestion(String question){
		this.question = question;	
	}	
	/**
	* @DatabasetableColumnName: tb_assessment:answer
	* @Description: 获取属性        回答
	* @return: String
	*/
	public String getAnswer(){
		return answer;	
	}
	
	/**
	* @DatabasetableColumnName: tb_assessment:answer
	* @Description: 设置属性        回答
	* @return: String
	*/
	public void setAnswer(String answer){
		this.answer = answer;	
	}	
	/**
	* @DatabasetableColumnName: tb_assessment:keyword
	* @Description: 获取属性        关键词
	* @return: String
	*/
	public String getKeyword(){
		return keyword;	
	}
	
	/**
	* @DatabasetableColumnName: tb_assessment:keyword
	* @Description: 设置属性        关键词
	* @return: String
	*/
	public void setKeyword(String keyword){
		this.keyword = keyword;	
	}	
	/**
	* @DatabasetableColumnName: tb_assessment:assessment
	* @Description: 获取属性        评估 0否定 1肯定 2中立
	* @return: Integer
	*/
	public Integer getAssessment(){
		return assessment;	
	}
	
	/**
	* @DatabasetableColumnName: tb_assessment:assessment
	* @Description: 设置属性        评估 0否定 1肯定 2中立
	* @return: Integer
	*/
	public void setAssessment(Integer assessment){
		this.assessment = assessment;	
	}

	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the rid
	 */
	public Integer getRid() {
		return rid;
	}

	/**
	 * @param rid the rid to set
	 */
	public void setRid(Integer rid) {
		this.rid = rid;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
	
	
	
}

