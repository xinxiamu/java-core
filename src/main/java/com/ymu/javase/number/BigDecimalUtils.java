package com.ymu.javase.number;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static void main(String[] args) {
        Integer i = 3050;
        BigDecimal b = BigDecimal.valueOf(i);
        BigDecimal c = BigDecimal.valueOf(8800.00000000);
        System.out.println(">>>>:" + b.divide(c, 4, BigDecimal.ROUND_HALF_UP)); //相除避免无限循环，所以要设定保留小数
    }
}
