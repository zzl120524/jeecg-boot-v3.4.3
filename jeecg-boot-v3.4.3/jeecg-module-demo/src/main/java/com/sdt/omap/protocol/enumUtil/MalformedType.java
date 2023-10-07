package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum MalformedType {
    SIP_EXCEPTION(1,"SIP信令解析异常"),
    ISUP_EXCEPTION(2,"ISUP信令解析异常"),
    XC_EXCEPTION(3,"XC信令解析异常"),
    RTP_EXCEPTION(4,"RTP报文解析异常"),
    H248_EXCEPTION(5,"H.248报文解析异常"),
    OTHER_EXCEPTION(6,"其他未识别报文");


    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    MalformedType(int code, String desc){
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
        MalformedType[] malformedTypes = values();
        for (MalformedType malformedType : malformedTypes) {
            if (malformedType.getcode() == code) {
                return malformedType.getdesc();
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
        MalformedType[] malformedTypes = values();
        for (MalformedType malformedType : malformedTypes) {
            if (malformedType.getdesc().equals(desc)) {
                return malformedType.getcode();
            }
        }
        return -1;
    }

    public static int unknown(int code) {
        MalformedType[] malformedTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (MalformedType malformedType : malformedTypes) {
            integers.add(malformedType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }
}
