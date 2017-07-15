package com.hdg.filter;

import com.hdg.util.ConfigUtil;
import com.hdg.util.StreamUtil;
import com.hdg.util.XMLUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 主要用于拦截没有登录的用户访问需要登录的资源
 * Created by BlueBuff on 2017/7/15.
 */
public class LoginFilter implements Filter {
    private static Set<String> urlSet;

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(logger.isInfoEnabled()){
            logger.info("登录验证过滤器初始化中....");
        }
        String allowFilePath = ConfigUtil.getConfigInfo("ALLOW_URL");
        InputStream inputStream = LoginFilter.class.getClassLoader().getResourceAsStream(allowFilePath);
        Document document = null;
        try {
            document = XMLUtil.getDocument(inputStream);
            urlSet = new HashSet<>();
            List<Element> elements= document.getRootElement().element("urls").elements();
            String url=null;
            for(int i=0,len=elements.size();i<len;i++){
                Element element=elements.get(i);
                url=element.getTextTrim();
                urlSet.add(url);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                StreamUtil.closeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(logger.isInfoEnabled()){
            logger.info("当前免登录即可访问,size[{}]",urlSet.size());
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        requestURI = URLDecoder.decode(requestURI, "utf-8");
        if(logger.isInfoEnabled()){
            logger.info("requestURI:{}",requestURI);
        }
        /**不需要登录即可访问的资源**/
        if(isIgnoreUrl(requestURI)){
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        /**比如静态资源，默认放行**/
        if(isStaticRequest(requestURI)){
            //放行
            filterChain.doFilter(request,response);
            return;
        }else {
            String adminName=request.getParameter(ConfigUtil.getConfigInfo("ADMIN_NAME"));
            String userName=request.getParameter(ConfigUtil.getConfigInfo("USER_NAME"));
            if(StringUtils.isNotBlank(adminName) || StringUtils.isNotBlank(userName)){
                request.getRequestDispatcher("/").forward(request,response);
            }else {
                filterChain.doFilter(request,response);
                return;
            }
        }
    }

    private boolean isIgnoreUrl(String requestUrl){
        Iterator<String> iterator = urlSet.iterator();
        String url=null;
        while (iterator.hasNext()){
            url=iterator.next();
            if(requestUrl!=null && requestUrl.startsWith(url)){
                return true;
            }
        }
        return false;
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
