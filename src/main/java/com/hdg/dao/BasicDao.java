package com.hdg.dao;

import com.hdg.entity.IpAddress;
import com.hdg.entity.PageParam;
import com.hdg.entity.QueryResult;
import com.hdg.util.SqlUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BlueBuff on 2017/7/15.
 */
@Repository
public class BasicDao extends BaseJdbcDao {

    public QueryResult<List<IpAddress>> getIpAddress(PageParam pageParam) {
        QueryResult<List<IpAddress>> queryResult = new QueryResult<>();
        StringBuffer stringBuffer = new StringBuffer(20);
        stringBuffer.append(" select * from t_ip where 1=1");
        String countSql = " select count(1) from t_ip";
        SqlUtil.appendPageSqlSupport(stringBuffer, pageParam);
        int count = jdbcTemplateRead.queryForObject(countSql, Integer.class);
        if (count > 0) {
            try {
                List<IpAddress> ipAddresses = jdbcTemplateRead.query(stringBuffer.toString(), BeanPropertyRowMapper.newInstance(IpAddress.class));
                queryResult.setCount(ipAddresses.size());
                queryResult.setData(ipAddresses);
            } catch (DataAccessException e) {
                e.printStackTrace();
                queryResult.setCount(-1);
                return queryResult;
            }
        } else {
            queryResult.setCount(count);
        }
        return queryResult;
    }
}
