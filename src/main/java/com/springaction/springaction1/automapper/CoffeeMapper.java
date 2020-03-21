package com.springaction.springaction1.automapper;

import com.springaction.springaction1.automodel.Coffee;
import com.springaction.springaction1.automodel.CoffeeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("coffeeMapperNew")
public interface CoffeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coffee record);

    int insertSelective(Coffee record);

    List<Coffee> selectByExampleWithRowbounds(CoffeeExample example, RowBounds rowBounds);

    List<Coffee> selectByExample(CoffeeExample example);

    Coffee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Coffee record);

    int updateByPrimaryKey(Coffee record);
}