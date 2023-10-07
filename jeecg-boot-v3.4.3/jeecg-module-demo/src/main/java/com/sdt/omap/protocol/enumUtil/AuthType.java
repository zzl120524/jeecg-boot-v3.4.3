package com.sdt.omap.protocol.enumUtil;

public enum AuthType {
    CERTIFIED("0","已认证"),
    NOTCERTIFIED("1","未认证");



    private final String code;
    private final String desc;
    //3.私化类的构造器（构造器默认就是private）,并给对象属性赋值
    AuthType(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
    //4.其他诉求：获取枚举类对象的属性
    public String getcode() {
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
    public static String getValue(String code) {
        AuthType[] AuthTypes = values();
        for (AuthType authType : AuthTypes) {
            if (authType.getcode().equals(code)) {
                return authType.getdesc();
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
    public static String getCode(String desc) {
        AuthType[] AuthTypes = values();
        for (AuthType authType : AuthTypes) {
            if (authType.getdesc().equals(desc)) {
                return authType.getcode();
            }
        }
        return null;
    }

}
