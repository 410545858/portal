package com.frank.startup.portal;

import com.frank.startup.portal.entity.User;
import com.frank.startup.portal.service.UserService;
import org.junit.*;
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

    /**
     * @Description: 在任何一个测试执行之前必须执行的代码
     * @param: @throws java.lang.Exception
     * @return: void
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @Description: 在任何一个测试执行之后必须执行的代码
     * @param: @throws java.lang.Exception
     * @return: void
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testAdd() {
        User entry = new User();
        entry.setLoginName("123");
        userService.add(entry);
        System.out.println("UserTest.testAdd");
    }

    @Test
    public final void testAdd1() {
        User entry = new User();
        System.out.println("UserTest.testAdd1");
    }

}
