package com.frank.startup.portal;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @ClassName:     AppTest.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 */
public class AppTest {

	/**
	 * @Description: 开始单元测试前执行，只会运行一次，比如进行文件操作前进行打开一个大文件并读入文件内容
	 * @param: @throws java.lang.Exception
	 * @return: void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("AppTest.setUpBeforeClass");
	}

	/**
	 * @Description: 结束单元测试后执行，只会运行一次，比如进行文件操作结束之后关闭IO
	 * @param: @throws java.lang.Exception
	 * @return: void
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AppTest.tearDownAfterClass");
	}

	/**
	 * @Description: 在任何一个测试执行之前必须执行的代码
	 * @param: @throws java.lang.Exception
	 * @return: void
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("AppTest.setUp");
	}

	/**
	 * @Description: 在任何一个测试执行之后必须执行的代码
	 * @param: @throws java.lang.Exception
	 * @return: void
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("AppTest.tearDown");
	}

	/**
	 * Test method for {@link com.yellow.junit.Calculator#add(int)}.
	 */
	@Test
	public final void testAdd() {
		System.out.println("AppTest.testAdd");
	}

	/**
	 * Test method for {@link com.yellow.junit.Calculator#substract(int)}.
	 */
	@Test
	public final void testSubstract() {
		System.out.println("AppTest.testSubstract");
	}


	/**
	 * Test method for {@link com.yellow.junit.Calculator#divide(int)}.
	 */
	@Test
	public final void testDivide() {
		System.out.println("AppTest.testDivide");
	}


	/**
	 * Test method for {@link com.yellow.junit.Calculator#square(int)}.
	 */
	@Test
	public final void testSquare() {
		System.out.println("AppTest.testSquare");
	}

	/**
	 * Test method for {@link com.yellow.junit.Calculator#squareRoot(int)}.
	 */
	// 此方法忽略测试
	@Test(timeout = 1000)
	// 设定一个执行时间，超过了这个时间，他们就会被系统强行终止，并且系统还会向你汇报该函数结束的原因是因为超时
	public final void testSquareRoot() {
		System.out.println("AppTest.testSquareRoot");
	}

	/**
	 * Test method for {@link com.yellow.junit.Calculator#clear()}.
	 */
	@Test
	public final void testClear() {
		System.out.println("AppTest.testClear");
	}

	/**
	 * Test method for {@link com.yellow.junit.Calculator#getResult()}.
	 */
	@Test
	public final void testGetResult() {
		System.out.println("AppTest.testGetResult");
	}
}
