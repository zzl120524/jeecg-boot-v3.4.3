package com.sdt.omap.protocol.cdr;

public class TailRecord {

    private byte recType;
    private byte[] endTime;
    private byte[] cdrRecordNum;
    private byte fileCloseCause;
    private byte[] rev;
    private byte[] endRec;



    public TailRecord parse(byte[] data) throws Exception {
        if (data == null && data.length <= 64) {
            throw new Exception("TailRecord 格式错误");
        }
        byte[] bDest = null;
        TailRecord tailRecord = new TailRecord();
        tailRecord.setRecType(data[0]);

        bDest = new byte[7];
        System.arraycopy(data, 1, bDest, 0, 7);
        tailRecord.setEndTime(bDest);

        bDest = new byte[4];
        System.arraycopy(data, 8, bDest, 0, 4);
        tailRecord.setCdrRecordNum(bDest);

        tailRecord.setFileCloseCause(data[12]);

        bDest = new byte[49];
        System.arraycopy(data, 13, bDest, 0, 49);
        tailRecord.setRev(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 62, bDest, 0, 2);
        tailRecord.setEndRec(bDest);

        return tailRecord;
    }

    public byte getRecType() {
        return recType;
    }

    public void setRecType(byte recType) {
        this.recType = recType;
    }

    public byte[] getEndTime() {
        return endTime;
    }

    public void setEndTime(byte[] endTime) {
        this.endTime = endTime;
    }

    public byte[] getCdrRecordNum() {
        return cdrRecordNum;
    }

    public void setCdrRecordNum(byte[] cdrRecordNum) {
        this.cdrRecordNum = cdrRecordNum;
    }

    public byte getFileCloseCause() {
        return fileCloseCause;
    }

    public void setFileCloseCause(byte fileCloseCause) {
        this.fileCloseCause = fileCloseCause;
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
}
