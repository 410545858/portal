package com.frank.startup.portal;

import com.frank.startup.portal.entity.User;
import com.frank.startup.portal.service.UserService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by FrankWong on 11/29/14.
 */
public class UserTest {

    private static UserService userService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("UserTest.setUpBeforeClass");
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"
                ,"classpath:spring-context.xml","classpath:spring-others.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
    }



    @AfterClass
    public static void tearDownAfterClass() throws Exception {


    }
    @Test
    public final void testAdd() {
        User entry = new User();
        entry.setLoginName("123");
        userService.add(entry);
        System.out.println("UserTest.testAdd");
    }

}
