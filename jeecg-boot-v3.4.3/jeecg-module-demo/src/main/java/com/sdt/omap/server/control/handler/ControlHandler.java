package com.sdt.omap.server.control.handler;

import com.sdt.omap.protocol.comm.CommonMsg;
import com.sdt.omap.protocol.comm.FACNotify;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.session.TransSession;
import com.sdt.omap.utils.Constants;
import com.sdt.util.standard.ByteUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;

public class ControlHandler extends IoHandlerAdapter {
    WorkLogMapper workLogMapper = SpringContextUtils.getBean(WorkLogMapper.class);

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

        CommonMsg commonMsg = new CommonMsg();
        commonMsg = commonMsg.parse(pkt);

        FACNotify facNotify = new FACNotify();
        facNotify = facNotify.parse(commonMsg.getContent());

        System.out.println("接收消息类型 EMsgType:" + commonMsg.getMsgType());

        if (commonMsg.getMsgType().equals(EMsgType.FACNOTIFY)) {
            WorkLog workLog = new WorkLog();
            workLog.setOperating("FACB"+ByteUtil.bytes2smallint(facNotify.getFacId(), 0)+"TCP连接");
            try {
                TransSession transSession = new TransSession(session);
                int i = ByteUtil.bytes2smallint(facNotify.getFacId(), 0);
                System.out.println("session i:" + i);
                Constants.FACSEESION.put(i, transSession);
                workLog.setOperatingResult("1");
            } catch (Exception e) {
                e.printStackTrace();
                workLog.setOperatingResult("0");
            }
            workLogMapper.insert(workLog);
        }

        System.out.println("session size:" + Constants.FACSEESION.size());

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }
}
