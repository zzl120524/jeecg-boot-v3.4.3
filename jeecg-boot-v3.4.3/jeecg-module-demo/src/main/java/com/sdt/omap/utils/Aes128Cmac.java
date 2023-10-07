package com.sdt.omap.utils;

import com.sdt.util.standard.ByteUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Aes128Cmac {

    public static byte[] generateMac(byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        // 使用AES算法生成Mac对象
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        mac.init(keySpec);
        // 使用AES-128-CMAC算法计算消息的MAC值
        byte[] macBytes = mac.doFinal(message);
        byte[] result = new byte[4];
        System.arraycopy(macBytes, 0, result, 0, 4);
        return result;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "0123456789abcdef".getBytes();
        System.out.println(ByteUtil.byte2HexString0x(key, true));
        byte[] message = "hello world".getBytes();
        byte[] mac = Aes128Cmac.generateMac(key, message);
        System.out.println(ByteUtil.byte2HexString0x(mac, true));
    }
}