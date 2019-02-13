package com.hnjing.ai.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hnjing.utils.paginator.domain.PageBounds;
import com.hnjing.ai.model.entity.Communicate;

/**
 * @ClassName: CommunicateMapper
 * @Description: 沟通详情映射
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@Mapper
public interface CommunicateMapper {

	/**
	 * @Title: addCommunicate
	 * @Description:添加沟通详情
	 * @param communicate 实体
	 * @return Integer
	 */
	Integer addCommunicate(Communicate communicate);
	
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
	 * @param pageBounds 分页信息
	 * @param communicate 实体
	 * @return List<Communicate>
	 */
	List<Communicate> queryCommunicateForPage(PageBounds pageBounds, @Param("communicate") Communicate communicate);
	 
	 /**
	  * @Title: queryCommunicateByProperty
	  * @Description:根据属性查询沟通详情
	  * @return List<Communicate>
	  */
	 List<Communicate> queryCommunicateByProperty(@Param("communicate") Map<String, Object> map);

	/** 
	* @Title: dropCommunicateByProjectId 
	* @Description: 
	* @param projectId
	* @return  
	* Integer    返回类型 
	* @throws 
	*/
	Integer dropCommunicateByProjectId(Integer projectId);
	 
	 
	 
}
