package com.ymu.javase.jni;

public class HelloJNI {

    static {
        // hello.dll (Windows) or libhello.so (Unixes)
//        System.load(""); //加载的全路径文件名
        System.loadLibrary("jni/hello");
    }

    private native void sayHello();

    public static void main(String[] args) {
        HelloJNI helloJNI = new HelloJNI();
        helloJNI.sayHello();
    }
}
