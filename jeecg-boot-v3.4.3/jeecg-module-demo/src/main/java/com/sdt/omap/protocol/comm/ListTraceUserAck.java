package com.sdt.omap.protocol.comm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ListTraceUserAck {

    private byte[] facId; // 2B
    private byte result; // 1B
    private byte frameType; // 1B
    private byte traceUserNum; // 1B
    private byte[] traceUser; // NB  traceUserNum * size TODO

    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(5 + this.traceUser.length);
        bf.order(ByteOrder.BIG_ENDIAN);

        bf.put(this.facId);
        bf.put(this.result);
        bf.put(this.frameType);
        bf.put(this.traceUserNum);
        bf.put(this.traceUser);
        return bf.array();
    }

    public byte[] getFacId() {
        return facId;
    }

    public void setFacId(byte[] facId) {
        this.facId = facId;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public byte getFrameType() {
        return frameType;
    }

    public void setFrameType(byte frameType) {
        this.frameType = frameType;
    }

    public byte getTraceUserNum() {
        return traceUserNum;
    }

    public void setTraceUserNum(byte traceUserNum) {
        this.traceUserNum = traceUserNum;
    }

    public byte[] getTraceUser() {
        return traceUser;
    }

    public void setTraceUser(byte[] traceUser) {
        this.traceUser = traceUser;
    }
}
