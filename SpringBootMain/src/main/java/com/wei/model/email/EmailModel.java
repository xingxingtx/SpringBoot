package com.wei.model.email;

import com.wei.model.base.BaseModel;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Administrator on 2018/10/25.
 */
@ToString
@Data
public class EmailModel extends BaseModel {
    //邮件发送人
    private String sender;
    //邮件接收人
    private String  recipients;
    //邮件主题
    private String subject;
    //邮件内容
    private String text;


}
