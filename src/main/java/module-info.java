module java.core {
    requires junit;
    requires java.sql;
    requires commons.net;
    requires slf4j.api;
    requires java.desktop;
    requires java.net.http;
    requires org.apache.commons.lang3;
    exports com.ymu.javase.io.stream;
    exports com.ymu.javase.thread;
    exports  com.ymu.javase.io.fileio;
}