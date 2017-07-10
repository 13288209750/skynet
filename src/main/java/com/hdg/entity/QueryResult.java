package com.hdg.entity;

import java.util.List;

/**
 * Created by BlueBuff on 2017/7/11.
 */
public class QueryResult<T> {
    private Integer count;
    private List<T> dataList;

    public QueryResult() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "count=" + count +
                ", dataList=" + dataList +
                '}';
    }
}
