package com.hdg.controller;

import com.hdg.annotation.IpAspectAnnotation;
import com.hdg.entity.*;
import com.hdg.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by BlueBuff on 2017/7/15.
 */
@Controller
@RequestMapping(value = {"/basic"})
public class BasicController {

    @Autowired
    private BasicService basicService;

    @ResponseBody
    @RequestMapping(value = "/getIpAddressInfo")
    @IpAspectAnnotation(description = "获取IP地址信息接口")
    public Result<List<IpAddress>> getIpAddressInfo(PageParam pageParam){
        if(pageParam.getPageNo()==null||pageParam.getPageSize()==null){
            pageParam.setPageNo(1);
            pageParam.setPageSize(10);
        }
        ExecuteResult<QueryResult<List<IpAddress>>> executeResult=basicService.queryIpAddress(pageParam);
        return new Result(executeResult.getCode(),executeResult.getObj(),executeResult.getMsg());
    }
}
