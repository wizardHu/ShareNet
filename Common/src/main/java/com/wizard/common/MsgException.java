package com.wizard.common;

/**
 * ]消息提示异常,消息传递专用
 * @author hulujie
 * @since 2017年4月1日 下午6:32:08
 */
public class MsgException extends RuntimeException {

	private static final long serialVersionUID = -7983532985554593761L;
	private String msgCode;
	
	public static String PARA_ERROR = "001";//参数错误
	public static String DATA_ERROR = "002";//数据错误

	public MsgException(String message) {
		super(message);
	}
	
	public MsgException(String  msgCode,String msg)
	{
		super(msg);
		this.msgCode = msgCode;
	}
	
	/**
	 * 错误信息编码
	 * @return
	 */
	public String getMsgCode() {
		return msgCode;
	}

	

}
