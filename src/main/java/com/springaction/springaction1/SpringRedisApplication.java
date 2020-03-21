package com.springaction.springaction1;

import com.springaction.springaction1.model.Coffee;
import com.springaction.springaction1.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

//@SpringBootApplication
@Slf4j
@EnableCaching(proxyTargetClass = true)
public class SpringRedisApplication implements CommandLineRunner {


    @Autowired
    private CoffeeService coffeeService;


    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        log.info("execute select all coffee");
        List<Coffee> coffeeList = coffeeService.getCoffeeList();
        coffeeList.forEach(row->{
            System.out.println(row.toString());
        });
        long end = System.currentTimeMillis();
        System.out.println("spend time:"+(end - start));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);

    }
}
