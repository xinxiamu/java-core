package com.ymu.javase.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Java 5�����java.util.Scanner�࣬����һ������ɨ�������ı����µ�ʵ�ó���������ǰ��StringTokenizer��Matcher��֮���ĳ�ֽ�ϡ������κ����ݶ�����ͨ��ͬһģʽ�Ĳ����������ͨ��ʹ��һ�������������ı��ĸ������֡����ǿ��Խ��ʹ��������ʽ�ʹ��������м����ض�����������ķ�����������������ʹ��������ʽ֮�⣬Scanner�໹��������ض��ַ����ͻ�������(��int��double)�����ݽ��з�����������Scanner����������κ�Ҫ������ı����ݱ�д�Զ�����﷨��������
 * ��������������������������������
 */
public class ScanningAndFormattingDemo {

    public static void main(String[] args) {
        System.out.println("���ڿ���̨�������ݣ�");
        Scanner s = new Scanner(System.in); // �Ӽ��̽�������
        if (s.hasNext()) {
            System.out.println(s.next());
        }
    }

    @Test
    public void test1()  {
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));

            while (s.hasNext()) { //�ո�ָ�
                System.out.println(s.next());
                if (s.hasNextInt()) { //�ж��Ƿ�����������
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

            while (s.hasNextLine()) { //һ��һ����Ԫ�����з�����
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
            s.useDelimiter(",\\s*"); //�����ŷָ�������

            while (s.hasNext()) { //һ��һ����Ԫ
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

    //------------------------------------- format ��ʽ�����
    //https://docs.oracle.com/javase/tutorial/essential/io/formatting.html
    @Test
    public void testFormat1() {
        int i = 2;
        double r = Math.sqrt(i);

        System.out.format("The square root of %d is %f.%n", i, r);
    }
}
