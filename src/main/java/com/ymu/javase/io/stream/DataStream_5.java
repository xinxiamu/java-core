package com.ymu.javase.io.stream;

import org.junit.Test;

import java.io.*;

//java.io.DataInputStream使您可以从InputStream中读取Java原语（boolean，char，byte，short，int，long，float和double）的二进制I / O以及String值，而不仅仅是原始字节。
//所有数据流都实现DataInput接口或DataOutput接口。同时其也是InputStream的子类，进一步扩展以可以操作原始数据类型。
//Java DataInputStream类是InputStream的子类，因此DataInputStream也具有基本的读取方法，使您可以从基础InputStream中读取单个字节或字节数组（如果需要）。
//数据流操作的是原始类型的二进制，所以在文本中不能友好看到数据。可以使用PrintWriter类的print()和PrintLn()方法可以轻松地实现将Java的各种数据类型转换为字符串类型输出。
public class DataStream_5 {

    static final String dataFile = "invoicedata";

    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = {
            "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain"
    };

    public static void main(String[] args) throws IOException {
        //写数据到文本。
        /*DataOutputStream out = null;
        out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(dataFile)));

        for (int i = 0; i < prices.length; i ++) {
            out.writeDouble(prices[i]);
            out.writeInt(units[i]);
            out.writeUTF(descs[i]);
        }*/


        DataInputStream in = null;
        in = new DataInputStream(new
                BufferedInputStream(new FileInputStream(dataFile)));

        double price;
        int unit;
        String desc;
        double total = 0.0;

        while (true) {
            price = in.readDouble();
            unit = in.readInt();
            desc = in.readUTF();
            System.out.format("You ordered %d" + " units of %s at $%.2f%n",
                    unit, desc, price);
            total += unit * price;
        }

    }

    @Test
    public void DataOutputSteamTest() throws IOException {
        BufferedOutputStream out = null;
        DataOutputStream dataOutputStream = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream("binary.data"));
            dataOutputStream = new DataOutputStream(out);

            /*dataOutputStream.write(45); //byte data
            dataOutputStream.writeInt(44545); // int data
            dataOutputStream.writeDouble(33.33D);
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChar('b'); // 字符
            dataOutputStream.writeShort(3); // short int
            dataOutputStream.writeUTF("中国人"); //utf-8字符串
            dataOutputStream.writeFloat(10.55F);*/

            dataOutputStream.writeInt(123);
            dataOutputStream.writeFloat(123.45F);
            dataOutputStream.writeLong(789);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    @Test
    public void DataInputStreamTest() {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("binary.data"));

            try(DataInputStream dataInputStream = new DataInputStream(in)) {
                int   int123     = dataInputStream.readInt();
                float float12345 = dataInputStream.readFloat();
                long  long789    = dataInputStream.readLong();

                dataInputStream.close();

                System.out.println("int123     = " + int123);
                System.out.println("float12345 = " + float12345);
                System.out.println("long789    = " + long789);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
