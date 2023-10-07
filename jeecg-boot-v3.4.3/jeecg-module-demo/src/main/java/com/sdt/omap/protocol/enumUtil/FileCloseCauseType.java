package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum FileCloseCauseType {
    CAUSE1(1,"事件到达"),
    CAUSE2(2,"容量到达");



    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    FileCloseCauseType(int code, String desc){
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
        FileCloseCauseType[] fileCloseCauseTypes = values();
        for (FileCloseCauseType fileCloseCauseType : fileCloseCauseTypes) {
            if (fileCloseCauseType.getcode() == code) {
                return fileCloseCauseType.getdesc();
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
        FileCloseCauseType[] fileCloseCauseTypes = values();
        for (FileCloseCauseType fileCloseCauseType : fileCloseCauseTypes) {
            if (fileCloseCauseType.getdesc().equals(desc)) {
                return fileCloseCauseType.getcode();
            }
        }
        return -1;
    }

    public static int unknown(int code) {
        FileCloseCauseType[] fileCloseCauseTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (FileCloseCauseType fileCloseCauseType : fileCloseCauseTypes) {
            integers.add(fileCloseCauseType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }
}
