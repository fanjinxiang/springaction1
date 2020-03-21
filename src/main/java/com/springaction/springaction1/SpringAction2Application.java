package com.springaction.springaction1;

import com.springaction.springaction1.model.Coffee;
import com.springaction.springaction1.service.CoffeeService;
import com.springaction.springaction1.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.math.BigDecimal;
import java.util.Map;

//@SpringBootApplication
//指定使用CJLIB实现代理
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableCaching(proxyTargetClass = true)
@MapperScan(basePackages = {"com.springaction.springaction1.mapper"})
@Slf4j
public class SpringAction2Application implements CommandLineRunner{

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPool jedisPool;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }


    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host){
        return new JedisPool(jedisPoolConfig());
    }




    public static void test(String[] args) {
        SpringApplication.run(SpringAction2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Coffee coffee = new Coffee();
        coffee.setName("lottee");
        coffee.setPrice(new BigDecimal(10));
        coffeeService.save(coffee);

        log.info("save a coffee to database");

        log.info("execute a select operation......");
        coffeeService.getCoffeeList().forEach(row->{
            System.out.println(row.toString());
        });

//        Coffee newCoffee = new Coffee();
//        newCoffee.setPrice(new BigDecimal(20));
//        newCoffee.setName("nana");
//        newCoffee.setId(4L);
//        coffeeService.update(newCoffee);

        coffeeService.getCoffeeList().forEach(row->{
            System.out.println(row.toString());
        });

        log.info("jedisPollConfig:"+jedisPoolConfig.toString());

        try(Jedis jedis = jedisPool.getResource()){
            coffeeService.getCoffeeList().forEach(row->{
                jedis.hset("bucks-coffee",row.getName(),row.getPrice().toString());
            });

            Map<String,String> coffeesMap = jedis.hgetAll("bucks-coffee");
            log.info("coffees:"+coffeesMap);
        }



//        fooService.insert();
//        System.out.println("count:"+jdbcTemplate.queryForObject("select count(*) from foo where name='aaa'",Long.class));
//        try{
//            fooService.insertThenRollback();
//        }catch (RollbackException e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println("count:"+jdbcTemplate.queryForObject("select count(*) from foo where name='bbb'",Long.class));
//
//        try{
//            fooService.invokeInsertThenRollback();
//        }catch (RollbackException e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println("count:"+jdbcTemplate.queryForObject("select count(*) from foo where name='bbb'",Long.class));

    }
}
