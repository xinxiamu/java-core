package com.ymu.javase.io.stream;

import org.junit.Test;

import java.io.*;

public class BufferInputStreamDemo_3 {

    public static void main(String[] args) throws IOException {

    }

    //逐行读取文件
    @Test
    public void test1() throws IOException {
        InputStream inputStream = new FileInputStream("data.txt");
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        while (br.readLine() != null) {
            System.out.println(br.readLine());
        }
    }

    //看代码，似乎是和readlimit参数有关。但是仔细查看相关代码，可知，BufferedInputStream默认开8k的缓冲区，如果你在开始就mark，那么理论上最长可以read 8k数据，还可以成功reset回来。
    //
    //  InputStream方法里关于mark的文档，仅仅是一个最低实现要求。也就是说，如果支持mark,那么当你读取不超过readlimit个字节时，reset必须还原至mark的点。但是如果你有能力缓冲更多，则没有要求，也就是说，并没有强制要求读取超过readlimit时必须失效
    @Test
    public void testMarkAndReset0() throws IOException {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data.txt"));
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            bis.mark(3);//在第三个字符打标签，读取六个字符之内标签有效。
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            bis.reset();//超过3个字符后，标签并没有失效
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            bis.reset();
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMarkAndReset1() throws IOException {
        try {
            int c = 0;
            byte [] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedInputStream bis = new BufferedInputStream(bais);
            bis.read();
            bis.mark(3);//3这个参数貌似不起作用
            for(int i = 0;i<6&&(c = bis.read()) != -1;i++){
                System.out.print(c);
            }
            System.out.println();
            bis.reset();
            for(int i = 0;i<6&&(c = bis.read()) != -1;i++){
                System.out.print(c);
            }
            System.out.println();
            bis.reset(); //还是能重新回到打标签的字符
            System.out.println(bis.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试mark()和reset()功能。
     *
     * @throws IOException
     */
    @Test
    public void testMarkAndReset() throws IOException {
        BufferInputStreamDemo_3 test = new BufferInputStreamDemo_3();
        String fileName = "data.txt";
        InputStream in1 = new FileInputStream(new File(fileName));
        if (!in1.markSupported()) {
            in1 = new BufferedInputStream(in1);//FileInputStream不支持mark()和reset()方法，所以要包装到BufferedInputStream中
        }
        test.read(in1);
        byte[] buf = new byte[100];
        while (in1.read(buf, 0, buf.length) != -1) {
            System.out.println(buf);
        }

        System.out.println("Success!");
    }

    public void read(InputStream in) throws IOException {
        if (in == null) {
            return;
        }
        int len = 0;
        in.mark(1);
        in.read(); //第一次读取
        in.reset();//又可以重新读取

        in.read();//第二次读取跟第一次读取结果一样。因为只读了一个，没有超过mark设置的整数。所以mark有效
    }

}
