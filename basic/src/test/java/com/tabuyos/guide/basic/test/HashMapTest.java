package com.tabuyos.guide.basic.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 * <pre>
 *   <b>project: </b><i>java-guide</i>
 *   <b>package: </b><i>com.tabuyos.guide.basic.test</i>
 *   <b>class: </b><i>HashMapTest</i>
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
 * @since 0.1.0 - 1/26/21 3:15 PM
 */
public class HashMapTest {

  private static class SimpleCache<K, V> extends LinkedHashMap<K, V> {

    private static final int MAX_NODE_NUM = 100;

    private int limit;

    public SimpleCache() {
      this(MAX_NODE_NUM);
    }

    public SimpleCache(int limit) {
      super(limit, 0.75f, true);
      this.limit = limit;
    }

    public V save(K key, V val) {
      return put(key, val);
    }

    public V getOne(K key) {
      return get(key);
    }

    public boolean exists(K key) {
      return containsKey(key);
    }

    /**
     * 判断节点数是否超限
     * @param eldest
     * @return 超限返回 true，否则返回 false
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
      return size() > limit;
    }
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
  void testLinkedHashMap() {
    // LinkedHashMap.Entry -> HashMap.Node
    /*
      LinkedHashMap.Entry 中的 after 和 before 主要是用来维护链表的前驱和后继
      linkNodeLast 将新元素添加到链表尾(覆写)
      afterNodeRemoval 移除元素(覆写)
     */
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("username", "tabuyos");
    map.put("password", "aaron liew");
    map.put("age", "25");
    System.out.println(map.keySet());
    System.out.println(map);

    SimpleCache<Integer, Integer> cache = new SimpleCache<>(6);

    for (int i = 0; i < 10; i++) {
      cache.save(i, i * i);
    }

    System.out.println("插入10个键值对后，缓存内容：");
    System.out.println(cache + "\n");

    System.out.println("访问键值为7的节点后，缓存内容：");
    cache.getOne(7);
    System.out.println(cache + "\n");

    System.out.println("插入键值为1的键值对后，缓存内容：");
    cache.save(1, 1);
    System.out.println(cache);
  }

  @Test
  void testHashMap() {
    HashMap<String, String> map = new HashMap<>(7);
    map.put("username", "tabuyos");
//    System.out.println(map);
//    int n = -1 >>> Integer.numberOfLeadingZeros(16 - 1);
//    System.out.println(Integer.numberOfLeadingZeros(4));
//    System.out.println(Integer.toBinaryString(-1));
    // 100
//    System.out.println(1 << 2);
    System.out.println(15 & -1552657431);
    System.out.println(Integer.toBinaryString(-1552657431));
    System.out.println(Integer.toBinaryString(15));
    System.out.println("tabuyos".hashCode());
    System.out.println(-1552617315 >>> 16);
    System.out.println(-1552617315^41844);
    System.out.println(Integer.toBinaryString(-1552617315));
    System.out.println(Integer.toBinaryString(41844));
    System.out.println(2^4);
    int h;
    System.out.println((h = "tabuyos".hashCode()) ^ (h >>> 16));
    System.out.println(12 ^ (12 >>> 16));
//    JDK 1.8 版本下 HashMap 扩容效率要高于之前版本. 如果大家看过 JDK 1.7 的源码会发现,
//    JDK 1.7 为了防止因 hash 碰撞引发的拒绝服务攻击, 在计算 hash 过程中引入随机种子.
//    以增强 hash 的随机性, 使得键值对均匀分布在桶数组中, 在扩容过程中, 相关方法会根据容量判断是否需要生成新的随机种子,
//    并重新计算所有节点的 hash. 而在 JDK 1.8 中, 则通过引入红黑树替代了该种方式. 从而避免了多次计算 hash 的操作, 提高了扩容效率.
  }

  @Test
  void testDoWhile() {
    HashMap<Integer, String> map = new HashMap<>(16);
    map.put(7, "");
    map.put(11, "");
    map.put(43, "");
    map.put(59, "");
    map.put(3, "");
    map.put(19, "");
    map.put(35, "");

    System.out.println("遍历结果：");
    for (Integer key : map.keySet()) {
      System.out.print(key + " -> ");
    }
    System.out.println();
    System.out.println(16 << 1);
    System.out.println(Integer.toBinaryString(16 - 1));
    System.out.println(Integer.toBinaryString((16 << 1) - 1));
    System.out.println(43 & (16 - 1));
    System.out.println(Integer.toBinaryString(27));
    System.out.println(Integer.toBinaryString(8));
  }
}
