package com.sdt.omap.protocol.comm;

import com.sdt.omap.protocol.enumUtil.MalformedType;
import com.sdt.omap.protocol.enumUtil.UploadType;
import com.sdt.omap.protocol.other.EMalformedType;
import com.sdt.omap.protocol.other.ESignalMsg;
import com.sdt.omap.protocol.other.EUpLoadType;
import com.sdt.omap.utils.ByteUtils;
import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MalformedPacket {

    private byte ver; // 1B
    private byte[] facId; // 2B
    private EUpLoadType upLoadType; // 1B
    private short upLoadLength; // 2B
    private EMalformedType malformedType; // 1B
    private short packetLen; // 2B
    private byte[] packet; // NB

    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(this.upLoadLength);
        bf.order(ByteOrder.BIG_ENDIAN);
        bf.put(this.ver);
        bf.put(this.facId);
        bf.put(this.upLoadType.toByte());
        bf.putShort(this.upLoadLength);
        bf.put(this.malformedType.toByte());
        bf.putShort(this.packetLen);
        bf.put(this.packet);
        return bf.array();
    }

    public MalformedPacket parse(byte[] data) {

        byte[] bDest = null;
        MalformedPacket malformedPacket = new MalformedPacket();

        malformedPacket.setVer(data[0]);

        bDest = new byte[2];
        System.arraycopy(data, 1, bDest, 0, 2);
        malformedPacket.setFacId(bDest);

        malformedPacket.setUpLoadType(EUpLoadType.valueOf(data[3]));

        bDest = new byte[2];
        System.arraycopy(data, 4, bDest, 0, 2);
        int len = ByteUtil.bytes2smallint(bDest, 0);
        malformedPacket.setUpLoadLength((short) len);


        malformedPacket.setMalformedType(EMalformedType.valueOf(data[6]));


        bDest = new byte[2];
        System.arraycopy(data, 7, bDest, 0, 2);
        int length = ByteUtil.bytes2smallint(bDest, 0);
        malformedPacket.setPacketLen((short) length);


        bDest = new byte[data.length - 9];
        System.arraycopy(data, 9, bDest, 0, data.length - 9);
        malformedPacket.setPacket(bDest);

        return malformedPacket;
    }

    public SignalUpload parseSignalUpload(MalformedPacket packet) throws UnsupportedEncodingException {
        SignalUpload signalUpload = new SignalUpload();
        signalUpload.setVer(String.valueOf(packet.getVer()));
        signalUpload.setFacId(ByteUtil.bytes2smallint(packet.getFacId(),0));
        int type = UploadType.unknown(ByteUtil.byte2tinyint(packet.getUpLoadType().toByte()));
        signalUpload.setUploadType(type);
        signalUpload.setUploadLength(Integer.valueOf(packet.getUpLoadLength()));
        int malformedType = MalformedType.unknown(Integer.valueOf(packet.getMalformedType().toByte()));
        signalUpload.setMalformedType(malformedType);
        signalUpload.setPacketlen(Integer.valueOf(packet.getPacketLen()));

        byte[] packetBytes = packet.getPacket();
        byte[] packetByte = new byte[packetBytes.length-28];
        System.arraycopy(packetBytes,28,packetByte,0,packetBytes.length-28);
        signalUpload.setPacket(new String(packetByte,"UTF-8"));

/*        String packetResult = "";
        try {
            byte[] packetBytes = packet.getPacket();
            System.out.println("packet:"+ ByteUtils.byteToBinStr(packetBytes));
            byte[] ipHeaderByte = new byte[28];
            byte[] packetByte = new byte[packetBytes.length-28];
            System.arraycopy(packetBytes,0,ipHeaderByte,0,28);
            System.arraycopy(packetBytes,28,packetByte,0,packetBytes.length-28);
            String bits = ByteUtils.byteToBinStr(ipHeaderByte);
            System.out.println("bits:"+bits);
            IpHeader ipHeader = new IpHeader();
            IpHeader parse = ipHeader.parse(bits);
            String ipHeaderStr = parse.toString();
            String packetStr = new String(packetByte, "UTF-8");
            packetResult = ipHeaderStr +'\n'+ packetStr;
            signalUpload.setPacket(packetResult);
        }catch (Exception e) {
            e.printStackTrace();
            signalUpload.setPacket(new String(packet.getPacket(),"UTF-8"));
        }*/


        return signalUpload;
    }

    public byte getVer() { return ver; }

    public void setVer(byte ver) { this.ver = ver; }

    public byte[] getFacId() { return facId; }

    public void setFacId(byte[] facId) { this.facId = facId; }

    public EUpLoadType getUpLoadType() { return upLoadType; }

    public void setUpLoadType(EUpLoadType upLoadType) { this.upLoadType = upLoadType; }

    public short getUpLoadLength() { return upLoadLength; }

    public void setUpLoadLength(short upLoadLength) { this.upLoadLength = upLoadLength; }

    public EMalformedType getMalformedType() { return malformedType; }

    public void setMalformedType(EMalformedType malformedType) { this.malformedType = malformedType; }

    public short getPacketLen() { return packetLen; }

    public void setPacketLen(short packetLen) { this.packetLen = packetLen; }

    public byte[] getPacket() { return packet; }

    public void setPacket(byte[] packet) { this.packet = packet; }
}
