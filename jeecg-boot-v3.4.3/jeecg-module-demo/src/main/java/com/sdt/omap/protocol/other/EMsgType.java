package com.sdt.omap.protocol.other;

public enum EMsgType {

    AUTHREQ((byte) 0x01),
    AUTHACK((byte) 0x02),
    FACNOTIFY((byte) 0x03),
    SETTRACEUSERREQ((byte) 0x04),
    SETTRACEUSERACK((byte) 0x05),
    LISTTRACEUSERREQ((byte) 0x06),
    REPORTTRACEUSER((byte) 0x07),
    SETALARMPOLICYREQ((byte) 0x08),
    SETALARMPOLICYACK((byte) 0x09);

    private byte _command;

    EMsgType(byte _command) {
        this._command = _command;
    }

    public byte toByte() {
        return this._command;
    }

    public static EMsgType valueOf(int value) {
        EMsgType[] array = EMsgType.values();

        for (int i = 0; i < array.length; ++i) {
            if (array[i]._command == value) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(REPORTTRACEUSER.toByte());
        System.out.println(EMsgType.valueOf(0x02));
    }
}
