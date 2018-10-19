package com.wei.utils.validation;



import com.wei.exceptions.DataValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:wei.peng
 * @Desicription:数据验证公共类
 * @Date:Created in 2017-11-26
 * @Modified By:
 */
@Component
public class DataValidation {



    public void ss(){
        try {
            chekeChinese("11","字符不是中文");
            chekeChinese("11","字符不是中文");
            chekeChinese("11","字符不是中文");
            chekeChinese("11","字符不是中文");
        }catch (DataValidationException e){
            //返回json字符串
        }
    }
    /***
     * 验证文件格式
     * @param file 上传文件
     * @param allowTypes 允许上传后缀
     * @return
     */
    public boolean isValid(MultipartFile file, String... allowTypes) {
        String fileName = file.getOriginalFilename();
        if (null == fileName || "".equals(fileName)) {
            return false;
        }
        for (String type : allowTypes) {
            if (fileName.indexOf(type) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证字符长度（最小值和最大值）
     *
     * @param str       验证的字符串
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return 成功返回true，失败返回false
     */
    public boolean chkLength(String str, int minLength, int maxLength, String errorMsg) {
        if (StringLength(str) < minLength) {
            throw new DataValidationException(errorMsg);
        }
        if (StringLength(str) > maxLength) {
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 整数
     */
    private static final String V_INTEGER = "^-?[0-9]\\d*$";

    /**
     * 正整数
     */
    private static final String V_Z_INDEX = "^[1-9]\\d*$";

    /**
     * 负整数
     */
    private static final String V_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    /**
     * 数字
     */
    private static final String V_NUMBER = "^([+-]?)\\d*\\.?\\d+$";

    /**
     * 正数
     */
    private static final String V_POSITIVE_NUMBER = "^[1-9]\\d*|0$";

    /**
     * 负数
     */
    private static final String V_NEGATINE_NUMBER = "^-[1-9]\\d*|0$";

    /**
     * 浮点数
     */
    private static final String V_FLOAT = "^([+-]?)\\d*\\.\\d+$";

    /**
     * 正浮点数
     */
    private static final String V_POSTTIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

    /**
     * 负浮点数
     */
    private static final String V_NEGATIVE_FLOAT = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

    /**
     * 非负浮点数（正浮点数 + 0）
     */
    private static final String V_UNPOSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";

    /**
     * 非正浮点数（负浮点数 + 0）
     */
    private static final String V_UN_NEGATIVE_FLOAT = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";

    /**
     * 邮件
     */
    private static final String V_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    /**
     * 颜色
     */
    private static final String V_COLOR = "^[a-fA-F0-9]{6}$";

    /**
     * url
     */
    private static final String V_URL = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    /**
     * 仅中文
     */
    private static final String V_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

    /**
     * 仅ACSII字符
     */
    private static final String V_ASCII = "^[\\x00-\\xFF]+$";

    /**
     * 邮编
     */
    private static final String V_ZIPCODE = "^\\d{6}$";

    /**
     * 手机
     */
    private static final String V_MOBILE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\\\d{8}$";

    /**
     * ip地址
     */
    private static final String V_IP4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

    /**
     * 非空
     */
    private static final String V_NOTEMPTY = "^\\S+$";

    /**
     * 图片
     */
    private static final String V_PICTURE = "(.*)\\.(jpg|bmp|ico|pcx|jpeg|tif|png|raw|tga)$";

    /**
     * 压缩文件
     */
    private static final String V_RAR = "(.*)\\.(rar|zip|7zip|tgz)$";

    /**
     * 日期
     */
    private static final String V_DATE = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";

    /**
     * 带时间的日期
     */
    private static final String V_DATETIME = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

    /**
     * QQ号码
     */
    private static final String V_QQ_NUMBER = "^[1-9]*[1-9][0-9]*$";

    /**
     * 电话号码的函数(包括验证国内区号,国际区号,分机号)
     */
    private static final String V_TEL = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    /**
     * 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     */
    private static final String V_USERNAME = "^\\w+$";

    /**
     * 字母
     */
    private static final String V_LETTER = "^[A-Za-z]+$";

    /**
     * 大写字母
     */
    private static final String V_LETTER_U = "^[A-Z]+$";

    /**
     * 小写字母
     */
    private static final String V_LETTER_I = "^[a-z]+$";

    /**
     * 身份证
     */
    private static final String V_IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**
     * 验证密码(数字和英文同时存在)
     */
    private static final String V_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

    /**
     * 验证密码长度(6-18位)
     */
    private static final String V_PASSWORD_LENGTH = "^\\d{6,18}$";

    /**
     * 验证两位数
     */
    private static final String V_TWO＿POINT = "^[0-9]+(.[0-9]{2})?$";

    /**
     * 验证一个月的31天
     */
    private static final String V_31DAYS = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
    /**
     * 支持大小写字母、数字及汉字输入
     */
    private static final String V_Number_C_E = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$";
    /**
     * 验证是不是整数
     *
     * @param value 要验证的字符串 要验证的字符串
     * @param errorMsg 需要返回的错误信息
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeInteger(String value, String errorMsg) {
        if (!match(V_INTEGER, value)) {
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是正整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeZ_index(String value, String errorMsg) {
        if (!match(V_Z_INDEX, value)) {
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是负整数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeNegative_integer(String value, String errorMsg) {
        if(!match(V_NEGATIVE_INTEGER, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是数字
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeNumber(String value,String errorMsg) {
        if(!match(V_NUMBER, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是正数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekePositiveNumber(String value,String errorMsg) {
        if(!match(V_POSITIVE_NUMBER, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是负数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeNegatineNumber(String value,String errorMsg) {
        if(!match(V_NEGATINE_NUMBER, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证一个月的31天
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeIs31Days(String value,String errorMsg) {
        if(match(V_31DAYS, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是ASCII
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeASCII(String value,String errorMsg) {
        if(match(V_ASCII, value)){
            throw new DataValidationException(errorMsg);
        }
        return  true;
    }


    /**
     * 验证是不是中文
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeChinese(String value,String errorMsg) {
        if(match(V_CHINESE, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }


    /**
     * 验证是不是颜色
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeColor(String value,String errorMsg) {
        if(!match(V_COLOR, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }


    /**
     * 验证是不是日期
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean checkDate(String value,String errorMsg) {
        if(!match(V_DATE, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是带时间的日期
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean checkDateTime(String value,String errorMsg) {
        if(match(V_DATETIME, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是邮箱地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeEmail(String value,String errorMsg) {
        if(!match(V_EMAIL, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeFloat(String value,String errorMsg) {
        if(!match(V_FLOAT, value)){
            throw new DataValidationException(errorMsg);
        }
        return  true;
    }

    /**
     * 验证是不是正确的身份证号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeIDcard(String value,String errorMsg) {
        if(!match(V_IDCARD, value)){
            throw new DataValidationException(errorMsg);
        }
        return  true;
    }

    /**
     * 验证是不是正确的IP地址
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeIP4(String value,String errorMsg) {
        if(!match(V_IP4, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeLetter(String value,String errorMsg) {
        if(match(V_LETTER, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证是不是小写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式灵域的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeLetter_i(String value,String errorMsg) {
        if(!match(V_LETTER_I, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }


    /**
     * 验证是不是大写字母
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeLetter_u(String value,String errorMsg) {
        if(match(V_LETTER_U, value)){
            throw new DataValidationException(errorMsg);
        }
        return  true;
    }


    /**
     * 验证是不是手机号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeMobile(String value,String errorMsg) {
        if(match(V_MOBILE, value)){
            throw new DataValidationException(errorMsg);
        }
        return  true;
    }

    /**
     * 验证是不是负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeNegative_float(String value,String errorMsg) {
        if(match(V_NEGATIVE_FLOAT, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证非空
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeNotempty(String value,String errorMsg) {
        if(StringUtils.isBlank(value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证密码的长度(6~18位)
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeNumber_length(String value,String errorMsg) {
        if(match(V_PASSWORD_LENGTH, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证密码(数字和英文同时存在)
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekePassword_reg(String value,String errorMsg) {
        if(match(V_PASSWORD, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证图片
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekePicture(String value,String errorMsg) {
        if(match(V_PICTURE, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekePosttive_float(String value,String errorMsg) {
        if(match(V_POSTTIVE_FLOAT, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证QQ号码
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeQQnumber(String value,String errorMsg) {
        if(match(V_QQ_NUMBER, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证压缩文件
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeRar(String value,String errorMsg) {
        if(match(V_RAR, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证电话
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeTel(String value,String errorMsg) {
        if(match(V_TEL, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证两位小数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeTwo_point(String value,String errorMsg) {
        if(match(V_TWO＿POINT, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证非正浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeUn_negative_float(String value,String errorMsg) {
        if(match(V_UN_NEGATIVE_FLOAT, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证非负浮点数
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeUnpositive_float(String value,String errorMsg) {
        if(match(V_UNPOSITIVE_FLOAT, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证URL
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeUrl(String value,String errorMsg) {
        if(match(V_URL, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeUserName(String value,String errorMsg) {
        if(match(V_USERNAME, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }

    /**
     * 验证邮编
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean chekeZipcode(String value,String errorMsg) {
        if(match(V_ZIPCODE, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }
    /**
     * 验证数字，字母，中文
     *
     * @param value 要验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public boolean cheFormat(String regex,String value,String errorMsg) {
        if(!match(regex, value)){
            throw new DataValidationException(errorMsg);
        }
        return true;
    }
    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private boolean match(String regex, String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     *
     * @param value   要检验的字符串
     * @return 返回检验的字符串字符长度
     */
    public static int StringLength(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

}
