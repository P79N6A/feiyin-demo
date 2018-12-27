package com.feiyin.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
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
    @Test
    public void testInnerMap() {

        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 1);
        map.put(3, 3);
        map.put(2, 2);
        map.put(4, 4);

        map.forEach((k, v) -> {

            if(k == 1){
                return;
            }
            System.out.println("v:" + v);
        });
    }

    @Test()
    public void testList() {

        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        list.forEach(category -> {

        });
    }

    /*public Map<Long, XSpaceDO> test(){
        ArrayListMultimap<Object, Object> multimap = ArrayListMultimap.create();
        multimap.put(1,1);
        return multimap;

    }*/


    @Test()
    public void testWhile() {

        int count = 0;
        while (count ++ == 0 || count <10){
            System.out.println("循环中。。。。。。。");
            if(count == 5){
                break;
            }
        }
        System.out.println("结束kkkkkkkkkkkkkkkkk");

    }


    @Test
    public void testMax(){

        Long parentId = 0L;


        int count = 0;
        while (count ++ < 10){
            System.out.println("44444");
            System.out.println(count);
        }
    }


}
