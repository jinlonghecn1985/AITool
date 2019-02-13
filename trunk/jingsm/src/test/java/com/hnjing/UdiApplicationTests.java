package com.hnjing;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hnjing.ai.model.entity.Contacts;
import com.hnjing.ai.service.ContactsService;
import com.hnjing.config.web.exception.ParameterException;
import com.hnjing.utils.file.office.ExcelUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UdiApplicationTests {
	@Autowired
	private ContactsService contactsService;

	@Test
	public void contextLoads() throws Exception {
		System.out.println("test");
//		String filePath = "D:\\";
//		String fileName= "待联系客户--清洗20190110 - 副本.xlsx";
//		Integer contactsId= 1;
//		if(fileName!=null && (fileName.endsWith("xlsx") || fileName.endsWith("xls"))) {
//			System.out.println(filePath + fileName);
//			try {
//				List<List<String>> xlsData = ExcelUtil.readDocFile(filePath + fileName, true);
//				if(xlsData!=null && xlsData.size()>0) {
//					for(List<String> data : xlsData) {
//						Contacts c = new Contacts();
//						c.setProjectId(contactsId);
//						c.setCompany(data.get(0));
//						c.setContacts(data.get(1));
//						c.setPhone(data.get(2));
//						contactsService.addContacts(c);
//					}
//				}
//			
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new ParameterException("projectName", "无法解析excel文档");
//			}
//		}
	}
	

}
