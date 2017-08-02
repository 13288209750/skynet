package com.hdg.controller;

import com.hdg.entity.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 测试接口
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月29日 星期六 20时18分.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/testPathVal/{testId}")
    public Result<String> testPathVal(@PathVariable String testId){
        return new Result<>(200,testId,"");
    }
}
