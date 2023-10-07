package com.sdt.omap.protocol.other;

public enum EUpLoadType {

    SIGNALTRACE((byte) 0x01),
    SIGNALRTP((byte) 0x02),
    SIGNALCOPY((byte) 0x03),
    EXCEPTION((byte) 0x04),;

    private byte _command;

    EUpLoadType(byte _command) {
        this._command = _command;
    }

    public byte toByte() {
        return this._command;
    }

    public static EUpLoadType valueOf(int value) {
        EUpLoadType[] array = EUpLoadType.values();

        for (int i = 0; i < array.length; ++i) {
            if (array[i]._command == value) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(SIGNALCOPY.toByte());
        System.out.println(EUpLoadType.valueOf(0x03));
    }
}
