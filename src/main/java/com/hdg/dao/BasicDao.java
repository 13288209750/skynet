package com.hdg.dao;

import com.hdg.entity.basic.IpAddress;
import com.hdg.entity.PageParam;
import com.hdg.entity.result.QueryResult;

import java.util.List;

/**
 * @Description
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月25日 星期二 22时07分.
 */
public interface BasicDao {

    /**
     * 查询IP地址
     * @param pageParam
     * @return
     */
    QueryResult<List<IpAddress>> getIpAddress(PageParam pageParam);
}
