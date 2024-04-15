class HelloWorld {
    // This declares that the static `hello` method will be provided
    // a native library.
    private static native String hello(String input);

    static {
        // This actually loads the shared object that we'll be creating.
        // The actual location of the .so or .dll may differ based on your
        // platform.
        System.loadLibrary("jni/hgrs_codec");
//        System.load("K:\\xinxiamu.github.com\\java-core\\core\\src\\main\\resources\\jni\\hgrs_codec.dll");
    }

    // The rest is just regular ol' Java!
    public static void main(String[] args) {
        String output = HelloWorld.hello("josh");
        System.out.println(output);
    }
}
