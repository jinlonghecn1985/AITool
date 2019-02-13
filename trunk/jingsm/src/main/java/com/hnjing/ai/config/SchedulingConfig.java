package com.hnjing.ai.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;




/**
 * @ClassName: SchedulingConfig
 * @Description: 定时任务配置类     秒 分 时 日 月 周
 * @author: Jinlong He
 * @date: 2017年6月8日 下午3:38:15
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
	private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

	
	
	
	/*************************************************************** IP check start ***/
	/** 
	* @Title: doCheckAllWebIPScheduler 
	* @Description: 定时任务-开始检测网站IP   每天下午13点
	* @throws 
	*/
	@Scheduled(cron = "0 26 20 * * ?")
	public void doCheckAllWebIPScheduler() {
		try {
			//siteIPService.checkAllSiteIP(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("定时任务执行发生错误：checkAllSiteIPScheduler" + e.getMessage());
		}
	}
	
	
		
	
}
