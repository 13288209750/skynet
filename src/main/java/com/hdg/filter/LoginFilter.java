package com.hdg.filter;

import com.hdg.entity.login.User;
import com.hdg.util.other.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 主要用于拦截没有登录的用户访问需要登录的资源
 * Created by BlueBuff on 2017/7/15.
 */
public class LoginFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("登录验证过滤器初始化中....当前免登录可直接访问的资源有:size[{}]",ConfigUtil.getUrlSetSize());
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /***当项目是以开发者模式启动，全部放行,否则，按生产模式执行***/
        if("true".equals(ConfigUtil.getConfigInfo("DEVELOPER"))){
            //全部放行
            filterChain.doFilter(request,response);
            return;
        }
        String requestURI = request.getRequestURI();
        requestURI = URLDecoder.decode(requestURI, "utf-8");
        if (logger.isInfoEnabled()) {
            logger.info("requestURI:{}", requestURI);
        }
        /**不需要登录即可访问的资源**/
        if (ConfigUtil.isIgnoreUrl(requestURI)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        /**比如静态资源，默认放行||临时通行证，放行**/
        if (isStaticRequest(requestURI) || request.getAttribute(ConfigUtil.getConfigInfo("TOKEN")) != null) {
            //放行
            filterChain.doFilter(request, response);
            return;
        } else {
            HttpSession session = request.getSession(false);
            User adminUser = (User) (session == null ? null : session.getAttribute(ConfigUtil.getConfigInfo("ADMIN_NAME")));
            User user = (User) (session == null ? null : session.getAttribute(ConfigUtil.getConfigInfo("USER_NAME")));
            if (adminUser == null && user == null) {
                request.getRequestDispatcher("/index/tologin").forward(request, response);
            } else {
                //一次请求发送临时通行证
                request.setAttribute(ConfigUtil.getConfigInfo("TOKEN"), true);
                filterChain.doFilter(request, response);
                return;
            }
        }
    }

    private static boolean isStaticRequest(String url) {
        String pattern = url.substring(1);
        if (pattern == null || pattern.trim().equals("")) {
            return false;
        }
        String regEx_time = ".*\\.((jpg)|(gif)|(css)|(js)|(src)|(map)|(ico)|(html)|(jsp)|(png)|(bmp)|(jpeg)|(cur)|(apk)|(swf)|(flv)|(coffee)|(tpl))$";
        Pattern p_time = Pattern.compile(regEx_time, Pattern.CASE_INSENSITIVE);//忽略大小写匹配
        Matcher m_time = p_time.matcher(pattern);
        if (m_time.find()) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
