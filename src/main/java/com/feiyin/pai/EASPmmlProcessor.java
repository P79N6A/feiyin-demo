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
        client.setToken("ZWYyZGMzMjNjOTMzNmNhNmI0M2RiODIwNjMyYTZkOTZlZTk5NWYzYw==");
        client.setEndpoint("eas-shanghai.alibaba-inc.com");
        client.setModelName("pai_eas_service_528533_1545903038722");
        String feature = "[{\"sex\":1,\"address\":0.0,\"famsize\":2,\"pstatus\":0.0,\"medu\":0.0,\"fedu\":0.0,\"mjob\":0.0, \"fjob\":0.0, \"guardian\":0.0,\"traveltime\":0.0, \"studytime\":0.0, \"failures\":0.0, \"schoolsup\":0.0, \"fumsup\":0.0, \"paid\":0.0, \"activities\":0.0, \"higher\":0.0, \"internet\":0.0, \"famrel\":0.0, \"freetime\":0.0, \"goout\":0.0, \"dalc\":0.0, "
            + "\"walc\":0.0, \"health\":0.0, \"absences\":0.0, \"finalscore\":0.0​}]";
        String res = client.predict(feature);
        client.shutdown();
        System.out.println("feature:"+feature);
        System.out.println(res);
    }
}
