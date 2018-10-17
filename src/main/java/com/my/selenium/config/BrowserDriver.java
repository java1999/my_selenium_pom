package com.my.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BrowserDriver {
    public static WebDriver getFirefoxDriver(){
        System.setProperty("webdriver.firefox.bin","D:\\tools\\Mozilla Firefox\\firefox.exe");
//        geckodriver.exe下载地址：https://github.com/mozilla/geckodriver/releases
        System.setProperty("webdriver.gecko.driver","D:\\tools\\Mozilla Firefox\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
}
