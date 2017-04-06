package com.wizard.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.wizard.service.DownLoadRemoteService;

public class DownLoadRemoteServiceImple implements DownLoadRemoteService{
	
	public final static ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(100, new NamedThreadFactory("YiNiuPosWorker", false));

	public static AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String[] args) {
		
//		for (int i = 17; i < 255; i++) {
			for (int j = 173; j < 184; j++) {
				for (int k = 0; k < 255; k++) {//new DownLoadRemoteServiceImple().new Ping("10."+i+"."+j+"."+k)
					final String ip = "10."+17+"."+j+"."+k;
					System.out.println(ip);
					WORKER_THREAD_POOL.execute(new  Runnable() {
						public void run() {
							ping(ip);
						}
					});
//				}
			}
		}
//		ping("10.17.171.22");
		while(count.get() <= 3000){
			System.out.println(count);
		}
		
		WORKER_THREAD_POOL.shutdownNow();
		
	}

	class Ping implements Runnable {
		
		private String ip;
		public Ping(String ip){
			this.ip = ip;
		}
		
		public void run() {
			ping(ip);
		}
	}
	
	private static void ping(String ip)
	{
		Socket s = new Socket();
		SocketAddress add = new InetSocketAddress(ip,3389);
		try {
			s.connect(add,500);
			System.out.println(ip+"---yes");
		} catch (IOException e) {
			System.out.println(ip+" xxxxxxno");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		count.incrementAndGet();
	}
}
