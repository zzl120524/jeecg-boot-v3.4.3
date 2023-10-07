package com.sdt.omap.protocol.auth;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AuthAck {

    private byte[] facId; // 2B
    private byte[] controlIfIp; // 4B
    private byte[] controlIfPort; // 2B
    private byte[] signalPacketReportIp; // 4B
    private byte[] signalPacketReportPort; // 2B
    private byte[] tracePacketReportIp; // 4B
    private byte[] tracePacketReportPort; // 2B
    private byte[] encryptPacketReportIp; // 4B
    private byte[] encryptPacketReportPort; // 2B
    private byte[] malformedPacketReportIp; // 4B
    private byte[] malformedPacketReportPort; // 2B
    private byte[] cdrFTPUser; // NB LV
    private byte[] cdrFTPKey; // NB LV
    private byte[] ftpDirectory; // NB LV
    private byte[] ftpServerIp; // 4B
    private byte[] mic; // 4B

    public byte[] getByteArray() {
        ByteBuffer bf = null;
        if (this.mic != null) {
            bf = ByteBuffer.allocate(40 + this.cdrFTPUser.length + this.cdrFTPKey.length + this.ftpDirectory.length);
        } else {
            bf = ByteBuffer.allocate(36 + this.cdrFTPUser.length + this.cdrFTPKey.length + this.ftpDirectory.length);
        }
        bf.order(ByteOrder.BIG_ENDIAN);

        bf.put(this.facId);
        bf.put(this.controlIfIp);
        bf.put(this.controlIfPort);
        bf.put(this.signalPacketReportIp);
        bf.put(this.signalPacketReportPort);
        bf.put(this.tracePacketReportIp);
        bf.put(this.tracePacketReportPort);
        bf.put(this.encryptPacketReportIp);
        bf.put(this.encryptPacketReportPort);
        bf.put(this.malformedPacketReportIp);
        bf.put(this.malformedPacketReportPort);
        bf.put(this.cdrFTPUser);
        bf.put(this.cdrFTPKey);
        bf.put(this.ftpDirectory);
        bf.put(this.ftpServerIp);
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

    public byte[] getControlIfIp() {
        return controlIfIp;
    }

    public void setControlIfIp(byte[] controlIfIp) {
        this.controlIfIp = controlIfIp;
    }

    public byte[] getControlIfPort() {
        return controlIfPort;
    }

    public void setControlIfPort(byte[] controlIfPort) {
        this.controlIfPort = controlIfPort;
    }

    public byte[] getSignalPacketReportIp() {
        return signalPacketReportIp;
    }

    public void setSignalPacketReportIp(byte[] signalPacketReportIp) {
        this.signalPacketReportIp = signalPacketReportIp;
    }

    public byte[] getSignalPacketReportPort() {
        return signalPacketReportPort;
    }

    public void setSignalPacketReportPort(byte[] signalPacketReportPort) {
        this.signalPacketReportPort = signalPacketReportPort;
    }

    public byte[] getTracePacketReportIp() {
        return tracePacketReportIp;
    }

    public void setTracePacketReportIp(byte[] tracePacketReportIp) {
        this.tracePacketReportIp = tracePacketReportIp;
    }

    public byte[] getTracePacketReportPort() {
        return tracePacketReportPort;
    }

    public void setTracePacketReportPort(byte[] tracePacketReportPort) {
        this.tracePacketReportPort = tracePacketReportPort;
    }

    public byte[] getEncryptPacketReportIp() {
        return encryptPacketReportIp;
    }

    public void setEncryptPacketReportIp(byte[] encryptPacketReportIp) {
        this.encryptPacketReportIp = encryptPacketReportIp;
    }

    public byte[] getEncryptPacketReportPort() {
        return encryptPacketReportPort;
    }

    public void setEncryptPacketReportPort(byte[] encryptPacketReportPort) {
        this.encryptPacketReportPort = encryptPacketReportPort;
    }

    public byte[] getMalformedPacketReportIp() {
        return malformedPacketReportIp;
    }

    public void setMalformedPacketReportIp(byte[] malformedPacketReportIp) {
        this.malformedPacketReportIp = malformedPacketReportIp;
    }

    public byte[] getMalformedPacketReportPort() {
        return malformedPacketReportPort;
    }

    public void setMalformedPacketReportPort(byte[] malformedPacketReportPort) {
        this.malformedPacketReportPort = malformedPacketReportPort;
    }

    public byte[] getCdrFTPUser() {
        return cdrFTPUser;
    }

    public void setCdrFTPUser(byte[] cdrFTPUser) {
        this.cdrFTPUser = cdrFTPUser;
    }

    public byte[] getCdrFTPKey() {
        return cdrFTPKey;
    }

    public void setCdrFTPKey(byte[] cdrFTPKey) {
        this.cdrFTPKey = cdrFTPKey;
    }

    public byte[] getFtpDirectory() {
        return ftpDirectory;
    }

    public void setFtpDirectory(byte[] ftpDirectory) {
        this.ftpDirectory = ftpDirectory;
    }

    public byte[] getFtpServerIp() {
        return ftpServerIp;
    }

    public void setFtpServerIp(byte[] ftpServerIp) {
        this.ftpServerIp = ftpServerIp;
    }

    public byte[] getMic() {
        return mic;
    }

    public void setMic(byte[] mic) {
        this.mic = mic;
    }
}
