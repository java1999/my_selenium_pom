package com.my.selenium.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class QQEmail {

    private String fileName;
    private String emailAddress;
    private String title;
    private String text;
    private Multipart multipart = null;
    private BodyPart messageBodyPart1 = null;
    private MimeBodyPart messageBodyPart2 = null;

//    三个构造方法
    public QQEmail() {}
    public QQEmail(String fileName) {
        this.fileName = fileName;
    }
    public QQEmail(String fileName, String emailAddress) {
        this.fileName = fileName;
        this.emailAddress = emailAddress;
    }
//    手动设置要发送的附件名称及要发送到的邮箱及发送内容
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void sendEmail() {
        // 创建一个Property文件对象
        Properties props = new Properties();

        // 设置邮件服务器的信息，这里设置smtp主机名称
        props.put("mail.smtp.host", "smtp.qq.com");

        // 设置socket factory 的端口
        props.put("mail.smtp.socketFactory.port", "465");

        // 设置socket factory
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        // 设置需要身份验证
        props.put("mail.smtp.auth", "true");

        // 设置SMTP的端口，QQ的smtp端口是25
        props.put("mail.smtp.port", "25");

        // 身份验证实现
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 第二个参数，就是我QQ开启smtp的授权码
                return new PasswordAuthentication("3555089657@qq.com", "joxlnjnktdruchaj");
            }
        });

        try {

            // 创建一个MimeMessage类的实例对象
            Message message = new MimeMessage(session);

            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress("3555089657@qq.com"));

            // 设置收件人邮箱地址+
            if(emailAddress != "" && emailAddress != null) {
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailAddress));
            }else {
                //可以填一个默认收件地址
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("3555089657@qq.com"));
            }

            // 设置邮件主题
            message.setSubject(title);

            // 创建一个MimeMultipart类的实例对象
            multipart = new MimeMultipart();

            if((text != null) || (fileName != null)) {
                if(text != null) {
                    // 创建一个MimeBodyPart的对象，以便添加内容
                    messageBodyPart1 = new MimeBodyPart();
                    // 设置邮件正文内容
                    messageBodyPart1.setText(text);
                    // 添加正文1内容
                    multipart.addBodyPart(messageBodyPart1);
                }
                if(fileName != null) {
                    // 创建另外一个MimeBodyPart对象，以便添加其他内容，这里添加一个附件
                    messageBodyPart2 = new MimeBodyPart();
                    // 设置邮件中附件文件的路径
                    String filename = ".\\src\\test\\resources\\log" + "\\" + fileName +".html";
                    // 创建一个datasource对象，并传递文件
                    DataSource source = new FileDataSource(filename);
                    // 设置handler
                    messageBodyPart2.setDataHandler(new DataHandler(source));
                    // 加载文件
                    messageBodyPart2.setFileName(filename);
                    // 添加正文2即附件内容
                    multipart.addBodyPart(messageBodyPart2);
                }
                // 设置内容
                message.setContent(multipart);
            }

            //没写正文，想发送邮件，添加默认正文内容，如果想必须实现添加正文的内容，请抛异常。
            if(messageBodyPart1 == null) {
                // MineMessage的content不能为空，所以创建一个MimeBodyPart的对象，以便添加默认的内容
                messageBodyPart1 = new MimeBodyPart();
                // 设置邮件正文内容
                messageBodyPart1.setText("这里什么也没写，联系管理员");
                // 添加正文1内容
                multipart.addBodyPart(messageBodyPart1);
                // 设置内容
                message.setContent(multipart);
            }

            // 最终发送邮件
            Transport.send(message);

            System.out.println("=====邮件已经发送=====");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
