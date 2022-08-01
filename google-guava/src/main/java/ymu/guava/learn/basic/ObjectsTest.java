package ymu.guava.learn.basic;

import com.google.common.base.Objects;
import org.junit.Test;

public class ObjectsTest {

    // 对象比较
    @Test
    public void equalsTest() {
        System.out.println(Objects.equal("a", "a")); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
    }

    // 用对象的所有字段作散列[hash]运算应当更简单。Guava的Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。你可以使用Objects.hashCode(field1, field2, …, fieldn)来代替手动计算散列值。
    @Test
    public void hashCodeTest() {
        // Returns "ClassName{x=1}"
        int h = Objects.hashCode(8, "ABS");
        System.out.println(h);
    }
}
