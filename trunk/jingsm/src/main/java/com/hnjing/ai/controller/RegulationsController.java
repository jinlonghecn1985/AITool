package com.hnjing.ai.controller;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
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

import com.hnjing.ai.model.entity.Project;
import com.hnjing.ai.model.entity.Regulations;
import com.hnjing.ai.service.ProjectService;
import com.hnjing.ai.service.RegulationsService;
import com.hnjing.config.validation.BeanValidator;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.utils.ClassUtil;
import com.hnjing.utils.HttpTool;
import com.hnjing.utils.JsonUtil;
import com.hnjing.utils.file.office.ExcelUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: RegulationsController
 * @Description: 项目流程话术HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@RestController
@Api(description="项目流程话术")
public class RegulationsController{

	private static String lineSeparator = System.getProperty("line.separator");
	
	@Autowired
	BeanValidator beanValidator;
	
	@Value("${file.upload.path}")
	private String filePath;
	
	@Autowired
	private RegulationsService regulationsService;
	
	@Autowired
	private ProjectService projectService;
	
	private static final Logger logger = LoggerFactory.getLogger(RegulationsController.class);

	
	@ApiOperation(value = "新增 添加项目流程话术信息", notes = "添加项目流程话术信息")
	@RequestMapping(value = "/regulations", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addRegulations(HttpServletResponse response,
			@ApiParam(value = "regulations") @RequestBody Regulations regulations) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		regulations.setRegulationsId(null);
		regulationsService.addRegulations(regulations);
		return regulations;
	}
	
	
	@ApiOperation(value = "更新 根据项目流程话术标识更新项目流程话术信息", notes = "根据项目流程话术标识更新项目流程话术信息")
	@RequestMapping(value = "/regulations/{regulationsId:.+}", method = RequestMethod.PUT)
	public Object modifyRegulationsById(HttpServletResponse response,
			@PathVariable Integer regulationsId,
			@ApiParam(value = "regulations", required = true) @RequestBody Regulations regulations
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Regulations tempRegulations = regulationsService.queryRegulationsByRegulationsId(regulationsId);
		regulations.setRegulationsId(regulationsId);
		if(null == tempRegulations){
			throw new NotFoundException("项目流程话术");
		}
		return regulationsService.modifyRegulations(regulations);
	}

	@ApiOperation(value = "删除 根据项目流程话术标识删除项目流程话术信息", notes = "根据项目流程话术标识删除项目流程话术信息")
	@RequestMapping(value = "/regulations/{regulationsId:.+}", method = RequestMethod.DELETE)
	public Object dropRegulationsByRegulationsId(HttpServletResponse response, @PathVariable Integer regulationsId) {
		Regulations regulations = regulationsService.queryRegulationsByRegulationsId(regulationsId);
		if(null == regulations){
			throw new NotFoundException("项目流程话术");
		}
		return regulationsService.dropRegulationsByRegulationsId(regulationsId);
	}
	
	@ApiOperation(value = "查询 根据项目流程话术标识查询项目流程话术信息", notes = "根据项目流程话术标识查询项目流程话术信息")
	@RequestMapping(value = "/regulations/{regulationsId:.+}", method = RequestMethod.GET)
	public Object queryRegulationsById(HttpServletResponse response,
			@PathVariable Integer regulationsId) {
		Regulations regulations = regulationsService.queryRegulationsByRegulationsId(regulationsId);
		if(null == regulations){
			throw new NotFoundException("项目流程话术");
		}
		return regulations;
	}
	
	@ApiOperation(value = "查询 根据项目流程话术属性查询项目流程话术信息列表", notes = "根据项目流程话术属性查询项目流程话术信息列表")
	@RequestMapping(value = "/regulations", method = RequestMethod.GET)
	public Object queryRegulationsList(HttpServletResponse response,
			Regulations regulations) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return regulationsService.queryRegulationsByProperty(ClassUtil.transBean2Map(regulations, false));
	}
	
	@ApiOperation(value = "查询分页 根据项目流程话术属性分页查询项目流程话术信息列表", notes = "根据项目流程话术属性分页查询项目流程话术信息列表")
	@RequestMapping(value = "/regulationss", method = RequestMethod.GET)
	public Object queryRegulationsPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, Regulations regulations) {				
		return regulationsService.queryRegulationsForPage(pagenum, pagesize, sort, regulations);
	}
	
	@ApiOperation(value = "上传项目话术清单", notes = "上传话术清单")
	@RequestMapping(value = "/regulationss/upload/{projectId:.+}", method = RequestMethod.POST)
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
			regulationsService.dropRegulationsByProjectId(projectId);
			if (xlsData != null && xlsData.size() > 0) {
				int i=0;
				for (List<String> data : xlsData) {
					if(i==0) {
						i++;
						continue;
					}
					if(data!=null && data.size()>4) {
						Regulations r = new Regulations();
						r.setProjectId(projectId);
						if(data.get(3)!=null && data.get(3).trim().length()>0) {
							if(data.get(3).trim().length()<4) {
								r.setUscode(data.get(3).trim());
							}
						}
						r.setRegulations(data.get(2));
						r.setContent(data.get(1).replaceAll(lineSeparator, "").replaceAll("\\n", "").replaceAll("\\pP" , ""));
						r.setUsorder("213");
						if(data.size()>=8 && data.get(7)!=null && data.get(7).trim().length()>0) {
							String o = data.get(7).trim();
							if("132".equals(o) || "123".equals(o) || "213".equals(o) || "231".equals(o) || "312".equals(o) || "321".equals(o)) {
								r.setUsorder(o);
							}
						}
						r.setAsYes(data.get(4));
						r.setAsNo(data.get(5));
						r.setAsOther(data.get(6));
						logger.info(JsonUtil.object2json(r));
						regulationsService.addRegulations(r);
					}					
				}
			}
			return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body onload='JavaScript:history.go(-1)'></body></html>";

		}
		throw new ParameterException("file", "错误的文件格式，当前仅允许上传xls或xlsx表格！");
	}
	
	@ApiOperation(value = "导出数据", notes = "导出数据")
	@RequestMapping(value = "/regulationss/export", method = RequestMethod.GET)
	public void exportDataList(HttpServletResponse response,
			Regulations regulations) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		if(regulations==null || regulations.getProjectId()==null) {
			throw new ParameterException("projectId", "找不到必传参数，计划标识 ！");
		}
		Project tempProject = projectService.queryProjectByProjectId(regulations.getProjectId());
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		String fileName = "regulations_item_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = regulationsService.exportByProperty(ClassUtil.transBean2Map(regulations, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

}
