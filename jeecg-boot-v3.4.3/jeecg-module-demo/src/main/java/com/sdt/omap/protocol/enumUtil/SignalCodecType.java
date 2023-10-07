package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum SignalCodecType {
    SIP(1,"SIP信令"),
    ISUP(2,"ISUP信令"),
    XC(3,"XC信令"),
    SMPP(4,"SMPP信令"),
    H248(5,"H.248信令"),
    IKE(6,"IKE信令");


    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    SignalCodecType(int code, String desc){
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
        SignalCodecType[] signalCodecTypes = values();
        for (SignalCodecType signalCodecType : signalCodecTypes) {
            if (signalCodecType.getcode() == code) {
                return signalCodecType.getdesc();
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
        SignalCodecType[] signalCodecTypes = values();
        for (SignalCodecType signalCodecType : signalCodecTypes) {
            if (signalCodecType.getdesc().equals(desc)) {
                return signalCodecType.getcode();
            }
        }
        return -1;
    }

    public static int unknown(int code) {
        SignalCodecType[] signalCodecTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (SignalCodecType signalCodecType : signalCodecTypes) {
            integers.add(signalCodecType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }
}
