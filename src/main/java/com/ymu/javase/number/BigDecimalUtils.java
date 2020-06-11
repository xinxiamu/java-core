package com.ymu.javase.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalUtils {

    public static void main(String[] args) {
        Integer i = 3050;
        BigDecimal b = BigDecimal.valueOf(i);
        BigDecimal c = BigDecimal.valueOf(8800.00000000);
        System.out.println(">>>>:" + b.divide(c, 4, BigDecimal.ROUND_HALF_UP)); //相除避免无限循环，所以要设定保留小数
    }

    /**
     * 测试向上，向下取整，四舍五保留固定小数位。
     */
    @Test
    public void test1() {
        double d = 3.2458;
//        BigDecimal dd = BigDecimal.valueOf(d).setScale(0, BigDecimal.ROUND_UP); //旧的
        BigDecimal dd = BigDecimal.valueOf(d).setScale(0, RoundingMode.UP); //向上取整，java9以上
        BigDecimal ddd = BigDecimal.valueOf(d).setScale(0, RoundingMode.DOWN); //向下取整，java9以上
        System.out.println(">>>>向上取整:" + dd);
        System.out.println(">>>>向下取整:" + ddd);

        //四舍五入，保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String d4 = decimalFormat.format(d);
        System.out.println(">>>>>四舍五入保留两位小数方式一：" + d4);
        //方式二，// #.00 表示两位小数 #.0000四位小数
        DecimalFormat df2 =new DecimalFormat("#.00");
        String d5 = df2.format(BigDecimal.valueOf(3.2448));
        System.out.println(">>>>四舍五入保留两位小数,方式二：" + d5);
    }

    /**
     * 加减乘除演示
     */
    @Test
    public void test2() {

    }
    
}
