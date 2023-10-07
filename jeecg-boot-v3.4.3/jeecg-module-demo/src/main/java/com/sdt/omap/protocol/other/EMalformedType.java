package com.sdt.omap.protocol.other;

public enum EMalformedType {

    SIP((byte) 0x01),
    ISUP((byte) 0x02),
    XC((byte) 0x03),
    RTP((byte) 0x04),
    H248((byte) 0x05);


    private byte _command;

    EMalformedType(byte _command) {
        this._command = _command;
    }

    public byte toByte() {
        return this._command;
    }

    public static EMalformedType valueOf(int value) {
        EMalformedType[] array = EMalformedType.values();

        for (int i = 0; i < array.length; ++i) {
            if (array[i]._command == value) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(H248.toByte());
        System.out.println(EMalformedType.valueOf(0x02));
    }
}
