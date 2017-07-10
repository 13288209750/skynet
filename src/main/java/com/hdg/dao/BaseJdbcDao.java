package com.hdg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库dao基类
 * Created by BlueBuff on 2017/7/10.
 */
public class BaseJdbcDao {

    @Autowired
    protected JdbcTemplate jdbcTemplateRead;//只读

    @Autowired
    protected JdbcTemplate jdbcTemplateWrite;//只写
}
