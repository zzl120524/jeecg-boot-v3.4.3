package com.sdt.omap.protocol.other;

import com.sdt.util.standard.ByteUtil;

public class NumberTransfer {

    public static byte[] PhoneNumToBCD(String strPhoneNum) {// 以0xf结束

        int strlen = strPhoneNum.length();
        byte buff[] = new byte[8];
        String substr = null;
        if (strlen % 2 == 0) {
            for (int i = 0; i < strlen / 2; i++) {
                substr = strPhoneNum.substring(2 * i, 2 * i + 2);
                byte temp = Byte.parseByte(substr);
                buff[i] = (byte) (temp / 10 * 16 + temp % 10);
            }
            buff[strlen / 2] = (byte) 0xf0;
        } else {
            for (int i = 0; i < strlen / 2; i++) {
                substr = strPhoneNum.substring(2 * i, 2 * i + 2);
                byte temp = Byte.parseByte(substr);
                buff[i] = (byte) (temp / 10 * 16 + temp % 10);
            }
            substr = strPhoneNum.substring(strlen - 1, strlen);
            byte hiOfByte = Byte.parseByte(substr);
            byte loOfByte = 0x0f;
            buff[strlen / 2] = (byte) ((hiOfByte << 4) | loOfByte);
        }
        return buff;
    }


    public static String BCDToStrPhoneNum(byte[] buff) { // buff以0xf结尾
        StringBuilder strPhoneNum = new StringBuilder();
        String strTemp = "";
        for (int i = 0; i < 8; i++) {
            byte temp = (byte) ((buff[i] & 0xf0) >> 4);
            if (temp == 0x0f) {
                break;
            } else {
                strTemp = Integer.toString(temp);

                temp = (byte) (buff[i] & 0x0f);
                if (temp == 0x0f) {
                    break;
                } else {
                    strTemp = Integer.toString(temp);
                    strPhoneNum.append(strTemp);
                }
            }
        }
        return strPhoneNum.toString();
    }

    public static void main(String[] args) {
        System.out.println(ByteUtil.byte2HexString0x(NumberTransfer.PhoneNumToBCD("13905172345"), true));
        System.out.println(NumberTransfer.BCDToStrPhoneNum(new byte[]{(byte) 0x01, (byte) 0x31, (byte) 0x09, (byte) 0x15, (byte) 0x27, (byte) 0x43, (byte) 0xf5, (byte) 0x00}));
    }
}
