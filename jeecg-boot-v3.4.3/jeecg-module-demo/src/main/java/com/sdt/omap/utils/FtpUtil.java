package com.sdt.omap.utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FtpUtil {

    /**
     * 获取一个ftp连接
     * @param host ip地址
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @return 返回ftp连接对象
     * @throws Exception 连接ftp时发生的各种异常
     */
    public static FTPClient getFtpClient(String host, Integer port, String username, String password) throws Exception{
        FTPClient ftpClient = new FTPClient();

        // 连接服务器
        ftpClient.connect(host, port);

        int reply = ftpClient.getReplyCode();
        if(!FTPReply.isPositiveCompletion(reply)){
            log.error("无法连接至ftp服务器， host:{}, port:{}", host, port);
            ftpClient.disconnect();
            return null;
        }

        // 登入服务器
        boolean login = ftpClient.login(username, password);
        if(!login){
            log.error("登录失败， 用户名或密码错误");
            ftpClient.logout();
            ftpClient.disconnect();
            return null;
        }

        // 连接并且成功登陆ftp服务器
        log.info("login success ftp server, host:{}, port:{}, user:{}", host, port, username);

        // 设置通道字符集， 要与服务端设置一致
        ftpClient.setControlEncoding("UTF-8");
        // 设置文件传输编码类型， 字节传输：BINARY_FILE_TYPE, 文本传输：ASCII_FILE_TYPE， 建议使用BINARY_FILE_TYPE进行文件传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // 动模式: enterLocalActiveMode(),被动模式: enterLocalPassiveMode(),一般选择被动模式
        ftpClient.enterLocalPassiveMode();
        // 切换目录
        //ftpClient.changeWorkingDirectory("xxxx");

        return ftpClient;
    }

    /**
     * 断开ftp连接
     * @param ftpClient ftp连接客户端
     */
    public static void disConnect(FTPClient ftpClient){
        if(ftpClient == null){
            return;
        }
        try {
            log.info("断开ftp连接， host:{}, port:{}", ftpClient.getPassiveHost(), ftpClient.getPassivePort());
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ftp连接断开异常， 请检查");
        }

    }

    /**
     * 文件下载
     * @param ftpClient ftp连接客户端
     * @param path 文件路径
     * @param fileName 文件名称
     */
    public static void download(FTPClient ftpClient, String path, String fileName) throws Exception {
        if(ftpClient == null || path == null || fileName == null){
            return;
        }

        // 中文目录处理存在问题， 转化为ftp能够识别中文的字符集
        String remotePath;
        try {
            remotePath = new String(path.getBytes(StandardCharsets.UTF_8), FTP.DEFAULT_CONTROL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            remotePath = path;
        }

        InputStream inputStream = ftpClient.retrieveFileStream(remotePath);
        if (inputStream == null) {
            log.error("{}在ftp服务器中不存在，请检查", path);
            return;
        }

        FileOutputStream outputStream = new FileOutputStream("D:\\SC\\" + fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        try{
            byte[] buffer = new byte[2048];
            int i;
            while ((i = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, i);
                bufferedOutputStream.flush();
            }
        } catch (Exception e) {
            log.error("文件下载异常", e);
            log.error("{}下载异常，请检查", path);
        }

        inputStream.close();
        outputStream.close();
        bufferedInputStream.close();
        bufferedOutputStream.close();

        // 关闭流之后必须执行，否则下一个文件导致流为空
        boolean complete = ftpClient.completePendingCommand();
        if(complete){
            log.info("文件{}下载完成", remotePath);
        }else{
            log.error("文件{}下载失败", remotePath);
        }

    }

    /**
     * 上传文件
     * @param ftpClient ftp连接客户端
     * @param sourcePath 源地址
     */
    public static void upload(FTPClient ftpClient, String sourcePath) throws Exception{
        if(ftpClient == null || sourcePath == null){
            return;
        }

        File file = new File(sourcePath);
        if(!file.exists() || !file.isFile()){
            return;
        }

        // 中文目录处理存在问题， 转化为ftp能够识别中文的字符集
        String remotePath ="/var/ftp/ftpuser/upload/213214.txt";
//        String remotePath ="/ftpuser/upload/213214.txt";
/*        String remotePath ;
        try {
            remotePath = new String(file.getName().getBytes(StandardCharsets.UTF_8), FTP.DEFAULT_CONTROL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            remotePath = file.getName();
        }*/

        try(
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = ftpClient.storeFileStream(remotePath);
        ){
            byte[] buffer = new byte[2048];
            int length;
            while((length = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, length);
                outputStream.flush();
            }
        }catch (Exception e){
            log.error("文件上传异常", e);
        }

        // 关闭流之后必须执行，否则下一个文件导致流为空
        boolean complete = ftpClient.completePendingCommand();
        if(complete){
            log.info("文件{}上传完成", remotePath);
        }else{
            log.error("文件{}上传失败", remotePath);
        }


    }


}

