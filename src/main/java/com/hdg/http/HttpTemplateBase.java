package com.hdg.http;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author BlueBuff
 * @Email 1010060164@qq.com
 * @CreatedDate 2017年07月31日 星期一 22时13分.
 */
public class HttpTemplateBase {
    @Autowired
    private HttpClientBuilder httpClient;
}
