package com.wei.utils.excel;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author:wei.peng
 * @Desicription:ExcelUtil工具类
 * @Date:Created in 2017-08-16 16:41
 * @Modified By:wei.peng
 */
public class ExcelUtil {
    public static String DEFAULT_DATE_PATTERN="yyyy年MM月dd日";//默认日期格式
    public static int DEFAULT_COLOUMN_WIDTH = 17;


    /**
     * 导出Excel 2007 OOXML (.xlsx)格式
     * @param title 标题行
     * @param headMap 属性-列头
     * @param jsonArray 数据集
     * @param arraysName 数据集中包含的数组名称集合  譬如  一个对象中包含  list1  list2  则为  {"list1","list2"}
     * @param datePattern 日期格式，传null值则默认 年月日
     * @param colWidth 列宽 默认 至少17个字节
     */
    public static void exportExcelX(String title, Map<String, String> headMap, JSONArray jsonArray, String datePattern, int colWidth, OutputStream out, String[] arraysName) {
        if(datePattern==null) datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);//缓存
        workbook.setCompressTempFiles(true);
        // 生成一个表格标题行样式
        CellStyle  headerStyle  = getColumnTopStyle(workbook);
        // 生成非标题样式
        CellStyle cellStyle  = getColumnStyle(workbook);
        // 生成一个(带标题)表格
        SXSSFSheet sheet = workbook.createSheet();
        //设置列宽
        int minBytes = colWidth<DEFAULT_COLOUMN_WIDTH?DEFAULT_COLOUMN_WIDTH:colWidth;//至少字节数
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter
                .hasNext();) {
            String fieldName = iter.next();

            properties[ii] = fieldName;
            headers[ii] = headMap.get(fieldName);

            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] =  bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii,arrColWidth[ii]*256);
            ii++;
        }
        // 遍历集合数据，产生数据行
        int rowIndex = 0;
        for (Object obj : jsonArray) {
            if(rowIndex == 65535 || rowIndex == 0){
                if ( rowIndex != 0 ) sheet = workbook.createSheet();//如果数据超过了，则在第二页显示

                SXSSFRow titleRow = sheet.createRow(0);//表头 rowIndex=0
                titleRow.createCell(0).setCellValue(title);
                titleRow.getCell(0).setCellStyle(cellStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));

                SXSSFRow headerRow = sheet.createRow(1); //列头 rowIndex =1
                for(int i=0;i<headers.length;i++)
                {
                    headerRow.createCell(i).setCellValue(headers[i]);
                    headerRow.getCell(i).setCellStyle(headerStyle);
                }
                rowIndex = 2;//数据内容从 rowIndex=2开始
            }
            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            SXSSFRow dataRow = sheet.createRow(rowIndex);
            for (int i = 0; i < properties.length; i++)
            {
                SXSSFCell newCell = dataRow.createCell(i);
                Object o =  jo.get(properties[i]);
                if(o instanceof JSONArray){//如果是数组
                    //获取子数组,写入子数组
                    if(arraysName!=null&&arraysName.length>0) {
                        for (String arrayName:arraysName){
                            JSONArray array=((JSONObject)o).getJSONArray(arrayName);
                            for(int j=0;j<array.size();j++) {
                                if(array.size()>2&&j>0){
                                    dataRow=sheet.createRow(++rowIndex);
                                }
                                String cellValue = "";
                                if (array.get(j) == null) cellValue = "";
                                else if (array.get(j) instanceof Date) cellValue = new SimpleDateFormat(datePattern).format(array.get(j));
                                else if (array.get(j) instanceof Float || array.get(j) instanceof Double)
                                    cellValue = new BigDecimal(array.get(j).toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                                else cellValue = array.get(j).toString();
                                newCell.setCellValue(cellValue);
                                newCell.setCellStyle(cellStyle);
                            }

                        }
                    }

                }else {
                    /*sheet.addMergedRegion(new CellRangeAddress(i, i + getMergeLenth((JSONObject) o, arraysName),
                            properties.length - 1, properties.length - 1));//根据最长数组长度合并单元格*/
                    String cellValue = "";
                    if (o == null) cellValue = "";
                    else if (o instanceof Date) cellValue = new SimpleDateFormat(datePattern).format(o);
                    else if (o instanceof Float || o instanceof Double)
                        cellValue = new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                    else cellValue = o.toString();

                    newCell.setCellValue(cellValue);
                    newCell.setCellStyle(cellStyle);
                }
            }
            rowIndex++;
        }

        try {
            workbook.write(out);
            workbook.close();
            workbook.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //对象中获取最长的数组长度
    private static int getMergeLenth(JSONObject jo, String[] arraysName){
        int i=0;
        if(arraysName!=null&&arraysName.length>0) {
            for (String arrayName:arraysName){
                JSONArray array=jo.getJSONArray(arrayName);
                if (array.size()>i){
                    i=array.size();
                }
            }
        }
        return i;
    }

    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");


    /**
     * 导出下载,弹出下载框
     * @param response
     */
    public static void download(String title, Map<String,String> headMap, JSONArray ja, HttpServletResponse response, String[] arraysName) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ExcelUtil.exportExcelX(title,headMap,ja,null,0,os,arraysName);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((title + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);

            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**

     * 清除文件

     * @param docsPath  文件路径

     */
    public static  void cleanFile(String docsPath) {
        File file = new File(docsPath);
        file.delete();
    }

    /**
     * 创建目录
     * @param destDirName
     * @return  true 存在目录  false 不存在目录
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return true;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    /*
 * 列头单元格样式
 */
    public static CellStyle  getColumnTopStyle(SXSSFWorkbook workbook) {
        // 设置样式;
        CellStyle  style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 设置字体
        Font  font = workbook.createFont();
        // 设置字体颜色
        font.setColor(HSSFColor.VIOLET.index);
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 在样式用应用设置的字体;
        style.setFont(font);

        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    public static CellStyle  getColumnStyle(SXSSFWorkbook workbook) {
        // 设置样式;
        CellStyle  style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 设置字体
        Font  font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 设置字体名字
        font.setFontName("Courier New");
        // 在样式用应用设置的字体;
        style.setFont(font);

        return style;
    }
}
