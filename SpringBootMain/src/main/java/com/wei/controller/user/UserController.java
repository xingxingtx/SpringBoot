package com.wei.controller.user;

import com.wei.config.HttpSessionListenerConfig;
import com.wei.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by wei.peng on 2018/10/19.
 */
@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${application.message:Hello World}")
    private String message ;

    @GetMapping("/asd/{name}")
    public String welcome(@PathVariable String name, Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        Utils.validation("",name,0,10,"姓名长度不超过10个字符");
        return "welcome";
    }

    @RequestMapping("/login/{name}")
    @ResponseBody
    public Object foo(@PathVariable String name,HttpServletRequest request) {
        //Utils.validation("",name,0,10,"姓名长度不超过10个字符");
        //获取当前url
        String url = request.getScheme() + "//" + request.getServerName() +":" + request.getServerPort()+"/";
        request.getLocalAddr();
        logger.info("url:" + url);
        return  "login";
    }


    @RequestMapping("/index")
    @ResponseBody
    public Object index(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("zxc", "zxc");
        return  "index";
    }

    @RequestMapping("/online")
    @ResponseBody
    public Object online() {
        return  "当前在线人数：" + HttpSessionListenerConfig.online + "人";
    }
}
