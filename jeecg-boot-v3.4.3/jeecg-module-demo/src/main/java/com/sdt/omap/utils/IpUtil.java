package com.sdt.omap.utils;

import com.sdt.util.standard.ByteUtil;

public class IpUtil {
    /**将给定的字节数组转换成IPV4的十进制分段表示格式的ip地址字符串*/
    public static String binaryArray2Ipv4Address(byte[]addr){
        String ip="";
        for(int i=0;i<addr.length;i++){
            ip+=(addr[i]&0xFF)+".";
        }
        return ip.substring(0, ip.length()-1);
    }
    /**将给定的用十进制分段格式表示的ipv4地址字符串转换成字节数组*/
    public static byte[] ipv4Address2BinaryArray(String ipAdd){
        byte[] binIP = new byte[4];
        String[] strs = ipAdd.split("\\.");
        for(int i=0;i<strs.length;i++){
            binIP[i] = (byte) Integer.parseInt(strs[i]);
        }
        return binIP;
    }

    public static void main(String[] args) {
        String ip = "192.168.1.11";
        byte[] bytes = ipv4Address2BinaryArray(ip);
        byte[] bytes1 = {(byte) -64, (byte) -88, (byte) 1, (byte) 11};
//        byte[] bytes2 = {45,b4,02,4c,b1,01,40,00,40,11,03,41,c0,a8,01,32,c0,a8,01,28};
        String s = binaryArray2Ipv4Address(bytes1);
        System.out.println(ByteUtil.byte2HexString(bytes,true));
        System.out.println(s);
    }
}
