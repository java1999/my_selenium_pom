package com.my.test;

import com.my.selenium.config.BrowserDriver;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;

import java.io.IOException;

public class BrowserDriverTest {
    @Test
    public void test1() throws IOException, BiffException {
        Logger logger = Logger.getLogger("baidu");
        PropertyConfigurator.configure(".\\src\\main\\resources\\log\\log4j.properties");
        WebDriver driver = BrowserDriver.getFirefoxDriver();
        logger.info("启动浏览器");
        driver.get("https://www.baidu.com");
        logger.info("打开百度首页");
        driver.findElement(By.id("kw")).sendKeys("Selenium");
        logger.info("在搜索输入框输入selenium");
//        System.out.println("当前打开的网页是："+ driver.getTitle());
        driver.quit();
    }
}
