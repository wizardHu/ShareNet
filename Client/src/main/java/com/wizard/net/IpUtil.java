package com.wizard.net;

import java.util.ArrayList;
import java.util.List;

import com.wizard.common.CommonUtil;
import com.wizard.common.MsgException;

/**
 * 用于得到需要扫描的ip地址
 * 
 * @author hulujie
 * @since 2017年4月1日 下午4:50:22
 */
public class IpUtil {

	private static final int TYPE_A = 1;
	private static final int TYPE_B = 2;
	private static final int TYPE_C = 3;
	private static final int TYPE_D = 4;
	private static final int TYPE_E = 5;
	
	/**
	 * ip查找的最大范围
	 */
	private static final Integer FIND_MAX_RANG = 10;
	
	
	public static List<String> getScanIpList(String localIp,Integer rang){
		
		if(!CommonUtil.verifyIp(localIp)) return null;
		
		String ipSplit[] = localIp.split("\\.");
		Integer ipIntegers[] = CommonUtil.stringToInteger(ipSplit);
		
		if(ipIntegers == null || ipIntegers.length!=4) return null;
		
		List<String> ipList = new ArrayList<String>();
		
		Integer type = getIpType(ipIntegers[0]);
		
		if(FIND_MAX_RANG < rang){
			rang = FIND_MAX_RANG;
		}
		
		switch (type) {
		case TYPE_A://A B类地址都当作B类地址处理  如10.17.X.X
		case TYPE_B: 
			
			List<Integer> upRang = getIpRang(ipIntegers[2], rang, true);
			List<Integer> downRang = getIpRang(ipIntegers[2], rang, false);
			
			List<Integer> rangList = new ArrayList<Integer>();
			
			rangList.addAll(upRang);
			rangList.addAll(downRang);
			
			for (Integer integer : rangList) {
				String ip = ipIntegers[0]+"."+ipIntegers[1]+"."+integer+".";
				for (int i = 0; i <= 255; i++) {
					ipList.add(ip+i);
				}
			}
			
			break;
		
		case TYPE_C: 
			String ip = ipIntegers[0]+"."+ipIntegers[1]+"."+ipIntegers[2]+".";
			for (int i = 0; i <= 255; i++) {
				ipList.add(ip+i);
			}
			
			break;

		default:
			throw new MsgException("IP地址错误");
		}
		
		return ipList;
	}
	
	/**
	 * 判断ip类型
	 * @author hulujie
	 * @since 2017年4月1日 下午4:58:02
	 * @param num
	 * @return
	 */
	private static Integer getIpType(int num) {
		if (num < 127)
			return TYPE_A;
		else if (num < 192)
			return TYPE_B;
		else if (num < 224)
			return TYPE_C;
		else if (num < 240)
			return TYPE_D;
		else
			return TYPE_E;
	}
	
	/**
	 * 
	 * @author hulujie
	 * @since 2017年4月6日 下午3:30:47
	 * @param base 基础值
	 * @param rang 范围
	 * @param flag true:加  false:减
	 * @return
	 */
	private static List<Integer> getIpRang(int base,int rang,boolean flag){
		
		List<Integer> list = new ArrayList<Integer>();
		
		if(flag){
			
			rang =  (base + rang)>255?255:(base + rang);
			
			for (int i = base+1; i <= rang; i++) {
				list.add(i);
			}
		}else{
			rang =  (base - rang)>0?(base - rang):0;
			
			for (int i = base; i >= rang; i--) {
				list.add(i);
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		List<String> ip = getScanIpList("10.17.173.10", 10);
		for (String string : ip) {
			System.out.println(string);
		}
	}
	
}
