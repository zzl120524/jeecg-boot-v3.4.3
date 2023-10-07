package com.sdt.omap.protocol.other;

public enum ECodec {

    AMR((byte) 0x01),
    EVRC((byte) 0x02),
    G711((byte) 0x03);

    private byte _command;

    ECodec(byte _command) {
        this._command = _command;
    }

    public byte toByte() {
        return this._command;
    }

    public static ECodec valueOf(int value) {
        ECodec[] array = ECodec.values();

        for (int i = 0; i < array.length; ++i) {
            if (array[i]._command == value) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(EVRC.toByte());
        System.out.println(ETrafficDirection.valueOf(0x02));
    }
}
