package com.wizard.test;

import java.io.File;
import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({ "/conf/app-context.xml" }) // 加载配置文件
public class BaseTest {
	
	@BeforeClass
	public static void init()
	{
		System.err.println("执行路径："+getRootPath());
		PropertyConfigurator.configure(getRootPath()+"/conf/log4j.properties");
	}
	
	public static String getRootPath() {

		URL url = BaseTest.class.getProtectionDomain().getCodeSource().getLocation();

		String filePath = null;

		try {
			filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
		} catch (Exception e) {

		}

		if (filePath.endsWith(".jar"))
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);

		File file = new java.io.File(filePath);
		filePath = file.getAbsolutePath();

		return filePath;
	}
}
