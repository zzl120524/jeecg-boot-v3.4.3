package com.sdt.omap.server.auth.handler;

import com.sdt.omap.protocol.auth.AuthAck;
import com.sdt.omap.protocol.auth.AuthMsg;
import com.sdt.omap.protocol.auth.AuthReq;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.utils.Aes128Cmac;
import com.sdt.omap.utils.AesUtil;
import com.sdt.omap.utils.DataTransfer;
import com.sdt.omap.utils.IpUtil;
import com.sdt.util.standard.ByteUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.demo.authAck.entity.AuthAckVo;
import org.jeecg.modules.demo.authAck.service.IAuthAckService;
import org.jeecg.modules.demo.facAuthKey.service.IFacAuthKeyService;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;

public class AuthHandler extends IoHandlerAdapter {

    WorkLogMapper workLogMapper = SpringContextUtils.getBean(WorkLogMapper.class);

    IAuthAckService authAckService = SpringContextUtils.getBean(IAuthAckService.class);

    IFacAuthKeyService facAuthKeyService = SpringContextUtils.getBean(IFacAuthKeyService.class);

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
        byte[] pkt = (byte[]) message;
        System.out.println(ByteUtil.byte2HexString0x(pkt, true));

        AuthMsg authMsg = new AuthMsg();
        authMsg = authMsg.parse(pkt);

        if (authMsg.getMsgType().equals(EMsgType.AUTHREQ)) {
            WorkLog workLog = new WorkLog();
            AuthReq authReq = new AuthReq();
            authReq = authReq.parse(authMsg.getContent());
  /*          Boolean checking = facAuthKeyService.checking(authReq);
            if (checking){
                System.out.println("FACID:"+ByteUtil.byte2HexString0x(authReq.getFacId(), true) +"验证通过");
            }else {
                System.out.println("FACID:"+ByteUtil.byte2HexString0x(authReq.getFacId(), true) +"验证失败");
            }*/
           /* byte[] authKey = facAuthKeyService.authKey(authReq);
            System.out.println("验证秘钥:"+ByteUtil.byte2HexString0x(authKey, true));*/
            workLog.setOperating("FACB"+ByteUtil.bytes2smallint(authReq.getFacId(), 0)+"认证");
            try {
                byte[] authKey = facAuthKeyService.authKey(authReq);
                System.out.println("验证秘钥:"+ByteUtil.byte2HexString0x(authKey, true));
                AuthMsg authAckMsg = new AuthMsg();
                AuthAckVo authAckByFacId = authAckService.getAuthAckByFacId();
                AuthAck authAck = new AuthAck();
                authAck.setFacId(authReq.getFacId());
                authAck.setControlIfIp(IpUtil.ipv4Address2BinaryArray(authAckByFacId.getControlifIp()));
                authAck.setControlIfPort(ByteUtil.smallint2bytes(authAckByFacId.getControlifPort()));
                authAck.setSignalPacketReportIp(IpUtil.ipv4Address2BinaryArray(authAckByFacId.getSignalPacketReportIp()));
                authAck.setSignalPacketReportPort(ByteUtil.smallint2bytes(authAckByFacId.getSignalPacketReportPort()));
                authAck.setTracePacketReportIp(IpUtil.ipv4Address2BinaryArray(authAckByFacId.getTracePacketReportIp()));
                authAck.setTracePacketReportPort(ByteUtil.smallint2bytes(authAckByFacId.getTracePacketReportUdp()));
                authAck.setEncryptPacketReportIp(IpUtil.ipv4Address2BinaryArray(authAckByFacId.getEncryptPacketReportIp()));
                authAck.setEncryptPacketReportPort(ByteUtil.smallint2bytes(authAckByFacId.getEncryptPacketReportUdp()));
                authAck.setMalformedPacketReportIp(IpUtil.ipv4Address2BinaryArray(authAckByFacId.getMalformedPacketReportIp()));
                authAck.setMalformedPacketReportPort(ByteUtil.smallint2bytes(authAckByFacId.getMalformedPacketReportUdp()));

//            byte[] num = ByteUtil.smallint2bytes(ftpLen);

                byte[] ftpUserBytes = authAckByFacId.getCdrFtpUser().getBytes();
                byte ftpUserLen = ByteUtil.tinyint2bytes(ftpUserBytes.length);
                System.out.println("ftpUser====>" + ByteUtil.byte2HexString0x(ftpUserBytes, true));
                byte[] cdrFtpUser = new byte[1 + ftpUserBytes.length];

                cdrFtpUser[0] = ftpUserLen;
                System.arraycopy(ftpUserBytes, 0, cdrFtpUser, 1, ftpUserBytes.length);
                authAck.setCdrFTPUser(cdrFtpUser);
                System.out.println("cdrFtpUser====>" + ByteUtil.byte2HexString0x(cdrFtpUser, true));

                byte[] ftpKeyBytes = authAckByFacId.getCdrFtpKey().getBytes();
                byte ftpKeyLen = ByteUtil.tinyint2bytes(ftpKeyBytes.length);
                System.out.println("ftpKey====>" + ByteUtil.byte2HexString0x(ftpKeyBytes, true));
                byte[] cdrFtpkey = new byte[1 + ftpKeyBytes.length];
                cdrFtpkey[0] = ftpKeyLen;
                System.arraycopy(ftpKeyBytes, 0, cdrFtpkey, 1, ftpKeyBytes.length);
                authAck.setCdrFTPKey(cdrFtpkey);
                System.out.println("cdrFtpkey====>" + ByteUtil.byte2HexString0x(cdrFtpkey, true));

                byte[] ftpDirectoryBytes = authAckByFacId.getFtpDirectory().getBytes();
                byte directoryLen = ByteUtil.tinyint2bytes(ftpDirectoryBytes.length);
                System.out.println("Directory====>" + ByteUtil.byte2HexString0x(ftpDirectoryBytes, true));
                byte[] ftpDirectory = new byte[1 + ftpDirectoryBytes.length];
                ftpDirectory[0] = directoryLen;
                System.arraycopy(ftpDirectoryBytes, 0, ftpDirectory, 1, ftpDirectoryBytes.length);
                System.out.println("ftpDirectory====>" + ByteUtil.byte2HexString0x(ftpDirectory, true));

                authAck.setFtpDirectory(ftpDirectory);
                authAck.setFtpServerIp(IpUtil.ipv4Address2BinaryArray(authAckByFacId.getFtpServerIp()));
                authAck.setMic(null);

                authAckMsg.setVersion(authMsg.getVersion());
                authAckMsg.setMsgType(EMsgType.AUTHACK);
                authAckMsg.setContent(authAck.getByteArray());

                // calc mic
                byte[] mic = null;
                if (authAck.getMic() == null) {
                    byte[] paddingData = DataTransfer.padding(authAckMsg.getByteArray());
//                    mic = Aes128Cmac.generateMac(Constants.AUTH_KEY, paddingData);
                    mic = Aes128Cmac.generateMac(authKey, paddingData);
                }
                authAck.setMic(mic);
                System.out.println("加密前authAck数据==》" + ByteUtil.byte2HexString0x(authAck.getByteArray(), true));
                System.out.println("加密前authAckMsg数据==》" + ByteUtil.byte2HexString0x(authAckMsg.getByteArray(), true));
                byte[] padEncData = DataTransfer.padding(authAck.getByteArray());
//                byte[] ackEnc = AesUtil.aesEncry(padEncData, Constants.AUTH_KEY);
                byte[] ackEnc = AesUtil.aesEncry(padEncData, authKey);
                authAckMsg.setContent(ackEnc);
                authAckMsg.setLength((short) (ackEnc.length + 1));

                System.out.println("server send to client");
                System.out.println(ByteUtil.byte2HexString0x(authAckMsg.getByteArray(), true));
                byte[] sendData = authAckMsg.getByteArray();
                session.write(sendData);
                workLog.setOperatingResult("1");
            } catch (Exception e) {
                e.printStackTrace();
                workLog.setOperatingResult("0");
            }
            workLogMapper.insert(workLog);
        }


    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    public void addAppMessage(AuthMsg authAckMsg) {
 /*       AppMessage appMessage = new AppMessage();
        appMessage.setVer();
        appMessage.setLength();
        appMessage
        appMessageService.save();*/
    }
}
