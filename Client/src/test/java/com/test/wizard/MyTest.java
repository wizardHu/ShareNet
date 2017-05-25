package com.test.wizard;

import org.springframework.context.annotation.Configuration;
import org.junit.Test;
import org.springframework.context.annotation.ImportResource;

import com.wizard.net.CheckOnline;

@Configuration
@ImportResource({"classpath:conf/*.xml"})
public class MyTest {

	@Test
	public void test1(){
		Thread thread = new Thread(new CheckOnline("10.0.74.208"));
		thread.start();
	}
	
}
