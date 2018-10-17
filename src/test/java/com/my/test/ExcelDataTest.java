package com.my.test;

import com.my.selenium.utils.ExcelData_1;
import com.my.selenium.utils.ExcelData_2;
import com.my.selenium.utils.ExcelData_3;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ExcelDataTest {
    @Test
    public void testExcelData_1() throws IOException, BiffException {
        ExcelData_1 excelData_1 = new ExcelData_1("test", "Sheet1");
        HashMap<String, String>[] hashMap = excelData_1.getExcelData();
        for(int i = 0; i < hashMap.length; i++){
            System.out.println(hashMap[i]);
        }
        System.out.println(excelData_1.getRowData(1));
        System.out.println(excelData_1.getRowData(2));
        String x = excelData_1.getValue(1, "mobile");
        System.out.println(x);
    }

    @Test
    public void testExcelDate_2() throws IOException, BiffException {
        ExcelData_2 excelData_2 = new ExcelData_2("test","Sheet1");
        Object[][] arr = excelData_2.getExcelData();
        for(int i = 0; i < arr.length; i++){
            HashMap<String,String> hs = (HashMap<String,String>)arr[i][0];
            System.out.println(hs);
        }
    }

    @Test
    public void testExcelDate_3() throws IOException, BiffException {
        ExcelData_3 excelData_3 = new ExcelData_3("test","Sheet1");
        HashMap<String, HashMap<String, String>> hashMap = excelData_3.getExcelData();
        System.out.println(hashMap);
    }

//    HashMap的基本用法
    @Test
    public void testHashMap(){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("cn", "中国");
        hashMap.put("jp", "日本");
        hashMap.put("fr", "法国");

        System.out.println(hashMap);
        System.out.println("****");
        System.out.println("cn:" + hashMap.get("cn"));
        System.out.println(hashMap.containsKey("cn"));

        System.out.println(hashMap.keySet());
        System.out.println(hashMap.isEmpty());

        hashMap.remove("cn");
        System.out.println(hashMap);
        //采用Iterator遍历HashMap
        Iterator it = hashMap.keySet().iterator();
        while(it.hasNext()) {
            String key = (String)it.next();
            System.out.println("key:" + key);
            System.out.println("value:" + hashMap.get(key));
        }

        //遍历HashMap的另一个方法
        Set<Map.Entry<String, String>> sets = hashMap.entrySet();
        for(Map.Entry<String, String> entry : sets) {
            System.out.print(entry.getKey() + ", ");
            System.out.println(entry.getValue());
        }
    }
}
