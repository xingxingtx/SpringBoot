package com.wei.controller.kaptcha;

        import java.awt.image.BufferedImage;
        import javax.imageio.ImageIO;
        import javax.servlet.ServletOutputStream;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import lombok.extern.apachecommons.CommonsLog;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import com.google.code.kaptcha.Constants;
        import com.google.code.kaptcha.Producer;
        import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wei.peng
 * @date 2018/5/4 下午12:36
 */
@CommonsLog
@Controller
public class KaptchaController{
    @Autowired
    private Producer captchaProducer;
    @RequestMapping("/getKaptchaImage")
    public String  getKaptchaImage(HttpServletResponse response, HttpSession session) throws Exception {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session
        //request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //将验证码存到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        log.info(capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return "index";
    }
}