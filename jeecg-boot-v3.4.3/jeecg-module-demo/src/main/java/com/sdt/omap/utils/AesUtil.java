package com.sdt.omap.utils;

import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class AesUtil {
    private final static String AES = "AES";
    //定义一个16byte的初始向量
    private static final String IV_STRING = "0000000000000000";

    /**
     * 产生一个16位的密钥字符串
     *
     * @return
     */
    public static String generateSecreKey() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid.substring(0, 16);
    }

    public static byte[] aesEncry(byte[] content, byte[] key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //初始化一个密钥对象
        SecretKeySpec keySpec = new SecretKeySpec(key, AES);
        //初始化一个初始向量,不传入的话，则默认用全0的初始向量
        byte[] initParam = IV_STRING.getBytes();
        System.out.println("IV_STRING===>"+ ByteUtil.byte2HexString(initParam,true));
        IvParameterSpec ivSpec = new IvParameterSpec(initParam);
        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedBytes = cipher.doFinal(content);
        return encryptedBytes;
    }

    public static byte[] aesDecry(byte[] content, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {

        //初始化一个密钥对象
        SecretKeySpec keySpec = new SecretKeySpec(key, AES);
        //初始化一个初始向量,不传入的话，则默认用全0的初始向量
        byte[] initParam = IV_STRING.getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(initParam);
        // 指定加密的算法、工作模式和填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] result = cipher.doFinal(content);
        return result;
    }

}

