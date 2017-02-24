package com.wizard.common;

import org.springframework.context.ApplicationContext;

/**
 * Spring工具类
 * @author hulujie
 * @since 2017年2月22日 下午9:44:10
 */
public class SpringUtil {

	private static ApplicationContext ctx;
	
	public static ApplicationContext getSpringContext()
	{
		return ctx;
	}
	
	public static void setSpringContext(ApplicationContext ctx)
	{
		SpringUtil.ctx = ctx;
	}
}
