package com.springaction.springaction1.service;

import com.springaction.springaction1.model.Coffee;

import java.util.List;

public interface CoffeeService {
    int save(Coffee coffee);
    List<Coffee> getCoffeeList();
    int update(Coffee coffee);
}
