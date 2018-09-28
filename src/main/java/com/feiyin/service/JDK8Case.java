package com.feiyin.service;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author 非音
 * @date 2018/9/28 - 下午4:11Ø
 */
public class JDK8Case {

    /**
     * 测试jdk写法
     * @date 2018/9/28 - 下午4:40
     */
    @Test()
    public void testInnerMap() {

        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);

        map.forEach((k, v) -> {
            System.out.println("k:" + k + ",v:" + v);
        });
    }
}
