package com.wei.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/***
 * @Description:系统配置
 * @Author:
 * @Date: 2017/11/18
*/
public class Config {

    // Format.
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final DateFormat DATE_FORMAT_HH_MM = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    public static final DateFormat DATE_FORMAT_CH = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    public static final DateFormat DATE_FORMAT_SIMPLE = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
    public static final DateFormat DATE_FORMAT_CH_Y = new SimpleDateFormat("yyyy年", Locale.CHINA);
    public static final DateFormat DATE_FORMAT_CH_HH_MM = new SimpleDateFormat("HH:mm", Locale.CHINA);
    public static final DateFormat DATE_FORMAT_CH_Y_MM= new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
    public static final DateFormat DATE_FORMAT_YYYYMMDDHHMMSS= new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

    public static final String ATTENTION_PERSON= "attention_person";
    public static final String INVOVLE_OBSCENE= "invovle_obscene";
    public static final String INVOLVE_EVIL= "involve_evil";
    public static final String GAMBLE= "gamble";
    public static final String INVOLVE_DRUG= "involve_drug";
    public static final String INVOLVE_PROPERTY= "involve_property";
    public static final String TREASURE_PERSON="treasure_person";
    public static final  String PSYCHOSIS_PERSON = "psychosis_person";


    public static final String PROJECT_DASE_INFO="project_dase_info";

}