package com.hdg.entity;

import org.apache.poi.ss.formula.functions.T;

/**
 * Created by BlueBuff on 2017/7/10.
 */
public class ExecuteResult<T> {

    private Integer code;
    private T obj;
    private String msg;

    public ExecuteResult(){}

    public ExecuteResult(Integer code,String  msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ExecuteResult{" +
                "code=" + code +
                ", obj=" + obj +
                ", msg='" + msg + '\'' +
                '}';
    }
}
