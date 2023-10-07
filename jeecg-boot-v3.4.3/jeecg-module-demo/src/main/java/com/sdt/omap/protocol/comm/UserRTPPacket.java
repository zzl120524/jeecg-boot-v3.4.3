package com.sdt.omap.protocol.comm;

import com.sdt.omap.protocol.enumUtil.RtpCodecType;
import com.sdt.omap.protocol.enumUtil.TrafficDirectionType;
import com.sdt.omap.protocol.enumUtil.UploadType;
import com.sdt.omap.protocol.other.ECodec;
import com.sdt.omap.protocol.other.ESignalMsg;
import com.sdt.omap.protocol.other.ETrafficDirection;
import com.sdt.omap.protocol.other.EUpLoadType;
import com.sdt.omap.utils.BCDUtils;
import com.sdt.omap.utils.ByteUtils;
import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UserRTPPacket {

    private byte ver; // 1B
    private byte[] facId; // 2B
    private EUpLoadType upLoadType; // 1B
    private short upLoadLength; // 2B
    private byte[] traceUser; // 25B
    private ETrafficDirection trafficDirection; // 1B
    private ECodec codec; // 1B
    private byte rtpPacketLen; // 1B
    private byte[] rtpPacket; // NB

    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(this.upLoadLength);
        bf.order(ByteOrder.BIG_ENDIAN);
        bf.put(this.ver);
        bf.put(this.facId);
        bf.put(this.upLoadType.toByte());
        bf.putShort(this.upLoadLength);
        bf.put(this.traceUser);
        bf.put(this.trafficDirection.toByte());
        bf.put(this.codec.toByte());
        bf.put(this.rtpPacketLen);
        bf.put(this.rtpPacket);
        return bf.array();
    }

    public UserRTPPacket parse(byte[] data) {

        byte[] bDest = null;
        UserRTPPacket userRTPPacket = new UserRTPPacket();

        userRTPPacket.setVer(data[0]);

        bDest = new byte[2];
        System.arraycopy(data, 1, bDest, 0, 2);
        userRTPPacket.setFacId(bDest);

        userRTPPacket.setUpLoadType(EUpLoadType.valueOf(data[3]));

        bDest = new byte[2];
        System.arraycopy(data, 4, bDest, 0, 2);
        int len = ByteUtil.bytes2smallint(bDest, 0);
        userRTPPacket.setUpLoadLength((short) len);

        bDest = new byte[25];
        System.arraycopy(data, 6, bDest, 0, 25);
        userRTPPacket.setTraceUser(bDest);

        userRTPPacket.setTrafficDirection(ETrafficDirection.valueOf(data[31]));
        userRTPPacket.setCodec(ECodec.valueOf(data[32]));
        userRTPPacket.setRtpPacketLen(data[33]);


        bDest = new byte[data.length - 34];
        System.arraycopy(data, 34, bDest, 0, data.length - 34);
        userRTPPacket.setRtpPacket(bDest);

        return userRTPPacket;
    }

    public SignalUpload parseSignalUpload(UserRTPPacket packet) throws UnsupportedEncodingException {
        SignalUpload signalUpload = new SignalUpload();
        signalUpload.setVer(String.valueOf(packet.getVer()));
        signalUpload.setFacId(ByteUtil.bytes2smallint(packet.getFacId(),0));
        int type = UploadType.unknown(ByteUtil.byte2tinyint(packet.getUpLoadType().toByte()));
        signalUpload.setUploadType(type);
        signalUpload.setUploadLength(Integer.valueOf(packet.getUpLoadLength()));
        byte[] traceUser = packet.getTraceUser();
        byte[] traceUserNum = new byte[24];
        System.arraycopy(traceUser,1,traceUserNum,0,24);
        signalUpload.setTraceUser(BCDUtils.bcd2str(traceUserNum));
        TrafficDirectionType.unknown(ByteUtil.byte2tinyint(packet.getTrafficDirection().toByte()));
        signalUpload.setTrafficeDirection(Integer.valueOf(packet.getTrafficDirection().toByte()));
        int codec = RtpCodecType.unknown(Integer.valueOf(packet.getCodec().toByte()));
        signalUpload.setCodec(codec);
        signalUpload.setRtpPacketLen(Integer.valueOf(packet.getRtpPacketLen()));

        byte[] rtpPacket = packet.getRtpPacket();
        byte[] rtpPacketByte = new byte[rtpPacket.length-28];
        System.arraycopy(rtpPacket,28,rtpPacketByte,0,rtpPacket.length-28);
        signalUpload.setRtpPacket(new String(rtpPacketByte,"UTF-8"));

 /*       String rtpPacketResult = "";
        try {
            byte[] rtpPacket = packet.getRtpPacket();
            System.out.println("rtpPacket:"+ ByteUtils.byteToBinStr(rtpPacket));
            byte[] ipHeaderByte = new byte[28];
            byte[] rtpPacketByte = new byte[rtpPacket.length-28];
            System.arraycopy(rtpPacket,0,ipHeaderByte,0,28);
            System.arraycopy(rtpPacket,28,rtpPacketByte,0,rtpPacket.length-28);
            String bits = ByteUtils.byteToBinStr(ipHeaderByte);
            System.out.println("bits:"+bits);
            IpHeader ipHeader = new IpHeader();
            IpHeader parse = ipHeader.parse(bits);
            String ipHeaderStr = parse.toString();
            String rtpPacketStr = new String(rtpPacketByte, "UTF-8");
            rtpPacketResult = ipHeaderStr +'\n'+ rtpPacketStr;
        }catch (Exception e) {
            e.printStackTrace();
            signalUpload.setRtpPacket(new String(packet.getRtpPacket(),"UTF-8"));
        }
        signalUpload.setRtpPacket(rtpPacketResult);*/

        return signalUpload;
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

    public byte[] getTraceUser() {
        return traceUser;
    }

    public void setTraceUser(byte[] traceUser) {
        this.traceUser = traceUser;
    }

    public ETrafficDirection getTrafficDirection() {
        return trafficDirection;
    }

    public void setTrafficDirection(ETrafficDirection trafficDirection) {
        this.trafficDirection = trafficDirection;
    }

    public ECodec getCodec() {
        return codec;
    }

    public void setCodec(ECodec codec) {
        this.codec = codec;
    }

    public byte getRtpPacketLen() {
        return rtpPacketLen;
    }

    public void setRtpPacketLen(byte rtpPacketLen) {
        this.rtpPacketLen = rtpPacketLen;
    }

    public byte[] getRtpPacket() {
        return rtpPacket;
    }

    public void setRtpPacket(byte[] rtpPacket) {
        this.rtpPacket = rtpPacket;
    }
}
