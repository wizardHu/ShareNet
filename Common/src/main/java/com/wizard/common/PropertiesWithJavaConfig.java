package com.wizard.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 读取配置文件
 * @author hulujie
 * @since 2017年2月24日 下午4:17:42
 */
@Configuration
@PropertySource({ "file:conf/net.properties"// 如果是相同的key，则最后一个起作用
})
public class PropertiesWithJavaConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
