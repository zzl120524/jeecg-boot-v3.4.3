package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum UploadType {
    USERTRACESIGNAL(1,"用户跟踪信令"),
    USERTRACEMEDIUM(2,"用户跟踪媒体"),
    SIGNALCOPY(3,"信令复制"),
    MALFORMED(4,"异常报文"),
    UNKNOWN(999,"未知");


    private int code;
    private String desc;
    private List<Integer> integers;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    UploadType(int code, String desc){
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
        UploadType[] uploadTypes = values();
        for (UploadType uploadType : uploadTypes) {
            if (uploadType.getcode() == code) {
                return uploadType.getdesc();
            }
        }
        return null;
    }

    public static int unknown(int code) {
        UploadType[] uploadTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (UploadType uploadType : uploadTypes) {
            integers.add(uploadType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }

    /**
     * 根据value获取key
     *
     * @param desc
     * @return
     */
    public static int getCode(String desc) {
        UploadType[] uploadTypes = values();
        for (UploadType uploadType : uploadTypes) {
            if (uploadType.getdesc().equals(desc)) {
                return uploadType.getcode();
            }
        }
        return -1;
    }
}
