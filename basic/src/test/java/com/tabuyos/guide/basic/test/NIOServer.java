package com.tabuyos.guide.basic.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>NIOServer</i>
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
 * @since 0.1.0 - 1/26/21 10:02 AM
 */
public class NIOServer {

  /*
   为什么不愿意用原生的NIO?
   1. 编程复杂, 编程模型难
   2. NIO的底层采用的epoll实现, 这种实现容易出现空轮询bug从而导致cpu飙升至100%
   3. 项目大了后容易出bug, 难以维护, 维护成本高
   采用Netty可以很大程度解决原生的问题
  */

  public static void main(String[] args) throws IOException {
    twoVersion();
  }

  private static void oneVersion() throws IOException {
    // 1. serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，
    // 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等
    Selector serverSelector = Selector.open();
    // 2. clientSelector负责轮询连接是否有数据可读
    Selector clientSelector = Selector.open();

    new Thread(
            () -> {
              try {
                // 对应IO编程中服务端启动
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                listenerChannel.socket().bind(new InetSocketAddress(3333));
                listenerChannel.configureBlocking(false);
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                  // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                  if (serverSelector.select(1) > 0) {
                    Set<SelectionKey> set = serverSelector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = set.iterator();

                    while (keyIterator.hasNext()) {
                      SelectionKey key = keyIterator.next();

                      if (key.isAcceptable()) {
                        try {
                          // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                          SocketChannel clientChannel =
                              ((ServerSocketChannel) key.channel()).accept();
                          clientChannel.configureBlocking(false);
                          clientChannel.register(clientSelector, SelectionKey.OP_READ);
                        } finally {
                          keyIterator.remove();
                        }
                      }
                    }
                  }
                }
              } catch (IOException ignored) {
              }
            })
        .start();
    new Thread(
            () -> {
              try {
                while (true) {
                  // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                  if (clientSelector.select(1) > 0) {
                    Set<SelectionKey> set = clientSelector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = set.iterator();

                    while (keyIterator.hasNext()) {
                      SelectionKey key = keyIterator.next();

                      if (key.isReadable()) {
                        try {
                          SocketChannel clientChannel = (SocketChannel) key.channel();
                          ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                          // (3) 面向 Buffer
                          clientChannel.read(byteBuffer);
                          byteBuffer.flip();
                          System.out.println(
                              Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                        } finally {
                          keyIterator.remove();
                          key.interestOps(SelectionKey.OP_READ);
                        }
                      }
                    }
                  }
                }
              } catch (IOException ignored) {
              }
            })
        .start();
  }

  private static void twoVersion() throws IOException {
    int port = 3333;
    // 1. serverSelector负责轮询是否有新的连接, 服务端监测到新的连接之后, 不再创建一个新的线程.
    // 而是直接将新连接绑定到clientSelector上, 这样就不用 IO 模型中 1w 个 while 循环在死等
    Selector serverSelector = Selector.open();
    // 2. clientSelector负责轮询连接是否有数据可读
    Selector clientSelector = Selector.open();
    new Thread(
            () -> {
              try {
                // 对应IO编程中服务端启动
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(port));
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                  // 监测是否有新的连接, 这里的1指的是阻塞的时间为 1ms
                  if (serverSelector.select(1) > 0) {
                    Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                      SelectionKey next = iterator.next();
                      if (next.isAcceptable()) {
                        try {
                          // (1) 每来一个新连接, 不需要创建一个线程, 而是直接注册到clientSelector
                          SocketChannel accept = ((ServerSocketChannel) next.channel()).accept();
                          accept.configureBlocking(false);
                          accept.register(clientSelector, SelectionKey.OP_READ);
                        } finally {
                          iterator.remove();
                        }
                      }
                    }
                  }
                }
              } catch (IOException e) {
                e.printStackTrace();
              }
            })
        .start();
    new Thread(
            () -> {
              while (true) {
                // (2) 批量轮询是否有哪些连接有数据可读,这里的1指的是阻塞的时间为 1ms
                try {
                  if (clientSelector.select(1) > 0) {
                    Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                      SelectionKey next = iterator.next();
                      if (next.isReadable()) {
                        try {
                          SocketChannel socketChannel = (SocketChannel) next.channel();
                          ByteBuffer buffer = ByteBuffer.allocate(1024);
                          // (3) 面向 Buffer
                          socketChannel.read(buffer);
                          buffer.flip();
                          System.out.println(
                              Charset.defaultCharset().newDecoder().decode(buffer).toString());
                        } finally {
                          iterator.remove();
                          next.interestOps(SelectionKey.OP_READ);
                        }
                      }
                    }
                  }
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            })
        .start();
  }
}
