package com.my.selenium.pageObject;

import com.my.selenium.framework.LogType;
import com.my.selenium.framework.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

public class BasePage {

    public static WebDriver driver;
    public static String pageTitle;
    public static String pageUrl;

    /*
     * 构造方法
     */
    protected BasePage (WebDriver driver){
        BasePage.driver = driver;
    }

    /*
     * 在文本框内输入字符
     */
    protected void inputText(WebElement element , String text){
        try {
            if (element.isEnabled()) {
                element.clear();
                Logger.Output(LogType.LogTypeName.INFO, "Clean the value if any in "+ element.toString()+".");
                element.sendKeys(text);
                Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + text+".");
            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }
    }

    /*
     * 点击元素，这里指点击鼠标左键
     */
    protected void click(WebElement element){
        try {
            if (element.isEnabled()) {
                element.click();
                Logger.Output(LogType.LogTypeName.INFO, "Element: "+element.toString()+" was clicked.");
            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }
    }

    /*
     * 在文本输入框执行清除操作
     */
    protected void clean(WebElement element){
        try {
            if (element.isEnabled()) {
                element.clear();
                Logger.Output(LogType.LogTypeName.INFO, "Element "+element.toString()+" was cleaned.");
            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }

    }

    /*
     * 判断一个页面元素是否显示在当前页面
     */
    protected void verifyElementIsPresent(WebElement element){
        try {
            if (element.isDisplayed()) {
                Logger.Output(LogType.LogTypeName.INFO, "This Element " + element.toString().trim()+" is present.");

            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }
    }

    /*
     * 获取页面的标题
     */
    protected String getCurrentPageTitle(){
        pageTitle=driver.getTitle();
        Logger.Output(LogType.LogTypeName.INFO, "Current page title is "+pageTitle);
        return pageTitle;
    }

    /*
     * 获取页面的url
     */
    protected String getCurrentPageUrl(){
        pageUrl=driver.getCurrentUrl();
        Logger.Output(LogType.LogTypeName.INFO, "Current page title is "+pageUrl);
        return pageUrl;
    }

    /*
     * 处理多窗口之间切换
     */
    public void switchWindow() {
        String currentWindow = driver.getWindowHandle();// 获取当前窗口句柄
        Set<String> handles = driver.getWindowHandles();// 获取所有窗口句柄
        Logger.Output(LogType.LogTypeName.INFO, "当前窗口数量： " + handles.size());
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            if (currentWindow == it.next()) {
                continue;
            }
            try {
                driver.close();// 关闭旧窗口
                WebDriver window = driver.switchTo().window(it.next());// 切换到新窗口
                Logger.Output(LogType.LogTypeName.INFO, "new page title is " + window.getTitle());
            } catch (Exception e) {
                Logger.Output(LogType.LogTypeName.ERROR, "法切换到新打开窗口" + e.getMessage());
            }
            //driver.close();//关闭当前焦点所在的窗口
        }
        // driver.switchTo().window(currentWindow);//回到原来页面
    }
}

