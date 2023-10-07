package com.sdt.omap.protocol.enumUtil;

import java.util.ArrayList;
import java.util.List;

public enum ReleaseCauseType {
    CAUSE1(1,"主叫早释"),
    CAUSE2(2,"主叫正常释放"),
    CAUSE3(3,"被叫正常释放"),
    CAUSE4(4,"老化释放");



    private int code;
    private String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    ReleaseCauseType(int code, String desc){
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
        ReleaseCauseType[] paraFlagTypes = values();
        for (ReleaseCauseType paraFlagType : paraFlagTypes) {
            if (paraFlagType.getcode() == code) {
                return paraFlagType.getdesc();
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
        ReleaseCauseType[] paraFlagTypes = values();
        for (ReleaseCauseType paraFlagType : paraFlagTypes) {
            if (paraFlagType.getdesc().equals(desc)) {
                return paraFlagType.getcode();
            }
        }
        return -1;
    }

    public static int unknown(int code) {
        ReleaseCauseType[] paraFlagTypes = values();
        List<Integer> integers = new ArrayList<>();
        for (ReleaseCauseType paraFlagType : paraFlagTypes) {
            integers.add(paraFlagType.code);
        }
        if (!integers.contains(code)){
            return 999;
        }
        return code;
    }
}
