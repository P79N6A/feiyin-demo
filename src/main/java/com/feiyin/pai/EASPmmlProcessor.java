package com.feiyin.pai;

import com.aliyun.openservices.eas.predict.http.HttpConfig;
import com.aliyun.openservices.eas.predict.http.PredictClient;

/**
 * @author 非音
 * @date 2018/12/20 - 下午8:27
 */
public class EASPmmlProcessor {

    public static void main(String[] args) {
        PredictClient client = new PredictClient(new HttpConfig());
        client.setToken("ZjNlOTdkYjAxMDUyNzQzNjkzMWYxNzVhNjg5MWVmZjUzNjk2ZGVlMg==");
        client.setEndpoint("eas-shanghai.alibaba-inc.com");
        client.setModelName("pai_eas_service_490423_1544673191968");
        String feature = "[{\"pm10\":0.0, \"so2\":0.0, \"co\":0.0, \"no2\":0.0}]";
        String res = client.predict(feature);
        client.shutdown();
        System.out.println(res);
    }
}
