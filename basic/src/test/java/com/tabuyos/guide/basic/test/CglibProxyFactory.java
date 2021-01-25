package com.tabuyos.guide.basic.test;

import net.sf.cglib.proxy.Enhancer;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>CglibProxyFactory</i>
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
 * @since 0.1.0 - 1/25/21 4:42 PM
 */
public class CglibProxyFactory {

  public static Object getProxy(Class<?> clazz) {
    // 创建代理增强类
    Enhancer enhancer = new Enhancer();
    // 设置类加载器
    enhancer.setClassLoader(clazz.getClassLoader());
    // 设置被代理类
    enhancer.setSuperclass(clazz);
    // 设置方法拦截器
    enhancer.setCallback(new DebugMethodInterceptor());
    // 创建代理类
    return enhancer.create();
  }
}
