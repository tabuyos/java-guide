package com.tabuyos.guide.basic.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>CallableTest</i>
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
 * @since 0.1.0 - 2/1/21 2:11 PM
 */
public class CallableTest implements Callable<String> {

  @BeforeEach
  void setUp() {
    System.out.println("=============================== before ===============================");
  }

  @AfterEach
  void tearDown() {
    System.out.println("=============================== after ================================");
  }

  @Test
  void testCallable() {
    System.out.println(1);
  }

  @Override
  public String call() throws Exception {
    TimeUnit.SECONDS.sleep(1);
    return Thread.currentThread().getName();
  }

  private static final int CORE_POOL_SIZE = 5;
  private static final int MAX_POOL_SIZE = 10;
  private static final int QUEUE_CAPACITY = 100;
  private static final Long KEEP_ALIVE_TIME = 1L;

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ThreadPoolExecutor executor =
        new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new CallerRunsPolicy());

    List<Future<String>> futureList = new ArrayList<>();
    CallableTest callableTest = new CallableTest();
    for (int i = 0; i < 10; i++) {
      Future<String> future = executor.submit(callableTest);
      futureList.add(future);
    }
    for (Future<String> future : futureList) {
      System.out.println(new Date() + "::" + future.get());
    }
    executor.shutdown();
  }
}
