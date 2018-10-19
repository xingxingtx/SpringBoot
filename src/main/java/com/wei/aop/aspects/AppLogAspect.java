package com.wei.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * <strong>Order</strong> 定义切面执行的优先级，数字越低，优先级越高 <br>
 * 在切入点之前执行：按order值有小到大的顺序执行  <br>
 * 在切入点之后执行：按order值由大到小的顺序执行
 */
@Component
@Aspect
@Order(-5)
public class AppLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppLogAspect.class);
    // 保证每个线程都有一个单独的实例
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(* com.wei.controller..*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")//表示这个方法是用来作为前置通知，也就是在它签名中所标识的具体方法调用之前就会进入这个方法
    public void doBefore(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求的内容
        LOGGER.info("Request URL: {}", request.getRequestURL().toString());
        LOGGER.info("Request Method: {}", request.getMethod());
        LOGGER.info("IP: {}", request.getRemoteAddr());
        LOGGER.info("User-Agent:{}", request.getHeader("User-Agent"));
        LOGGER.info("Class Method:{}", joinPoint.getSignature().getDeclaringTypeName() + "."  + joinPoint.getSignature().getName());
        LOGGER.info("Cookies:{}", request.getCookies());
        LOGGER.info("Params:{}", Arrays.toString(joinPoint.getArgs()));
        Enumeration<String> enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paraName = enums.nextElement();
            LOGGER.info(paraName + ":" + request.getParameter(paraName));
        }
    }

    @After("pointcut()")//表示这个方法是用来作为后置通知，也就是在它签名中所标识的具体方法调用之后才会进入这个方法
    public void doAfter(JoinPoint joinPoint) {
        LOGGER.info("doAfter():{}", joinPoint.toString());
    }

    @AfterReturning("pointcut()")//表示这个方法是用来作为返回通知，也就是在它签名中所标识的具体方法调用并返回之后才会进入这个方法
    public void doAfterReturning(JoinPoint joinPoint) {
        LOGGER.info("耗时 :{}", ((System.currentTimeMillis() - threadLocal.get())) + "ms");
    }
    @AfterThrowing(value = "pointcut()",throwing = "ex")//表示这个方法是用来作为异常通知，也就是在它签名中所标识的具体方法调用并出现异常之后才会进入这个方法
    public void doAfterThrowing(JoinPoint joinPoint,Exception ex) {
        LOGGER.info("异常", ex);
    }

    /**表示这个方法是用来作为环绕通知，也就是在它签名中所标识的具体方法调用会进入这个方法
     *环绕通知其实就相当于一个代理，可以在里面写上前置、后置、异常或返回等
     *环绕通知的日志方法必须要有返回值，并且以ProceedingJoinPoint pjoinpoint作为参数
     *pjoinpoint.proceed()表示执行它所受理的方法，并返回执行结果
     */
    @Around( "pointcut()")
    public void doAfterThrowing(ProceedingJoinPoint joinPoint) {
        LOGGER.info("环绕通知{}",joinPoint);
    }
}
