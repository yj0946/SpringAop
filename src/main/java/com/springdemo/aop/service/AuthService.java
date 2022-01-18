package com.springdemo.aop.service;

import com.springdemo.aop.security.CheckUserHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    public void checkAccess() throws Exception {
        String user = CheckUserHolder.get();
        if (!user.equals("admin")) {
             throw new Exception("权限不够");
        }
    }
}
