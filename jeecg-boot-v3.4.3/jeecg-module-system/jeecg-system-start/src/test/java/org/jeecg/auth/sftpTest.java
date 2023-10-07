package org.jeecg.auth;

import com.alibaba.fastjson.JSONObject;
import com.sdt.omap.protocol.comm.IpHeader;
import com.sdt.omap.utils.ByteUtils;
import com.sdt.omap.utils.SFTPUtil;
import com.sdt.util.standard.ByteUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JeecgSystemApplication.class)
public class sftpTest {

    private static String ip ="192.168.1.11";
    private static int port = 22;
    private static String username ="ftpuser";
    private static String password ="ftp123ftp";
    private static String root ="root";
    @Test
    public void sftp() throws Exception{
        SFTPUtil sftpUtil = new SFTPUtil(username, password, ip , port);
        sftpUtil.login();

        System.out.println("连接成功");
        File file = new File("D:/SC/213214.txt");
        InputStream is = new FileInputStream(file);
  /*      try {
            sftpUtil.upload("/","var/ftp/ftpuser/upload", "test_sftp.txt", is);

        }catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            sftpUtil.download("/var/ftp/ftpuser/upload","test_sftp.txt", "D:/SC/22.txt");

        }catch (Exception e) {
            e.printStackTrace();
        }
        sftpUtil.logout();

    }

    @Test
    public void ipHeader() throws Exception{
     /*   byte[] bytes = {0x45, (byte) 0xb4, 0x02, 0x4c, 0x41, (byte) 0xfd, 0x40, 0x00, 0x40, 0x11, 0x72, 0x45, (byte) 0xc0, (byte) 0xa8,
                0x01, 0x32, (byte) 0xc0, (byte) 0xa8, 0x01, 0x28, 0x13, (byte) 0xc6, 0x13, (byte) 0xc6, 0x02, 0x38, (byte) 0xfc, 0x28};*/
        byte[] bytes = {0x45, 0x00, 0x00, (byte)0x86, 0x46, (byte)0xf4, 0x40, 0x00, 0x40, 0x11, 0x70, 0x18, (byte)0xc0, (byte)0xa8,
                0x01, 0x04, (byte)0xc0, (byte)0xa8, 0x01, 0x06, (byte)0xb9, 0x2c, 0x1f, 0x46, 0x00, 0x72, (byte)0xc9, (byte)0xd5};
     String versionStr = ByteUtils.getBinaryStrFromByte(bytes[0]);
        Integer version = Integer.valueOf(versionStr.substring(0, 4), 2);
        try {
            byte a = 0x65;
            String binaryStr = ByteUtils.getBinaryStrFromByte(a);
            String substring = binaryStr.substring(0, 4);
            Integer integer = Integer.valueOf(substring, 2);

            String bits = ByteUtils.byteToBinStr(bytes);
            System.out.println("bits:"+bits);
            IpHeader ipHeader = new IpHeader();
            IpHeader parse = ipHeader.parse(bits);

//            System.out.println(JSONObject.toJSONString(parse));
            System.out.println("ipHeader:"+parse.toString());
            System.out.println("version:"+integer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
