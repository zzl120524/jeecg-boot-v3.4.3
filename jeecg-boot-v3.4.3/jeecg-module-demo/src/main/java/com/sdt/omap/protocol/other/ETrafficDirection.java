package com.sdt.omap.protocol.other;

public enum ETrafficDirection {

    FORWARD((byte) 0x01),
    BACKWARD((byte) 0x02);

    private byte _command;

    ETrafficDirection(byte _command) {
        this._command = _command;
    }

    public byte toByte() {
        return this._command;
    }

    public static ETrafficDirection valueOf(int value) {
        ETrafficDirection[] array = ETrafficDirection.values();

        for (int i = 0; i < array.length; ++i) {
            if (array[i]._command == value) {
                return array[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(BACKWARD.toByte());
        System.out.println(ETrafficDirection.valueOf(0x02));
    }
}
