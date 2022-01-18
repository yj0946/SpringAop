package com.springdemo.aop.domain;

import com.springdemo.aop.Factory.CgClass1;
import com.springdemo.aop.Factory.CglibProxyFactory;
import com.springdemo.aop.Factory.Interface1;
import com.springdemo.aop.entity.UserDao;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CglibClient {
    public static void main(String[] args) throws Exception {
//        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:/proxy");
//
//        UserDao userDao = new UserDao();
//        CglibProxyFactory cglibProxy = new CglibProxyFactory(userDao);
//        //jdk需要提供接口，cglib需要是非私有类，且不能处理final关键字修饰的方法
//        Enhancer enhancer = new Enhancer();
//
//        enhancer.setSuperclass(UserDao.class);
//        //设置回调对象
//        enhancer.setCallback(cglibProxy);
//
//        UserDao proxy = (UserDao)enhancer.create();
//        proxy.save();
//        System.out.println("===1===");
//        proxy.delete();
//        System.out.println("===2===");
//        proxy.saveAndDelete();
        //jdk动态代理
//        Interface1 o = (Interface1) Proxy.newProxyInstance(Interface1.class.getClassLoader(), new Class[]{Interface1.class}, (proxy, method, args1) -> {
//            System.out.println("java.lang.reflect.InvocationHandler.invoke " + method + " " + Arrays.toString(args1));
//            return "123";
//        });
//
//        o.method1();
//        o.method2("Interface1 method2");


        //****************cglib
        //jdk需要提供接口，cglib需要是非私有类，且不能处理final关键字修饰的方法
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(CgClass1.class);

        //设置回调对象
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("方法前......" + method.getName());
                Object o1 = methodProxy.invokeSuper(o, objects);
                System.out.println("方法后......." + method.getName());
                return o1;
            }
        });

        CgClass1 cgClass1 = (CgClass1) enhancer.create();
        cgClass1.method1();
        cgClass1.method2("cg method2");


        Thread.sleep(50 * 10000);
    }
}
