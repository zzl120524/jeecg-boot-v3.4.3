package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum TrafficDirectionType {
    FORWARD(1,"前向流"),
    BACKWARD(2,"后向流");

    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    TrafficDirectionType(int code, String desc){
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
        TrafficDirectionType[] trafficDirectionTypes = values();
        for (TrafficDirectionType trafficDirectionType : trafficDirectionTypes) {
            if (trafficDirectionType.getcode() == code) {
                return trafficDirectionType.getdesc();
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
        TrafficDirectionType[] trafficDirectionTypes = values();
        for (TrafficDirectionType trafficDirectionType : trafficDirectionTypes) {
            if (trafficDirectionType.getdesc().equals(desc)) {
                return trafficDirectionType.getcode();
            }
        }
        return -1;
    }

    public static int unknown(int code) {
        TrafficDirectionType[] trafficDirectionTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (TrafficDirectionType trafficDirectionType : trafficDirectionTypes) {
            integers.add(trafficDirectionType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }
}
