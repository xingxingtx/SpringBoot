package com.wei.utils.excel;


import java.io.*;

/**
 * Created by Administrator on 2018/10/23.
 */
public class Test {
    public static void main(String[] args) {
        ImportExcel importExcel = new ImportExcel();
        File file = null;
        InputStream inputStream = null;
        try {
            file = new File("D:\\work\\其他文件\\2018-41周工作总结-彭伟.xlsx");
            inputStream = new FileInputStream(file);
            importExcel.readExcelContent(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
