package com.my.selenium.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelData_1 {
    private String fileName;
    private String sheetName;
    private Workbook workbook;
    private Sheet sheet;
    private int rows;
    private int columns;

    public ExcelData_1(String fileName, String sheetName){
        this.fileName = fileName;
        this.sheetName = sheetName;
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

    public HashMap<String, String>[] getExcelData() throws IOException, BiffException {
        getDrive();
        HashMap<String, String>[] arrMap = new HashMap[rows - 1];
        if(rows >= 1) {
            for (int i = 0; i < rows - 1; i++) {
                arrMap[i] = new HashMap<String, String>();
            }
            ArrayList<String> list = new ArrayList<String>();
            for(int c = 0; c < columns; c++){
                String cellvalue = sheet.getCell(c, 0).getContents();
                list.add(cellvalue);
            }
            for(int r = 1; r < rows; r++){
                for(int c = 0; c < columns; c++){
                    String cellvalue = sheet.getCell(c, r).getContents();
                    arrMap[r - 1].put(list.get(c),cellvalue);
                }
            }
        }else{
            System.out.print("表中没有数据");
        }
        return arrMap;
    }

    public HashMap<String, String> getRowData(int x) throws IOException, BiffException {
        HashMap<String, String>[] arrMap = getExcelData();
        HashMap<String, String> hashMap = null;
        if (x < arrMap.length){
            hashMap = arrMap[x - 1];
        }else {
            throw new RuntimeException("没有这一行");
        }
        return hashMap;
    }

    public String getValue(int row, String column) throws IOException, BiffException {
        HashMap<String, String>[] arrMap = getExcelData();
        String value = null;
        if (row < arrMap.length){
            if (arrMap[0].containsKey(column)){
                value = arrMap[row - 1].get(column);
            }else {
                throw new RuntimeException("没有这个数据");
            }
        }else {
            throw new RuntimeException("没有这一行");
        }
        return value;
    }

}
