package com.hdg.service;

import com.hdg.dao.BasicDao;
import com.hdg.entity.ExecuteResult;
import com.hdg.entity.IpAddress;
import com.hdg.entity.PageParam;
import com.hdg.entity.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BlueBuff on 2017/7/15.
 */
@Service
public class BasicService {

    @Autowired
    private BasicDao basicDao;

    public ExecuteResult<QueryResult<List<IpAddress>>> queryIpAddress(PageParam pageParam){
        ExecuteResult<QueryResult<List<IpAddress>>> executeResult=new ExecuteResult<QueryResult<List<IpAddress>>>(-1,"");
        QueryResult<List<IpAddress>> queryResult=basicDao.getIpAddress(pageParam);
        if(queryResult.getCount()>0){
            executeResult.setObj(queryResult);
        }else if(queryResult.getCount()==0){
            executeResult.setMsg("没有找到您需要的数据");
            return executeResult;
        }else {
            executeResult.setMsg("接口异常");
            return executeResult;
        }
        //默认成功
        executeResult.setCode(200);
        executeResult.setMsg("成功");
        return executeResult;
    }
}
