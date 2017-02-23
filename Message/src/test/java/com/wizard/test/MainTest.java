package com.wizard.test;

import org.junit.Test;

import com.wizard.common.MsgContext;
import com.wizard.msg.Message;

public class MainTest extends BaseTest{

	@Test
	public void testDubbo(){
		Message m = MsgContext.getMessageRef();
		System.out.println(m+"================");
		m.sayHello();
		System.out.println("-----");
	}
	
}
