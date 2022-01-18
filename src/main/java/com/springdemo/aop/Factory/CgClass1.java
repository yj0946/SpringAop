package com.springdemo.aop.Factory;

public class CgClass1 {
    public void method1() {
        System.out.println("com.xm.ggn.test.proxy.CgClass1.method1");
    }

    public void method2(String test) {
        System.out.println("com.xm.ggn.test.proxy.CgClass1.method2");
        int i = 0;
        i++;

        int k = 0;
        k++;

        int j = 0;
        j++;
    }
}
