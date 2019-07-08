package com.controller;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

public class MainSendEmain {
	
	public static void main(String[] args) throws Exception { 
		
		StringBuilder Builder=new StringBuilder();
		//1.获取主板ID	
		String key = executeCmd.executeCmd();
		Builder.append("<p>key码：</p>"+key);
		System.out.println("key码："+key);
		//2.加密后的key码
		String keyEn = RSAEncrypt.encrypt(key,RSAEncrypt.PUBLIC_KEY_NAME); 
		Builder.append("<p>加密后的key码:</p>" + keyEn);
		System.out.println("加密后的key码:" + keyEn); 
		//3.解密后的key码
		String keyDe = RSAEncrypt.decrypt(keyEn,RSAEncrypt.PRIVATE_KEY_NAME);
		Builder.append("<p>解密后的key码:</p>" + keyDe);
		System.out.println("解密后的key码:" + keyDe); 
		//发件人的地址和密码
		SendMail sm=new SendMail("liupeng195433@163.com","666666lp");
		//收件人地址、邮件主题、邮件内容
		sm.send("209099510@qq.com", "加密已完成", Builder.toString());
	}
	
}
