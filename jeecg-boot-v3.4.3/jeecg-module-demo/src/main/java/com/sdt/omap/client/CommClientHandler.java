package com.sdt.omap.client;

import com.sdt.util.standard.ByteUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class CommClientHandler extends IoHandlerAdapter {

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("会话已建立[" + session + "]");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("会话已打开[" + session + "]");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("会话已关闭[" + session + "]");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("收到了消息");
        byte[] pkt = (byte[]) message;
        System.out.println(ByteUtil.byte2HexString0x(pkt, true));
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("处理异常[" + session + "]");
        System.out.println(cause);
    }



    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }
}
