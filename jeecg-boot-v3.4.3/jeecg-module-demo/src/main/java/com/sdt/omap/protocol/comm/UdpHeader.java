package com.sdt.omap.protocol.comm;

public class UdpHeader {


    private byte[] sourcePort; // 2B
    private byte[] destinationPort; // 2B
    private byte[] length; // 2B
    private byte[] checksum; // 2B


    public byte[] getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(byte[] sourcePort) {
        this.sourcePort = sourcePort;
    }

    public byte[] getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(byte[] destinationPort) {
        this.destinationPort = destinationPort;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getChecksum() {
        return checksum;
    }

    public void setChecksum(byte[] checksum) {
        this.checksum = checksum;
    }
}
