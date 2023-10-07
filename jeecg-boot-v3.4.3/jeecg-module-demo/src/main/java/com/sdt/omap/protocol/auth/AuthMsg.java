package com.sdt.omap.protocol.auth;

import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.util.standard.ByteUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AuthMsg {

    private byte version; // 1B
    private short length; // 2B
    private EMsgType msgType; // 1B
    private byte[] content; // nB

    public byte[] getByteArray() {

        ByteBuffer bf = ByteBuffer.allocate(4 + this.content.length);
        bf.order(ByteOrder.BIG_ENDIAN);

        bf.put(this.version);
        bf.putShort(this.length);
        bf.put(this.msgType.toByte());
        bf.put(this.content);
        return bf.array();
    }

    public AuthMsg parse(byte[] data) {

        byte[] bDest = null;
        AuthMsg authMsg = new AuthMsg();

        authMsg.setVersion(data[0]);

        bDest = new byte[2];
        System.arraycopy(data, 1, bDest, 0, 2);
        int len = ByteUtil.bytes2smallint(bDest, 0);
        authMsg.setLength((short) len);

        authMsg.setMsgType(EMsgType.valueOf(ByteUtil.byte2tinyint(data[3])));

        bDest = new byte[len - 1];
        System.arraycopy(data, 4, bDest, 0, len - 1);
        authMsg.setContent(bDest);

        return authMsg;
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

    public EMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(EMsgType msgType) {
        this.msgType = msgType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "version:" + this.version + ", length:" + this.length + ",msgType:" + this.msgType;
    }
}
