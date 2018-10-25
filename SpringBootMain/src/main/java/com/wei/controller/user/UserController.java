package com.wei.controller.user;

import com.wei.config.HttpSessionListenerConfig;
import com.wei.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * Created by wei.peng on 2018/10/19.
 */
@Api("用户模块")
@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private JavaMailSender mailSender; //自动注入的Bean

    @Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数
    /**
     * 邮件发送功能
     * @param
     * @param
     * @return
     */
    @RequestMapping("/api/email")
    @ApiOperation(value = "用户发送邮件操作", httpMethod = "GET", response = String.class, notes = "用户发送邮件操作，提供用户管理-用户发送邮件操作")
    public String sendEmail(
            @ApiParam(value = "接受者邮箱",required = true) @RequestParam(value = "recipients") String recipients,
            @ApiParam(value = "邮件主题",required = true) @RequestParam(value = "subject") String subject,
            @ApiParam(value = "邮件内容",required = true) @RequestParam(value = "text") String text,

            HttpServletRequest request) {
        logger.info("email:"+sender );
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            //helper.setTo("317181792@qq.com");
            helper.setTo(sender);
            helper.setSubject("主题：带附件的邮件");
            helper.setText("带附件的邮件内容");
            String rscId = "picture";
            helper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:picture' style = 'width:600px;height:300px'/></body></html>", true);
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File("E:\\aaaProjrct\\springboot\\picture.jpg"));
            //加入邮件
            helper.addInline(rscId, file);
            helper.addAttachment("beautiful girl.jpg", file);
        } catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
        return  "login";
    }

    @RequestMapping("/api/online")
    @ResponseBody
    public Object online() {
        return  "当前在线人数：" + HttpSessionListenerConfig.online + "人";
    }



}
