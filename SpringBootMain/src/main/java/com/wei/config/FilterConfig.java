package com.wei.config;

/**
 * 过滤器
 * Created by wei.peng on 2018/10/19.
 */
public class FilterConfig /*implements Filter*/{
/*    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest.getParameter("name"));
        HttpServletRequest hrequest = (HttpServletRequest)servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        if(hrequest.getRequestURI().indexOf("/index") != -1 ||
                hrequest.getRequestURI().indexOf("/asd") != -1 ||
                hrequest.getRequestURI().indexOf("/online") != -1 ||
                hrequest.getRequestURI().indexOf("/login") != -1 ||
                hrequest.getRequestURI().indexOf("/email") != -1 ||
                hrequest.getRequestURI().indexOf("/getKaptchaImage") != -1||
                hrequest.getRequestURI().indexOf("/swagger-ui.html#/") != -1
                ) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            wrapper.sendRedirect("/login");
        }
    }
    @Override
    public void destroy() {
    }*/

}
