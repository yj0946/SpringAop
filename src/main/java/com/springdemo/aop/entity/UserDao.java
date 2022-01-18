package com.springdemo.aop.entity;

public class UserDao{
    public UserDao() {
    }

    public void save() {
        System.out.println("save");
    }

    public void delete() {
        System.out.println("delete");
    }

    public void saveAndDelete() {
        System.out.println("saveAndDelete");
    }
}
