/**  
 * Copyright © 2019公司名字. All rights reserved.
 * @Title: UploadController.java
 * @Prject: SiteMonitor
 * @Package: com.hnjing.dpc.controller
 * @Description: TODO
 * @author: Jinlong He
 * @mail: hejinlong01@hnjing.com
 * @date: 2019年1月23日 上午11:37:55
 * @version: V1.0  
 */
package com.hnjing.ai.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.annotations.Api;

/**
 * @ClassName: UploadController
 * @Description: TODO
 * @author: Jinlong He
 * @date: 2019年1月23日 上午11:37:55
 */
@RestController
@Api(description = "系统控制-文件上传")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	// @GetMapping("/upload")
	// public String upload() {
	// return "upload";
	// }

	@RequestMapping(value = "/upload/single", method = RequestMethod.POST)
	public String upload(@RequestParam("xlsFile") MultipartFile xlsFile) {
		if (xlsFile.isEmpty()) {
			return "上传失败，请选择文件";
		}
		String fileName = xlsFile.getOriginalFilename();
		logger.info("上传成功"+fileName);
		String filePath = "D:\\";
		File dest = new File(filePath + fileName);
		try {
			xlsFile.transferTo(dest);
			logger.info("上传成功");
			return "上传成功";
		} catch (IOException e) {
			logger.error(e.toString(), e);
		}
		return "上传失败！";
	}

//	/**
//	 * 实现多文件上传
//	 */
//	@RequestMapping(value = "upload/muti", method = RequestMethod.POST)
//	public String multifileUpload(HttpServletRequest request) {
//
//		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
//
//		if (files.isEmpty()) {
//			return "false";
//		}
//
//		String path = "D:\\";
//
//		for (MultipartFile file : files) {
//			String fileName = file.getOriginalFilename();
//			int size = (int) file.getSize();
//			System.out.println(fileName + "-->" + size);
//
//			if (file.isEmpty()) {
//				return "false";
//			} else {
//				File dest = new File(path + "/" + fileName);
//				if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
//					dest.getParentFile().mkdir();
//				}
//				try {
//					file.transferTo(dest);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					return "false";
//				}
//			}
//		}
//		return "true";
//	}

}
