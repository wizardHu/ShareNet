package com.wizard.common;

import java.io.File;
import java.net.URL;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.wizard.msg.Message;

public class MsgContext {

	/**
	 * 获得系统安装路径
	 * 
	 * @return
	 */
	public static String getRootPath() {

		URL url = MsgContext.class.getProtectionDomain().getCodeSource().getLocation();

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
	
	
	private static Message message;
	
	public static Message getMessageRef() {

		if (message == null) {
			synchronized (Message.class) {

				if (message == null) {
//					logger.info("初始化PosRemoteCardService!");

					try {
						message = createRef(Message.class,"");
					} catch (RuntimeException e) {
//						logger.error("CardPosService初始化失败: ", e);
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
			ip="localhost";
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
