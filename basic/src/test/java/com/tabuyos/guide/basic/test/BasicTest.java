package com.tabuyos.guide.basic.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>BasicTest</i>
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
 * @since 0.1.0 - 1/25/21 10:31 AM
 */
public class BasicTest {

  @BeforeEach
  void setUp() {
    System.out.println("=============================== before ===============================");
  }

  @AfterEach
  void tearDown() {
    System.out.println("=============================== after ================================");
  }

  @Test
  void testInteger() {
    // 自动装箱, 会自动的缓存起来, 范围是[-128, 127], 所以x和y是相同的地址
    Integer x = 3;
    Integer y = 3;
    // true
    System.out.println(x == y);
    // 不会缓存
    Integer a = new Integer(3);
    Integer b = new Integer(3);
    // 拥有不同的地址
    // false
    System.out.println(a == b);
    // 拥有相同的值
    // true
    System.out.println(a.equals(b));
    System.out.println(Integer.MAX_VALUE);
    System.out.println(Integer.MIN_VALUE);
    // 超过范围不会进行缓存, 会自动创建新的对象
    Integer xx = 1000;
    Integer yy = 1000;
    // false
    System.out.println(xx == yy);

    //    Byte, Short, Integer, Long, Character, Boolean: 前面 4 种包装类默认创建了数值[-128，127]的相应类型的缓存数据,
    //    Character 创建了数值在[0,127]范围的缓存数据, Boolean 直接返回 True Or False. 如果超出对应范围仍然会去创建新的对象
  }

  @Test
  void testBigDecimal() {
    float a = 1.0f - 0.9f;
    float b = 0.9f - 0.8f;
    // 0.100000024
    System.out.println(a);
    // 0.099999964
    System.out.println(b);
    // 发生精度丢失
    // false
    System.out.println(a == b);

    // use BigDecimal
    BigDecimal aa = new BigDecimal("1.0");
    BigDecimal bb = new BigDecimal("0.9");
    BigDecimal cc = new BigDecimal("0.8");
    BigDecimal xx = aa.subtract(bb);
    BigDecimal yy = bb.subtract(cc);
    System.out.println(xx);
    System.out.println(yy);
    System.out.println(Objects.equals(xx, yy));

    // 保留小数, 四舍五入
    BigDecimal m = new BigDecimal("1.255533");
    BigDecimal n = m.setScale(3, RoundingMode.HALF_DOWN);
    // 1.255
    System.out.println(n);

    // BigDecimal(double) 存在精度损失
    BigDecimal decimal = new BigDecimal(0.1f);
    // Expected: 0.1
    // Actual: 0.100000001490116119384765625
    System.out.println(decimal);
  }

  @Test
  void testArray() {
    // usage1
    String[] arr = {"tabuyos", "aaron", "liew"};
    List<String> list1 = Arrays.asList(arr);
    System.out.println(list1);
    // usage2
    List<String> list2 = Arrays.asList("tabuyos", "aaron", "liew");
    System.out.println(list2);
    // Arrays.asList 将数组转换为集合, 不能使用修改方法, 否则会抛出异常
    // 实际上asList体现的是一种适配器模式, 只是转换接口, 实际上后台数据仍是数组
    // catch: UnsupportedOperationException
    // list1.add("hello");

    // use stream
    // boxed 自动进行装箱
    int[] myArray2 = {1, 2, 3};
    List<Integer> collect = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
    collect.add(5);
    System.out.println(collect);
  }
}
