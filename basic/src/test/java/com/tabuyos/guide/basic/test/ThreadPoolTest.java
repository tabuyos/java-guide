package com.tabuyos.guide.basic.test;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>ThreadPoolTest</i>
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
 * @since 0.1.0 - 1/29/21 10:48 AM
 */
public class ThreadPoolTest implements Runnable {

  private String command;

  public ThreadPoolTest(String command) {
    this.command = command;
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
  void testPool() {

  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " start. time = " + new Date());
    try {
      processCommand();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " end. time = " + new Date());
  }

  private void processCommand() throws InterruptedException {
    TimeUnit.SECONDS.sleep(5);
  }

  @Override
  public String toString() {
    return this.command;
  }

  private static final int CORE_POOL_SIZE = 5;
  private static final int MAX_POOL_SIZE = 10;
  private static final int QUEUE_CAPACITY = 100;
  private static final Long KEEP_ALIVE_TIME = 1L;

  public static void main(String[] args) {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        CORE_POOL_SIZE,
        MAX_POOL_SIZE,
        KEEP_ALIVE_TIME,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(QUEUE_CAPACITY),
        new CallerRunsPolicy()
    );

    for (int i = 0; i < 10; i++) {
      Runnable runnable = new ThreadPoolTest("" + i);
      executor.execute(runnable);
    }
    executor.shutdown();
    while (!executor.isTerminated()) {
      // ignore
    }
    System.out.println("Finish all thread");
  }
}
