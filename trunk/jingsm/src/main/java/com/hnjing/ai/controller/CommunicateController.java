package com.hnjing.ai.controller;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hnjing.ai.model.entity.Communicate;
import com.hnjing.ai.model.entity.Project;
import com.hnjing.ai.service.CommunicateService;
import com.hnjing.ai.service.ProjectService;
import com.hnjing.config.validation.BeanValidator;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.utils.ClassUtil;
import com.hnjing.utils.DateUtil;
import com.hnjing.utils.HttpTool;
import com.hnjing.utils.file.office.ExcelUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: CommunicateController
 * @Description: 沟通详情HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@RestController
@Api(description="沟通详情")
public class CommunicateController{

	@Autowired
	BeanValidator beanValidator;
	
	@Value("${file.upload.path}")
	private String filePath;
	
	@Autowired
	private CommunicateService communicateService;
	
	@Autowired
	private ProjectService projectService;
	
	private static final Logger logger = LoggerFactory.getLogger(RegulationsController.class);

	
	@ApiOperation(value = "新增 添加沟通详情信息", notes = "添加沟通详情信息")
	@RequestMapping(value = "/communicate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addCommunicate(HttpServletResponse response,
			@ApiParam(value = "communicate") @RequestBody Communicate communicate) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		communicate.setCid(null);
		communicateService.addCommunicate(communicate);
		return communicate;
	}
	
	
	@ApiOperation(value = "更新 根据沟通详情标识更新沟通详情信息", notes = "根据沟通详情标识更新沟通详情信息")
	@RequestMapping(value = "/communicate/{cid:.+}", method = RequestMethod.PUT)
	public Object modifyCommunicateById(HttpServletResponse response,
			@PathVariable Integer cid,
			@ApiParam(value = "communicate", required = true) @RequestBody Communicate communicate
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Communicate tempCommunicate = communicateService.queryCommunicateByCid(cid);
		communicate.setCid(cid);
		if(null == tempCommunicate){
			throw new NotFoundException("沟通详情");
		}
		return communicateService.modifyCommunicate(communicate);
	}

	@ApiOperation(value = "删除 根据沟通详情标识删除沟通详情信息", notes = "根据沟通详情标识删除沟通详情信息")
	@RequestMapping(value = "/communicate/{cid:.+}", method = RequestMethod.DELETE)
	public Object dropCommunicateByCid(HttpServletResponse response, @PathVariable Integer cid) {
		Communicate communicate = communicateService.queryCommunicateByCid(cid);
		if(null == communicate){
			throw new NotFoundException("沟通详情");
		}
		return communicateService.dropCommunicateByCid(cid);
	}
	
	@ApiOperation(value = "查询 根据沟通详情标识查询沟通详情信息", notes = "根据沟通详情标识查询沟通详情信息")
	@RequestMapping(value = "/communicate/{cid:.+}", method = RequestMethod.GET)
	public Object queryCommunicateById(HttpServletResponse response,
			@PathVariable Integer cid) {
		Communicate communicate = communicateService.queryCommunicateByCid(cid);
		if(null == communicate){
			throw new NotFoundException("沟通详情");
		}
		return communicate;
	}
	
	@ApiOperation(value = "查询 根据沟通详情属性查询沟通详情信息列表", notes = "根据沟通详情属性查询沟通详情信息列表")
	@RequestMapping(value = "/communicate", method = RequestMethod.GET)
	public Object queryCommunicateList(HttpServletResponse response,
			Communicate communicate) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return communicateService.queryCommunicateByProperty(ClassUtil.transBean2Map(communicate, false));
	}
	
	@ApiOperation(value = "查询分页 根据沟通详情属性分页查询沟通详情信息列表", notes = "根据沟通详情属性分页查询沟通详情信息列表")
	@RequestMapping(value = "/communicates", method = RequestMethod.GET)
	public Object queryCommunicatePage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, Communicate communicate) {				
		return communicateService.queryCommunicateForPage(pagenum, pagesize, sort, communicate);
	}
	

	@ApiOperation(value = "上传项目沟通清单", notes = "上传沟通清单")
	@RequestMapping(value = "/communicates/upload/{projectId:.+}", method = RequestMethod.POST)
	public Object queryContactsPage(@PathVariable Integer projectId, @RequestParam("xlsFile") MultipartFile xlsFile) {
		if (xlsFile.isEmpty()) {
			throw new ParameterException("file", "请选择要上传的文件！");
		}
		Project tempProject = projectService.queryProjectByProjectId(projectId);
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		String fileName = xlsFile.getOriginalFilename().toLowerCase();
		logger.info("上传文件成功" + fileName);		
		File dest = new File(filePath + fileName);
		try {
			xlsFile.transferTo(dest);
			logger.info("保存成功" + filePath + fileName);
		} catch (IOException e) {
			logger.error(e.toString(), e);
			throw new ParameterException("file", "保存文件失败");
		}		
		if (fileName != null && (fileName.endsWith("xlsx") || fileName.endsWith("xls"))) {
			List<List<String>> xlsData;
			try {
				xlsData = ExcelUtil.readDocFile(filePath + fileName, true);
			} catch (Exception e) {
				throw new ParameterException("file", "无法解析excel文档");
			}			
			communicateService.dropCommunicateByProjectId(projectId);
			if (xlsData != null && xlsData.size() > 0) {
				int i=0;
				for (List<String> data : xlsData) {
					if(i==0) {
						i++;
						continue;
					}
					if(data!=null && data.size()>10 && data.get(1)!=null && data.get(1).trim().length()>4) {
						Communicate r = new Communicate();
						r.setProjectId(projectId);
						r.setCallFrom(data.get(2));
						r.setPhone(data.get(1));
						r.setName(data.get(3));
						r.setCompany(data.get(4));
						if(data.get(6)!=null && data.get(6).trim().length()>0) {
							try {
								r.setCallLength(Integer.parseInt(data.get(6).trim()));
							} catch (NumberFormatException e) {
								//e.printStackTrace();
							}
						}
						if(data.get(7)!=null && data.get(6).trim().length()>0) {
							try {
								r.setCallTimes(Integer.parseInt(data.get(7).trim()));
							} catch (NumberFormatException e) {
								//e.printStackTrace();
							}
						}
						if(data.get(5)!=null && data.get(5).length()>12) {
							try {
								r.setCallDate(DateUtil.String2DateTime(data.get(5)));
							} catch (ParseException e) {
								//e.printStackTrace();
							}
						}
						r.setUserType(data.get(8));
						r.setNote(data.get(9));
						r.setContent(data.get(10));
						communicateService.addCommunicate(r);
					}					
				}
			}
			return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body onload='JavaScript:history.go(-1)'></body></html>";

		}
		throw new ParameterException("file", "错误的文件格式，当前仅允许上传xls或xlsx表格！");
	}
	
	
	@ApiOperation(value = "导出数据", notes = "导出数据")
	@RequestMapping(value = "/communicates/export", method = RequestMethod.GET)
	public void exportDataList(HttpServletResponse response,
			Communicate communicate) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		if(communicate==null || communicate.getProjectId()==null) {
			throw new ParameterException("projectId", "找不到必传参数，计划标识 ！");
		}
		Project tempProject = projectService.queryProjectByProjectId(communicate.getProjectId());
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		String fileName = "sensitive_item_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = communicateService.exportByProperty(ClassUtil.transBean2Map(communicate, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

}
