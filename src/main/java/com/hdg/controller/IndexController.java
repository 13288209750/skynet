package com.hdg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangxiaojun on 2017/7/8.
 */
@Controller
@RequestMapping(value = {"/index"})
public class IndexController {

    private static final Logger logger= LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/tologin")
    public String toLogin(){
        if(logger.isInfoEnabled()){
            logger.info("跳转到登录页面");
        }
        return "login";
    }
}
