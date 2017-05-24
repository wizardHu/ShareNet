package com.wizard.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 判断IP是否在线
 * @author hulujie
 * @since 2017年2月27日 下午3:19:18
 */
public class CheckOnline implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(CheckOnline.class);
	
	private NetworkUtil util = NetworkUtil.networkUtil;
	private String ip;
	private Integer port;
	private Integer ipCount;
	
	public CheckOnline(String ip){
		this.ip = ip;
		port = util.getPort();
		ipCount = util.getIpCount();
	}

	public void run() {
	
		if(!util.isInFindededIp(ip) && util.getOnLineIpSize()<=ipCount){//以前没有查找过 并且 在线数量还没到达设置的值
			find();
		}
	}
	
	private void find(){
		Socket socket = new Socket();
		SocketAddress address = new InetSocketAddress(ip,port);
		try {
			socket.connect(address,500);
			util.addInOnLineIp(ip);//保存到在线的
			logger.info("CheckOnline==>find ip={}",ip);
		} catch (IOException e) {
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
		
		util.addInFindededIp(ip);//记录已经查找过的
	}

}
