package com.hdg.util.other;

import com.hdg.filter.LoginFilter;
import com.hdg.other.Constant;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 项目的配置信息
 * Created by BlueBuff on 2017/7/15.
 */
public class ConfigUtil {
    //配置信息容器
    private static final Map<String,String> configMap;
    //免登录资源容器
    private static final Set<String> urlSet;

    private static Logger logger= LoggerFactory.getLogger(ConfigUtil.class);

    static {
        configMap=new HashMap<>();
        urlSet = new HashSet<>();
        init();
    }

    private static void init(){
        if(logger.isInfoEnabled()){
            logger.info("加载配置文件信息");
        }
        Properties properties=new Properties();
        InputStream inputStream1=ConfigUtil.class.getClassLoader().getResourceAsStream(Constant.CONFIG_PATH);
        try {
            properties.load(inputStream1);
            if(properties.isEmpty()){
                if(logger.isWarnEnabled()){
                    logger.warn("配置（config.properties）文件是空的");
                }
            }else {
                Set<Map.Entry<Object,Object>> entries=properties.entrySet();
                for(Map.Entry<Object,Object> entry : entries){
                    String key=entry.getKey().toString().trim();
                    String value=entry.getValue().toString().trim();
                    configMap.put(key,value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(logger.isInfoEnabled()){
                logger.info("配置文件config.properties解析失败");
            }
        }finally {
            try {
                StreamUtil.closeStream(inputStream1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(logger.isInfoEnabled()){
            logger.info("配置文件加载完毕!!!\t配置项,size:[{}]",configMap.size());
        }
        /**####################################################################**/
        String allowFilePath = ConfigUtil.getConfigInfo("ALLOW_URL");
        InputStream inputStream2 = LoginFilter.class.getClassLoader().getResourceAsStream(allowFilePath);
        Document document = null;
        try {
            document = XMLUtil.getDocument(inputStream2);
            List<Element> elements = document.getRootElement().element("urls").elements();
            String url = null;
            for (int i = 0, len = elements.size(); i < len; i++) {
                Element element = elements.get(i);
                url = element.getTextTrim();
                urlSet.add(url);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                StreamUtil.closeStream(inputStream2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**启动模式**/
        if("true".equals(getConfigInfo("DEVELOPER"))){
            if(logger.isInfoEnabled()){
                logger.info("当前项目启动模式---【开发模式】");
            }
        }else if("false".equals(getConfigInfo("DEVELOPER"))){
            if(logger.isInfoEnabled()){
                logger.info("当前项目启动模式---【生产模式】");
            }
        }else {
            if(logger.isInfoEnabled()){
                logger.info("模式配置错误!!!-->\""+getConfigInfo("DEVELOPER")+"\"<---【默认生产模式】");
            }
        }
    }

    public static String getConfigInfo(String keyName){
        String value=configMap.get(keyName);
        if(StringUtils.isBlank(value)){
            value="";
        }
        return value;
    }

    public static int getUrlSetSize(){
        return urlSet.size();
    }


    public static boolean isIgnoreUrl(String requestUrl) {
        Iterator<String> iterator = urlSet.iterator();
        String url = null;
        if ("/".equals(requestUrl)) {
            return true;
        }
        while (iterator.hasNext()) {
            url = iterator.next();
            if ("/".equals(url)) {
                continue;
            }
            if (requestUrl != null && requestUrl.startsWith(url)) {
                return true;
            }
        }
        return false;
    }
}
