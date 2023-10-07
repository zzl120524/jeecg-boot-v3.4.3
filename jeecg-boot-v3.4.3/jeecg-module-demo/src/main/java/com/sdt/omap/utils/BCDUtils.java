package com.sdt.omap.utils;

import com.sdt.util.standard.ByteUtil;
import org.springframework.stereotype.Component;

@Component
public class BCDUtils {

    public static byte[] toBCDCode(String num) {
        int size = num.length() / 2;
        int remainder = num.length() % 2;
        byte[] bcdBytes = new byte[size + remainder];
        for (int i = 0; i < size; i++) {
            int low = Integer.parseInt(num.substring(2 * i, 2 * i + 1));
            int high = Integer.parseInt(num.substring(2 * i + 1, 2 * i + 2));
            bcdBytes[i] = (byte) (high << 4 | low);
        }

        if (remainder > 0) {
            int low = Integer.parseInt(num.substring(num.length() - 1));
            bcdBytes[bcdBytes.length - 1] = (byte) (0xf << 4 | low);
        }
     /*   byte[] result = new byte[7];
        if (size + remainder == 7) {
            return bcdBytes;
        } else {
            System.arraycopy(bcdBytes, 0, result, 0, bcdBytes.length);
            return result;
        }*/
        byte[] result = new byte[24];
        if (size + remainder == 24) {
            return bcdBytes;
        } else {
            System.arraycopy(bcdBytes, 0, result, 0, bcdBytes.length);
            if (bcdBytes.length < result.length){
                for ( int j = bcdBytes.length ; j < result.length; j++) {
                    result[j] = (byte) 0xff;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        // 13905172345
        // 31 09 15 27 43 F5 00
//        System.out.println(ByteUtil.byte2HexString0x(toBCDCode("13905172345"), true));
//        byte[] result = new byte[]{0x31,0x09,0x15,0x27,0x43,(byte)0xf5,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff, (byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff};
        byte[] result = new byte[]{0x11, 0x11, 0x31, 0x19, 0x04, 0x30, (byte)0x98, (byte)0xf5, 0x00, 0x00, 0x00,  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
//        byte[] result = new byte[]{(byte)0x99, 0x65, 0x29,  0x00, (byte)0x90, (byte)0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,  0x00, 0x00, 0x00, 0x00, 0x00};
        System.out.println(bcd2str(result));
    }

    public static String bcd2str(byte[] bcd) {
        if (null == bcd || bcd.length == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bcd.length; i++) {
                if((byte)0xff != bcd[i]){
                    int low = (bcd[i] & 0x0f);
                    sb.append(low);
                    int high = ((bcd[i] & 0xf0) >> 4);
                    if (high != 0xf) {
                        sb.append(high);
                    }else {
                        break;
                    }
                }else {
                    break;
                }
            }
            return sb.toString();
        }
    }

    public static String bcd11str(byte[] bcd) {
        if (null == bcd || bcd.length == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bcd.length; i++) {
                if((byte)0xff != bcd[i]){
                    int low = (bcd[i] & 0x0f);
                    sb.append(low);
                    int high = ((bcd[i] & 0xf0) >> 4);
                    if (high != 0xf) {
                        sb.append(high);
                    }
                }

            }
            return sb.toString().substring(0,11);
//            return sb.toString();
        }
    }


    public static String byte2str(byte[] bcd) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bcd.length; i++) {
            String tmp = Integer.toHexString(bcd[i] & 255);
           if (tmp.length() == 1){
               sb.append("0").append(tmp);
           }else {
               sb.append(tmp);
           }
        }

    return sb.toString();
    }

    public static String formatStr(String time) {

        String year  = time.substring(0, 4);
        String month  = time.substring(4, 6);
        String day  = time.substring(6, 8);
        String hour  = time.substring(8, 10);
        String minute  = time.substring(10, 12);
        String second  = time.substring(12, 14);
        String formatStr = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        return formatStr;
    }
}
