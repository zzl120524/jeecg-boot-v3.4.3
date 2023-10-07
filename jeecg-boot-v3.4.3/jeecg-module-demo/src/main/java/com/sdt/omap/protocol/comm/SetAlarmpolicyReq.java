package com.sdt.omap.protocol.comm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SetAlarmpolicyReq {


    private byte opType; // 1B
    private byte alarmpolicy; // 1B


    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(2);
        bf.order(ByteOrder.BIG_ENDIAN);

        bf.put(this.opType);
        bf.put(this.alarmpolicy);

        return bf.array();
    }

    public byte getOpType() {
        return opType;
    }

    public void setOpType(byte opType) {
        this.opType = opType;
    }

    public byte getAlarmpolicy() {
        return alarmpolicy;
    }

    public void setAlarmpolicy(byte alarmpolicy) {
        this.alarmpolicy = alarmpolicy;
    }
}
