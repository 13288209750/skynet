package com.hdg.service;

import com.hdg.dao.BasicDao;
import com.hdg.dao.BasicDaoImpl;
import com.hdg.entity.basic.PhoneCodeParam;
import com.hdg.entity.result.ExecuteResult;
import com.hdg.entity.basic.IpAddress;
import com.hdg.entity.PageParam;
import com.hdg.entity.result.QueryResult;
import com.hdg.http.basic.CellPhoneCodeHttp;
import com.hdg.other.Constant;
import com.hdg.util.serializable.ISerializableUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BlueBuff on 2017/7/15.
 */
@Service
public class BasicServiceImpl implements BasicService{

    @Autowired
    private BasicDao basicDao;

    @Autowired
    private CellPhoneCodeHttp cellPhoneCodeHttp;

    @Autowired
    private PhoneCodeParam phoneCodeParam;


    @Override
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

    @Override
    public ExecuteResult<JSONObject> sendCode(String mobile) {
        ExecuteResult<JSONObject> executeResult=new ExecuteResult<>(Constant.WITHOUT,"");
        phoneCodeParam.setMobile(mobile);
        String result=cellPhoneCodeHttp.SendPhoneCode(phoneCodeParam);
        if(StringUtils.isNotBlank(result)){
            JSONObject jsonObject=JSONObject.fromObject(result);
            executeResult.setObj(jsonObject);
        }else {
            executeResult.setMsg("参数错误");
            return executeResult;
        }
        executeResult.setCode(Constant.SUCCESS);
        return executeResult;
    }
}
