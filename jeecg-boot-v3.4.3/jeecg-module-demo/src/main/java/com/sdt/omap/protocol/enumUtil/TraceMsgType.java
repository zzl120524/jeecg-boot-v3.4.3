package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum TraceMsgType {
    SIP(1,"SIP信令"),
    ISUP(2,"ISUP信令"),
    XC(3,"XC信令"),
    SMPP(4,"SMPP信令"),
    H248(5,"H.248信令");



    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    TraceMsgType(int code, String desc){
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
        TraceMsgType[] traceMsgTypes = values();
        for (TraceMsgType traceMsgType : traceMsgTypes) {
            if (traceMsgType.getcode() == code) {
                return traceMsgType.getdesc();
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
        TraceMsgType[] traceMsgTypes = values();
        for (TraceMsgType traceMsgType : traceMsgTypes) {
            if (traceMsgType.getdesc().equals(desc)) {
                return traceMsgType.getcode();
            }
        }
        return -1;
    }

    public static int unknown(int code) {
        TraceMsgType[] traceMsgTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (TraceMsgType traceMsgType : traceMsgTypes) {
            integers.add(traceMsgType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }
}
