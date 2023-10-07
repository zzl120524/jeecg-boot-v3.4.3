package com.sdt.omap.protocol.other;

public enum EResult {

    SUCCESS((byte) 0x00),
    FAIL((byte) 0x02);

    private byte _command;

    EResult(byte _command) {
        this._command = _command;
    }

    public byte toByte() {
        return this._command;
    }

    public static EResult valueOf(int value) {
        EResult[] array = EResult.values();

        for (int i = 0; i < array.length; ++i) {
            if (array[i]._command == value) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(SUCCESS.toByte());
        System.out.println(EResult.valueOf(0x02));
    }
}
