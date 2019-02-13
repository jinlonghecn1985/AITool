package com.hnjing.ai.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ai.model.entity.Regulations;

/**
 * @ClassName: RegulationsMapper
 * @Description: 项目流程话术映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Mapper
public interface RegulationsMapper {

	/**
	 * @Title: addRegulations
	 * @Description:添加项目流程话术
	 * @param regulations 实体
	 * @return Integer
	 */
	Integer addRegulations(Regulations regulations);
	
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
	 * @param pageBounds 分页信息
	 * @param regulations 实体
	 * @return List<Regulations>
	 */
	List<Regulations> queryRegulationsForPage(PageBounds pageBounds, @Param("regulations") Regulations regulations);
	 
	 /**
	  * @Title: queryRegulationsByProperty
	  * @Description:根据属性查询项目流程话术
	  * @return List<Regulations>
	  */
	 List<Regulations> queryRegulationsByProperty(@Param("regulations") Map<String, Object> map);
	 
	 
	 
}
