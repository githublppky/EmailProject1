package com.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.smtp.SMTPSenderFailedException;

public class SendMail {

	   //�����ʼ���props�ļ�
	private final transient Properties props=System.getProperties();
	//�ʼ���������¼��֤
	private transient MailAuthenticator authenticator;
	//����session
	private transient Session session;
	/**
	* ��ʼ�������ʼ�
	* @param smtpHostName�ʼ���������ַ
	* @param username�����ʼ����û���
	* @param password����
	*/
	public SendMail(final String smtpHostName,final String username,final String password){
	init(username,password,smtpHostName);
	}
	/**
	* ��ʼ�������ʼ�
	* @param username �����ʼ����û���
	* @param password ����
	*/
	public SendMail(final String username,final String password){
	//ͨ�������ַ������smtp���������Դ�������䶼����
	final String smtpHostName="smtp."+username.split("@")[1];
	init(username,password,smtpHostName);
	}
	/**
	* 
	* ��ʼ��
	* @param username �����ʼ����û���
	* @param password ����
	* @param smtpHostName SMTP������ַ
	*/
	private void init(String username, String password, String smtpHostName) {
	//��ʼ��props
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.host", smtpHostName);
	//��֤
	authenticator=new MailAuthenticator(username,password);
	//����session
	session=Session.getInstance(props,authenticator);
	session.setDebug(true);
	}
	/**
	* �����ʼ�
	* @param recipient�ռ��������ַ
	* @param subject�ʼ�����
	* @param content�ʼ�����
	* @throws MessagingException 
	* @throws AddressException 
	*/
	public void send(String recipient,String subject,Object content) throws AddressException, MessagingException{
	//����mime���͵��ʼ�
	final MimeMessage message=new MimeMessage(session);
	//���÷�����
	message.setFrom(new InternetAddress(authenticator.getUsername()));
	//�����ռ���
	message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
	//��������
	message.setSubject(subject);
	//�����ʼ�����
	message.setContent(content.toString(), "text/html;charset=utf-8");
	//����
	Transport.send(message,message.getRecipients(RecipientType.TO));
	}
	/**
	* Ⱥ���ʼ�
	* @param recipients �ռ�����
	* @param subject ����
	* @param content ����
	* @throws AddressException
	* @throws MessagingException
	*/
	public void send(List<String> recipients,String subject,Object content) throws AddressException, MessagingException{
	//����mime���͵��ʼ�
	final MimeMessage message=new MimeMessage(session);
	//���÷�����
	message.setFrom(new InternetAddress(authenticator.getUsername()));
	//�����ռ�����
	InternetAddress[] addresses=new InternetAddress[recipients.size()];
	for(int i=0;i<recipients.size();i++){
	addresses[i]=new InternetAddress(recipients.get(i));
	}
	message.setRecipients(RecipientType.TO,addresses);
	//��������
	message.setSubject(subject);
	//�����ʼ�����
	message.setContent(content.toString(), "text/html;charset=utf-8");
	//����
	Transport.send(message);
	}
	//������
}
