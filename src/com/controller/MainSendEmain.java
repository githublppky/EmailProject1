package com.controller;

import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

public class MainSendEmain {
	
	public static void main(String[] args) throws Exception { 
		
		StringBuilder Builder=new StringBuilder();
		//1.��ȡ����ID	
		String key = executeCmd.executeCmd();
		Builder.append("<p>key�룺</p>"+key);
		System.out.println("key�룺"+key);
		//2.���ܺ��key��
		String keyEn = RSAEncrypt.encrypt(key,RSAEncrypt.PUBLIC_KEY_NAME); 
		Builder.append("<p>���ܺ��key��:</p>" + keyEn);
		System.out.println("���ܺ��key��:" + keyEn); 
		//3.���ܺ��key��
		String keyDe = RSAEncrypt.decrypt(keyEn,RSAEncrypt.PRIVATE_KEY_NAME);
		Builder.append("<p>���ܺ��key��:</p>" + keyDe);
		System.out.println("���ܺ��key��:" + keyDe); 
		//�����˵ĵ�ַ������
		SendMail sm=new SendMail("liupeng195433@163.com","666666lp");
		//�ռ��˵�ַ���ʼ����⡢�ʼ�����
		sm.send("209099510@qq.com", "���������", Builder.toString());
	}
	
}
