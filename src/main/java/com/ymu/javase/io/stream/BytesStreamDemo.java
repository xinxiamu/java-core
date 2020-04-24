package com.ymu.javase.io.stream;

import org.junit.Test;

import java.io.*;

/**
 * 字节流使用演示。InputSteam和OutputStream。
 */
public class BytesStreamDemo {

//    public static void main(String[] args) throws Exception {
//        copyBytes();
//    }

    /**
     * 演示通过文件字节流
     * @throws Exception
     */
    @Test
    public void copyBytes() throws Exception {
        FileInputStream in = null;
        FileOutputStream out = null;

            try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    @Test
    public void copyBytes2() throws Exception {
        ByteArrayInputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            String aa = "A a b c d";
            out = new ByteArrayOutputStream();
            out.write(aa.getBytes("UTF-8"));

            in = new ByteArrayInputStream(out.toByteArray());

            int data = in.read();
            System.out.println(data);
            while(data != -1) {
                //do something with data

                //read next byte
                data = in.read();

                System.out.println(data);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
