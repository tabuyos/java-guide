package com.tabuyos.guide.basic.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>KeyWordTest</i>
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
 * @since 0.1.0 - 1/25/21 1:57 PM
 */
public class KeyWordTest {
  static int count = 0;

  public static int getCount() {
    return count;
  }

  public static void setCount(int count) {
    KeyWordTest.count = count;
  }

  public KeyWordTest() {
    System.out.println(123);
  }

  private class Holder {
    private final KeyWordTest INSTANCE = new KeyWordTest();
  }

  private static class SingletonHolder {
    private static final KeyWordTest INSTANCE = new KeyWordTest();
  }

  public static KeyWordTest getUniqueInstance() {
    return SingletonHolder.INSTANCE;
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
  void testFinal() {
    // final修饰的类不被继承, 且类下的成员隐式的被final修饰
    // final修饰的变量是常量, 如果是基本数据类型, 则初始化后不会发生改变, 如果是引用类型, 那么则一直指向这个对象
    // final修饰的方法不被重写
    final String username = "tabuyos";
    System.out.println(username);
    // can't assign new a value
    // username = "aaronliew";
  }

  @Test
  void testStatic() {
    // static: 成员变量, 成员方法  静态代码块  静态内部类  静态导包
    // 静态变量放在java内存区域的方法区
    // 静态代码块: 只会初始化一次
    // 静态内部类: 不依赖外围类创建, 不能使用外围类的非static成员变量和方法
    System.out.println(KeyWordTest.count);
    // 运行时并不会初始化静态内部类, 只有当调用getUniqueInstance时才会初始化(即不依赖外围类创建)
    // 延迟加载
    System.out.println(KeyWordTest.getUniqueInstance().hashCode());
    System.out.println(1);
    KeyWordTest.Holder holder = new Holder();
    System.out.println(holder.INSTANCE);
  }

  @Test
  void testThis() {
    // this 不能放在static方法中
    // this 指向引用类的当前实例
    this.tearDown();
  }

  @Test
  void testSupper() {
    // supper 不能放在static方法中
  }
}
