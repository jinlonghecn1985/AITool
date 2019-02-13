/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: RgeulationsBo.java
 * @Prject: AITool
 * @Package: com.hnjing.ai.bo
 * @Description: 
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年2月1日 上午11:41:55
 * @version: V1.0  
 */
package com.hnjing.ai.bo;

import java.io.Serializable;

import com.hnjing.ai.model.entity.Regulations;

/**
 * @ClassName: RgeulationsBo
 * @Description: 
 * @author: Jinlong He
 * @date: 2019年2月1日 上午11:41:55
 */
public class RgeulationsBo implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	
	private Regulations regulations;
	
	private String[] keyWordsYes;
	
	private String[] keyWordsNo;
	
	private String[] keyWordsOther;
	
	public RgeulationsBo() {
		super();
	}
		
	/**
	 * @return the regulations
	 */
	public Regulations getRegulations() {
		return regulations;
	}

	/**
	 * @param regulations the regulations to set
	 */
	public void setRegulations(Regulations regulations) {
		this.regulations = regulations;
		if(this.regulations!=null && this.regulations.getAsYes()!=null) {
			setKeyWordsYes(this.regulations.getAsYes().trim().replaceAll("。", ",").replaceAll("、", ",").replaceAll("，", ",").split(","));
		}
		if(this.regulations!=null && this.regulations.getAsNo()!=null) {
			setKeyWordsNo(this.regulations.getAsNo().trim().replaceAll("。", ",").replaceAll("、", ",").replaceAll("，", ",").split(","));			
		}
		if(this.regulations!=null && this.regulations.getAsOther()!=null) {
			setKeyWordsOther(this.regulations.getAsOther().trim().replaceAll("。", ",").replaceAll("、", ",").replaceAll("，", ",").split(","));			
		}
	}

	/**
	 * @return the keyWordsYes
	 */
	public String[] getKeyWordsYes() {
		return keyWordsYes;
	}

	/**
	 * @param keyWordsYes the keyWordsYes to set
	 */
	public void setKeyWordsYes(String[] keyWordsYes) {
		this.keyWordsYes = keyWordsYes;
	}

	/**
	 * @return the keyWordsNo
	 */
	public String[] getKeyWordsNo() {
		return keyWordsNo;
	}

	/**
	 * @param keyWordsNo the keyWordsNo to set
	 */
	public void setKeyWordsNo(String[] keyWordsNo) {
		this.keyWordsNo = keyWordsNo;
	}

	/**
	 * @return the keyWordsOther
	 */
	public String[] getKeyWordsOther() {
		return keyWordsOther;
	}

	/**
	 * @param keyWordsOther the keyWordsOther to set
	 */
	public void setKeyWordsOther(String[] keyWordsOther) {
		this.keyWordsOther = keyWordsOther;
	}

	
	
	
	
	

}
