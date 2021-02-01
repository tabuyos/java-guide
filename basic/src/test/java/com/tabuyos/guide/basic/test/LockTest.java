package com.tabuyos.guide.basic.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>LockTest</i>
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
 * @since 0.1.0 - 1/28/21 3:55 PM
 */
public class LockTest {

  @BeforeEach
  void setUp() {
    System.out.println("=============================== before ===============================");
  }

  @AfterEach
  void tearDown() {
    System.out.println("=============================== after ================================");
  }

  @Test
  void testLock() {
    /*
      synchronized和ReentrantLock的区别
        1. 二者都是可重入锁: 如果线程已经获得了某个对象的锁, 此时对象的锁并没有释放,
                           当其再次获取这个对象的锁, 还是能够获取的, 这就是可重入锁, 每次获取锁, 锁计数器都会加1
      volatile
        1. 不仅能够防止指令冲排序, 另一个重要功能是保证变量的可见性
      并发编程三特性
        1. 原子性: 一个或多个操作, 要么做完要么什么都不做, synchronized可以保证原子性
        2. 可见性: 一个线程对共享变量进行了修改(从主内存中复制到本地内存中(也就变成了共享内存的副本了)),
                  那么另外的线程也能立即看到修改后的最新值, volatile可以保证变量的可见性
        3. 有序性: 代码在执行的过程中的先后顺序, Java在编译器以及运行期间的优化,
                  代码的执行顺序未必就是编写代码时候的顺序, volatile可以保证有序性
      synchronized和volatile的区别
        1. 二者互补却不对立
        2. volatile是线程同步的轻量级实现, 因此volatile性能比synchronized好, 但是volatile只能用于变量, synchronized可以修饰方法和代码块
        3. volatile可以保证数据可见性但是不能保证数据原子性, synchronized二者都能保证
        4. volatile主要用于解决变量在多个线程间的可见性, synchronized解决多线程间访问资源的同步性
     */
  }
}
