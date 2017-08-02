package com.hdg.entity.basic;

/**
 * @Description 网易云短信接口参数实体
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月31日 星期一 22时30分.
 */
public class PhoneCodeParam {
    private String appKey;//开发者平台分配的appkey
    private String nonce;//随机数（最大长度128个字符）
    private String curTime;//当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
    private String checkSum;//SHA1(AppSecret + Nonce + CurTime),三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
    private String mobile;//目标手机号
    private String deviceId;//目标设备号
    private String templateId;//模板编号(如不指定则使用配置的默认模版)
    private String codeLen;//验证码长度，范围4～10，默认为4
    private String appSecret;
    private String url;

    public PhoneCodeParam() {
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCodeLen() {
        return codeLen;
    }

    public void setCodeLen(String codeLen) {
        this.codeLen = codeLen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @Override
    public String toString() {
        return "PhoneCodeParam{" +
                "appKey='" + appKey + '\'' +
                ", nonce='" + nonce + '\'' +
                ", curTime='" + curTime + '\'' +
                ", checkSum='" + checkSum + '\'' +
                ", mobile='" + mobile + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", codeLen='" + codeLen + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
