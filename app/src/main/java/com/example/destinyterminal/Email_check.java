package com.example.destinyterminal;
import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email_check implements Runnable {
    private String email;// 收件人邮箱
    private String code;// 激活码

    public Email_check(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void run() {
        // 1.创建连接对象javax.mail.Session
        // 2.创建邮件对象 javax.mail.Message
        // 3.发送一封激活邮件
        String from = "kleeloveboom@163.com";// 发件人电子邮箱
        String host = "smtp.163.com"; // 指定发送邮件的主机smtp.163.com(网易)

        Properties properties = System.getProperties();// 获取系统属性

        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.auth", "true");// 打开认证
        properties.setProperty("mail.smtp.ssl.enable", "true");

        try {
            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("kleeloveboom@163.com", "EYMPLTXIUURHVTCC"); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.email));
            // 2.3设置邮件主题
            message.setSubject("邮箱验证");
            // 2.4设置邮件内容
            String content ="欢迎使用天命终端，这是您的验证码</br>"+this.code+"</br>"+"验证码15分钟后将失效，请尽快填入！";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}