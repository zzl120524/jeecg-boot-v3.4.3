package com.sdt.omap.server.report.handler;

import com.sdt.omap.protocol.comm.*;
import com.sdt.omap.protocol.other.EUpLoadType;
import com.sdt.omap.utils.ByteUtils;
import com.sdt.omap.utils.IpUtil;
import com.sdt.util.standard.ByteUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.demo.alarmList.entity.AlarmList;
import org.jeecg.modules.demo.alarmList.mapper.AlarmListMapper;
import org.jeecg.modules.demo.authAck.service.IAuthAckService;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;
import org.jeecg.modules.demo.signalUpload.service.ISignalUploadService;

import java.nio.charset.StandardCharsets;

public class ReportHandler extends IoHandlerAdapter {
    ISignalUploadService signalUploadService = SpringContextUtils.getBean(ISignalUploadService.class);
    AlarmListMapper alarmListMapper = SpringContextUtils.getBean(AlarmListMapper.class);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("messageReceived");
        byte[] pkt = (byte[])message;

        System.out.println(ByteUtil.byte2HexString0x(pkt, true));

        byte type = pkt[3];

        System.out.println("报文类型"+ByteUtil.byte2tinyint(type));
        SignalUpload signalUpload = new SignalUpload();
        if (EUpLoadType.SIGNALTRACE.toByte() == type){
            UserSignalPacket packet = new UserSignalPacket();
            packet = packet.parse(pkt);
            byte[] signal = packet.getSignal();
            System.out.println("signal:"+ByteUtils.byteToBinStr(signal));
            signalUpload = packet.parseSignalUpload(packet);
            System.out.println("跟踪信令报文"+ByteUtil.byte2tinyint(type)+"内容"+signalUpload.getSigal());
        }else if (EUpLoadType.SIGNALRTP.toByte() == type){
            UserRTPPacket packet = new UserRTPPacket();
            packet = packet.parse(pkt);
            byte[] packetStr = packet.getRtpPacket();
            System.out.println("RtpPacket:"+ByteUtils.byteToBinStr(packetStr));
            signalUpload = packet.parseSignalUpload(packet);
            System.out.println("跟踪媒体报文"+ByteUtil.byte2tinyint(type)+"内容"+signalUpload.getRtpPacket());
        }else if (EUpLoadType.SIGNALCOPY.toByte() == type){
            SignalCopyPacket packet = new SignalCopyPacket();
            packet = packet.parse(pkt);
            byte[] signalPacket = packet.getSignalPacket();
            System.out.println("signalPacket:"+ByteUtils.byteToBinStr(signalPacket));

            signalUpload = packet.parseSignalUpload(packet);
            System.out.println("复制报文"+ByteUtil.byte2tinyint(type)+"内容"+signalUpload.getSignalPacket());
        }else if (EUpLoadType.EXCEPTION.toByte() == type){
            MalformedPacket packet = new MalformedPacket();
            packet = packet.parse(pkt);
            byte[] exceptionPacket = packet.getPacket();
            System.out.println("Packet"+ByteUtil.byte2tinyint(type)+"内容"+new String(exceptionPacket, "UTF-8"));
            signalUpload = packet.parseSignalUpload(packet);
            System.out.println("异常报文"+ByteUtil.byte2tinyint(type)+"内容"+signalUpload.getPacket());
            AlarmList alarmList = new AlarmList();
            alarmList.setFacId(signalUpload.getFacId());
            alarmList.setUploadType(signalUpload.getUploadType());
            alarmList.setUploadLength(signalUpload.getUploadLength());
            alarmList.setMalformedType(signalUpload.getMalformedType());
            alarmList.setPacketlen(signalUpload.getPacketlen());
            alarmList.setPacket(signalUpload.getPacket());
            alarmListMapper.insert(alarmList);
        }
        signalUploadService.save(signalUpload);
        System.out.println("报文类型"+Byte.toString(type)+"报文上传成功");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }
}
