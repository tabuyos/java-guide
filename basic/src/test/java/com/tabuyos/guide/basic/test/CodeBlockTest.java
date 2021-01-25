package com.tabuyos.guide.basic.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>CodeBlockTest</i>
 *   comment here.
 * </pre>
 *
 * @author <pre><b>username: </b><i><a href="http://www.tabuyos.com">Tabuyos</a></i></pre>
 * <pre><b>site: </b><i><a href="http://www.tabuyos.com">http://www.tabuyos.com</a></i></pre>
 * <pre><b>email: </b><i>tabuyos@outlook.com</i></pre>
 * <pre><b>description: </b><i>
 *   <pre>
 *     Talk is cheap, show me the code.
 *   </pre>
 * </i></pre>
 * @version 0.1.0
 * @since 0.1.0 - 1/25/21 2:38 PM
 */
public class CodeBlockTest {

  static {
    System.out.println("静态代码块");
  }

  {
    // 构造代码块也叫非静态代码块
    System.out.println("构造代码块");
  }

  public CodeBlockTest() {
    System.out.println("默认构造方法");
  }

  private static void test() {
    System.out.println("静态方法");
    {
      System.out.println("静态方法中的代码块");
    }
  }

  @BeforeEach
  void setUp() {
    System.out.println("=============================== before ===============================");
  }

  @AfterEach
  void tearDown() {
    System.out.println("=============================== after ================================");
  }

  @Test
  void testCodeBlock() {
    CodeBlockTest codeBlockTest = new CodeBlockTest();
    CodeBlockTest.test();
  }
}
