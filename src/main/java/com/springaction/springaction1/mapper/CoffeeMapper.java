package com.springaction.springaction1.mapper;

import com.springaction.springaction1.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("coffeeMapper")
public interface CoffeeMapper {
    int save(Coffee coffee);
    List<Coffee> getCoffeeList();
    int update(Coffee coffee);
}
