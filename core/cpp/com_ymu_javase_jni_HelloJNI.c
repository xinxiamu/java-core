// Save as "HelloJNI.c"
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "com_ymu_javase_jni_HelloJNI.h"   // Generated

// Implementation of the native method sayHello()
JNIEXPORT void JNICALL Java_com_ymu_javase_jni_HelloJNI_sayHello
  (JNIEnv *, jobject) {
   printf("Hello World!\n");
   return;
}