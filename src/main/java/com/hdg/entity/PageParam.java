package com.hdg.entity;

import java.io.Serializable;

/**
 * 分页类
 * Created by BlueBuff on 2017/7/14.
 */
public class PageParam implements Serializable{
    private Integer pageNo;//当前页
    private Integer pageSize;//每页获取最大数据量
    private Boolean ignore;//是否忽略默认分页，当ignore为true的时候，不分页;

    public PageParam(){

    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", ignore=" + ignore +
                '}';
    }
}
