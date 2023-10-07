package org.jeecg.common.util;
import cn.hutool.core.util.StrUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * map文件映射读取器
 *
 * @Author: lizixian
 * @date: 2023/4/24 23:01
 */
public class MappedReader {

    private MappedByteBuffer[] mappedBufArray;
    private int count = 0;
    private int number;
    private FileInputStream fileIn;
    private static long fileLength; // 文件大小，字节数
    private int arraySize;
    private byte[] array;
    private String lastLine;    // 列表的最后一行，处理映射分块截断一行的问题
    private long readLineCount;    // 已经读取的行数

    /**
     * 创建映射
     *
     * @Author: lizixian
     * @date: 2023/4/25 10:10
     */
    public MappedReader(String filePath) throws IOException {
        this(filePath, 655360);
    }

    /**
     * 创建映射
     *
     * @author lizixian
     * @date 2021/3/16 17:38
     */
    public MappedReader(String filePath, int arraySize) throws IOException {
        this.fileIn = new FileInputStream(filePath);
        FileChannel fileChannel = fileIn.getChannel();
        this.fileLength = fileChannel.size();
        this.number = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);
        this.mappedBufArray = new MappedByteBuffer[number];// 内存文件映射数组
        long preLength = 0;
        long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小
        for (int i = 0; i < number; i++) {// 将文件的连续区域映射到内存文件映射数组中
            if (fileLength - preLength < (long) Integer.MAX_VALUE) {
                regionSize = fileLength - preLength;// 最后一片区域的大小
            }
            mappedBufArray[i] = fileChannel.map(FileChannel.MapMode.READ_ONLY, preLength, regionSize);
            preLength += regionSize;// 下一片区域的开始
        }
        this.arraySize = arraySize;
    }

    /**
     * 读取文件
     *
     * @author lizixian
     * @date 2021/3/16 17:39
     */
    public int read() {
        if (count >= number) {
            return -1;
        }
        int limit = mappedBufArray[count].limit();
        int position = mappedBufArray[count].position();
        if (limit - position > arraySize) {
            array = new byte[arraySize];
            mappedBufArray[count].get(array);
            return arraySize;
        } else {

            // 本内存文件映射最后一次读取数据
            array = new byte[limit - position];
            mappedBufArray[count].get(array);
            if (count < number) {
                count++;// 转换到下一个内存文件映射
            }
            return limit - position;
        }
    }

    /**
     * 使用完毕记得把流关闭
     *
     * @author lizixian
     * @date 2021/3/16 17:40
     */
    public void close() throws IOException {
        fileIn.close();
        array = null;
    }

    public byte[] getArray() {
        return array;
    }

    /**
     * 转成list
     *
     * @author lizixian
     * @date 2021/3/16 17:49
     */
    public List<String> getList() {
        String part = new String(this.getArray());
        List<String> list = new CopyOnWriteArrayList();
        if (StrUtil.isNotEmpty(part)) {
            String[] split = part.split("\n");
            for (String s : split) {
                list.add(s);
            }

            // 尝试拼接上一块映射的最后一行（或者半行）
            if (lastLine != null) {
                list.set(0, lastLine + list.get(0));
//                System.out.println("拼接第一个：" + lastLine);
            }

            // 缓存本次块的最后一行
            lastLine = list.get(list.size() - 1);
            list.remove(list.size() - 1);
        }
        return list;
    }

    /**
     * 文件大小，字节数
     *
     * @Author: lizixian
     * @date: 2023/4/25 13:27
     */
    public long getFileLength() {
        return fileLength;
    }

    /**
     * 已经读取到的行数
     *
     * @Author: lizixian
     * @date: 2023/4/25 13:42
     */
    public long getReadLineCount() {
        return readLineCount;
    }

    /**
     * 更新读取到的行数
     *
     * @Author: lizixian
     * @date: 2023/4/25 13:42
     */
    public long incrReadLineCount(long count) {
        readLineCount += count;
        return readLineCount;
    }

    public String getLastLine() {
        return lastLine;
    }
}
