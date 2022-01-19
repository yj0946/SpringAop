package com.springdemo.aop.design;

import com.sun.net.httpserver.Filter;

public abstract class ChainHandler {
    public void execute(Chain chain) {
        handleProcess();
        chain.proceed();
    }

    protected abstract void handleProcess();
}
