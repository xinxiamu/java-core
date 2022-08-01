package com.ymu.javase.io.stream;

import org.junit.Test;

import java.io.*;

/**
 * 字符流。所有字符流都继承Reader和Writer类。
 */
public class CharacterStreamDemo_2 {

    //逐个字符读取，16位的int表示一个字符
    @Test
    public void copyCharacters() throws IOException {
        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    //逐行读取文本， 放到缓冲区
    @Test
    public void copyCharactersLine() throws IOException {
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("xanadu.txt"));
            outputStream = new PrintWriter(new FileWriter("characteroutput.txt"));

            String l; //表示一行
            while ((l = inputStream.readLine()) != null) {
                outputStream.write(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
