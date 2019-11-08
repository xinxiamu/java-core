package com.ymu.javase.io;

import org.junit.Test;

import java.io.*;

public class BufferOutputStreamDemo {

    public static void main(String[] args) throws IOException {
        byte[] bytes = "{ab}".getBytes();
        System.out.println(bytes.length);
        System.out.println("ab".hashCode());
    }

    //一个字节，一个字节，写入缓冲区。
    @Test
    public void writeByteSingle() {
        //放到try块中，会自动关闭输出流。不必手动close。
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("H:\\a.txt"))) {
            outputStream.write(123); //123是一个字节的int字面值,123代表字节'{'，就像97代表a。
            outputStream.write(97);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //整个字节数组一次性写入。
    @Test
    public void writeByteArrays() {
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("H:\\b.txt"))) {
           byte[] bytes = {123, 97, 98, 125};
           outputStream.write(bytes);
           outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //设置缓冲区大小
    @Test
    public void setSize() {
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("H:\\b.txt"), 1024 * 2)) {
            byte[] bytes = {123, 97, 98, 125};
            outputStream.write(bytes);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
