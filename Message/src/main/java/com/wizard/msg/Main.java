package com.wizard.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wizard.common.MsgContext;
import com.wizard.common.SpringUtil;

public class Main {

	private final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		Main m = new Main();
		m.initSpring();
	}
	
	
	/**
	 * 启动spring
	 */
	private void initSpring() {
		ApplicationContext ctx;
		try {
			ctx = new FileSystemXmlApplicationContext("/conf/spring.xml");
		} catch (BeansException e) {

			logger.error("initSpring() error: ", e);

			throw e;
		}
		logger.info("Spring容器启动完成！");
		SpringUtil.setSpringContext(ctx);
		
		
		Message m = MsgContext.getMessageRef();
		m.sayHello();
	}
}
