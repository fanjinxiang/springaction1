package com.springaction.springaction1;

import com.springaction.springaction1.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RedisUtilsTemplateTests {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void set(){
        redisUtils.set("test","testvalue");
    }

    @Test
    public void get(){
        String value = redisUtils.get("test");
        System.out.println("value:"+value);
    }
}
