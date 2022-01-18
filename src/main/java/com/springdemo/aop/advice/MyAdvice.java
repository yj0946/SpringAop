package com.springdemo.aop.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

//xml切面
@Aspect    // 表示该类是一个通知类
@Component // 交给spring管理
public class MyAdvice {
    // 定义一个空方法，借用其注解抽取切点表达式
    //@Pointcut("execution(* com.springdemo.aop.service.*ServiceImpl.*(..))")
    @Pointcut("within(com.springdemo.aop.service.UserServiceImpl)")
    public void pc() {

    }
    // 前置通知
    @Before("MyAdvice.pc()")
    public void before(JoinPoint joinPoint) throws Exception {
        System.out.println("---------------前置通知开始~~~~~~~~~~~");
        // 获取到类名
        String targetName = joinPoint.getTarget().getClass().getName();
        System.out.println("代理的类是:" + targetName);

        // 获取到方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("增强的方法是:" + methodName);

        // 获取到参数
        Object[] parameter = joinPoint.getArgs();
        System.out.println("传入的参数是:" + Arrays.toString(parameter));

        // 获取字节码对象
        Class<?> targetClass = Class.forName(targetName);

        // 获取所有的方法
        Method[] methods = targetClass.getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == parameter.length) {
                    System.out.println("找到这个方法");
                    //处理一些业务逻辑
                    break;
                }
            }
        }

        System.out.println("---------------前置通知结束~~~~~~~~~~~");
    }

    // 后置通知(异常发生后不会调用)
    @AfterReturning("MyAdvice.pc()")
    public void afterRunning() {
        System.out.println("这是后置通知(异常发生后不会调用)");
    }

    @Around("MyAdvice.pc()")
    // 环绕通知(推荐下面这种方式获取方法)
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("----------------环绕通知之前 的部分----------------");
        // 获取到类名
        String targetName = pjp.getTarget().getClass().getName();
        System.out.println("代理的类是:" + targetName);

        // 获取到参数
        Object[] parameter = pjp.getArgs();
        System.out.println("传入的参数是:" + Arrays.toString(parameter));

        // 获取到方法签名，进而获得方法
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        System.out.println("增强的方法名字是:" + method.getName());

        // 获取参数类型
        Class<?>[] parameterTypes = method.getParameterTypes();
        System.out.println("参数类型是:" + parameterTypes.toString());

        //让方法执行(proceed是方法的返回结果，可以针对返回结果处理一下事情)
        System.out.println("--------------方法开始执行-----------------");
        Object proceed = pjp.proceed();

        //环绕通知之后的业务逻辑部分
        System.out.println("----------------环绕通知之后的部分----------------");
        return proceed;
    }

    // 异常通知
    @AfterThrowing("MyAdvice.pc()")
    public void afterException() {
        System.out.println("这是异常通知(发生异常后调用)~~~~~~~~~~~");
    }

    // 最终通知(发生异常也会在最终调用)
    @After("MyAdvice.pc()")
    public void after() {
        System.out.println("这是后置通知(发生异常也会在最终调用)");
    }
}
