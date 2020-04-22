package com.ymu.simple.java.stringandarrays;

/**
 * 字符串不可变性。
 * <p>
 *     总而言之，一旦在memory（heap）中创建了一个字符串，就无法更改它。更改了，就会创建新的字符串对象返回。 String的所有方法都不会更改字符串本身，而是返回一个新的String。
 * </p>
 * <p>
 *     如果我们需要一个可以修改的字符串，则需要StringBuffer或StringBuilder。否则，由于每次创建新的String，都会浪费大量时间进行垃圾回收。
 * </p>
 */
public class Str_immutability {

    public static void main(String[] args) {
        String s = "abcd"; //声明字符串，会堆创建一个字符串对象。变量s存储字符串对象的引用
        String s2 = s; //s赋于s2。s2存储相同的参考值，因为它是相同的字符串对象。所以，实际上，s和s2两个变量都存储的是相同字符串对象的引用。
        System.out.println(s.equals(s2)); //输出true，值是一样的。
        System.out.println(s == s2);//输出true，代表的是同一个对象。

        s = s.concat("ef"); //s字符串拼接一段，此时，会在堆中创建一个新的对象，s会重新存储该新字符对象的引用。
        System.out.println(s.equals(s2)); //输出false，不同字符串值
        System.out.println(s == s2);//输出false，不同字符串对象
    }
}
