package com.my.test;


import com.my.selenium.framework.SendEmail;
import org.testng.annotations.Test;

public class TestEmail {

    @Test
    public void sendEmail() {
        SendEmail sendEmail = new SendEmail();
        sendEmail.setEmailAddress("291829302@qq.com");
        sendEmail.setTitle("hello 2b");
        sendEmail.setFileName("application");
        sendEmail.setText("胡总");
        sendEmail.sendEmail();
    }

}
