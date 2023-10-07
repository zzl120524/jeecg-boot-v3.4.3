package com.sdt.omap.utils;

import java.io.*;
import java.sql.SQLException;
import java.util.Random;

public class FileOperate {

    public static void main(String[] args) throws SQLException {

    }

    /**
     * 将字节byte[]数组输出到文件内
     *
     * @param file   输出的文件
     * @param bytes  字节数组
     * @param append 是否续写，true-续写，false-复写
     * @throws Exception 异常信息
     */
    public static void byteToFile(File file, byte[] bytes, boolean append)
            throws Exception {

        if (bytes.length == 0) {
            return;
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, append);
            // 设置1K缓存
            int bufferLength = 1024;
            int circleNum = (bytes.length + bufferLength - 1) / bufferLength;

            for (int i = 0; i < circleNum; i++) {
                byte[] buffer = null;
                if (bytes.length - i * bufferLength < bufferLength) {
                    buffer = new byte[bytes.length - i * bufferLength];
                } else {
                    buffer = new byte[bufferLength];
                }
                System.arraycopy(bytes, i * bufferLength, buffer, 0,
                        buffer.length);
                fos.write(buffer);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            fos.close();
        }
    }

    /**
     * 文件复制
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @throws Exception 失败异常
     */
    public static void copyFile(File srcFile, File destFile) throws Exception {

        InputStream fis = null;
        OutputStream fos = null;

        fis = new FileInputStream(srcFile);
        fos = new FileOutputStream(destFile);

        int bufferLength = 1024;
        byte[] buffer = new byte[bufferLength];
        int n = 0;
        while ((n = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, n);
        }
        fis.close();
        fos.close();

    }

    public static void copyFile(InputStream fis, File destFile) throws Exception {

        OutputStream fos = null;
        fos = new FileOutputStream(destFile);
        int bufferLength = 1024;
        byte[] buffer = new byte[bufferLength];
        int n = 0;
        while ((n = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, n);
        }
        fis.close();
        fos.close();

    }

    public static void deleteDir(String dir) throws Exception {
        File keyFileDir = new File(dir);
        // 默认删除文件夹，也支持对传入文件的删除
        if (keyFileDir.isDirectory()) {
            for (File f : keyFileDir.listFiles()) {
                if (f.isFile()) {
                    deleteFileByWrite(f);
                }

            }
        } else {
            deleteFileByWrite(keyFileDir);
        }

    }

    /**
     * 通过三擦三写的安全方式删除文件
     *
     * @param srcFile 要删除的文件
     * @throws Exception 失败异常
     */
    public static void deleteFileByWrite(File srcFile) throws Exception {

        if (srcFile == null) {
            throw new Exception("要安全删除的文件为空");
        }
        if (!srcFile.exists() || !srcFile.isFile()) {
            throw new Exception("要安全删除的文件为空");
        }
        int bufferLen = 1024 * 1024;
        // 准备三擦三写数据
        byte[] zeroData = new byte[bufferLen];
        byte[] oneData = new byte[bufferLen];
        byte[] randomData = new byte[bufferLen];
        // 填充数据
        makeZeroData(zeroData);
        makeOneData(oneData);
        makeRandomData(randomData);
        // 建立临时要写的数据
        byte[] temp = new byte[bufferLen];
        // 循环次数，三擦三写，设定为3
        int circle = 3;
        FileOutputStream fos = null;
        for (int i = 0; i < circle; i++) {
            if (i == (circle - 3)) {
                System.arraycopy(zeroData, 0, temp, 0, bufferLen);
            } else if (i == (circle - 2)) {
                System.arraycopy(randomData, 0, temp, 0, bufferLen);
            } else if (i == (circle - 1)) {
                System.arraycopy(oneData, 0, temp, 0, bufferLen);
            }

            try {

                long fileLength = srcFile.length();
                fos = new FileOutputStream(srcFile);
                if (fileLength <= bufferLen) {
                    fos.write(temp, 0, (int) fileLength);
                } else {
                    // 循环次数
                    long circleNumber = (fileLength + (bufferLen - 1))
                            / bufferLen;
                    // 剩余要填充的字节数
                    long otherLen = fileLength - (circleNumber - 1) * bufferLen;
                    for (long j = 0; j < circleNumber; j++) {
                        // 处理尾巴数据
                        if (j == (circleNumber - 1)) {
                            // 处理可以整除的数据
                            fos.write(temp, 0, (int) otherLen);
                        } else {
                            fos.write(temp);
                        }
                    }
                }
            } catch (Exception ex) {
                throw ex;
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        srcFile.delete();
        return;
    }

    public static void deleteFileByWriteOneTime(File srcFile) throws Exception {

        if (srcFile == null) {
            throw new Exception("要安全删除的文件为空");
        }
        if (!srcFile.exists() || !srcFile.isFile()) {
            throw new Exception("要安全删除的文件为空");
        }
        int bufferLen = 1024 * 1024;
        // 准备三擦三写数据
        byte[] zeroData = new byte[bufferLen];
        byte[] oneData = new byte[bufferLen];
        byte[] randomData = new byte[bufferLen];
        // 填充数据
        makeZeroData(zeroData);
        makeOneData(oneData);
        makeRandomData(randomData);
        // 建立临时要写的数据
        byte[] temp = new byte[bufferLen];
        // 循环次数，三擦三写，设定为3
        int circle = 1;
        FileOutputStream fos = null;
        for (int i = 0; i < circle; i++) {
            if (i == (circle - 3)) {
                System.arraycopy(zeroData, 0, temp, 0, bufferLen);
            } else if (i == (circle - 2)) {
                System.arraycopy(randomData, 0, temp, 0, bufferLen);
            } else if (i == (circle - 1)) {
                System.arraycopy(oneData, 0, temp, 0, bufferLen);
            }

            try {

                long fileLength = srcFile.length();
                fos = new FileOutputStream(srcFile);
                if (fileLength <= bufferLen) {
                    fos.write(temp, 0, (int) fileLength);
                } else {
                    // 循环次数
                    long circleNumber = (fileLength + (bufferLen - 1))
                            / bufferLen;
                    // 剩余要填充的字节数
                    long otherLen = fileLength - (circleNumber - 1) * bufferLen;
                    for (long j = 0; j < circleNumber; j++) {
                        // 处理尾巴数据
                        if (j == (circleNumber - 1)) {
                            // 处理可以整除的数据
                            fos.write(temp, 0, (int) otherLen);
                        } else {
                            fos.write(temp);
                        }
                    }
                }
            } catch (Exception ex) {
                throw ex;
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return;
    }

    /**
     * 通过一次擦写的安全方式删除文件
     *
     * @param srcFile
     * @throws Exception
     */
    public static void deleteFileByWriteOne(File srcFile) throws Exception {

        if (srcFile == null) {
            throw new Exception("要安全删除的文件为空");
        }
        if (!srcFile.exists() || !srcFile.isFile()) {
            throw new Exception("要安全删除的文件为空");
        }
        int bufferLen = 512;
        // 准备三擦三写数据
        byte[] zeroData = new byte[bufferLen];
        // 填充数据
        makeZeroData(zeroData);
        // 建立临时要写的数据
        byte[] temp = new byte[bufferLen];

        FileOutputStream fos = null;
        System.arraycopy(zeroData, 0, temp, 0, bufferLen);
        try {
            long fileLength = srcFile.length();
            fos = new FileOutputStream(srcFile);
            if (fileLength <= bufferLen) {
                fos.write(temp, 0, (int) fileLength);
            } else {
                // 循环次数
                long circleNumber = (fileLength + (bufferLen - 1)) / bufferLen;
                // 剩余要填充的字节数
                long otherLen = fileLength - (circleNumber - 1) * bufferLen;
                for (long j = 0; j < circleNumber; j++) {
                    // 处理尾巴数据
                    if (j == (circleNumber - 1)) {
                        // 处理可以整除的数据
                        fos.write(temp, 0, (int) otherLen);
                    } else {
                        fos.write(temp);
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
        }
        srcFile.delete();
        return;
    }

    /**
     * 为三擦三写准备全0数据
     *
     * @param zeroData
     */
    public static void makeZeroData(byte[] zeroData) {
        for (int i = 0; i < zeroData.length; i++) {
            zeroData[i] = 0x0;
        }
    }

    /**
     * 准备全1数据
     *
     * @param oneData
     */
    public static void makeOneData(byte[] oneData) {
        for (int i = 0; i < oneData.length; i++) {
            oneData[i] = (byte) 0xFF;
        }
    }

    /**
     * 准备随机数文件
     *
     * @param randomData
     */
    public static void makeRandomData(byte[] randomData) {

        final byte[] RANDOMDADA = {-91, -15, 95, -77, -31, 1, 22, 56, 8, 56,
                2, -61, -21, 59, 47, -6, 2, -104, 21, 55, 100, 110, 47, 115,
                -30, -20, -107, 52, 1, 9, 9, 0, 12, 27, 106, -71, 109, -90, 51,
                -91, 122, 25, 82, -26, -50, 45, 16, -45, -115, -76, 6, 121,
                116, 57, 64, -42, -102, -6, -95, 63, -68, 90, -76, 7};

        int randomDataForDeleteLen = RANDOMDADA.length;
        if (randomDataForDeleteLen == 0) {
            Random ran = new Random();
            ran.nextBytes(RANDOMDADA);
            randomDataForDeleteLen = RANDOMDADA.length;
        }
        // 需要掩盖数据长度小于初始化生成随机掩盖数据长度时
        if (randomData.length <= randomDataForDeleteLen) {
            Random random = new Random();
            random.nextBytes(randomData);
//            new Random().nextBytes(randomData);
            // System.arraycopy(RANDOMDADA, 0, randomData, 0,
            // randomData.length);
        } else {
            // 循环次数
            int circleNumber = (randomData.length + (randomDataForDeleteLen - 1))
                    / randomDataForDeleteLen;
            // 剩余要填充的随机字节数
            int otherLen = randomData.length - (circleNumber - 1)
                    * randomDataForDeleteLen;
            for (int i = 0; i < circleNumber; i++) {
                // 处理尾巴数据
                if (i == (circleNumber - 1)) {
                    System.arraycopy(RANDOMDADA, 0, randomData, i
                            * randomDataForDeleteLen, otherLen);
                    // 处理可以整除的数据
                } else {
                    System.arraycopy(RANDOMDADA, 0, randomData, i
                            * randomDataForDeleteLen, randomDataForDeleteLen);
                }
            }
        }
    }

    public static void copyFile(String pathform, String pathto) throws Exception {
        File to = new File(pathto);
        if (!to.exists()) {
            File fromFile = new File(pathform);
            if (fromFile.isDirectory()) {
                to.mkdirs();
            }
        }
        File dr = new File(pathform);
        File[] files = dr.listFiles();
        for (File file : files) {
            chuli1(file, pathto);

        }
    }

    static String split = "\\";

    private static void chuli1(File file, String pathto) throws Exception {
        if (file.isDirectory()) {
            new File(pathto + split + file.getName()).mkdirs();
            File[] files = file.listFiles();
            for (File file2 : files) {
                String name = file.getName();
                chuli1(file2, pathto + split + name);
            }
        } else {
            String name = file.getName();
            File toFile = new File(pathto + split + name);
            //已经存在的文件不拷贝，例如配置文件
            if (toFile.exists()) {
                return;
            } else {
                copyFile(file, toFile);
            }

        }
    }

    /**
     * 将文件读入到数组中
     *
     * @return 输出文件的byte数组
     * @throws FileNotFoundException
     * @throws Exception
     */
    public static byte[] fileToByte(File file) throws Exception {

        FileInputStream fis = null;
        IDataStack ds = new ArrayDataStack();
        try {
            fis = new FileInputStream(file);
            // 设置1K缓存
            int len = 1024;
            byte[] buffer = new byte[len];
            int n = 0;
            while ((n = fis.read(buffer)) != -1) {
                ds.add(buffer, 0, n);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != fis) {
                fis.close();
            }
        }
        return ds.getData();
    }


}
