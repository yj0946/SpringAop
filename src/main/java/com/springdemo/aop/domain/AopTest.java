package com.springdemo.aop.domain;

import com.springdemo.aop.security.CheckUserHolder;
import com.springdemo.aop.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class AopTest {
    @Autowired
    private ProductService productService;

    /**
     * 匿名权限访问校验
     */
    @Test(expected = Exception.class) //正确结果应该抛出异常
    public void annoDeleteTest() {
        CheckUserHolder.set("kevin");
        try {
            productService.delete(1L);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }

    }

    /**
     * 管理员权限校验
     */
    @Test
    public void adminDelete() {
        CheckUserHolder.set("admin");
        productService.delete(1L);
    }
}
