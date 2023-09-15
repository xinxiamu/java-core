package com.core.jdk17.basic;

/**
 * 数组学习
 */
public class ArrayDemo {

    //拷贝数组
    public static void arraycopy() {
        String[] copyFrom = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto" };

        String[] copyTo = new String[7];
        System.arraycopy(copyFrom, 2, copyTo, 0, 7); //数组拷贝方法
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }
    }

    //java.util.Arrays
    //学习该类下各种数组操作方法
    //类中的方法提供的其他一些有用的操作java.util.Arrays是：
    //在数组中搜索特定值以获取该值所在的索引（方法binarySearch()）。
    //比较两个数组以确定它们是否相等（equals()方法）。
    //填充数组以在每个索引处放置特定值（fill()方法）。
    //将数组按升序排序。这可以使用该方法顺序完成，sort()也可以使用parallelSort()Java SE 8 中引入的方法同时完成。多处理器系统上大型数组的并行排序比顺序数组排序更快。
    //创建一个使用数组作为源的流（stream()方法）。例如，以下语句copyTo以与上一个示例相同的方式打印数组的内容：
    public static void arrayUtils() {
        String[] copyFrom = {
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",
                "Marocchino", "Ristretto" };

        String[] copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);
        for (String coffee : copyTo) {
            System.out.print(coffee + " ");
        }
    }

    public static void main(String[] args) {

        //测试数组复制
        arraycopy();
    }
}
