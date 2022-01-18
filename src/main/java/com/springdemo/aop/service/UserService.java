package com.springdemo.aop.service;

public interface UserService {
    void save();
    void save(String userId)throws Exception;
    void update();
    void delete();
    void find();
}
