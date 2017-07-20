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
    private String name;

    private String onlineAddress;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnlineAddress() {
        return onlineAddress;
    }

    public void setOnlineAddress(String onlineAddress) {
        this.onlineAddress = onlineAddress;
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
                ", name='" + name + '\'' +
                ", onlineAddress='" + onlineAddress + '\'' +
                '}';
    }
}
