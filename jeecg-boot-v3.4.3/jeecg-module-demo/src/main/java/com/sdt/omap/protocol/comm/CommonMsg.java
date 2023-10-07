package com.sdt.omap.protocol.comm;

import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.util.standard.ByteUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CommonMsg {

    private byte startSymbol; // 1B 01111110
    private byte version; // 1B
    private short length; // 2B
    private EMsgType msgType; // 1B
    private int seqNum; // 4B
    private byte[] content; // nB
    private byte endSymbol; // 1B 10010110

    public byte[] getByteArray() {
        ByteBuffer bf = null;
        if (this.content != null) {
            bf = ByteBuffer.allocate(10 + this.content.length);
        } else {
            bf = ByteBuffer.allocate(10);
        }
        bf.order(ByteOrder.BIG_ENDIAN);

        bf.put(this.startSymbol);
        bf.put(this.version);
        bf.putShort(this.length);
        bf.put(this.msgType.toByte());
        bf.putInt(this.seqNum);
        if (this.content != null) {
            bf.put(this.content);
        }
        bf.put(this.endSymbol);
        return bf.array();
    }

    public CommonMsg parse(byte[] data) {
        byte[] bDest = null;
        CommonMsg commonMsg = new CommonMsg();

        commonMsg.setStartSymbol(data[0]);
        commonMsg.setVersion(data[1]);

        bDest = new byte[2];
        System.arraycopy(data, 2, bDest, 0, 2);
        int len = ByteUtil.bytes2smallint(bDest, 0);
        commonMsg.setLength((short) len);

        commonMsg.setMsgType(EMsgType.valueOf(data[4]));

        bDest = new byte[4];
        System.arraycopy(data, 5, bDest, 0, 4);
        commonMsg.setSeqNum(ByteUtil.bytes2int(bDest, 0));

        bDest = new byte[len - 1];
        System.arraycopy(data, 9, bDest, 0, len - 1);
        commonMsg.setContent(bDest);

        commonMsg.setEndSymbol(data[data.length - 1]);
        return commonMsg;
    }

    public byte getStartSymbol() {
        return startSymbol;
    }

    public void setStartSymbol(byte startSymbol) {
        this.startSymbol = startSymbol;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte getEndSymbol() {
        return endSymbol;
    }

    public void setEndSymbol(byte endSymbol) {
        this.endSymbol = endSymbol;
    }

    public EMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(EMsgType msgType) {
        this.msgType = msgType;
    }
}
