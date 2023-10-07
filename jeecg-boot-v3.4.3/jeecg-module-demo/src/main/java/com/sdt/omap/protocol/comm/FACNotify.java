package com.sdt.omap.protocol.comm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FACNotify {

    private byte[] facId;//2b

    public byte[] getFacId() {
        return facId;
    }

    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(2);
        bf.order(ByteOrder.BIG_ENDIAN);

        bf.put(this.facId);
        return bf.array();
    }

    public void setFacId(byte[] facId) {
        this.facId = facId;
    }

    public FACNotify parse(byte[] data) {
        FACNotify facNotify = new FACNotify();
        byte[] facId = new byte[2];
        System.arraycopy(data,0,facId,0,2);
        facNotify.setFacId(facId);
        return facNotify;
    }
}
