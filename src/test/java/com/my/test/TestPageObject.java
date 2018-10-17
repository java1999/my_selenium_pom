package com.my.test;

import com.my.selenium.config.BrowserDriver;
import com.my.selenium.pageObject.GoodsDetailsPage;
import com.my.selenium.pageObject.HomePage;
import com.my.selenium.pageObject.PageFactory;
import com.my.selenium.pageObject.SearchResultListPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPageObject {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = BrowserDriver.getFirefoxDriver();
    }


    @Test
    public void checkPrice() throws InterruptedException{
        driver.get("https://www.jd.com");
        HomePage homepage = PageFactory.initElements(driver, HomePage.class);
        homepage.searchWithKeyword("iPhone");

        //进入搜索结果列表页
        SearchResultListPage srlp = PageFactory.initElements(driver, SearchResultListPage.class);
        Thread.sleep(1000);
        String price1 = srlp.getGoodsPriceOnListPage(); // 获取列表页商品的价格
        System.out.println(price1);
        srlp.clickItemImg();
        srlp.switchWindow(); // 调用窗口切换方法

        //进入商品详情页
        GoodsDetailsPage gdp = PageFactory.initElements(driver, GoodsDetailsPage.class);
        Thread.sleep(1000);
        String price2 = gdp.getPriceOnDetailsPage(); //获取商品详情页价格
        System.out.println(price2);

        //判断 同一个商品在列表页和详情页价格是否显示一致
//		if(price1 == price2){
//			System.out.println("Pass");
//		}else
//			System.out.println("Failed");
//

        // 第二种断言
        Assert.assertEquals(price2, price1);
        gdp.addGoodToCart(); // 添加到购物车
    }

    //如果需要看清楚结果，把@AfterClass注销就好
    @AfterClass
    public void tearDown(){

        driver.quit();
    }

}
