package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum RecType {
    HEADREOCORD(16,"HeadReocord"),
    CDRRECORD(32,"CDRRecord-SIP"),
    ENDREOCORD(144,"EndReocord");



    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    RecType(int code, String desc){
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
        RecType[] recTypes = values();
        for (RecType recType : recTypes) {
            if (recType.getcode() == code) {
                return recType.getdesc();
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
        RecType[] recTypes = values();
        for (RecType recType : recTypes) {
            if (recType.getdesc().equals(desc)) {
                return recType.getcode();
            }
        }
        return -1;
    }

    public static int unknown(int code) {
        RecType[] recTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (RecType recType : recTypes) {
            integers.add(recType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }
}
