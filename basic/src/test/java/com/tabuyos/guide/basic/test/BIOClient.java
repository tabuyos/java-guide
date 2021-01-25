package com.tabuyos.guide.basic.test;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>BIOClient</i>
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
 * @since 0.1.0 - 1/25/21 5:13 PM
 */
public class BIOClient {

  public static void main(String[] args) {
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
}
