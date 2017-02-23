package com.wizard.test;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({ "/conf/spring.xml" }) // 加载配置文件
public class BaseTest {
	
	@BeforeClass
	public static void init()
	{
		PropertyConfigurator.configure("conf/log4j.properties");
	}
}
