package com.feiyin.service;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 非音
 * @date 2018/10/25 - 下午10:24
 */
public class PatternTest {

    @Test
    public void testEmailPattern(){

        String a = "lll";
        String b = null;

        if(a.equals(b)){
            System.out.println("9");
        }

        List<Long> aa = Lists.newArrayList(1L);

        if(aa.contains(1l)){
            aa.remove(1l);
            System.out.println(aa);
        }
        String email = "abcdfefabc@gamil.com";
        Pattern p = Pattern.compile("(\\w{3})(\\w+)(\\w{3})(@\\w+)");
        Matcher m = p.matcher(email);
        System.out.println(m.toString());
        System.out.println(m.replaceAll("$1...$3$4"));
    }



}
