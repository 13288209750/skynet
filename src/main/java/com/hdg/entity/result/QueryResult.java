package com.hdg.entity.result;

import java.util.List;

/**
 * Created by BlueBuff on 2017/7/11.
 */
public class QueryResult<T> {
    private Integer count;
    private T data;

    public QueryResult() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "count=" + count +
                ", data=" + data +
                '}';
    }
}
