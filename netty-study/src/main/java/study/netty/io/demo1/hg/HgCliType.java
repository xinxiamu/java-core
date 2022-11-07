package study.netty.io.demo1.hg;

public enum HgCliType {
    M0("m0", ""),
    M1("m1", ""),
    M2("m2", ""),
    M3("m3", ""),
    M4("m4", ""),
    S13("s13", "");

    private final String type;
    private final String desc;

    HgCliType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static HgCliType getInstance(String type) {
        HgCliType[] values = HgCliType.values();
        for (int i = 0; i < values.length; i++) {
            HgCliType hgCliType = values[i];
            if (hgCliType.getType().equals(type)) {
                return hgCliType;
            }
        }

        return null;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
