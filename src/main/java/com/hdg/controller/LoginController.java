package com.hdg.controller;

import com.hdg.entity.ExecuteResult;
import com.hdg.entity.User;
import com.hdg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BlueBuff on 2017/7/8.
 */
@Controller
@RequestMapping(value = {"/login"})
public class LoginController {

    private static final Logger logger= LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private UserService userService;

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
        ExecuteResult<User> executeResult=userService.getUser(user);
        model.addAttribute("user",executeResult.getObj());
        return "home";
    }
}
