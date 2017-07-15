package com.hdg.util;

import com.hdg.other.Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 项目的配置信息
 * Created by BlueBuff on 2017/7/15.
 */
public class ConfigUtil {
    //配置信息容器
    private static final Map<String,String> configMap;

    private static Logger logger= LoggerFactory.getLogger(ConfigUtil.class);

    static {
        configMap=new HashMap<>();
        init();
    }

    private static void init(){
        if(logger.isInfoEnabled()){
            logger.info("加载配置文件信息");
        }
        Properties properties=new Properties();
        InputStream inputStream=ConfigUtil.class.getClassLoader().getResourceAsStream(Constant.CONFIG_PATH);
        try {
            properties.load(inputStream);
            if(properties.isEmpty()){
                if(logger.isWarnEnabled()){
                    logger.warn("配置（config.properties）文件是空的");
                }
            }else {
                Set<Map.Entry<Object,Object>> entries=properties.entrySet();
                for(Map.Entry<Object,Object> entry : entries){
                    String key=entry.getKey().toString();
                    String value=entry.getValue().toString();
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
                StreamUtil.closeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(logger.isInfoEnabled()){
            logger.info("配置文件加载完毕!!!\t配置项,size:[{}]",configMap.size());
        }
    }

    public static String getConfigInfo(String keyName){
        String value=configMap.get(keyName);
        if(StringUtils.isBlank(value)){
            value="";
        }
        return value;
    }
}
