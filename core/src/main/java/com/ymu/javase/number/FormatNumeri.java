package com.ymu.javase.number;

import java.util.Calendar;
import java.util.Locale;

/**
 * 格式化数字输出。
 *
 * @author mumu
 */
public class FormatNumeri {

    public static void main(String[] args) {
        int intVar = 461012;
        float floatVar = 0.1f;
        String stringVar = "abc";

        System.out.format("The value of " + "the float variable is " +
                "%f, while the value of the " + "integer variable is %d, " +
                "and the string is %s", floatVar, intVar, stringVar);
        System.out.println();
        //本地化输出
        System.out.format(Locale.CANADA_FRENCH,
                "The value of the float " + "variable is %f, while the " +
                        "value of the integer variable " + "is %d, and the string is %s%n",
                floatVar, intVar, stringVar);

        /////////////////////
        long n = 461012;
        System.out.format("%d%n", n);      //  -->  "461012"
        System.out.format("%08d%n", n);    //  -->  "00461012"  //八位，前面补零
        System.out.format("%+8d%n", n);    //  -->  " +461012"
        System.out.format("%,8d%n", n);    // -->  " 461,012"
        System.out.format("%+,8d%n%n", n); //  -->  "+461,012"

        double pi = Math.PI;

        System.out.format("%f%n", pi);       // -->  "3.141593"
        System.out.format("%.3f%n", pi);     // -->  "3.142"
        System.out.format("%10.3f%n", pi);   // -->  "     3.142"
        System.out.format("%-10.3f%n", pi);  // -->  "3.142"
        System.out.format(Locale.FRANCE,
                "%-10.4f%n%n", pi); // -->  "3,1416"

        Calendar c = Calendar.getInstance();
        System.out.format("%tB %te, %tY%n", c, c, c); // -->  "May 29, 2006"

        System.out.format("%tl:%tM %tp%n", c, c, c);  // -->  "2:34 am"

        System.out.format("%tD%n", c);    // -->  "05/29/06"
    }


}
