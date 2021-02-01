package com.tabuyos.guide.basic.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>Volatile1</i>
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
 * @since 0.1.0 - 2/1/21 9:40 AM
 */
public class Volatile1Test {

  // 这个并不是多线程并发问题, 加volatile不能避免问题
  // 加volatile仅仅确保了每次从主内存中读取value是最新的而已
  private int data = 0;

  public static void main(String[] args) throws Exception {
    Volatile1Test vt = new Volatile1Test();
    for (int i = 0; i < 1000; i++) {
      new Thread(
              () -> {
                try {
                  TimeUnit.MILLISECONDS.sleep(1);
                  vt.data++;
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              })
          .start();
    }
//    TimeUnit.SECONDS.sleep(1);
    // can't get expect value: 1000
    System.out.println(vt.data);
    /*
     * 以上原因是:
     *   变量的修改有三个步骤: 1. read and load  2. use and assign  3. store and write, 由于这三个步骤并不是原子操作, 这个值会被多个线程读取到
     */
  }
}
