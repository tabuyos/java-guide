package com.tabuyos.guide.basic.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>DebugInvocationHandler</i>
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
 * @since 0.1.0 - 1/25/21 4:16 PM
 */
public class DebugInvocationHandler implements InvocationHandler {

  private final Object object;

  public DebugInvocationHandler(Object object) {
    this.object = object;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before method: " + method.getName());
    Object invoke = method.invoke(object, args);
    System.out.println("after method: " + method.getName());
    return invoke;
  }
}
