package com.wei.utils.excel;


import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/23.
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        //hutool 工具类封装调用的方法读取excel
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("D:\\work\\其他文件\\2018-41周工作总结-彭伟.xlsx"));
        List<List<Object>> readAll = reader.read();

        //调用自己定义的readExcelContent方法读取excel数据
        InputStream inputStream = new FileInputStream(new File("D:\\work\\其他文件\\2018-41周工作总结-彭伟.xlsx"));
        ImportExcel importExcel = new ImportExcel();
        List<ArrayList<String>> arrayLists = importExcel.readExcelContent(inputStream, 2);
        
        
    }
}
