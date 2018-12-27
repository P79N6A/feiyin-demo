package com.feiyin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author 非音
 * @date 2018/11/19 - 上午11:16
 */
public class JsonDemo {

    @Test
    public void testJson(){

        String memo = "";

        JSONObject memoJson = JSON.parseObject(memo);
        System.out.println(memoJson);

    }
}
