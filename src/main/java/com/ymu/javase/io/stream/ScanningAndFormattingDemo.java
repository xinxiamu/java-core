package com.ymu.javase.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Java 5添加了java.util.Scanner类，这是一个用于扫描输入文本的新的实用程序。它是以前的StringTokenizer和Matcher类之间的某种结合。由于任何数据都必须通过同一模式的捕获组检索或通过使用一个索引来检索文本的各个部分。于是可以结合使用正则表达式和从输入流中检索特定类型数据项的方法。这样，除了能使用正则表达式之外，Scanner类还可以任意地对字符串和基本类型(如int和double)的数据进行分析。借助于Scanner，可以针对任何要处理的文本内容编写自定义的语法分析器。
 * ————————————————
 */
public class ScanningAndFormattingDemo {

    public static void main(String[] args) {
        System.out.println("请在控制台输入数据：");
        Scanner s = new Scanner(System.in); // 从键盘接收数据
        if (s.hasNext()) {
            System.out.println(s.next());
        }
    }

    @Test
    public void test1()  {
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));

            while (s.hasNext()) { //空格分隔
                System.out.println(s.next());
                if (s.hasNextInt()) { //判断是否是整型数字
                    System.out.println(">>int:" + s.nextInt());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    @Test
    public void test2()  {
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));

            while (s.hasNextLine()) { //一行一个单元，换行符结束
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    @Test
    public void test3()  {
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));
            s.useDelimiter(",\\s*"); //按逗号分隔，正则

            while (s.hasNext()) { //一行一个单元
                System.out.println(s.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    @Test
    public void test4()  {
        Scanner s = null;
        double sum = 0;

        try {
            s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    sum += s.nextDouble();
                } else {
                    s.next();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

        System.out.println(sum);
    }

    //------------------------------------- format 格式化输出
    //https://docs.oracle.com/javase/tutorial/essential/io/formatting.html
    @Test
    public void testFormat1() {
        int i = 2;
        double r = Math.sqrt(i);

        System.out.format("The square root of %d is %f.%n", i, r);
    }
}
