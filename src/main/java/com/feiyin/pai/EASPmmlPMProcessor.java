package com.feiyin.pai;

import com.aliyun.openservices.eas.predict.http.HttpConfig;
import com.aliyun.openservices.eas.predict.http.PredictClient;

/**
 * @author 非音
 * @date 2018/12/27 - 下午5:56
 */
public class EASPmmlPMProcessor {

    public static void main(String[] args) {
        PredictClient client = new PredictClient(new HttpConfig());
        client.setToken("NjdlZDI2NjhmODM3YWViMjU4OTk3MmNlNDQwMTNmMWVkYmJjZTAyMA==");
        client.setEndpoint("eas-shanghai.alibaba-inc.com");
        client.setModelName("pai_eas_service_512299_1545901638712");
        String feature = "[{\"pm10\":0.0, \"so2\":0.0, \"co\":0.0, \"no2\":0.0}]";

        String res = client.predict(feature);
        client.shutdown();
        System.out.println(res);
    }

}
