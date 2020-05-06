package com.ymu.javase.io.stream;

import java.io.*;

//对象流。支持对象I/O
//封装了InputStream,直接操作对象，对对象序列化和反序列化。实现了Serializable接口的对象才可以序列化和反序列化，否则序列化失败。
// 对象流ObjectInputStream和ObjectOutputStream是InputStream和OutputStream的子类，并实现了ObjectInput。
public class ObjectStreamDemo_6 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream("person.bin"));
        Person person = new Person();
        person.name = "Jakob Jenkov";
        person.age  = 40;
        objectOutputStream.writeObject(person);
        objectOutputStream.close(); //记得关闭

        //反序列化
        InputStream inputStream = new FileInputStream("person.bin");
        //java7特性，不管如何，总会关闭流资源
        //不用再显试调用close(),try-with-resources构造可以解决这一问题。
        try(ObjectInputStream objectInputStream =
                    new ObjectInputStream(inputStream)) {
            Person personRead = (Person) objectInputStream.readObject();
            System.out.println(personRead.name);
            System.out.println(personRead.age);
        }
    }

    /**
     * 可序列化对象。
     */
    public static class Person implements Serializable {
        public String name = null;
        public int    age  =   0;
        public String sex = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
