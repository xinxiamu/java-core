package ymu.guava.learn.basic;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.junit.Test;

// 介绍如何避免空指针
public class NullAvoiding {
    public static void main(String[] args) {
        Optional<String> r = compareReturnOption(null, "dd");
        if (r.isPresent()) {
            System.out.println("null");
        }
    }

    @Test
    public void optionalTest() {
        // 创建指定引用的Optional实例，若引用为null则快速失败
//        Optional<Integer> possible = Optional.of(null); // 如果是null，快速失败，抛出异常
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());// returns true， 非空
        System.out.println(possible.get()); // 返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
    }

    @Test
    public void optionalTest2() {
//        Optional<String> possible = Optional.fromNullable(null); // 创建指定引用的Optional实例，若引用为null则表示缺失
        Optional<String> possible = Optional.fromNullable("abc");
        System.out.println(possible.isPresent()); // 包含非null返回true，否则返回false
        System.out.println(possible.or("abc")); // 为null输出默认abc
        System.out.println(possible.orNull()); // 返回Optional所包含的引用，若引用缺失，返回null
        System.out.println(possible.asSet()); //返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
    }

    @Test
    public void optionalTest3() {
        Optional<String> possible = Optional.absent(); // 创建引用缺失的Optional实例。还没指定值
        System.out.println(possible.or("abc"));
        System.out.println(possible.asSet());
    }

    // Strings.emptyToNull
    // 如果空则返回null，null也是返回null，否则返回该字符串
    @Test
    public void emptyToNullTest() {
        String s = Strings.emptyToNull("ab");
        System.out.println(s);
        String s1 = Strings.emptyToNull(null);
        System.out.println(s1);
        String s3 = Strings.emptyToNull("");
        System.out.println(s3);
    }

    // null返回空
    @Test
    public void nullToEmptyTest() {
        String s1 = Strings.nullToEmpty("");
        String s2 = Strings.nullToEmpty(null);
        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);
    }

    // 如果空，null，则返回true
    @Test
    public void isNullOrEmptyTest() {
        boolean b = Strings.isNullOrEmpty("ab");
        boolean b2 = Strings.isNullOrEmpty("");
        boolean b3 = Strings.isNullOrEmpty(null);
        System.out.println(b);
        System.out.println(b2);
        System.out.println(b3);
    }

    /**
     * 以自然顺序比较两个字符串并返回较大的字符串。
     * 只需要返回值包装，提示调用着做null判断。
     */
    public static Optional<String> compareReturnOption(String firstString, String secondString) {
        if (firstString == null || secondString == null) {
            return Optional.fromNullable(null);
        }
        if (firstString.compareTo(secondString) >= 0) {
            return Optional.of(firstString);
        }
        return Optional.of(secondString);
    }
}
