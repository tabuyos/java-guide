package com.tabuyos.guide.basic.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>ReflectTest</i>
 *   comment here.
 * </pre>
 *
 * @author
 *     <pre><b>username: </b><i><a href="http://www.tabuyos.com">Tabuyos</a></i></pre>
 *     <pre><b>site: </b><i><a href="http://www.tabuyos.com">http://www.tabuyos.com</a></i></pre>
 *     <pre><b>email: </b><i>tabuyos@outlook.com</i></pre>
 *     <pre><b>description: </b><i>
 *   <pre>
 *     Talk is cheap, show me the code.
 *   </pre>
 * </i></pre>
 *
 * @version 0.1.0
 * @since 0.1.0 - 1/25/21 2:46 PM
 */
public class ReflectTest {

  private final String value;

  public ReflectTest() {
    value = "Tabuyos";
  }

  public void publicMethod(String s) {
    System.out.println("I love " + s);
  }

  private void privateMethod() {
    System.out.println("value is " + value);
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
  void testReflect() throws Exception {
    // 获取Class对象的四种方法
    // usage 1
    Class<?> clazz1 = ReflectTest.class;
    System.out.println(clazz1.getSimpleName());
    // usage 2
    Class<?> clazz2 = Class.forName("com.tabuyos.guide.basic.test.ReflectTest");
    System.out.println(clazz2.getSimpleName());
    // usage 3
    ReflectTest test = new ReflectTest();
    Class<? extends ReflectTest> clazz3 = test.getClass();
    System.out.println(clazz3.getSimpleName());
    // usage 4
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    Class<?> clazz4 = classLoader.loadClass("com.tabuyos.guide.basic.test.ReflectTest");
    System.out.println(clazz4.getSimpleName());
  }

  @Test
  void testReflect1() throws Exception {
    Class<?> clazz = Class.forName("com.tabuyos.guide.basic.test.ReflectTest");
    ReflectTest test = (ReflectTest) clazz.getConstructor().newInstance();
    test.publicMethod("Tabuyos");
    test.privateMethod();
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      System.out.println(method.getName());
    }
    Method publicMethod = clazz.getDeclaredMethod("publicMethod", String.class);
    publicMethod.invoke(test, "aaron liew");
    Field value = clazz.getDeclaredField("value");
    value.setAccessible(true);
    value.set(test, "test");
    Method privateMethod = clazz.getDeclaredMethod("privateMethod");
    privateMethod.setAccessible(true);
    privateMethod.invoke(test);
  }

  @Test
  void testReflect2() {
    // 静态编译: 编译时确定类型, 绑定对象
    // 动态编译: 运行时确定类型, 绑定对象
    // 优点: 运行时确定类型, 动态加载类, 提高代码灵活性
    // 反射相当于一系列解释操作，通知 JVM 要做的事情，性能比直接的 java 代码要慢很多。2,安全问题，让我们可以动态操作改变类的属性同时也增加了类的安全隐患。
    // 缺点: 性能瓶颈: 反射相当于一系列的解释操作, 通知jvm要做的事情, 性能比直接的java代码要慢很多  安全问题: 既然可以动态的改变类的属性, 那么同时也增加了类的安全问题
  }
}
