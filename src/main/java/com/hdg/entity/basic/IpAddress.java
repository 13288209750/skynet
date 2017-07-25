package com.hdg.entity.basic;

import java.io.Serializable;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class IpAddress implements Serializable {

    private static final long serialVersionUID = 5221625568682909260L;
    private Integer id;
    private String hostName;
    private String address;
    private Integer status;


    public IpAddress() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpAddress ipAddress = (IpAddress) o;
        return address != null ? address.equals(ipAddress.address) : ipAddress.address == null;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "id=" + id +
                ", hostName='" + hostName + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
