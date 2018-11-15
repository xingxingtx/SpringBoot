package com.wei.utils;


import com.wei.utils.validation.DataValidation;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统工具类
 * Create by tanshaoxing on 2018-07-19.
 * @author Administrator
 */
public class Utils {
    //======================================== 基础工具 ========================================

    /**
     * 根据时间戳和格式参数返回格式字符串
     */
    public static String currentDateTime(int delaySeconds, String formatStr) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, delaySeconds);
        DateFormat DATE_FORMAT = new SimpleDateFormat(formatStr, Locale.CHINA);
        return DATE_FORMAT.format(now.getTime());
    }

    /**
     * MD5转码
     */
    public static String getMd5DigestAsHex(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }


    /**
     * Escape " for shell command.
     */
    public static String escapeShell(String s) {
        return s.replace("\"", "\\\"");
    }


    /**
     * 将时间格式字符串按格式转换成日期
     * add by yanggen 20180818
     */
    public static Date strToDate(String strDate, DateFormat formatter) {
        Date strtodate = null;
        try {
            strtodate = formatter.parse(strDate);
        } catch (ParseException e) {
            strtodate = null;
        }
        return strtodate;
    }

    /**
     * 将时间按格式转换成字符串
     * add by yanggen 20180818
     */
    public static String dateToStr(Date date, DateFormat formatter) {
        String dateStr;
        try {
            dateStr = formatter.format(date);
        } catch (Exception e) {
            dateStr = null;
        }
        return dateStr;
    }

    public static Date getCurrDateBefore(int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    //删除字符末尾零
    public static String delLastZero(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String tempStr = str;
        if (str.length() - 1 == str.lastIndexOf("0")) {
            tempStr = str.substring(0, str.length() - 1);
            tempStr = delLastZero(tempStr);
        }
        return tempStr;
    }

    /**
     * 产生4位随机数(0000-9999)
     * create by zhgp
     * @return 4位随机数
     */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++) {
                fourRandom = "0" + fourRandom;
            }
        }
        return fourRandom;
    }

    /**
     * 图片转换成base64 字符串
     * @param bufferedImage
     * @return
     */
    public static String encodeImgageToBase64( BufferedImage bufferedImage) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
//        // 对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }
    //加密
    public static String getBase64(String str){
        byte[] b=null;
        String s=null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(b!=null){
            s=new BASE64Encoder().encode(b);
        }
        return s;
    }
    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
   //查询快递名称
    public static String GetCourier(String code){
        switch (code) {
            case "SF": return "顺丰速运";
            case "HTKY": return "百世快递";
            case "ZTO": return "中通快递";
            case "STO": return "申通快递";
            case "YD": return "韵达速递";
            case "YZPY": return "邮政快递包裹";
            case "EMS": return "EMS";
            case "HHTT": return "天天快递";
            case "JD": return "京东";
            case "QFKD": return "全峰快递";
            case "GTO": return "国通快递";
            case "UC": return "优速快递";
            case "DBL": return "德邦";
            case "FAST": return "快捷快递";
            case "ZJS": return "宅急送";
            case "YTO": return "圆通速递";
            default:
                return "";

        }

    }
    //去除时间末尾0
    public static String DelTimeO(String time){
        if(!StringUtils.isBlank(time)) {
            time = time.substring(0, time.length() - 2);
        }
        return time;
    }

    /**
     * 获取当前时间
     * @return 返回当前时间字符串
     */
    public static String getCurrentTime(String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date()).toString();
    }
    /**
     * 根据数据库返回的0和1返回是和否
     * 方法名：CheckStringByInt
     * 方法返回类型：返回是活否
     */
    public static String CheckStringByInt(Integer num){
        if(num==0){
            return "否";
        }else if(num==1){
            return "是";
        }
        return "";
    }



    /**
     * 获取年龄段
     * @param ageGroup 所选年龄段
     * @return
     */
    public String getAgeStr(String ageGroup){
        StringBuilder paramSql=new StringBuilder();
        if (!StringUtils.isBlank(ageGroup)) {
            switch (Integer.parseInt(ageGroup)) {
                case 1:
                    paramSql.append(" and age !=0 and a.age<14  ");
                    break;
                case 2:
                    paramSql.append(" and a.age> 13 and a.age< 16  ");
                    break;
                case 3:
                    paramSql.append(" and a.age> 15 and a.age< 18 ");
                    break;
                case 4:
                    paramSql.append(" and a.age> 17 and a.age< 25 ");
                    break;
                case 5:
                    paramSql.append(" and a.age> 24 and a.age< 35 ");
                    break;
                case 6:
                    paramSql.append(" and a.age> 34 and a.age< 45 ");
                    break;
                case 7:
                    paramSql.append(" and a.age> 44 and a.age< 55 ");
                    break;
                case 8:
                    paramSql.append(" and a.age> 54 and a.age< 70 ");
                    break;
                case 9:
                    paramSql.append(" and a.age > 70 ");
                    break;
                default:
                    break;
            }
        }
        return paramSql.toString();
    }
    /**
     * 判断两个字符串是否相同
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isSame(String str1, String str2) {
        if (str1 != null) {
            if (str2 != null) {
                String[] str1s = str1.split(",");
                String[] str2s = str2.split(",");
                if (str1s.length > 1 || str2s.length > 1) {
                    if (str1s.length > str2s.length) {
                        for (int i = 0; i < str1s.length; i++) {
                            if (!Arrays.asList(str2s).contains(str1s[i])) {
                                return false;
                            }
                        }
                    } else {
                        for (int i = 0; i < str2s.length; i++) {
                            if (!Arrays.asList(str1s).contains(str2s[i])) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            if (StringUtils.isBlank(str1)&&StringUtils.isBlank(str2)) {
                return true;
            }
            return str1.equals(str2);
        } else if ("".equals(str2)) {
            return true;
        } else {
            return str1 == str2;
        }
    }

    public static String getEvilDrugType(Integer type){
        if(type!=null) {
            if (type == 1 || type == 2 || type == 3 || type == 4) {
                return "新型毒品";
            } else if (type == 5 || type == 6 || type == 7 || type == 8) {
                return "传统毒品";
            }
        }
        return "";
    }
    public static String getDrugType(Integer type){
        if(type!=null) {
            if (type == 1) {
                return "新型毒品";
            } else if (type == 2) {
                return "传统毒品";
            }
        }
        return "";
    }

    public static String getEvilDrugName(Integer type) {
        if (type != null) {
            switch (type) {
                case 1:
                    return "冰毒";
                case 2:
                    return "麻古";
                case 3:
                    return "摇头丸";
                case 4:
                    return "K粉";
                case 5:
                    return "鸦片";
                case 6:
                    return "海洛因";
                case 7:
                    return "吗啡";
                case 8:
                    return "大麻";
                default:
                    break;
            }
        }
        return "";
    }

    public static String getFile(HttpServletRequest rq, String imgURL, String fileName){
        /**本机测试的时候 */
           String realPath = rq.getSession().getServletContext()
                   .getRealPath("files/");
        /**部署到服务器的时候 */
//        String realPath = imgURL;
        String newName = realPath + fileName + ".xlsx";
        deleteFile(newName);
        return  realPath;//savepath
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */

    public static   boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String getString(String pram){
        if(pram==null){
            return "";
        }
        return pram;
    }

    /**
     * 比较两个时间之间相差几个月
     * @param startDate 开始时间
     * @param endData 结束时间
     * @return
     */
    public static int monthsBetween(String startDate,String endData){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM");
        DateTime start = formatter.parseDateTime(startDate);
        DateTime end = formatter.parseDateTime(endData);
        int months = Months.monthsBetween(start, end).getMonths();
        return months;
    }

    /***
     * 获取上一个月份
     * @param date
     * @return
     */
    public static String preMonths(String date){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
        String retDate="";
        try {
            Date currdate = sd.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currdate);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            retDate= sd.format(calendar.getTime());
        } catch (ParseException e) {
           e.printStackTrace();
        }
        return retDate;
    }


    /***
     * 时间间隔内所有的月份
     * by zhgp 2018-9-27
     * @param minDate 开始时间
     * @param maxDate 结束时间
     * @return list
     * EXCEPTION
     */
    public static String[] getMonthBetween(String minDate, String maxDate) throws ParseException {

        List<String> times = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        if (!StringUtils.isBlank(minDate) || !StringUtils.isBlank(maxDate)) {//不能全为空
            if (StringUtils.isBlank(minDate)) {//if 开始时间为空
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(maxDate));//set最后时间
                c.add(Calendar.MONTH, -6);//取之前六个月
                min.setTime(c.getTime());
            } else {
                min.setTime(sdf.parse(minDate));
            }
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
            if (StringUtils.isBlank(maxDate)) {//if 结束时间为空 则取当前时间
                max.setTime(new Date());
            } else {
                max.setTime(sdf.parse(maxDate));
            }
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        } else {//否则取当前时间  到之前六个月
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());//set最后时间
            c.add(Calendar.MONTH, -6);//取之前六个月
            min.setTime(c.getTime());
            max.setTime(new Date());
        }
        Calendar curr = min;
        while (curr.before(max)) {
            times.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return times.toArray(new String[times.size()]);
    }

   /* public static void main(String[] arg)throws ParseException{
       Arrays.asList(Utils.getMonthBetween("","")).stream().forEach(System.out::println);
    }*/


    /**
     * 字符格式和长度验证
     * @param date 要验证的数据()
     * @param msg 提示信息
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @param regex 验证格式的正则
     * @return JsonResponseData
     */
    public static String validation(String regex,String date,int minLength, int maxLength, String msg){
        DataValidation validation = new DataValidation();
        if(!StringUtils.isBlank(date)){
                if(!StringUtils.isBlank(regex)) {
                    validation.cheFormat(regex, date, msg);
                }
                validation.chkLength(date,minLength,maxLength,msg);
        }
        return null;
    }


}