package com.hnjing.ai.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.ai.model.entity.Communicate;

/**
 * @ClassName: Communicate
 * @Description: 沟通详情服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public interface CommunicateService {

    /**
	 * @Title: addCommunicate
	 * @Description:添加沟通详情
	 * @param communicate 实体
	 * @return Integer
	 */
	Communicate addCommunicate(Communicate communicate);
	
	/**
	 * @Title modifyCommunicate
	 * @Description:修改沟通详情
	 * @param communicate 实体
	 * @return Integer
	 */
	Integer modifyCommunicate(Communicate communicate);
	
	/**
	 * @Title: dropCommunicateByCid
	 * @Description:删除沟通详情
	 * @param cid 实体标识
	 * @return Integer
	 */
	Integer dropCommunicateByCid(Integer cid);
	
	Integer dropCommunicateByProjectId(Integer projectId);
	
	/**
	 * @Title: queryCommunicateByCid
	 * @Description:根据实体标识查询沟通详情
	 * @param cid 实体标识
	 * @return Communicate
	 */
	Communicate queryCommunicateByCid(Integer cid);
	 
	/**
	 * @Title: queryCommunicateForPage
	 * @Description: 根据沟通详情属性与分页信息分页查询沟通详情信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param communicate 实体
	 * @return List<Communicate>
	 */
	Map<String, Object> queryCommunicateForPage(Integer pagenum, Integer pagesize, String sort, Communicate communicate);
	 
	 /**
	 * @Title: queryCommunicateByProperty
	 * @Description:根据属性查询沟通详情
	 * @return List<Communicate>
	 */
	 List<Communicate> queryCommunicateByProperty(Map<String, Object> map);

	/** 
	* @Title: exportByProperty 
	* @Description: 导出数据
	* @param transBean2Map
	* @return  
	* HSSFWorkbook    返回类型 
	* @throws 
	*/
	HSSFWorkbook exportByProperty(Map<String, Object> communicate);	 
	 
}
