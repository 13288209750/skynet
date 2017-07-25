package com.hdg.dao;

import com.hdg.entity.basic.IpAddress;
import com.hdg.entity.PageParam;
import com.hdg.entity.result.QueryResult;
import com.hdg.util.other.SqlUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by BlueBuff on 2017/7/15.
 */
@Repository
public class BasicDaoImpl implements BasicDao {
    /**
     * 类路径
     */
    private final String classPath = this.getClass().getName();

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public QueryResult<List<IpAddress>> getIpAddress(PageParam pageParam) {
        QueryResult<List<IpAddress>> queryResult = new QueryResult<>();
        int count = sqlSessionTemplate.selectOne(classPath + ".getIpCount");
        queryResult.setCount(count);
        if (count > 0) {
            Map<String,Integer> map = SqlUtil.getPageLimt(pageParam);
            List<IpAddress> ipAddresses = sqlSessionTemplate.selectList(classPath + ".getIpAddress", map);
            queryResult.setData(ipAddresses);
        }
        return queryResult;
    }
}
