package com.my.base.jar.smtp;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 暂时没测试成功，报错信息：com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM
 * @author michealyang
 * @version 1.0
 * @created 18/7/9
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class NetEaseMailer {
    public static void main(String [] args) {

        // 收件人电子邮箱
        String to = "michealyang@aliyun.com";

        // 发件人电子邮箱
        String from = "tinginsky@126.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.126.com";  //126邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tinginsky@126.com", "wy98808108"); //发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");

            // 设置消息体
            message.setText("This is actual message");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from runoob.com");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
