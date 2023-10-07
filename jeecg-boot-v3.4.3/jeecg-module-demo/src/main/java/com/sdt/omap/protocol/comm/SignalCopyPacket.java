package com.sdt.omap.protocol.comm;

import com.sdt.omap.protocol.enumUtil.SignalCodecType;
import com.sdt.omap.protocol.enumUtil.UploadType;
import com.sdt.omap.protocol.other.ECodec;
import com.sdt.omap.protocol.other.ESignalMsg;
import com.sdt.omap.protocol.other.ETrafficDirection;
import com.sdt.omap.protocol.other.EUpLoadType;
import com.sdt.omap.utils.ByteUtils;
import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.common.util.ByteConversionUtils;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SignalCopyPacket {

    private byte ver; // 1B
    private byte[] facId; // 2B
    private EUpLoadType upLoadType; // 1B
    private short upLoadLength; // 2B
    private byte[] relationId; // 8B
    private ESignalMsg signalType; // 1B
    private byte[] msgName; // 1B
    private short signalPacketLen; // 2B
    private byte[] signalPacket; // NB

    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(this.upLoadLength);
        bf.order(ByteOrder.BIG_ENDIAN);
        bf.put(this.ver);
        bf.put(this.facId);
        bf.put(this.upLoadType.toByte());
        bf.putShort(this.upLoadLength);
        bf.put(this.relationId);
        bf.put(this.signalType.toByte());
        bf.put(this.msgName);
        bf.putShort(this.signalPacketLen);
        bf.put(this.signalPacket);
        return bf.array();
    }

    public SignalCopyPacket parse(byte[] data) {

        byte[] bDest = null;
        SignalCopyPacket signalCopyPacket = new SignalCopyPacket();

        signalCopyPacket.setVer(data[0]);

        bDest = new byte[2];
        System.arraycopy(data, 1, bDest, 0, 2);
        signalCopyPacket.setFacId(bDest);

        signalCopyPacket.setUpLoadType(EUpLoadType.valueOf(data[3]));

        bDest = new byte[2];
        System.arraycopy(data, 4, bDest, 0, 2);
        int len = ByteUtil.bytes2smallint(bDest, 0);
        signalCopyPacket.setUpLoadLength((short) len);

        bDest = new byte[8];
        System.arraycopy(data, 6, bDest, 0, 8);
        signalCopyPacket.setRelationId(bDest);

        signalCopyPacket.setSignalType(ESignalMsg.valueOf(data[14]));

        bDest = new byte[16];
        System.arraycopy(data, 15, bDest, 0, 16);
        signalCopyPacket.setMsgName(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 31, bDest, 0, 2);
        int length = ByteUtil.bytes2smallint(bDest, 0);
        signalCopyPacket.setSignalPacketLen((short) length);


        bDest = new byte[data.length - 33];
        System.arraycopy(data, 33, bDest, 0, data.length - 33);
        signalCopyPacket.setSignalPacket(bDest);

        return signalCopyPacket;
    }

    public SignalUpload parseSignalUpload(SignalCopyPacket packet) throws UnsupportedEncodingException {
        SignalUpload signalUpload = new SignalUpload();
        signalUpload.setVer(String.valueOf(packet.getVer()));
        signalUpload.setFacId(ByteUtil.bytes2smallint(packet.getFacId(),0));
        int type = UploadType.unknown(ByteUtil.byte2tinyint(packet.getUpLoadType().toByte()));
        signalUpload.setUploadType(type);
        signalUpload.setUploadLength(Integer.valueOf(packet.getUpLoadLength()));
        signalUpload.setRelationId(ByteConversionUtils.bytesToLongBig(packet.getRelationId()));
        int signalType = SignalCodecType.unknown(Integer.valueOf(packet.getSignalType().toByte()));
        signalUpload.setSignalType(signalType);
        System.out.println("复制报文MsgName" + ByteUtil.byte2HexString0x(packet.getMsgName(),true));
        String msgName = new String(packet.getMsgName(), "UTF-8");
        signalUpload.setMsgName(msgName.trim());
        if (isInt(msgName)){
            signalUpload.setDirection("回应");
        }else {
            signalUpload.setDirection("发送");
        }
        signalUpload.setSignalPacketLen(Integer.valueOf(packet.getSignalPacketLen()));

        byte[] signalPacket = packet.getSignalPacket();
        byte[] signalPacketByte = new byte[signalPacket.length-28];
        System.arraycopy(signalPacket,28,signalPacketByte,0,signalPacket.length-28);
        signalUpload.setSignalPacket(new String(signalPacketByte,"UTF-8"));

   /*     String signalPacketResult = "";
        try {
            byte[] signalPacket = packet.getSignalPacket();
            System.out.println("signalPacket:"+ ByteUtils.byteToBinStr(signalPacket));
            byte[] ipHeaderByte = new byte[28];
            byte[] signalPacketByte = new byte[signalPacket.length-28];
            System.arraycopy(signalPacket,0,ipHeaderByte,0,28);
            System.arraycopy(signalPacket,28,signalPacketByte,0,signalPacket.length-28);
            String bits = ByteUtils.byteToBinStr(ipHeaderByte);
            System.out.println("bits:"+bits);
            IpHeader ipHeader = new IpHeader();
            IpHeader parse = ipHeader.parse(bits);
            String ipHeaderStr = parse.toString();
            String signalPacketStr = new String(signalPacketByte, "UTF-8");
            signalPacketResult = ipHeaderStr +'\n'+ signalPacketStr;
        }catch (Exception e) {
            e.printStackTrace();
            signalUpload.setSignalPacket(new String(packet.getSignalPacket(),"UTF-8"));
        }
        signalUpload.setSignalPacket(signalPacketResult);*/
//        signalUpload.setSignalPacket(new String(packet.getSignalPacket(),"UTF-8"));

        return signalUpload;
    }

    public Boolean isInt(String str){
        if ( str.trim().matches("\\d+")){
            return true;
        }
        return false;
    }


    public byte getVer() {
        return ver;
    }

    public void setVer(byte ver) {
        this.ver = ver;
    }

    public byte[] getFacId() {
        return facId;
    }

    public void setFacId(byte[] facId) {
        this.facId = facId;
    }

    public EUpLoadType getUpLoadType() {
        return upLoadType;
    }

    public void setUpLoadType(EUpLoadType upLoadType) {
        this.upLoadType = upLoadType;
    }

    public short getUpLoadLength() {
        return upLoadLength;
    }

    public void setUpLoadLength(short upLoadLength) {
        this.upLoadLength = upLoadLength;
    }

    public byte[] getRelationId() {
        return relationId;
    }

    public void setRelationId(byte[] relationId) {
        this.relationId = relationId;
    }

    public ESignalMsg getSignalType() {
        return signalType;
    }

    public void setSignalType(ESignalMsg signalType) {
        this.signalType = signalType;
    }

    public byte[] getMsgName() { return msgName; }

    public void setMsgName(byte[] msgName) { this.msgName = msgName; }

    public short getSignalPacketLen() {
        return signalPacketLen;
    }

    public void setSignalPacketLen(short signalPacketLen) {
        this.signalPacketLen = signalPacketLen;
    }

    public byte[] getSignalPacket() {
        return signalPacket;
    }

    public void setSignalPacket(byte[] signalPacket) {
        this.signalPacket = signalPacket;
    }

    public static int bytes2IntBig(byte[] bytes )
    {
        int int1=bytes[3]&0xff;
        int int2=(bytes[2]&0xff)<<8;
        int int3=(bytes[1]&0xff)<<16;
        int int4=(bytes[0]&0xff)<<24;

        return int1|int2|int3|int4;
    }
}
