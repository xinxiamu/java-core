package com.ymu.javase.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

//Java NIO的通道类似流，但又有些不同：
//既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
//通道可以异步地读写。
//通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。
public class ChannelTest {

    public static void main(String[] args) {
    }

    //FileChannel操作
    @Test
    public void test01() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel(); //打开一个与文件连接的通道，操作文件

        //分配一个新的字节缓冲区
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); // 通过文件通道读取文件内容到缓冲区
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();//切换读取数据的模式

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear(); //清空缓冲区
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
