package com.wizard.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.wizard.common.PropertiseUtil;
import com.wizard.common.SpringUtil;


public class NetworkUtil {

	private final Logger logger = LoggerFactory.getLogger(NetworkUtil.class);
	
	private String ipFilePath = "address/ip.txt";
	
	/**
	 * 记录已经查找过的IP地址
	 */
	private ConcurrentHashSet<String> findededIp = new ConcurrentHashSet<String>();
	
	/**
	 * 记录在线的IP
	 */
	private ConcurrentHashSet<String> onLineIp = new ConcurrentHashSet<String>();
	
	/**
	 * 记录缓存文件的IP
	 */
	private Set<String> cacheIpSet = new HashSet<String>();
	
	/**
	 * 端口
	 */
	private Integer port;
	
	/**
	 * 总共要查找出在线的ip数量
	 */
	private Integer ipCount;
	
	private NetworkUtil(){
		port = Integer
				.parseInt(SpringUtil.getSpringContext().getBean(PropertiseUtil.class).getPort());
		ipCount = Integer
				.parseInt(SpringUtil.getSpringContext().getBean(PropertiseUtil.class).getIpCount());
	}
	
	
	/**
	 * 判断是否已经查找过了
	 * @author hulujie
	 * @since 2017年2月27日 下午3:33:13
	 * @param ip
	 * @return
	 */
	protected boolean isInFindededIp(String ip){
		return findededIp.contains(ip);
	}
	
	/**
	 * 往findededIp插入
	 * @author hulujie
	 * @since 2017年2月27日 下午3:34:20
	 * @param ip
	 * @return
	 */
	protected boolean addInFindededIp(String ip){
		return findededIp.add(ip);
	}
	
	/**
	 * 
	 * @author hulujie
	 * @since 2017年2月27日 下午3:38:48
	 * @param ip
	 * @return
	 */
	protected boolean addInOnLineIp(String ip){
		return onLineIp.add(ip);
	}
	
	/**
	 * 
	 * @author hulujie
	 * @since 2017年2月27日 下午5:19:40
	 * @return
	 */
	public Integer getOnLineIpSize(){
		return onLineIp.size();
	}
	
	/**
	 * 获得在线的IP列表
	 * @author hulujie
	 * @since 2017年2月24日 下午4:58:32
	 * @param localIp
	 * @return
	 */
	public  Vector<String> getIpList(String localIp){

		readIpCacheFile();
		
		return null;
	}
	
	/**
	 * 读取IP的缓存文件
	 * @author hulujie
	 * @since 2017年2月24日 下午5:00:32
	 * @return
	 * @throws IOException 
	 */
	private  void readIpCacheFile(){
		
		BufferedReader br = null;
		FileReader fr = null;
		
		File file = new File(ipFilePath);
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String strTemp = null;
			
			while((strTemp = br.readLine())!=null){
				cacheIpSet.add(strTemp);
			}
			
			
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}finally {
			if(fr!=null){
				try {
					fr.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		
		logger.info("NetworkUtil==>readIpCacheFile cacheIpSet={}",cacheIpSet);
	}
	
	public Integer getPort() {
		return port;
	}
	
	public Integer getIpCount() {
		return ipCount;
	}
	
	public static void main(String[] args) {
		NetworkUtil netWorkUtil = new NetworkUtil();
		netWorkUtil.getIpList("");
	}
	
}
