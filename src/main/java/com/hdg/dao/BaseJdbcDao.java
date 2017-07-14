package com.hdg.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 数据库dao基类
 * Created by BlueBuff on 2017/7/10.
 */
public class BaseJdbcDao {

    @Autowired
    public JdbcTemplate jdbcTemplateRead;//只读

    @Autowired
    public JdbcTemplate jdbcTemplateWrite;//只写

    public JdbcTemplate getJdbcTemplateRead() {
        return jdbcTemplateRead;
    }

    public void setJdbcTemplateRead(JdbcTemplate jdbcTemplateRead) {
        this.jdbcTemplateRead = jdbcTemplateRead;
    }

    public JdbcTemplate getJdbcTemplateWrite() {
        return jdbcTemplateWrite;
    }

    public void setJdbcTemplateWrite(JdbcTemplate jdbcTemplateWrite) {
        this.jdbcTemplateWrite = jdbcTemplateWrite;
    }
}
