package study.netty.io.demo1.hg;

public final class HgCliSend {
    private String protocol = ">$pro";
    private HgCliType type;
    private String[] body;

    public HgCliSend() {
    }

    public HgCliSend(HgCliType type, String... body) {
        this.type = type;
        this.body = body;
    }

    public String getResult() {
        if (this.type == null) {
            throw new NullPointerException("type非null");
        }

        StringBuffer sb = new StringBuffer(getProtocol());
        sb.append(",").append(this.type.getType());
        if (this.body != null && this.body.length > 0) {
            sb.append(",");
            for (int i = 0; i < this.body.length; i++) {
                String d = this.body[i];
                if (i == this.body.length - 1) {
                    sb.append(d);
                } else {
                    sb.append(d).append(",");
                }
            }
        }

        String sub = sb.substring(2, sb.length());
        String crc16CCITT = CRCUtils.crc16CCITT(sub.getBytes());
        sb.append("*").append(crc16CCITT);
        sb.append("\r").append("\n");

        return sb.toString();
    }

    public String getProtocol() {
        return protocol;
    }

    public HgCliType getType() {
        return type;
    }

    public void setType(HgCliType type) {
        this.type = type;
    }

    public String[] getBody() {
        return body;
    }

    public void setBody(String... body) {
        this.body = body;
    }
}
