package com.springaction.springaction1.service;

import com.springaction.springaction1.exception.RollbackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


//    @Autowired
//    private FooService fooService;

    @Override
    @Transactional
    public void insert() {
        jdbcTemplate.execute("insert into foo(name) values('aaa')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException{
        jdbcTemplate.execute("insert into foo(name) values('bbb')");
        throw new RollbackException("throw exception");
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException{
        ((FooService)(AopContext.currentProxy())).insertThenRollback();
//        insertThenRollback();
    }
}
