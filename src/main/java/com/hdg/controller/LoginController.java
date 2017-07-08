package com.hdg.controller;

import com.hdg.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangxiaojun on 2017/7/8.
 */
@Controller
@RequestMapping(value = {"/login"})
public class LoginController {

    private static final Logger logger= LoggerFactory.getLogger(LoggerFactory.class);

    /**
     * 登录
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/tohome")
    public String toLogin(User user, Model model){
        if(logger.isInfoEnabled()){
            logger.info("进入登录{}",user);
        }
        model.addAttribute("user",user);
        return "home";
    }
}
