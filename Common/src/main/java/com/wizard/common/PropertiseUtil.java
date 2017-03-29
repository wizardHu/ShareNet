package com.wizard.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiseUtil {

	@Value("${ip_count}")
	private String ipCount;
	
	@Value("${port}")
	private String port;
	
	public String getIpCount() {
		return ipCount;
	}
	
	public String getPort() {
		return port;
	}
}
