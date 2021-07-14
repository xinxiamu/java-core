package com.ymu.study.office;

import javassist.*;
import org.junit.Test;

import java.io.IOException;

public class AsposeCrack {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {

    }

    // 破解aspose-words-21.1-jdk17.jar
    public void test1() throws NotFoundException, CannotCompileException, IOException {
        ClassPool.getDefault().insertClassPath("F:\\sorft\\aspose-words\\aspose-words-21.1-jdk17.jar");
        CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzZE0");
        CtMethod zzZ4h = zzZJJClass.getDeclaredMethod("zzZ4h");
        CtMethod zzZ4g = zzZJJClass.getDeclaredMethod("zzZ4g");
        zzZ4h.setBody("{return 1;}");
        zzZ4g.setBody("{return 1;}");
        //这一步就是将破译完的代码放在桌面上
        zzZJJClass.writeFile("F:\\sorft\\aspose-words\\");
    }

    // 破解 aspose-pdf-20.8.jar
    // 参考网址： https://xie.infoq.cn/article/7d475ca4d2eb4632c2459fe05
    @Test
    public void test2() throws NotFoundException, CannotCompileException, IOException {
        ClassPool.getDefault().insertClassPath("F:\\sorft\\aspose\\aspose-pdf\\aspose-pdf-20.8.jar");
        CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.pdf.ADocument");
        CtMethod[] zzv = zzZJJClass.getDeclaredMethods();
        for (CtMethod m: zzv) {
            CtClass[] ps = m.getParameterTypes();
            if (ps.length == 2 && m.getName().equals("lT") && ps[0].getName().equals("com.aspose.pdf.ADocument")) {
                System.out.println(ps[0].getName());
                System.out.println(ps[1].getName());
            }

            /*破解授权*/
            if (ps.length == 2 && m.getName().equals("lT") && ps[0].getName().equals("com.aspose.pdf.ADocument") && ps[1].getName().equals("int")) {
                m.setBody("{return false;}");
            }

            /*破解水印*/
            if (m.getName().equals("lt") && ps.length == 0) {
                m.setBody("{return true;}");
            }
        }
        zzZJJClass.writeFile("F:\\sorft\\aspose\\aspose-pdf\\");
    }
}
