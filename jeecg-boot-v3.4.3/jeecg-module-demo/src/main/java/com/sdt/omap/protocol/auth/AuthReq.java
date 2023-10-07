package com.sdt.omap.protocol.auth;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AuthReq {

    private byte[] facId; // 2B
    private byte[] randFAC; // 4B
    private byte[] mic; // 4B

    public AuthReq parse(byte[] data) {

        byte[] bDest = null;
        AuthReq authReq = new AuthReq();

        bDest = new byte[2];
        System.arraycopy(data, 0, bDest, 0, 2);
        authReq.setFacId(bDest);

        bDest = new byte[4];
        System.arraycopy(data, 2, bDest, 0, 4);
        authReq.setRandFAC(bDest);

        bDest = new byte[4];
        System.arraycopy(data, 6, bDest, 0, 4);
        authReq.setMic(bDest);
        return authReq;
    }

    public byte[] getByteArray() throws Exception {
        ByteBuffer bf = null;
        if (this.mic !=null) {
            bf = ByteBuffer.allocate(10);
        } else {
            bf = ByteBuffer.allocate(6);
        }

        bf.order(ByteOrder.BIG_ENDIAN);
        bf.put(this.facId);
        bf.put(this.randFAC);
        if (this.mic != null) {
            bf.put(this.mic);
        }

        return bf.array();
    }

    public byte[] getFacId() {
        return facId;
    }

    public void setFacId(byte[] facId) {
        this.facId = facId;
    }

    public byte[] getRandFAC() {
        return randFAC;
    }

    public void setRandFAC(byte[] randFAC) {
        this.randFAC = randFAC;
    }

    public byte[] getMic() {
        return mic;
    }

    public void setMic(byte[] mic) {
        this.mic = mic;
    }


}
