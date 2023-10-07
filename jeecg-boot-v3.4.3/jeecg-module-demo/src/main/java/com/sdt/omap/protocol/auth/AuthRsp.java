package com.sdt.omap.protocol.auth;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AuthRsp {

    private byte[] facId; // 2B
    private byte[] randOMAP; // 4B
    private byte[] ipA; // B
    private byte[] portA; // B
    private byte[] bfileDirectory; // B
    private byte[] cIp; // B
    private byte[] c1port; // B
    private byte[] c2port; // B
    private byte[] mic; // 4B

    public AuthRsp parse(byte[] data) {

        byte[] bDest = null;
        AuthRsp authReq = new AuthRsp();

        bDest = new byte[2];
        System.arraycopy(data, 0, bDest, 0, 2);
        authReq.setFacId(bDest);

        bDest = new byte[4];
        System.arraycopy(data, 2, bDest, 0, 4);
        authReq.setRandOMAP(bDest);

        bDest = new byte[4];
        System.arraycopy(data, 6, bDest, 0, 4);
        authReq.setRandOMAP(bDest);
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
        bf.put(this.randOMAP);
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

    public byte[] getRandOMAP() {
        return randOMAP;
    }

    public void setRandOMAP(byte[] randOMAP) {
        this.randOMAP = randOMAP;
    }

    public byte[] getIpA() {
        return ipA;
    }

    public void setIpA(byte[] ipA) {
        this.ipA = ipA;
    }

    public byte[] getPortA() {
        return portA;
    }

    public void setPortA(byte[] portA) {
        this.portA = portA;
    }

    public byte[] getBfileDirectory() {
        return bfileDirectory;
    }

    public void setBfileDirectory(byte[] bfileDirectory) {
        this.bfileDirectory = bfileDirectory;
    }

    public byte[] getcIp() {
        return cIp;
    }

    public void setcIp(byte[] cIp) {
        this.cIp = cIp;
    }

    public byte[] getC1port() {
        return c1port;
    }

    public void setC1port(byte[] c1port) {
        this.c1port = c1port;
    }

    public byte[] getC2port() {
        return c2port;
    }

    public void setC2port(byte[] c2port) {
        this.c2port = c2port;
    }

    public byte[] getMic() {
        return mic;
    }

    public void setMic(byte[] mic) {
        this.mic = mic;
    }

}
