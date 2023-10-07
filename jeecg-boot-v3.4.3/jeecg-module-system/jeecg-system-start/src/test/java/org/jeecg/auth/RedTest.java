package org.jeecg.auth;

import com.sdt.omap.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;

public class RedTest {

    private static String ip ="192.168.1.8";
    private static int port = 21;
    private static String username ="ftpuser";
    private static String password ="ftp123ftp";
    private static String path ="/var/ftp/ftpuser/upload/213214.txt";
    public static void main(String[] args) throws Exception {
        FtpUtil ftpUtil = new FtpUtil();
        FTPClient ftpClient = ftpUtil.getFtpClient(ip, port, username, password);
//        ftpUtil.upload(ftpClient,"D:\\SC\\121.txt");

/*        int i = 256;
        String s = i / 100.0 + "%";
        System.out.println(s);

        byte b = (byte) 15;
        int s = ByteUtil.byte2tinyint(b);
        System.out.println(s);

//        ftpUtil.upload(ftpClient,"D:\\SC\\213214.txt");*/
        ftpUtil.download(ftpClient,path,"zzl.txt");
    }
}
