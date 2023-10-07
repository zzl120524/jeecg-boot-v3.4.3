package com.sdt.omap.protocol.other;

public enum ESignalMsg {

    SIP((byte) 0x01),
    ISUP((byte) 0x02),
    XC((byte) 0x03),
    SMPP((byte) 0x04),
    H248((byte) 0x05),
    IKE((byte) 0x06);


    private byte _command;

    ESignalMsg(byte _command) {
        this._command = _command;
    }

    public byte toByte() {
        return this._command;
    }

    public static ESignalMsg valueOf(int value) {
        ESignalMsg[] array = ESignalMsg.values();

        for (int i = 0; i < array.length; ++i) {
            if (array[i]._command == value) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(H248.toByte());
        System.out.println(ESignalMsg.valueOf(0x02));
    }
}
