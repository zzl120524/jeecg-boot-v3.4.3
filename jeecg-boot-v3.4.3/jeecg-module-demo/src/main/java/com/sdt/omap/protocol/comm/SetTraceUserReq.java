package com.sdt.omap.protocol.comm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SetTraceUserReq {

    private byte opType; // 1B
    private byte traceUserNum; // 1B
    private byte[] traceUser; // traceUserNum * N  25(1+24) * N
    private byte[] traceDuration;// 2B

    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(4 + this.traceUser.length);
        bf.order(ByteOrder.BIG_ENDIAN);
        bf.put(this.opType);
        bf.put(this.traceUserNum);
        bf.put(this.traceUser);
        bf.put(this.traceDuration);
        return bf.array();
    }

    public byte getOpType() {
        return opType;
    }

    public void setOpType(byte opType) {
        this.opType = opType;
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

    public byte[] getTraceDuration() {
        return traceDuration;
    }

    public void setTraceDuration(byte[] traceDuration) {
        this.traceDuration = traceDuration;
    }
}
