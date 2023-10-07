package com.sdt.omap.protocol.cdr;

import com.sdt.util.standard.ByteUtil;

public class HeadRecord {

    private byte recType;
    private byte[] facId;
    private byte[] startTime;
    private byte[] fileSeq;
    private byte version;
    private byte[] rev;
    private byte[] endRec;

    public HeadRecord parse(byte[] data) throws Exception {
        if (data == null && data.length <= 64) {
            throw new Exception("HeadRecord 格式错误");
        }
        byte[] bDest = null;
        HeadRecord headRecord = new HeadRecord();
        headRecord.setRecType(data[0]);

        bDest = new byte[2];
        System.arraycopy(data, 1, bDest, 0, 2);
        headRecord.setFacId(bDest);

        bDest = new byte[7];
        System.arraycopy(data, 3, bDest, 0, 7);
        headRecord.setStartTime(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 10, bDest, 0, 2);
        headRecord.setFileSeq(bDest);

        headRecord.setVersion(data[12]);

        bDest = new byte[49];
        System.arraycopy(data, 13, bDest, 0, 49);
        headRecord.setRev(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 62, bDest, 0, 2);
        headRecord.setEndRec(bDest);

        return headRecord;
    }

    public byte getRecType() {
        return recType;
    }

    public void setRecType(byte recType) {
        this.recType = recType;
    }

    public byte[] getFacId() {
        return facId;
    }

    public void setFacId(byte[] facId) {
        this.facId = facId;
    }

    public byte[] getStartTime() {
        return startTime;
    }

    public void setStartTime(byte[] startTime) {
        this.startTime = startTime;
    }

    public byte[] getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(byte[] fileSeq) {
        this.fileSeq = fileSeq;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte[] getRev() {
        return rev;
    }

    public void setRev(byte[] rev) {
        this.rev = rev;
    }

    public byte[] getEndRec() {
        return endRec;
    }

    public void setEndRec(byte[] endRec) {
        this.endRec = endRec;
    }

    @Override
    public String toString() {
        System.out.println("================>HeadRecord");
        String msg = "recType =" + this.recType + "\n"
                + "facId=" + ByteUtil.byte2HexString0x(this.facId, true) + "\n"
                + "startTime=" + ByteUtil.byte2HexString0x(this.startTime, true) + "\n"
                + "fileSeq=" + ByteUtil.byte2HexString0x(this.fileSeq, true) + "\n"
                + "fileSeq=" + ByteUtil.byte2HexString0x(this.fileSeq, true) + "\n"
                + "version=" + this.version + "\n"
                + "rev=" + ByteUtil.byte2HexString0x(this.rev, true) + "\n";
        return msg;
    }
}
