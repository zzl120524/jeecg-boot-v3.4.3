package com.sdt.omap.protocol.comm;

public class SetAlarmpolicyAck {

    private byte[] facId; // 2B
    private byte result; // 1B

    public SetAlarmpolicyAck parse(byte[] data) {
        SetAlarmpolicyAck setTraceUserAck = new SetAlarmpolicyAck();
        byte[] facId = new byte[2];
        System.arraycopy(data,0,facId,0,2);
        setTraceUserAck.setFacId(facId);
        setTraceUserAck.setResult(data[2]);
        return setTraceUserAck;
    }

    public byte[] getFacId() {
        return facId;
    }

    public void setFacId(byte[] facId) {
        this.facId = facId;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }
}
