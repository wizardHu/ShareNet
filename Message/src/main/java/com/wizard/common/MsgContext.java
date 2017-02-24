package com.wizard.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.wizard.msg.Message;

public class MsgContext {

	private final Logger logger = LoggerFactory.getLogger(MsgContext.class);
	
	private static Message message;
	
	public static Message getMessageRef() {

		if (message == null) {
			synchronized (Message.class) {

				if (message == null) {

					try {
						message = createRef(Message.class,"");
					} catch (RuntimeException e) {
						throw e;
					}
				}
			}
		}

		return message;
	}
	
	private static <T> T createRef(Class<T> cls,String...strings)
	{
		String ip;
		String port;
		int connectTimeout;
		
		if (strings!=null&&strings.length==3)
		{
			ip = strings[0];
			port = strings[1];
			connectTimeout = Integer.parseInt(strings[2]);
		}
		else
		{
			//测试临时使用
			ip="10.17.171.12";
			port="20003";
			connectTimeout=10000;
			
//			PropertiseUtil propertiseUtil = SpringUtil.getSpringContext().getBean(PropertiseUtil.class);
//			ip = propertiseUtil.getRemoteServiceIp();
//			port = propertiseUtil.getRemoteServicePort();
//			connectTimeout = propertiseUtil.getRemoteServiceTimeout();
			
			
		}

		ReferenceConfig<T> referenceConfig = new ReferenceConfig<T>();
		referenceConfig.setInterface(cls);
		StringBuilder url = new StringBuilder();
		url.append("dubbo://");
		url.append(ip);
		url.append(":");
		url.append(port);
		url.append("/");
		url.append(cls.getName());
		referenceConfig.setUrl(url.toString());
		//hardcode
		referenceConfig.setConnections(1);
		ApplicationConfig application = new ApplicationConfig();
		application.setName("yiniu_pos_consumer");
		referenceConfig.setApplication(application);
		referenceConfig.setTimeout(connectTimeout);
		
		return referenceConfig.get();
	}
	
}
