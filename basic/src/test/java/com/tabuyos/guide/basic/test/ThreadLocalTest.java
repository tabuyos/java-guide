package com.tabuyos.guide.basic.test;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>ThreadLocalTest</i>
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
 * @since 0.1.0 - 1/29/21 10:02 AM
 */
public class ThreadLocalTest implements Runnable {

  // 每个线程都有专属变量(变量拷贝)
  private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm"));

  @BeforeEach
  void setUp() {
    System.out.println("=============================== before ===============================");
  }

  @AfterEach
  void tearDown() {
    System.out.println("=============================== after ================================");
  }

  @Test
  void testThreadLocal() {
    /*
      可以使用ThreadLocal, 来实现每一个线程的专属本地变量
      ThreadLocal主要解决的就是让每个线程绑定自己的值, 类似一个盒子, 存放的是每个线程的私有数据
      ThreadLocal
        1. 专属变量
        2. 线程安全
     */
  }

  @Override
  public void run() {
    System.out.println("thread name: " + Thread.currentThread().getName() + " default formatter: " + formatter.get().toPattern());
    try {
      TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    formatter.set(new SimpleDateFormat());
    System.out.println("thread name: " + Thread.currentThread().getName() + " default formatter: " + formatter.get().toPattern());
  }

  public static void main(String[] args) throws InterruptedException {
    ThreadLocalTest test = new ThreadLocalTest();
    for (int i = 0; i < 10; i++) {
      Thread thread = new Thread(test, "" + i);
      TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
      // 里面会运行this#run()方法
      thread.start();
    }
  }
}
