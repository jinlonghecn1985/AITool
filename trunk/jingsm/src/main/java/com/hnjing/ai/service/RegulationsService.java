package com.hnjing.ai.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hnjing.ai.model.entity.Regulations;

/**
 * @ClassName: Regulations
 * @Description: 项目流程话术服务接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
public interface RegulationsService {

    /**
	 * @Title: addRegulations
	 * @Description:添加项目流程话术
	 * @param regulations 实体
	 * @return Integer
	 */
	Regulations addRegulations(Regulations regulations);
	
	/**
	 * @Title modifyRegulations
	 * @Description:修改项目流程话术
	 * @param regulations 实体
	 * @return Integer
	 */
	Integer modifyRegulations(Regulations regulations);
	
	/**
	 * @Title: dropRegulationsByRegulationsId
	 * @Description:删除项目流程话术
	 * @param regulationsId 实体标识
	 * @return Integer
	 */
	Integer dropRegulationsByRegulationsId(Integer regulationsId);
	
	Integer dropRegulationsByProjectId(Integer projectId);
	
	/**
	 * @Title: queryRegulationsByRegulationsId
	 * @Description:根据实体标识查询项目流程话术
	 * @param regulationsId 实体标识
	 * @return Regulations
	 */
	Regulations queryRegulationsByRegulationsId(Integer regulationsId);
	 
	/**
	 * @Title: queryRegulationsForPage
	 * @Description: 根据项目流程话术属性与分页信息分页查询项目流程话术信息
	 * @param pagenum 页 
	 * @param pagesize 页大小 
	 * @param sort 排序
	 * @param regulations 实体
	 * @return List<Regulations>
	 */
	Map<String, Object> queryRegulationsForPage(Integer pagenum, Integer pagesize, String sort, Regulations regulations);
	 
	 /**
	 * @Title: queryRegulationsByProperty
	 * @Description:根据属性查询项目流程话术
	 * @return List<Regulations>
	 */
	 List<Regulations> queryRegulationsByProperty(Map<String, Object> map);	 
	 
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
