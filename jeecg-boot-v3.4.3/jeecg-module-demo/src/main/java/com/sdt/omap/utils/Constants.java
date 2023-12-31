package com.sdt.omap.utils;

import com.sdt.omap.session.TransSession;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static String AUTH_IP = "127.0.0.1";
    public static int AUTH_PORT = 8002;

    public static String CONTROL_IP = "127.0.0.1";
    public static int CONTROL_PORT = 8004;

    public static String REPORT_IP = "127.0.0.1";
    public static int REPORT_PORT = 8006;

    public static byte[] AUTH_KEY1 = new byte[]{0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};
    public static byte[] AUTH_KEY = new byte[]{(byte) 0xC9, 0x15, (byte) 0xCB, 0x6A, 0x56, 0x71, (byte) 0xAF, 0x51, 0x71, 0x04, (byte) 0xA7, 0x36, 0x6B, (byte) 0xDC, 0x4A, 0x57};
/*    public static byte[] AUTH_KEY4 = new byte[]{ 0x01, 0x1b, 0x1b, 0x03, 0x03, 0x13,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x01, 0x00, 0x01, 0x03, 0x02, 0x45, (byte)0xb4, 0x03, 0x02, 0x7e,
        0x15, 0x40, 0x00, 0x40, 0x11, 0x35, 0x77, (byte)0xc0, (byte)0xa8, 0x01, 0x32, (byte)0xc0, (byte)0xa8, 0x01, 0x28, 0x13,
        (byte)0xc6, 0x13, (byte)0xc6, 0x02, (byte)0xee, (byte)0xdb, (byte)0xc0, 0x53, 0x49, 0x50, 0x2f, 0x32, 0x2e, 0x30, 0x20, 0x32,
        0x30, 0x30, 0x20, 0x4f, 0x4b, 0x0d, 0x0a, 0x56, 0x69, 0x61, 0x3a, 0x20, 0x53, 0x49, 0x50, 0x2f,
        0x32, 0x2e, 0x30, 0x2f, 0x55, 0x44, 0x50, 0x20, 0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e,
        0x31, 0x2e, 0x34, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32, 0x3b, 0x62, 0x72, 0x61, 0x6e, 0x63, 0x68,
        0x3d, 0x7a, 0x39, 0x68, 0x47, 0x34, 0x62, 0x4b, 0x61, 0x32, 0x30, 0x61, 0x34, 0x32, 0x65, 0x32,
        0x34, 0x37, 0x62, 0x36, 0x33, 0x36, 0x32, 0x37, 0x33, 0x32, 0x36, 0x36, 0x36, 0x64, 0x37, 0x64,
        0x35, 0x64, 0x30, 0x63, 0x39, 0x31, 0x38, 0x31, 0x0d, 0x0a, 0x52, 0x65, 0x63, 0x6f, 0x72, 0x64,
        0x2d, 0x52, 0x6f, 0x75, 0x74, 0x65, 0x3a, 0x20, 0x3c, 0x73, 0x69, 0x70, 0x3a, 0x31, 0x39, 0x32,
        0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e, 0x35, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32, 0x3b, 0x6c,
        0x72, 0x3e, 0x0d, 0x0a, 0x52, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x2d, 0x52, 0x6f, 0x75, 0x74, 0x65,
        0x3a, 0x20, 0x3c, 0x73, 0x69, 0x70, 0x3a, 0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31,
        0x2e, 0x34, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32, 0x3b, 0x6c, 0x72, 0x3e, 0x0d, 0x0a, 0x46, 0x72,
        0x6f, 0x6d, 0x3a, 0x20, 0x31, 0x31, 0x31, 0x31, 0x31, 0x33, 0x39, 0x31, 0x34, 0x30, 0x30, 0x33,
        0x38, 0x39, 0x35, 0x20, 0x3c, 0x73, 0x69, 0x70, 0x3a, 0x31, 0x31, 0x31, 0x31, 0x31, 0x33, 0x39,
        0x31, 0x34, 0x30, 0x30, 0x33, 0x38, 0x39, 0x35, 0x40, 0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38,
        0x2e, 0x31, 0x2e, 0x34, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32, 0x3e, 0x3b, 0x74, 0x61, 0x67, 0x3d,
        0x37, 0x65, 0x63, 0x30, 0x32, 0x39, 0x35, 0x38, 0x31, 0x34, 0x39, 0x65, 0x65, 0x62, 0x31, 0x61,
        0x65, 0x61, 0x34, 0x33, 0x33, 0x37, 0x35, 0x37, 0x65, 0x39, 0x37, 0x62, 0x37, 0x39, 0x35, 0x63,
        0x0d, 0x0a, 0x54, 0x6f, 0x3a, 0x20, 0x39, 0x39, 0x35, 0x36, 0x39, 0x32, 0x30, 0x30, 0x30, 0x39,
        0x20, 0x3c, 0x73, 0x69, 0x70, 0x3a, 0x39, 0x39, 0x35, 0x36, 0x39, 0x32, 0x30, 0x30, 0x30, 0x39,
        0x40, 0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e, 0x35, 0x30, 0x3a, 0x35, 0x30,
        0x36, 0x32, 0x3e, 0x3b, 0x74, 0x61, 0x67, 0x3d, 0x30, 0x66, 0x65, 0x35, 0x66, 0x34, 0x31, 0x30,
        0x35, 0x64, 0x38, 0x62, 0x36, 0x30, 0x32, 0x62, 0x64, 0x37, 0x36, 0x65, 0x61, 0x31, 0x31, 0x38,
        0x32, 0x64, 0x66, 0x30, 0x62, 0x61, 0x61, 0x35, 0x0d, 0x0a, 0x43, 0x61, 0x6c, 0x6c, 0x2d, 0x49,
        0x44, 0x3a, 0x20, 0x36, 0x34, 0x45, 0x46, 0x35, 0x32, 0x46, 0x37, 0x2d, 0x32, 0x2d, 0x31, 0x40,
        0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e, 0x34, 0x30, 0x0d, 0x0a, 0x43, 0x53,
        0x65, 0x71, 0x3a, 0x20, 0x31, 0x30, 0x34, 0x38, 0x38, 0x36, 0x20, 0x49, 0x4e, 0x56, 0x49, 0x54,
        0x45, 0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x61, 0x63, 0x74, 0x3a, 0x20, 0x3c, 0x73, 0x69, 0x70,
        0x3a, 0x36, 0x35, 0x36, 0x39, 0x32, 0x30, 0x30, 0x30, 0x39, 0x40, 0x31, 0x39, 0x32, 0x2e, 0x31,
        0x36, 0x38, 0x2e, 0x31, 0x2e, 0x35, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32, 0x3b, 0x75, 0x73, 0x65,
        0x72, 0x3d, 0x70, 0x68, 0x6f, 0x6e, 0x65, 0x3b, 0x74, 0x72, 0x61, 0x6e, 0x73, 0x70, 0x6f, 0x72,
        0x74, 0x3d, 0x75, 0x64, 0x70, 0x3e, 0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 0x74, 0x2d,
        0x54, 0x79, 0x70, 0x65, 0x3a, 0x20, 0x61, 0x70, 0x70, 0x6c, 0x69, 0x63, 0x61, 0x74, 0x69, 0x6f,
        0x6e, 0x2f, 0x73, 0x64, 0x70, 0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 0x74, 0x2d, 0x4c,
        0x65, 0x6e, 0x67, 0x74, 0x68, 0x3a, 0x20, 0x20, 0x20, 0x31, 0x38, 0x39, 0x0d, 0x0a, 0x0d, 0x0a,
        0x76, 0x3d, 0x30, 0x0d, 0x0a, 0x6f, 0x3d, 0x2d, 0x20, 0x31, 0x36, 0x39, 0x32, 0x39, 0x31, 0x31,
        0x34, 0x39, 0x36, 0x20, 0x32, 0x20, 0x49, 0x4e, 0x20, 0x49, 0x50, 0x34, 0x20, 0x31, 0x39, 0x32,
        0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e, 0x35, 0x30, 0x0d, 0x0a, 0x73, 0x3d, 0x53, 0x75, 0x6e,
        0x4b, 0x61, 0x69, 0x73, 0x65, 0x6e, 0x73, 0x20, 0x53, 0x49, 0x50, 0x20, 0x41, 0x67, 0x65, 0x6e,
        0x74, 0x20, 0x42, 0x79, 0x20, 0x5a, 0x4a, 0x59, 0x0d, 0x0a, 0x63, 0x3d, 0x49, 0x4e, 0x20, 0x49,
        0x50, 0x34, 0x20, 0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e, 0x35, 0x30, 0x0d,
        0x0a, 0x74, 0x3d, 0x30, 0x20, 0x30, 0x0d, 0x0a, 0x6d, 0x3d, 0x61, 0x75, 0x64, 0x69, 0x6f, 0x20,
        0x34, 0x39, 0x32, 0x30, 0x36, 0x20, 0x52, 0x54, 0x50, 0x2f, 0x41, 0x56, 0x50, 0x20, 0x38, 0x0d,
        0x0a, 0x61, 0x3d, 0x72, 0x74, 0x70, 0x6d, 0x61, 0x70, 0x3a, 0x38, 0x20, 0x50, 0x43, 0x4d, 0x41,
        0x2f, 0x38, 0x30, 0x30, 0x30, 0x0d, 0x0a, 0x61, 0x3d, 0x70, 0x74, 0x69, 0x6d, 0x65, 0x3a, 0x32,
        0x30, 0x0d, 0x0a, 0x61, 0x3d, 0x70, 0x63, 0x66, 0x67, 0x3a, 0x31, 0x20, 0x74, 0x3d, 0x31, 0x0d,
        0x0a, 0x61, 0x3d, 0x73, 0x65, 0x6e, 0x64, 0x72, 0x65, 0x63, 0x76, 0x0d, 0x0a
};*/

    public static byte[] AUTH_KEY4 = new byte[]{ 0x01, 0x1b, 0x1b, 0x04, 0x02, (byte) 0x87, 0x01, 0x02,  0x7e, 0x45, (byte) 0xb4, 0x03, 0x57, 0x1f, (byte) 0xd7, 0x40,
            0x00, 0x40, 0x11, (byte) 0x93, 0x60, (byte) 0xc0, (byte) 0xa8, 0x01,  0x28, (byte) 0xc0, (byte) 0xa8, 0x01, 0x32, 0x13, (byte) 0xc6, 0x13,
            (byte) 0xc6, 0x03, 0x43, 0x29, (byte) 0xcc, 0x49, 0x4e, 0x56,  0x49, 0x54, 0x45, 0x20, 0x73, 0x69, 0x70, 0x3a,
            0x39, 0x39, 0x35, 0x36, 0x39, 0x32, 0x30, 0x30,  0x30, 0x39, 0x40, 0x31, 0x39, 0x32, 0x2e, 0x31,
            0x36, 0x38, 0x2e, 0x31, 0x2e, 0x35, 0x30, 0x3a,  0x35, 0x30, 0x36, 0x32, 0x20, 0x53, 0x49, 0x50,
            0x2f, 0x32, 0x2e, 0x30, 0x0d, 0x0a, 0x56, 0x69,  0x61, 0x3a, 0x20, 0x53, 0x49, 0x50, 0x2f, 0x32,
            0x2e, 0x30, 0x2f, 0x55, 0x44, 0x50, 0x20, 0x31,  0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31,
            0x2e, 0x34, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32,  0x3b, 0x62, 0x72, 0x61, 0x6e, 0x63, 0x68, 0x3d,
            0x7a, 0x39, 0x68, 0x47, 0x34, 0x62, 0x4b, 0x34,  0x37, 0x38, 0x30, 0x39, 0x38, 0x61, 0x39, 0x61,
            0x61, 0x35, 0x30, 0x61, 0x66, 0x37, 0x30, 0x31,  0x62, 0x64, 0x35, 0x62, 0x61, 0x66, 0x64, 0x65,
            0x64, 0x66, 0x35, 0x64, 0x65, 0x32, 0x64, 0x0d,  0x0a, 0x52, 0x65, 0x63, 0x6f, 0x72, 0x64, 0x2d,
            0x52, 0x6f, 0x75, 0x74, 0x65, 0x3a, 0x20, 0x3c,  0x73, 0x69, 0x70, 0x3a, 0x31, 0x39, 0x32, 0x2e,
            0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e, 0x34, 0x30,  0x3a, 0x35, 0x30, 0x36, 0x32, 0x3b, 0x6c, 0x72,
            0x3e, 0x0d, 0x0a, 0x46, 0x72, 0x6f, 0x6d, 0x3a,  0x20, 0x31, 0x31, 0x31, 0x31, 0x31, 0x33, 0x39,
            0x31, 0x34, 0x30, 0x30, 0x33, 0x38, 0x39, 0x35,  0x20, 0x3c, 0x73, 0x69, 0x70, 0x3a, 0x31, 0x31,
            0x31, 0x31, 0x31, 0x33, 0x39, 0x31, 0x34, 0x30,  0x30, 0x33, 0x38, 0x39, 0x35, 0x40, 0x31, 0x39,
            0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e,  0x34, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32, 0x3e,
            0x3b, 0x74, 0x61, 0x67, 0x3d, 0x30, 0x61, 0x66,  0x31, 0x36, 0x33, 0x37, 0x30, 0x66, 0x65, 0x64,
            0x34, 0x30, 0x34, 0x65, 0x33, 0x30, 0x65, 0x34,  0x65, 0x64, 0x38, 0x65, 0x32, 0x36, 0x64, 0x62,
            0x64, 0x33, 0x30, 0x61, 0x37, 0x0d, 0x0a, 0x54,  0x6f, 0x3a, 0x20, 0x39, 0x39, 0x35, 0x36, 0x39,
            0x32, 0x30, 0x30, 0x30, 0x39, 0x20, 0x3c, 0x73,  0x69, 0x70, 0x3a, 0x39, 0x39, 0x35, 0x36, 0x39,
            0x32, 0x30, 0x30, 0x30, 0x39, 0x40, 0x31, 0x39,  0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e, 0x31, 0x2e,
            0x35, 0x30, 0x3a, 0x35, 0x30, 0x36, 0x32, 0x3e,  0x0d, 0x0a, 0x43, 0x61, 0x6c, 0x6c, 0x2d, 0x49,
            0x44, 0x3a, 0x20, 0x36, 0x34, 0x45, 0x46, 0x35,  0x32, 0x42, 0x43, 0x2d, 0x32, 0x2d, 0x31, 0x40,
            0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e,  0x31, 0x2e, 0x34, 0x30, 0x0d, 0x0a, 0x43, 0x53,
            0x65, 0x71, 0x3a, 0x20, 0x31, 0x37, 0x32, 0x35,  0x32, 0x20, 0x49, 0x4e, 0x56, 0x49, 0x54, 0x45,
            0x0d, 0x0a, 0x43, 0x6f, 0x6e, 0x74, 0x61, 0x63,  0x74, 0x3a, 0x20, 0x3c, 0x73, 0x69, 0x70, 0x3a,
            0x31, 0x31, 0x31, 0x31, 0x31, 0x33, 0x39, 0x31,  0x34, 0x30, 0x30, 0x33, 0x38, 0x39, 0x35, 0x40,
            0x31, 0x39, 0x32, 0x2e, 0x31, 0x36, 0x38, 0x2e,  0x31, 0x2e, 0x34, 0x30, 0x3a, 0x35, 0x30, 0x36,
            0x32, 0x3b, 0x75, 0x73, 0x65, 0x72, 0x3d, 0x70,  0x68, 0x6f, 0x6e, 0x65, 0x3b, 0x74, 0x72, 0x61,
            0x6e, 0x73, 0x70, 0x6f, 0x72, 0x74, 0x3d, 0x75,  0x64, 0x70, 0x3e, 0x0d, 0x0a, 0x43, 0x6f, 0x6e,
            0x74, 0x65, 0x6e, 0x74, 0x2d, 0x54, 0x79, 0x70,  0x65, 0x3a, 0x20, 0x61, 0x70, 0x70, 0x6c, 0x69,
            0x63, 0x61, 0x74, 0x69, 0x6f, 0x6e, 0x2f, 0x73,  0x64, 0x70, 0x0d, 0x0a, 0x41, 0x6c, 0x6c, 0x6f,
            0x77, 0x3a, 0x20, 0x49, 0x4e, 0x56, 0x49, 0x54,  0x45, 0x2c, 0x50, 0x52, 0x41, 0x43, 0x4b, 0x2c,
            0x41, 0x43, 0x4b, 0x2c, 0x43, 0x41, 0x4e, 0x43,  0x45, 0x4c, 0x2c, 0x42, 0x59, 0x45, 0x2c, 0x55,
            0x50, 0x44, 0x41, 0x54, 0x45, 0x2c, 0x4f, 0x50,  0x54, 0x49, 0x4f, 0x4e, 0x53, 0x2c, 0x49, 0x4e,
            0x46, 0x4f, 0x0d, 0x0a, 0x4d, 0x61, 0x78, 0x2d,  0x46, 0x6f, 0x72, 0x77, 0x61, 0x72, 0x64, 0x73,
            0x3a, 0x20, 0x37, 0x30, 0x0d, 0x0a, 0x52, 0x65,  0x73, 0x6f, 0x75, 0x72, 0x63, 0x65, 0x2d, 0x50,
            0x72, 0x69, 0x6f, 0x72, 0x69, 0x74, 0x79, 0x3a,  0x20, 0x77, 0x70, 0x73, 0x2e, 0x34, 0x0d, 0x0a,
            0x43, 0x6f, 0x6e, 0x74, 0x65, 0x6e, 0x74, 0x2d,  0x4c, 0x65, 0x6e, 0x67, 0x74, 0x68, 0x3a, 0x20,
            0x20, 0x20, 0x32, 0x31, 0x35, 0x0d, 0x0a,};

    public static Map<Integer, TransSession> FACSEESION = new HashMap<Integer, TransSession>();
}
