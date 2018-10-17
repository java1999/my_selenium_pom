package com.my.selenium.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelData_2 {

    private String fileName;
    private String caseName;

    private Workbook workbook;
    private Sheet sheet;
    private int rows;
    private int columns;
    private String sourceFile;

    /**
     * @param fileName   excel文件名
     * @param caseName   sheet名
     */
    public ExcelData_2(String fileName, String caseName) {
        super();
        this.fileName = fileName;
        this.caseName = caseName;
    }
    /**
     * 获得excel表中的数据
     */
    public Object[][] getExcelData() throws BiffException, IOException {

        workbook = Workbook.getWorkbook(new File(getPath()));
        sheet = workbook.getSheet(caseName);
        rows = sheet.getRows();
        columns = sheet.getColumns();
        // 为了返回值是Object[][],定义一个多行单列的二维数组
        @SuppressWarnings("unchecked")
        HashMap<String, String>[][] arrmap = new HashMap[rows - 1][1];
        // 对数组中所有元素hashmap进行初始化
        if (rows > 1) {
            for (int i = 0; i < rows - 1; i++) {
                arrmap[i][0] = new HashMap<String, String>();
            }
        } else {
            System.out.println("excel中没有数据");
        }
        //定义集合存储hashmap的key值
        ArrayList<String> arrkey = new ArrayList<String>();

        // 获得首行的列名，作为hashmap的key值
        for (int c = 0; c < columns; c++) {
            String cellvalue = sheet.getCell(c, 0).getContents();
            arrkey.add(cellvalue);
        }
        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                String cellvalue = sheet.getCell(c, r).getContents();
                arrmap[r - 1][0].put(arrkey.get(c), cellvalue);
            }
        }
        return arrmap;
    }

    /**
     * 获得excel文件的路径
     * @return
     * @throws IOException
     */
    public String getPath() throws IOException {
        File directory = new File(".");
        sourceFile = directory.getCanonicalPath() + "\\src\\main\\resources\\excel\\"
                + fileName + ".xls";
        return sourceFile;
    }

}
