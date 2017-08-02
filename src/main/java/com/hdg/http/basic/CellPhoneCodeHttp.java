package com.hdg.http.basic;

import com.hdg.entity.basic.PhoneCodeParam;
import com.hdg.http.CheckSumBuilder;
import com.hdg.http.HttpTemplateBase;
import com.hdg.util.http.HttpClientTool;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Set;

/**
 * @Description
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月31日 星期一 22时17分.
 */
@Component
public class CellPhoneCodeHttp extends HttpTemplateBase {

    /**
     * 发送验证码
     */
    public String SendPhoneCode(PhoneCodeParam phoneCodeParam) {
        String url = phoneCodeParam.getUrl();
        final String appKey = phoneCodeParam.getAppKey();
        String appSecret = phoneCodeParam.getAppSecret();
        final String nonce = phoneCodeParam.getNonce();
        final String TEMPLATEID = phoneCodeParam.getTemplateId();
        final String MOBILE = phoneCodeParam.getMobile();
        final String CODELEN = phoneCodeParam.getCodeLen();
        final String curTime = String.valueOf((new Date()).getTime() / 1000L);
        final String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        if(StringUtils.isBlank(url)
                ||StringUtils.isBlank(appKey)
                ||StringUtils.isBlank(nonce)
                ||StringUtils.isBlank(appKey)
                ||StringUtils.isBlank(TEMPLATEID)
                ||StringUtils.isBlank(MOBILE)
                ||StringUtils.isBlank(CODELEN)
                ){
            return null;
        }
        String response=HttpClientTool.doCustomPost(url, new HttpClientTool.CallBackHttp() {
            @Override
            public void setRequestParam(Set<NameValuePair> set) {
                set.add(new NameValuePair("templateid", TEMPLATEID));
                set.add(new NameValuePair("mobile", MOBILE));
                set.add(new NameValuePair("codeLen", CODELEN));
            }
            @Override
            public void setRequestHeader(PostMethod method) {
                // 设置请求的header
                method.setRequestHeader("AppKey", appKey);
                method.setRequestHeader("Nonce", nonce);
                method.setRequestHeader("CurTime", curTime);
                method.setRequestHeader("CheckSum", checkSum);
                method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            }
        });
        System.out.println("短信状态-->" + response);
        return response;
    }

    /**
     * 校验验证码
     */
    public void checkPhoneCode() {

    }
}
