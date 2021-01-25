package com.tabuyos.guide.basic.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>EnumTest</i>
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
 * @since 0.1.0 - 1/25/21 11:39 AM
 */
public class EnumTest {

  private PizzaStatus status;
  public enum PizzaStatus {
    ORDERED,
    READY,
    DELIVERED
  }

  public enum PizzaDeliveryStrategy {
    EXPRESS {
      @Override
      public void deliver(EnumTest pz) {
        System.out.println("Pizza will be delivered in express mode");
      }
    },
    NORMAL {
      @Override
      public void deliver(EnumTest pz) {
        System.out.println("Pizza will be delivered in normal mode");
      }
    };

    public abstract void deliver(EnumTest pz);
  }

  public enum PizzaDeliverySystemConfiguration {
    INSTANCE;
    PizzaDeliverySystemConfiguration() {
      // Initialization configuration which involves
      // overriding defaults like delivery strategy
    }

    private final PizzaDeliveryStrategy deliveryStrategy = PizzaDeliveryStrategy.NORMAL;

    // 该方法获取到的实例是单例的
    public static PizzaDeliverySystemConfiguration getInstance() {
      return INSTANCE;
    }

    public PizzaDeliveryStrategy getDeliveryStrategy() {
      return deliveryStrategy;
    }
  }

  public boolean isDeliverable() {
    return getStatus() == PizzaStatus.READY;
  }

  public PizzaStatus getStatus() {
    return status;
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
  void testEnum() {
    // 以这种方式定义的常量使代码更具可读性，允许进行编译时检查，预先记录可接受值的列表，并避免由于传入无效值而引起的意外行为。
    // 枚举的方式相比较与常量, 更具有可读性, 同时还允许编译时进行检查
    PizzaStatus pizza = null;
    // exception
//    System.out.println(pizza.equals(PizzaStatus.DELIVERED));
    System.out.println(pizza == PizzaStatus.DELIVERED);
    PizzaDeliveryStrategy deliveryStrategy1 = PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy();
    System.out.println(deliveryStrategy1.hashCode());
    PizzaDeliveryStrategy deliveryStrategy2 = PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy();
    System.out.println(deliveryStrategy2.hashCode());
    PizzaDeliveryStrategy deliveryStrategy3 = PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy();
    System.out.println(deliveryStrategy3.hashCode());
    deliveryStrategy1.deliver(new EnumTest());
  }
}
