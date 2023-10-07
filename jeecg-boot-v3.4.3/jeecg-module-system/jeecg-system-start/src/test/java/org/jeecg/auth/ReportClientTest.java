package org.jeecg.auth;

import com.sdt.omap.client.CommClient;
import com.sdt.omap.protocol.comm.MalformedPacket;
import com.sdt.omap.protocol.comm.SignalCopyPacket;
import com.sdt.omap.protocol.comm.UserRTPPacket;
import com.sdt.omap.protocol.comm.UserSignalPacket;
import com.sdt.omap.protocol.other.*;
import com.sdt.omap.session.TransSession;
import com.sdt.omap.utils.Constants;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.common.util.PasswordUtil;
import org.junit.Test;

import java.security.SecureRandom;

public class ReportClientTest {

    public static void main(String[] args) throws Exception {
        CommClient client = new CommClient();
        TransSession session = client.udpReportConnect(Constants.REPORT_IP, Constants.REPORT_PORT);

   /*     UserSignalPacket userSignalPacket = new UserSignalPacket();
        userSignalPacket.setVer((byte) 0x01);
        userSignalPacket.setFacId(new byte[]{0x00, 0x01});
        userSignalPacket.setUpLoadType(EUpLoadType.SIGNALTRACE);
        userSignalPacket.setUpLoadLength((short) 35);
        userSignalPacket.setTraceUser(new byte[]{0x01, 0x03, 0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01});
        userSignalPacket.setSignalMsgType(ESignalMsg.SIP);
        userSignalPacket.setSignalLength((short) 16);
        userSignalPacket.setSignal(Constants.AUTH_KEY);

        System.out.println("发送前的数据");
        System.out.println(ByteUtil.byte2HexString0x(userSignalPacket.getByteArray(), true));
        session.write(userSignalPacket.getByteArray());*/
        session.write(Constants.AUTH_KEY4);
    }

    @Test
    public void testRTP() throws Exception{
//   public static void main(String[] args) throws Exception {
        CommClient client = new CommClient();
        TransSession session = client.udpReportConnect(Constants.REPORT_IP, Constants.REPORT_PORT);

        UserRTPPacket packet = new UserRTPPacket();
        packet.setVer((byte) 0x01);
        packet.setFacId(new byte[]{0x00, 0x01});
        packet.setUpLoadType(EUpLoadType.SIGNALRTP);
        packet.setUpLoadLength((short) 35);
        packet.setTraceUser(new byte[]{0x01, 0x03, 0x00, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01});
        packet.setTrafficDirection(ETrafficDirection.FORWARD);
        packet.setCodec(ECodec.AMR);
        packet.setRtpPacketLen((byte) 0x10);
        packet.setRtpPacket(Constants.AUTH_KEY);
        System.out.println("发送前的数据");
        System.out.println(ByteUtil.byte2HexString0x(packet.getByteArray(), true));
//        session.write(packet.getByteArray());

        for(int i =0; i < 10; i++){
            session.write(packet.getByteArray());
            try {
                // 休眠一段时间，单位为毫秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopy() throws Exception{
        CommClient client = new CommClient();
        TransSession session = client.udpReportConnect(Constants.REPORT_IP, Constants.REPORT_PORT);

        SignalCopyPacket packet = new SignalCopyPacket();
        packet.setVer((byte) 0x01);
        packet.setFacId(new byte[]{0x00, 0x01});
        packet.setUpLoadType(EUpLoadType.SIGNALCOPY);
        packet.setUpLoadLength((short) 33);
        packet.setRelationId(new byte[]{0x00, 0x01, 0x03, 0x00, 0x01, 0x01, 0x01, 0x01});
        packet.setSignalType(ESignalMsg.SIP);
        packet.setSignalPacketLen((short) 16);
        packet.setSignalPacket(Constants.AUTH_KEY);
        System.out.println("发送前的数据");
        System.out.println(ByteUtil.byte2HexString0x(packet.getByteArray(), true));
        session.write(packet.getByteArray());
    }

    @Test
    public void testMalformed() throws Exception{
        CommClient client = new CommClient();
        TransSession session = client.udpReportConnect(Constants.REPORT_IP, Constants.REPORT_PORT);

        MalformedPacket packet = new MalformedPacket();
        packet.setVer((byte) 0x01);
        packet.setFacId(new byte[]{0x00, 0x01});
        packet.setUpLoadType(EUpLoadType.EXCEPTION);
        packet.setUpLoadLength((short) 35);

        packet.setMalformedType(EMalformedType.SIP);
        packet.setPacketLen((short) 16);
        packet.setPacket(Constants.AUTH_KEY);
        System.out.println("发送前的数据");
        System.out.println(ByteUtil.byte2HexString0x(packet.getByteArray(), true));
        session.write(packet.getByteArray());
    }

    @Test
    public void seqNum() throws Exception{
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[4];
        secureRandom.nextBytes(randomBytes);
        int i = bytes2IntBig(randomBytes);
        System.out.println("bytes2int=====   " + ByteUtil.bytes2int(randomBytes,0));
        System.out.println("bytes2smallint=====   " + ByteUtil.bytes2smallint(randomBytes,0));
        System.out.println("bytes2intInSmallEnd=====   " + ByteUtil.bytes2intInSmallEnd(randomBytes,0));
        System.out.println("bytes2intInBigEnd=====   " + ByteUtil.bytes2intInBigEnd(randomBytes,0));
        System.out.println("bytes2IntBig=====   " + bytes2IntBig(randomBytes));
        System.out.println("bytes2intInBigEnd=====   " + ByteUtil.bytes2intInBigEnd(randomBytes,0));


        byte[] bytes = {(byte) 0xff, (byte)0xff, (byte)0xff, (byte)0xff};
        int j = bytes2IntBig(bytes);
        System.out.println(j);


    }

    public static int bytes2IntBig(byte[] bytes )
    {
        int int1=bytes[3]&0xff;
        int int2=(bytes[2]&0xff)<<8;
        int int3=(bytes[1]&0xff)<<16;
        int int4=(bytes[0]&0xff)<<24;

        return int1|int2|int3|int4;
    }

    public static short byteToShortBig(byte[] b) {
        return (short) (((b[0] << 8) | b[1] & 0xff));
    }

    @Test
    public void passwordEncode() throws Exception{
        String passwordEncode = PasswordUtil.encrypt("jeecg", "jeecg", "mIgiYJow");
        System.out.println(passwordEncode);


    }
}
