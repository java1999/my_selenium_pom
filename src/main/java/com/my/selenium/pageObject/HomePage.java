package com.my.selenium.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    /*
     * 京东商城的首页，主要提供一些其他子模块或者页面的入口，一般点击一个元素，进入下一页面
     */

    // 元素定位
    //搜索输入框
    @FindBy (id="key")
    WebElement search_inputBox;

    //搜索提交按钮
    @FindBy (xpath="//*[@id='search']/div/div[2]/button")
    WebElement search_submitBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /*
     * 搜索框输入关键字，点击搜索
     */
    public SearchResultListPage searchWithKeyword(String keyword){
        inputText(search_inputBox, keyword);
        click(search_submitBtn);
        return new SearchResultListPage(driver);
    }

}
