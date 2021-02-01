package com.tabuyos.guide.basic.test;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>NIOClient</i>
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
 * @since 0.1.0 - 1/26/21 10:01 AM
 */
public class NIOClient {

  public static void main(String[] args) {
    twoVersion();
  }

  private static void oneVersion() {
    new Thread(
            () -> {
              try {
                Socket socket = new Socket("127.0.0.1", 3333);
                while (true) {
                  try {
                    socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                    Thread.sleep(2000);
                  } catch (Exception e) {
                    //
                  }
                }
              } catch (IOException e) {
                //
              }
            })
        .start();
  }

  private static void twoVersion() {
    String host = "127.0.0.1";
    int port = 3333;
    new Thread(
            () -> {
              try {
                Socket socket = new Socket(host, port);
                // 开启轮询
                while (true) {
                  socket
                      .getOutputStream()
                      .write((new Date() + ": hello tabuyos").getBytes(StandardCharsets.UTF_8));
                  TimeUnit.SECONDS.sleep(3);
                }
              } catch (IOException | InterruptedException e) {
                e.printStackTrace();
              }
            })
        .start();
  }
}
