package com.hdg.service;

import com.hdg.entity.result.ExecuteResult;
import com.hdg.entity.basic.IpAddress;
import com.hdg.entity.PageParam;
import com.hdg.entity.result.QueryResult;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @Description
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月25日 星期二 22时03分.
 */
public interface BasicService {

    ExecuteResult<QueryResult<List<IpAddress>>> queryIpAddress(PageParam pageParam);

    ExecuteResult<JSONObject> sendCode(String mobile);
}
