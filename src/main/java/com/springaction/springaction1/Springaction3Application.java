package com.springaction.springaction1;

import com.github.pagehelper.PageInfo;
import com.springaction.springaction1.automapper.CoffeeMapper;
import com.springaction.springaction1.automodel.Coffee;
import com.springaction.springaction1.automodel.CoffeeExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;

//@SpringBootApplication
@Slf4j
@MapperScan(basePackages = {"com.springaction.springaction1.automapper"})
public class Springaction3Application implements CommandLineRunner{


    @Resource(name="coffeeMapperNew")
    private CoffeeMapper coffeeMapper;

    @Override
    public void run(String... args) throws Exception {
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo("nana");
//        List<Coffee> list = coffeeMapper.selectByExample(example);
//        list.forEach(row->{
//            System.out.println(row.toString());
//        });

        CoffeeExample example1 = new CoffeeExample();
        List<Coffee> coffees = coffeeMapper.selectByExampleWithRowbounds(null,new RowBounds(1,3));
        coffees.forEach(row-> System.out.println(row.toString()));

        PageInfo pageInfo = new PageInfo(coffees);
        System.out.println(pageInfo.toString());
    }

    public static void test(String[] args) {
        SpringApplication.run(Springaction3Application.class, args);
    }
}
