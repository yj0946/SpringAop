package com.springdemo.aop.Factory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyFactory implements MethodInterceptor {

    private Object target;

    public CglibProxyFactory(Object target) {
        super();
        this.target = target;
    }

    /**
     * 1、代理对象；2、委托类方法；3、方法参数；4、代理方法的MethodProxy对象。
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("事务开始......" + method.getName());
        Object o1 = methodProxy.invoke(target, objects);
        System.out.println("事务结束......." + method.getName());
        return o1;
    }


}
