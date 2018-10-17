package com.my.selenium.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ExcelData_3 {
    private String fileName;
    private String sheetName;
    private Workbook workbook;
    private Sheet sheet;
    private int rows;
    private int columns;

    public ExcelData_3(String fileName, String sheetName){
        this.fileName = fileName;
        this.sheetName = sheetName;
    }

    public HashMap<String, HashMap<String, String>> getExcelData() throws IOException, BiffException {
        getDrive();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap<String, HashMap<String, String>>();
        if(rows >= 1){
            for(int i = 1; i < rows; i++){
                hashMap.put(""+i, new HashMap<String, String>());
            }

            for(int r = 1; r < rows; r++){
                for(int c = 0; c < columns; c++){
                    String cellvalue_columns = sheet.getCell(c, 0).getContents();
                    String cellvalue = sheet.getCell(c, r).getContents();
                    hashMap.get(""+r).put(cellvalue_columns, cellvalue);
                }
            }

        }else{
            System.out.println("表中没有数据");
        }
        return hashMap;
    }

    public void getDrive() throws IOException, BiffException {
        workbook = Workbook.getWorkbook(new File(getPath()));
        sheet = workbook.getSheet(sheetName);
        rows = sheet.getRows();
        columns = sheet.getColumns();
    }

    public String getPath() throws IOException {
        File directory = new File(".");
        String sourceFile = directory.getCanonicalPath() + "\\src\\main\\resources\\excel\\"
                + fileName + ".xls";
        return sourceFile;
    }

}
