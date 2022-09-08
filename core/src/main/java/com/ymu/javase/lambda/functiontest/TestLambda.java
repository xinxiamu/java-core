package com.ymu.javase.lambda.functiontest;

import org.junit.Test;

public class TestLambda {

    @Test
    public void test01() {
        Runnable r = () -> System.out.println("aa");
        r.run();
    }

    @Test
    public void  test02() {
        FunctionItf01 f = (a, b) -> System.out.println(a + b);
        f.one("abc", "dd");
    }
}
