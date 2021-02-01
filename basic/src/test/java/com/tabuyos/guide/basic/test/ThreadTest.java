package com.tabuyos.guide.basic.test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
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
 *   <b>class: </b><i>ThreadTest</i>
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
 * @since 0.1.0 - 1/27/21 4:38 PM
 */
public class ThreadTest {
  private static final Object resource1 = new Object();
  private static final Object resource2 = new Object();

  public static void main(String[] args) {
    ThreadTest test = new ThreadTest();
    test.testLock();
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
  void testMultiThread() {
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
    for (ThreadInfo threadInfo : threadInfos) {
      System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
    }
    /*
     线程中, 为什么程序计算器是私有的
       1. 为了保存现场, 以便恢复现场
     线程中, 为什么虚拟机栈和本地方法栈是私有的
       1. java方法在执行的时候, 会创建栈帧以便存储局部变量表, 操作数栈常量池引用等等信息, 从方法的调用到结束都对应着入栈出栈,
          所以, 为了保证这些信息不被其他线程所访问, 需要使其成为私有的
     什么是堆, 什么是方法区
       1. 堆和方法区都是线程共享的资源, 其中堆是进程中最大的一块内存区域,
         主要用来存放新创建的对象, 几乎所有的对象都在这里进行内存分配,
         而方法区主要存放已加载的信息, 如类信息, 常量, 静态变量, 即时编译器编译后的代码等数据信息
     什么是并发, 什么是并行
       1. 并发: 同一时间段, 多个任务都在执行, 单位时间不一定同时执行
       2. 并行: 单位时间内, 多个任务同时执行
     为什么要使用多线程
       1. 总体: 线程作为程序的最小执行单位, 在系统开销上的成本是远低于进程的开销, 同时多核CPU也能够同时运行多个线程
               当前的发展趋势, 目前的流量动不动都是上万的, 如果按照以前的模式, 已经不能很好的适应当前的要求,
               因此对要求的提高, 多线程才能更好的处理当前的情况, 从而提高系统的性能和响应
       2. 细节: 在单核的情况下采用多线程能够更好的利用CPU和IO, 提高吞吐量, 例如一个线程操作CPU, 另一个操作IO
               在多核的情况下采用多线程也主要是提高吞吐量, 创建多线程可以让多个核同时被利用
      会带来什么问题
        1. 虽然多线程提高了系统的利用率, 但是也使编程更加的复杂, 同时也带来了一系列的安全问题, 例如内存泄漏, 死锁, 线程不安全等
      线程的状态
        1. 初始-NEW: 线程创建时
        2. 运行-RUNNABLE: java将操作系统中就绪和运行统称为运行
        3. 阻塞-BLOCKED: 线程阻塞于锁, 例如synchronized
        4. 等待-WAITING: 线程等待被调用
        5. 超时等待-TIME_WAITING: 类似于等待, 但它可以在指定的时间自行返回, 例如sleep(long), wait(long), join(long)
        6. 终止-TERMINATED: 线程运行结束
      线程并不是一直处于某个状态, 而是随着运行, 在各个状态间进行切换
      当一个线程的时间片使用完后, 让出CPU给其他线程使用, 这就是一次上下文调用, 然而上下文切换会消耗大量的CPU时间, 这可能是操作系统中时间消耗最大的操作--上下文切换(linux相比较于其他操作系统, 在上下文切换和模式切换消耗的时间都比较小)
      什么是死锁
        1. 死锁是由资源竞争所导致的
        2. 多个线程同时被阻塞, 他们中的一个或多个甚至乃至全部都在等待某个资源的释放, 由于线程被无限期的阻塞, 因此程序不被终止
           例如两个线程都持有己方资源, 且同时想请求对方的资源, 那么就会因为相互等待而进入死锁状态
      死锁四条件
        1. 互斥条件: 线程的任意时刻, 同一资源只能被一个线程占用
        2. 请求与保持条件: 进程因请求资源被阻塞时, 对于已经获取到的资源, 应该保持不妨
        3. 不可剥夺条件: 线程已经获取到的资源, 在未使用完前不可以被其他线程强制剥夺, 只有自己使用完后才能释放
        4. 循环等待条件: 若干进程之间形成一种头尾相连的等待资源的关系
      如何避免死锁(破坏四条件)
        1. 破坏互斥条件: 这个条件我们没有办法破坏, 因为我们用锁本来就是想让他们互斥的(临界资源需要互斥访问)
        2. 破坏请求与保持条件: 一次性申请所有的资源.
        3. 破坏不剥夺条件: 占用部分资源的线程进一步申请其他资源时, 如果申请不到, 可以主动释放它占有的资源.
        4. 破坏循环等待条件: 靠按序申请资源来预防, 按某一顺序申请资源, 释放资源则反序释放, 破坏循环等待条件.

      sleep和wait
        1. 主要区别: sleep没有释放锁, wait释放了锁
        2. 共同: 暂停线程的执行
        3. wait通常用于线程的通信和交互, sleep通常用于线程的暂停执行
        4. wait的方法不会被自动唤醒, 需要其他线程在同一对象上进行notify或者notifyAll, 而sleep会自动苏醒, wait(long timeout)超时后自动唤醒
      为什么使用start()(会执行run()), 而不直接使用run()
        1. 调用start()方法方可启动线程并使线程进入就绪状态, 直接执行run()方法的话不会以多线程的方式执行.
    */
  }

  @Test
  void testLock() {
    new Thread(
            () -> {
              synchronized (resource1) {
                System.out.println(Thread.currentThread() + " get resource1");
                try {
                  TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting for resource2");
                synchronized (resource2) {
                  System.out.println(Thread.currentThread() + " get resource2");
                }
              }
            },
            "thread-1")
        .start();

//    new Thread(
//            () -> {
//              synchronized (resource2) {
//                System.out.println(Thread.currentThread() + " get resource2");
//                try {
//                  TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                  e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread() + " waiting for resource1");
//                synchronized (resource1) {
//                  System.out.println(Thread.currentThread() + " get resource1");
//                }
//              }
//            },
//            "thread-2")
//        .start();

    new Thread(
            () -> {
              synchronized (resource1) {
                System.out.println(Thread.currentThread() + " get resource1");
                try {
                  TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resource2");
                synchronized (resource2) {
                  System.out.println(Thread.currentThread() + " get resource2");
                }
              }
            },
            "thread-2")
        .start();
  }
}
