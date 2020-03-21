package com.springaction.springaction1.service.impl;

import com.springaction.springaction1.mapper.CoffeeMapper;
import com.springaction.springaction1.model.Coffee;
import com.springaction.springaction1.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@CacheConfig(cacheNames="coffee")
public class CoffeeServiceImpl implements CoffeeService {

    @Autowired
    @Qualifier("coffeeMapper")
    private CoffeeMapper coffeeMapper;

    @Override
    public int save(Coffee coffee) {
        return coffeeMapper.save(coffee);
    }

    @Override
    @Cacheable(key="#root.methodName")
    public List<Coffee> getCoffeeList() {
        return coffeeMapper.getCoffeeList();
    }

    @Override
    @CacheEvict
    public int update(Coffee coffee) {
        return coffeeMapper.update(coffee);
    }
}
