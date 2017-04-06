package com.wizard.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	/**
	 * 校验ip合法性
	 * @author hulujie
	 * @since 2017年4月1日 下午5:52:02
	 * @param ip
	 * @return
	 */
	public static boolean verifyIp(String ip){
		
		String regex = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";
        
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(ip);
		
		return matcher.matches();
	}
	
	public static boolean verifyNum(String num){
		
		String regex = "^\\d+$";
        
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(num);
		
		return matcher.matches();
	}
	
	/**
	 * 将str数组转换成int性
	 * @author hulujie
	 * @since 2017年4月1日 下午5:57:29
	 * @param str
	 * @return
	 */
	public static Integer[] stringToInteger(String[] str){
		
		if(str == null) return null;
		
		Integer[] integer = new Integer[str.length];
		
		for (int i = 0; i < str.length; i++) {
			
			if(!verifyNum(str[i])) return null;
			
			integer[i] = Integer.parseInt(str[i]);
			
		}
		
		return integer;
	}
	
}
