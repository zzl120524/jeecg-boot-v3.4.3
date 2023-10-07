package com.sdt.omap.protocol.comm;

import com.sdt.omap.protocol.auth.AuthMsg;
import com.sdt.omap.protocol.enumUtil.TraceMsgType;
import com.sdt.omap.protocol.enumUtil.UploadType;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.protocol.other.ESignalMsg;
import com.sdt.omap.protocol.other.EUpLoadType;
import com.sdt.omap.utils.BCDUtils;
import com.sdt.omap.utils.ByteUtils;
import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UserSignalPacket {

    private byte ver; // 1B
    private byte[] facId; // 2B
    private EUpLoadType upLoadType; // 1B
    private short upLoadLength; // 2B
    private byte[] traceUser; // 25B
    private ESignalMsg signalMsgType; // 1B
    private byte[] msgName; // 16B
    private short signalLength; // 2B
    private byte[] signal; // NB

    public byte[] getByteArray() {
        ByteBuffer bf = ByteBuffer.allocate(this.upLoadLength);
        bf.order(ByteOrder.BIG_ENDIAN);
        bf.put(this.ver);
        bf.put(this.facId);
        bf.put(this.upLoadType.toByte());
        bf.putShort(this.upLoadLength);
        bf.put(this.traceUser);
        bf.put(this.signalMsgType.toByte());
        bf.put(this.msgName);
        bf.putShort(this.signalLength);
        bf.put(this.signal);
        return bf.array();
    }

    public UserSignalPacket parse(byte[] data) {

        byte[] bDest = null;
        UserSignalPacket userSignalPacket = new UserSignalPacket();

        userSignalPacket.setVer(data[0]);

        bDest = new byte[2];
        System.arraycopy(data, 1, bDest, 0, 2);
        userSignalPacket.setFacId(bDest);

        userSignalPacket.setUpLoadType(EUpLoadType.valueOf(data[3]));

        bDest = new byte[2];
        System.arraycopy(data, 4, bDest, 0, 2);
        int len = ByteUtil.bytes2smallint(bDest, 0);
        userSignalPacket.setUpLoadLength((short) len);

        bDest = new byte[25];
        System.arraycopy(data, 6, bDest, 0, 25);
        userSignalPacket.setTraceUser(bDest);

        userSignalPacket.setSignalMsgType(ESignalMsg.valueOf(data[31]));//

        bDest = new byte[16];
        System.arraycopy(data, 32, bDest, 0, 16);
        userSignalPacket.setMsgName(bDest);

        bDest = new byte[2];
        System.arraycopy(data, 48, bDest, 0, 2);
        int length = ByteUtil.bytes2smallint(bDest, 0);
        userSignalPacket.setSignalLength((short) length);

        bDest = new byte[data.length - 50];
        System.arraycopy(data, 50, bDest, 0, data.length - 50);
        userSignalPacket.setSignal(bDest);

        return userSignalPacket;
    }

    public SignalUpload parseSignalUpload(UserSignalPacket packet) throws UnsupportedEncodingException {
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
        int msgType = TraceMsgType.unknown(ByteUtil.byte2tinyint(packet.getSignalMsgType().toByte()));
        signalUpload.setMsgType(msgType);
        System.out.println("信令追踪MsgName" + ByteUtil.byte2HexString0x(packet.getMsgName(),true));
        String msgName = new String(packet.getMsgName(), "UTF-8");
        signalUpload.setMsgName(msgName.trim());
        if (isInt(msgName)){
            signalUpload.setDirection("回应");
        }else {
            signalUpload.setDirection("发送");
        }
        signalUpload.setSignalLength(Integer.valueOf(packet.getSignalLength()));

        byte[] signal = packet.getSignal();
        byte[] signalByte = new byte[signal.length-28];
        System.arraycopy(signal,28,signalByte,0,signal.length-28);
        signalUpload.setSigal(new String(signalByte,"UTF-8"));

    /*    String signalResult = "";
        try {
            byte[] signal = packet.getSignal();
            System.out.println("signal:"+ ByteUtils.byteToBinStr(signal));
            byte[] ipHeaderByte = new byte[28];
            byte[] signalByte = new byte[signal.length-28];
            System.arraycopy(signal,0,ipHeaderByte,0,28);
            System.arraycopy(signal,28,signalByte,0,signal.length-28);
            String bits = ByteUtils.byteToBinStr(ipHeaderByte);
            System.out.println("bits:"+bits);
            IpHeader ipHeader = new IpHeader();
            IpHeader parse = ipHeader.parse(bits);
            String ipHeaderStr = parse.toString();
            String signalStr = new String(signalByte, "UTF-8");
            signalResult = ipHeaderStr +'\n'+ signalStr;
        }catch (Exception e) {
            e.printStackTrace();
            signalUpload.setSigal(new String(packet.getSignal(),"UTF-8"));
        }
        signalUpload.setSigal(signalResult);*/
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

    public byte[] getTraceUser() {
        return traceUser;
    }

    public void setTraceUser(byte[] traceUser) {
        this.traceUser = traceUser;
    }

    public ESignalMsg getSignalMsgType() {
        return signalMsgType;
    }

    public void setSignalMsgType(ESignalMsg signalMsgType) {
        this.signalMsgType = signalMsgType;
    }

    public byte[] getMsgName() { return msgName; }

    public void setMsgName(byte[] msgName) { this.msgName = msgName; }

    public short getSignalLength() {
        return signalLength;
    }

    public void setSignalLength(short signalLength) {
        this.signalLength = signalLength;
    }

    public byte[] getSignal() {
        return signal;
    }

    public void setSignal(byte[] signal) {
        this.signal = signal;
    }

}
