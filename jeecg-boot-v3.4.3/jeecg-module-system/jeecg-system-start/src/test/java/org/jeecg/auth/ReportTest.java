package org.jeecg.auth;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ReportTest {
    public static void main(String[] args) {
    int i = 1;
        String s = i== 1 ? "开始":"结束";
        System.out.println(s);
    }
    @Test
    public  void string () throws UnsupportedEncodingException {
        byte[] bytes =new byte[]{0x32, 0x30, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00,  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        String s = new String(bytes, "UTF-8");
        System.out.println(s.length());
        System.out.println(s.trim().length());
     /* if (a.startsWith("1") || a.startsWith("2") || a.startsWith("3") || a.startsWith("4") || a.startsWith("5") || a.startsWith("6")){
          return true;
      }
     return false;
    }*/
     if (s.trim().matches("\\d+")){
         System.out.println("true");
     }else {
         System.out.println("false");
     }


    }
}
