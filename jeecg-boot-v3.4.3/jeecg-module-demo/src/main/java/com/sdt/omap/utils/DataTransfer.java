package com.sdt.omap.utils;

import com.sdt.util.standard.ByteUtil;

public class DataTransfer {

    public static byte[] padding(byte[] data) {

        int otherLen = 0;
        if (data.length <= 16) {
            otherLen = 16 - data.length;
        } else {
            otherLen = 16 - data.length % 16;
        }

        if (otherLen == 0) {
            return data;
        } else {
            byte[] otherData = new byte[otherLen];
            for (int i = 0; i < otherLen; i++) {
//                otherData[i] = (byte) otherLen;// TODO 最后一字节为0的时候，如何处理？
                otherData[i] = 0x00;
            }
            byte[] newData = new byte[data.length + otherLen];

            System.arraycopy(data, 0, newData, 0, data.length);
            System.arraycopy(otherData, 0, newData, data.length, otherLen);

            return newData;
        }
    }

    public static void main(String[] args) {
        System.out.println(ByteUtil.byte2HexString0x(new DataTransfer().padding(
                new byte[]{0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01}), true));
    }
}
