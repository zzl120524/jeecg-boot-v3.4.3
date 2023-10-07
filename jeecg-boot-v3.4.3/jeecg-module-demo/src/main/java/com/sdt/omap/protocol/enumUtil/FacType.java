package com.sdt.omap.protocol.enumUtil;

public enum FacType {
    FACA(0,"FAC A"),
    FACB(1,"FAC B"),
    FACC(10,"FAC C"),
    FACD(11,"FAC D"),
    FACE(100,"FAC E");


    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    FacType(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    //4.其他诉求：获取枚举类对象的属性
    public int getcode() {
        return code;
    }
    public String getdesc() {
        return desc;
    }

    /**
     * 根据key获取value
     *
     * @param code
     * @return
     */
    public static String getValue(int code) {
        FacType[] facTypes = values();
        for (FacType facType : facTypes) {
            if (facType.getcode() == code) {
                return facType.getdesc();
            }
        }
        return null;
    }

    /**
     * 根据value获取key
     *
     * @param desc
     * @return
     */
    public static int getCode(String desc) {
        FacType[] facTypes = values();
        for (FacType facType : facTypes) {
            if (facType.getdesc().equals(desc)) {
                return facType.getcode();
            }
        }
        return -1;
    }
}
