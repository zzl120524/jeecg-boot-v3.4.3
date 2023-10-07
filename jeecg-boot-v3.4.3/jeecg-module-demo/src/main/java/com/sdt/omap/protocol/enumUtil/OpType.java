package com.sdt.omap.protocol.enumUtil;

public enum OpType {
    START(1,"start"),
    STOP(2,"stop");

    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    OpType(int code, String desc){
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
        OpType[] opTypes = values();
        for (OpType opType : opTypes) {
            if (opType.getcode() == code) {
                return opType.getdesc();
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
        OpType[] opTypes = values();
        for (OpType opType : opTypes) {
            if (opType.getdesc().equals(desc)) {
                return opType.getcode();
            }
        }
        return -1;
    }
}
