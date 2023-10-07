package com.sdt.omap.protocol.cdr;

public class CDRRecordSip {

    private byte recType;
    private byte[] cdrNo;
    private byte[] relationId;
    private byte[] callingPartyNumber;
    private byte[] calledPartyNumber;
    private byte paraFlag;
    private byte[] callingLocation;
    private byte[] calledLocation;
    private byte[] fwHistoryInfo;
    private byte[] bwHistoryInfo;
    private byte[] startTime;
    private byte[] alertTime;
    private byte[] answerTime;
    private byte[] releaseTime;
    private byte[] releaseCause;
    private byte fwMediaInterrupEventNum;
    private byte bwMediaInterrupEventNum;
    private byte[] fwMediaFlowLossRate;
    private byte[] bwMediaFlowLossRate;
    private byte[] mediaEventType1Num;
    private byte[] mediaEventType2Num;
    private byte[] mediaEventType3Num;
    private byte[] mediaEventType4Num;
    private byte[] mediaEventType5Num;
    private byte[] mediaEventType6Num;
    private byte[] mediaEventType7Num;
    private byte[] mediaEventType8Num;
    private byte[] mediaEventType9Num;
    private byte[] mediaEventType10Num;
    private byte[] rev;
    private byte[] endRec;
    public CDRRecordSip parse(byte[] data) throws Exception {
        if (data == null && data.length <= 264) {
            throw new Exception("CdrRecordSip 格式错误");
        }
        byte[] bDest = null;
        CDRRecordSip cdrRecordSip = new CDRRecordSip();
        cdrRecordSip.setRecType(data[0]);

        bDest = new byte[4];
        System.arraycopy(data, 1, bDest, 0, 4);
        cdrRecordSip.setCdrNo(bDest);

        bDest = new byte[8];
        System.arraycopy(data, 5, bDest, 0, 8);
        cdrRecordSip.setRelationId(bDest);

        bDest = new byte[24];//24B
        System.arraycopy(data, 13, bDest, 0, 24);
        cdrRecordSip.setCallingPartyNumber(bDest);

        bDest = new byte[24];
        System.arraycopy(data, 37, bDest, 0, 24);
        cdrRecordSip.setCalledPartyNumber(bDest);

        cdrRecordSip.setParaFlag(data[61]);

        bDest = new byte[33];
        System.arraycopy(data, 62, bDest, 0, 33);
        cdrRecordSip.setCallingLocation(bDest);

        bDest = new byte[33];
        System.arraycopy(data, 95, bDest, 0, 33);
        cdrRecordSip.setCalledLocation(bDest);

        bDest = new byte[26];
        System.arraycopy(data, 128, bDest, 0, 26);
        cdrRecordSip.setFwHistoryInfo(bDest);

        bDest = new byte[26];
        System.arraycopy(data, 154, bDest, 0, 26);
        cdrRecordSip.setBwHistoryInfo(bDest);

        bDest = new byte[7];
        System.arraycopy(data, 180, bDest, 0, 7);
        cdrRecordSip.setStartTime(bDest);

        bDest = new byte[7];
        System.arraycopy(data, 187, bDest, 0, 7);
        cdrRecordSip.setAlertTime(bDest);

        bDest = new byte[7];
        System.arraycopy(data, 194, bDest, 0, 7);
        cdrRecordSip.setAnswerTime(bDest);

        bDest = new byte[7];
        System.arraycopy(data, 201, bDest, 0, 7);
        cdrRecordSip.setReleaseTime(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 208, bDest, 0, 2);
        cdrRecordSip.setReleaseCause(bDest);

        cdrRecordSip.setFwMediaInterrupEventNum(data[210]);

        cdrRecordSip.setBwMediaInterrupEventNum(data[211]);

        bDest = new byte[2];
        System.arraycopy(data, 212, bDest, 0, 2);
        cdrRecordSip.setFwMediaFlowLossRate(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 214, bDest, 0, 2);
        cdrRecordSip.setBwMediaFlowLossRate(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 216, bDest, 0, 2);
        cdrRecordSip.setMediaEventType1Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 218, bDest, 0, 2);
        cdrRecordSip.setMediaEventType2Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 220, bDest, 0, 2);
        cdrRecordSip.setMediaEventType3Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 222, bDest, 0, 2);
        cdrRecordSip.setMediaEventType4Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 224, bDest, 0, 2);
        cdrRecordSip.setMediaEventType5Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 226, bDest, 0, 2);
        cdrRecordSip.setMediaEventType6Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 228, bDest, 0, 2);
        cdrRecordSip.setMediaEventType7Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 230, bDest, 0, 2);
        cdrRecordSip.setMediaEventType8Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 232, bDest, 0, 2);
        cdrRecordSip.setMediaEventType9Num(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 234, bDest, 0, 2);
        cdrRecordSip.setMediaEventType10Num(bDest);

        bDest = new byte[26];
        System.arraycopy(data, 236, bDest, 0, 26);
        cdrRecordSip.setRev(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 262, bDest, 0, 2);
        cdrRecordSip.setEndRec(bDest);
        return cdrRecordSip;
    }

    public byte getRecType() {
        return recType;
    }

    public void setRecType(byte recType) {
        this.recType = recType;
    }

    public byte[] getCdrNo() {
        return cdrNo;
    }

    public void setCdrNo(byte[] cdrNo) {
        this.cdrNo = cdrNo;
    }

    public byte[] getRelationId() {
        return relationId;
    }

    public void setRelationId(byte[] relationId) {
        this.relationId = relationId;
    }

    public byte[] getCallingPartyNumber() {
        return callingPartyNumber;
    }

    public void setCallingPartyNumber(byte[] callingPartyNumber) {
        this.callingPartyNumber = callingPartyNumber;
    }

    public byte[] getCalledPartyNumber() {
        return calledPartyNumber;
    }

    public void setCalledPartyNumber(byte[] calledPartyNumber) {
        this.calledPartyNumber = calledPartyNumber;
    }

    public byte getParaFlag() {
        return paraFlag;
    }

    public void setParaFlag(byte paraFlag) {
        this.paraFlag = paraFlag;
    }

    public byte[] getCallingLocation() {
        return callingLocation;
    }

    public void setCallingLocation(byte[] callingLocation) {
        this.callingLocation = callingLocation;
    }

    public byte[] getCalledLocation() {
        return calledLocation;
    }

    public void setCalledLocation(byte[] calledLocation) {
        this.calledLocation = calledLocation;
    }

    public byte[] getFwHistoryInfo() {
        return fwHistoryInfo;
    }

    public void setFwHistoryInfo(byte[] fwHistoryInfo) {
        this.fwHistoryInfo = fwHistoryInfo;
    }

    public byte[] getBwHistoryInfo() {
        return bwHistoryInfo;
    }

    public void setBwHistoryInfo(byte[] bwHistoryInfo) {
        this.bwHistoryInfo = bwHistoryInfo;
    }

    public byte[] getStartTime() {
        return startTime;
    }

    public void setStartTime(byte[] startTime) {
        this.startTime = startTime;
    }

    public byte[] getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(byte[] alertTime) {
        this.alertTime = alertTime;
    }

    public byte[] getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(byte[] answerTime) {
        this.answerTime = answerTime;
    }

    public byte[] getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(byte[] releaseTime) {
        this.releaseTime = releaseTime;
    }

    public byte[] getReleaseCause() {
        return releaseCause;
    }

    public void setReleaseCause(byte[] releaseCause) {
        this.releaseCause = releaseCause;
    }

    public byte getFwMediaInterrupEventNum() {
        return fwMediaInterrupEventNum;
    }

    public void setFwMediaInterrupEventNum(byte fwMediaInterrupEventNum) {
        this.fwMediaInterrupEventNum = fwMediaInterrupEventNum;
    }

    public byte getBwMediaInterrupEventNum() {
        return bwMediaInterrupEventNum;
    }

    public void setBwMediaInterrupEventNum(byte bwMediaInterrupEventNum) {
        this.bwMediaInterrupEventNum = bwMediaInterrupEventNum;
    }

    public byte[] getFwMediaFlowLossRate() {
        return fwMediaFlowLossRate;
    }

    public void setFwMediaFlowLossRate(byte[] fwMediaFlowLossRate) {
        this.fwMediaFlowLossRate = fwMediaFlowLossRate;
    }

    public byte[] getBwMediaFlowLossRate() {
        return bwMediaFlowLossRate;
    }

    public void setBwMediaFlowLossRate(byte[] bwMediaFlowLossRate) {
        this.bwMediaFlowLossRate = bwMediaFlowLossRate;
    }

    public byte[] getMediaEventType1Num() {
        return mediaEventType1Num;
    }

    public void setMediaEventType1Num(byte[] mediaEventType1Num) {
        this.mediaEventType1Num = mediaEventType1Num;
    }

    public byte[] getMediaEventType2Num() {
        return mediaEventType2Num;
    }

    public void setMediaEventType2Num(byte[] mediaEventType2Num) {
        this.mediaEventType2Num = mediaEventType2Num;
    }

    public byte[] getMediaEventType3Num() {
        return mediaEventType3Num;
    }

    public void setMediaEventType3Num(byte[] mediaEventType3Num) {
        this.mediaEventType3Num = mediaEventType3Num;
    }

    public byte[] getMediaEventType4Num() {
        return mediaEventType4Num;
    }

    public void setMediaEventType4Num(byte[] mediaEventType4Num) {
        this.mediaEventType4Num = mediaEventType4Num;
    }

    public byte[] getMediaEventType5Num() {
        return mediaEventType5Num;
    }

    public void setMediaEventType5Num(byte[] mediaEventType5Num) {
        this.mediaEventType5Num = mediaEventType5Num;
    }

    public byte[] getMediaEventType6Num() {
        return mediaEventType6Num;
    }

    public void setMediaEventType6Num(byte[] mediaEventType6Num) {
        this.mediaEventType6Num = mediaEventType6Num;
    }

    public byte[] getMediaEventType7Num() {
        return mediaEventType7Num;
    }

    public void setMediaEventType7Num(byte[] mediaEventType7Num) {
        this.mediaEventType7Num = mediaEventType7Num;
    }

    public byte[] getMediaEventType8Num() {
        return mediaEventType8Num;
    }

    public void setMediaEventType8Num(byte[] mediaEventType8Num) {
        this.mediaEventType8Num = mediaEventType8Num;
    }

    public byte[] getMediaEventType9Num() {
        return mediaEventType9Num;
    }

    public void setMediaEventType9Num(byte[] mediaEventType9Num) {
        this.mediaEventType9Num = mediaEventType9Num;
    }

    public byte[] getMediaEventType10Num() {
        return mediaEventType10Num;
    }

    public void setMediaEventType10Num(byte[] mediaEventType10Num) {
        this.mediaEventType10Num = mediaEventType10Num;
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
