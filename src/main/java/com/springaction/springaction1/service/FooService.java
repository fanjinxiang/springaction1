package com.springaction.springaction1.service;

import com.springaction.springaction1.exception.RollbackException;

public interface FooService {
    void insert();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;

}
