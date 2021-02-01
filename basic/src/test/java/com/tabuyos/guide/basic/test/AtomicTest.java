package com.tabuyos.guide.basic.test;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>AtomicTest</i>
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
 * @since 0.1.0 - 1/29/21 11:35 AM
 */
public class AtomicTest {

  /*
    AtomicInteger 主要是利用 CAS + volatile + native 方法来保证原子性, 从而避免synchronized的高开销, 使得效率得到极大的提升.
   */
  private final AtomicInteger integer = new AtomicInteger();

  private void incrementAndGet() {
    System.out.println(integer.incrementAndGet());
  }

  private void getAndIncrement() {
    System.out.println(integer.getAndIncrement());
  }

  private void getCount() {
    System.out.println(integer.get());
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
  void testAtomic() {
    getCount();
    incrementAndGet();
    getCount();
    getAndIncrement();
    getCount();
  }
}
