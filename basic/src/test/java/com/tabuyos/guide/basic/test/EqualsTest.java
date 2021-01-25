package com.tabuyos.guide.basic.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.tabuyos.guide.basic.entity.Model;
import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Description:
 *
 * <pre>
 *   <b>project: </b><i>awesome-orm</i>
 *   <b>package: </b><i>com.tabuyos.awesomeorm.test</i>
 *   <b>class: </b><i>AnnotationTest</i>
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
 * @since 0.1.0 - 1/22/21 1:32 PM
 */
public class EqualsTest {

  @BeforeEach
  void setUp() {
    System.out.println("=============================== before ===============================");
  }

  @AfterEach
  void tearDown() {
    System.out.println("=============================== after ================================");
  }

  @Test
  void testEquals() {
    Model model1 = new Model();
    model1.setAge("12");
    model1.setName("tabuyos1");
    Model model2 = new Model();
    model2.setAge("12");
    model2.setName("tabuyos2");

    // 重写后比较的是age
    assertEquals(model1, model2);
    // 比较地址
    assertNotSame(model1, model2);
    HashSet<Model> hashSet = new HashSet<>();
    hashSet.add(model1);
    hashSet.add(model2);
    assertEquals(hashSet.size(), 1);
  }
}
