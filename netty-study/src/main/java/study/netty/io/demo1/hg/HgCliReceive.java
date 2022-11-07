package study.netty.io.demo1.hg;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class HgCliReceive {
    private String protocol = "<$pro";
    private HgCliType type;
    private String[] body;
    private String crc16;

    public HgCliReceive() {
    }

    /*public static void main(String[] args) {
        HgCliReceive receive = new HgCliReceive();
        receive.getResult("<$pro,m4*ABD0");
        System.out.println(receive.getType().getType());
        System.out.println(receive.getCrc16());
    }*/

    public HgCliReceive getResult(String rsp) {
        if (rsp == null || "".equals(rsp)) {
//            throw new NullPointerException("rsp非null,非空");
            return null;
        }

        String crc = rsp.substring(rsp.indexOf("*") + 1);
        this.crc16 = crc;
        String str = rsp.substring(protocol.length() + 1, rsp.indexOf('*'));
        String[] array = str.split(",");
        this.type = HgCliType.getInstance(array[0]);
        String[] body = new String[array.length -1];
        for (int i = 1; i < array.length; i++) {
            body[i - 1] = array[i];
        }
        this.body = body;

        return this;
    }

    public String getCrc16() {
        return crc16;
    }

    public String getProtocol() {
        return protocol;
    }

    public HgCliType getType() {
        return type;
    }


    public String[] getBody() {
        return body;
    }
}
