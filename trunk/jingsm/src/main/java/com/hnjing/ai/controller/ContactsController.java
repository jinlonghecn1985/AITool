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

import com.hnjing.ai.model.entity.Contacts;
import com.hnjing.ai.model.entity.Project;
import com.hnjing.ai.service.ContactsService;
import com.hnjing.ai.service.ProjectService;
import com.hnjing.config.validation.BeanValidator;
import com.hnjing.config.web.exception.NotFoundException;
import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.utils.ClassUtil;
import com.hnjing.utils.HttpTool;
import com.hnjing.utils.StringUtil;
import com.hnjing.utils.file.office.ExcelUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: ContactsController
 * @Description: 项目联系人信息HTTP接口
 * @author: Jinlong He
 * @email: mailto:jinlong_he@126.com
 * @date: 2019年01月25日 14时39分
 */
@RestController
@Api(description="项目联系人信息")
public class ContactsController{
	private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);

	@Autowired
	BeanValidator beanValidator;
	
	@Autowired
	private ContactsService contactsService;
	
	@Value("${file.upload.path}")
	private String filePath;
	
	@Autowired
	private ProjectService projectService;

	
	@ApiOperation(value = "新增 添加项目联系人信息信息", notes = "添加项目联系人信息信息")
	@RequestMapping(value = "/contacts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object addContacts(HttpServletResponse response,
			@ApiParam(value = "contacts") @RequestBody Contacts contacts) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		contacts.setContactsId(null);
		contactsService.addContacts(contacts);
		return contacts;
	}
	
	
	@ApiOperation(value = "更新 根据项目联系人信息标识更新项目联系人信息信息", notes = "根据项目联系人信息标识更新项目联系人信息信息")
	@RequestMapping(value = "/contacts/{contactsId:.+}", method = RequestMethod.PUT)
	public Object modifyContactsById(HttpServletResponse response,
			@PathVariable Integer contactsId,
			@ApiParam(value = "contacts", required = true) @RequestBody Contacts contacts
			) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Contacts tempContacts = contactsService.queryContactsByContactsId(contactsId);
		contacts.setContactsId(contactsId);
		if(null == tempContacts){
			throw new NotFoundException("项目联系人信息");
		}
		return contactsService.modifyContacts(contacts);
	}

	@ApiOperation(value = "删除 根据项目联系人信息标识删除项目联系人信息信息", notes = "根据项目联系人信息标识删除项目联系人信息信息")
	@RequestMapping(value = "/contacts/{contactsId:.+}", method = RequestMethod.DELETE)
	public Object dropContactsByContactsId(HttpServletResponse response, @PathVariable Integer contactsId) {
		Contacts contacts = contactsService.queryContactsByContactsId(contactsId);
		if(null == contacts){
			throw new NotFoundException("项目联系人信息");
		}
		return contactsService.dropContactsByContactsId(contactsId);
	}
	
	@ApiOperation(value = "查询 根据项目联系人信息标识查询项目联系人信息信息", notes = "根据项目联系人信息标识查询项目联系人信息信息")
	@RequestMapping(value = "/contacts/{contactsId:.+}", method = RequestMethod.GET)
	public Object queryContactsById(HttpServletResponse response,
			@PathVariable Integer contactsId) {
		Contacts contacts = contactsService.queryContactsByContactsId(contactsId);
		if(null == contacts){
			throw new NotFoundException("项目联系人信息");
		}
		return contacts;
	}
	
	@ApiOperation(value = "查询 根据项目联系人信息属性查询项目联系人信息信息列表", notes = "根据项目联系人信息属性查询项目联系人信息信息列表")
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public Object queryContactsList(HttpServletResponse response,
			Contacts contacts) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		return contactsService.queryContactsByProperty(ClassUtil.transBean2Map(contacts, false));
	}
	
	@ApiOperation(value = "查询分页 根据项目联系人信息属性分页查询项目联系人信息信息列表", notes = "根据项目联系人信息属性分页查询项目联系人信息信息列表")
	@RequestMapping(value = "/contactss", method = RequestMethod.GET)
	public Object queryContactsPage(HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pagenum,
			@RequestParam(value = "pageSize", required = false) Integer pagesize, 
			@RequestParam(value = "sort", required = false) String sort, Contacts contacts) {				
		return contactsService.queryContactsForPage(pagenum, pagesize, sort, contacts);
	}
	
	@ApiOperation(value = "上传联系人清单", notes = "上传联系人清单")
	@RequestMapping(value = "/contactss/upload/{projectId:.+}", method = RequestMethod.POST)
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
			contactsService.dropContactsByProjectId(projectId);
			if (xlsData != null && xlsData.size() > 0) {
				int i=0;
				for (List<String> data : xlsData) {
					if(i==0) {
						i++;
						continue;
					}
					Contacts c = new Contacts();
					c.setProjectId(projectId);
					c.setCompany(data.get(0));
					c.setContacts(data.get(1));
					c.setPhone(StringUtil.ToDBC(data.get(2)));
					if(c.getPhone()!=null && c.getPhone().length()>8) {
						contactsService.addContacts(c);
					}
				}
			}
			return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body onload='JavaScript:history.go(-1)'></body></html>";

		}
		throw new ParameterException("file", "错误的文件格式，当前仅允许上传xls或xlsx表格！");
	}
	
	@ApiOperation(value = "导出数据", notes = "导出数据")
	@RequestMapping(value = "/contactss/export", method = RequestMethod.GET)
	public void exportDataList(HttpServletResponse response,
			Contacts contacts) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		if(contacts==null || contacts.getProjectId()==null) {
			throw new ParameterException("projectId", "找不到必传参数，计划标识 ！");
		}
		Project tempProject = projectService.queryProjectByProjectId(contacts.getProjectId());
		if(null == tempProject){
			throw new NotFoundException("AI项目");
		}
		String fileName = "contacts_item_"+System.currentTimeMillis()+".xls";
		HSSFWorkbook wb = contactsService.exportByProperty(ClassUtil.transBean2Map(contacts, false));
		HttpTool.setResponseHeader(response, fileName);
		OutputStream os = response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();
	}

}
