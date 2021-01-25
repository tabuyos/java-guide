package com.tabuyos.guide.basic.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>BIOServer</i>
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
 * @since 0.1.0 - 1/25/21 5:12 PM
 */
public class BIOServer {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(3333);

    // 接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理
    new Thread(
            () -> {
              while (true) {
                try {
                  // 阻塞方法获取新的连接
                  Socket socket = serverSocket.accept();
                  // 每一个新的连接都创建一个线程，负责读取数据
                  new Thread(
                          () -> {
                            try {
                              int len;
                              byte[] data = new byte[1024];
                              InputStream inputStream = socket.getInputStream();
                              // 按字节流方式读取数据
                              while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                              }
                            } catch (IOException e) {
                              //
                            }
                          })
                      .start();
                } catch (IOException e) {
                  //
                }
              }
            })
        .start();
  }
}
