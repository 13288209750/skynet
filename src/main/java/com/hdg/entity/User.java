package com.hdg.entity;

import java.io.Serializable;

/**
 * Created by huangxiaojun on 2017/7/8.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -7415417556276109352L;
    private Integer uid;
    private String userName;
    private String password;
    private String sex;
    private Long phone;
    private Boolean status;

    public User() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phone=" + phone +
                ", status=" + status +
                '}';
    }
}
