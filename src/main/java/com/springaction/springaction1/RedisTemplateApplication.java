package com.springaction.springaction1;

import com.springaction.springaction1.model.Coffee;
import com.springaction.springaction1.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootApplication
@Slf4j
public class RedisTemplateApplication implements CommandLineRunner{

    @Autowired
    private RedisTemplate<String,Coffee> redisTemplate;


    private static final String CACHE="bucks_coffee";

    @Autowired
    private CoffeeService coffeeService;

    @Bean
    public RedisTemplate<String,Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Coffee> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Override
    public void run(String... args) throws Exception {


        HashOperations<String,String,String> operations = redisTemplate.opsForHash();
        if(!redisTemplate.hasKey(CACHE)){
            List<Coffee> coffeeList = coffeeService.getCoffeeList();
            log.info("缓存不存在，执行数据库查询，并保存到缓存中");
            coffeeList.forEach(row->{
                operations.put(CACHE,row.getName(),row.getPrice().toString());
            });
        }else{
            log.info("缓存中存在，从缓存中获取");
            List<String> coffeeList = operations.values(CACHE);
            coffeeList.forEach(row->{
                System.out.println(row);
            });
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisTemplateApplication.class,args);
    }
}
