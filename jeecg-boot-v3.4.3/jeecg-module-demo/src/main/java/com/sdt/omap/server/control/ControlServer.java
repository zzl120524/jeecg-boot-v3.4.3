package com.sdt.omap.server.control;

import com.sdt.omap.protocol.comm.CommonMsg;
import com.sdt.omap.protocol.comm.SetTraceUserReq;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.server.control.codec.ControlProtocolFactory;
import com.sdt.omap.server.control.handler.ControlHandler;
import com.sdt.omap.session.TransSession;
import com.sdt.omap.utils.Constants;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ControlServer {

    private IoAcceptor acceptor;
    private InetSocketAddress listenAddr;
    private IoHandler handler = null;
    private Integer readTimeout;
    private Integer idleTimeout;

    public ControlServer() {
        this.readTimeout = 600;
        this.idleTimeout = 600;
    }

    public void listen(InetSocketAddress listenAddr) throws Exception {
        if (listenAddr == null) {
            throw new Exception("监听地址不能为空");
        } else {
            this.listenAddr = listenAddr;
            this.acceptor = new NioSocketAcceptor(100);
            DefaultIoFilterChainBuilder chain = this.acceptor.getFilterChain();
            chain.addLast("codec", new ProtocolCodecFilter(new ControlProtocolFactory()));
            chain.addLast("threadPool", new ExecutorFilter(new OrderedThreadPoolExecutor(10, 300, 5L, TimeUnit.SECONDS)));
            this.handler = new ControlHandler();
            this.acceptor.setHandler(this.handler);

            try {
                this.acceptor.bind(listenAddr);
            } catch (Exception e) {
                throw new Exception("监听启动失败，原因：" + e.getMessage());
            }
        }
    }

    public void stop() throws Exception {
        this.acceptor.dispose();
    }

    public static void main(String[] args) throws Exception {
        try {
            ControlServer server = new ControlServer();
            server.listen(new InetSocketAddress(Constants.CONTROL_PORT));

            Scanner scanner = new Scanner(System.in);


            int i = -1;
            while(true) {
                i = scanner.nextInt();
                if (i == 1) {
                    TransSession transSession = Constants.FACSEESION.get((int) 0x01);
                    if (transSession != null) {
                        System.out.println("SetTraceUserReq");
                        CommonMsg commonMsg = new CommonMsg();
                        commonMsg.setStartSymbol((byte) 126);
                        commonMsg.setVersion((byte) 0x01);

                        commonMsg.setLength((short) (1 + getSetTraceUserReqMsg().length));
                        commonMsg.setMsgType(EMsgType.SETTRACEUSERREQ);
                        commonMsg.setSeqNum(1);
                        commonMsg.setContent(getSetTraceUserReqMsg());
                        commonMsg.setEndSymbol((byte) 150);

                        transSession.write(commonMsg.getByteArray());
                    }
                } else if(i == 2){
                    TransSession transSession = Constants.FACSEESION.get((int) 0x01);
                    if (transSession != null) {
                        System.out.println("ListTraceUserReqMsg");
                        CommonMsg commonMsg = new CommonMsg();
                        commonMsg.setStartSymbol((byte) 126);
                        commonMsg.setVersion((byte) 0x01);
                        if (getListTraceUserReqMsg() !=null) {
                            commonMsg.setLength((short) (1 + getListTraceUserReqMsg().length));
                        } else {
                            commonMsg.setLength((short) 1);
                        }

                        commonMsg.setMsgType(EMsgType.LISTTRACEUSERREQ);
                        commonMsg.setSeqNum(1);
                        commonMsg.setContent(getListTraceUserReqMsg());
                        commonMsg.setEndSymbol((byte) 150);

                        transSession.write(commonMsg.getByteArray());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] getSetTraceUserReqMsg() {
        SetTraceUserReq setTraceUserReq = new SetTraceUserReq();
        setTraceUserReq.setOpType((byte) 0x01);
        setTraceUserReq.setTraceUserNum((byte) 0x01);
        setTraceUserReq.setTraceUser(new byte[]{0x01, 0x02, 0x03, 0x04, 0x01, 0x02, 0x03, 0x04});
        setTraceUserReq.setTraceDuration(new byte[]{0x01, 0x01});
        return setTraceUserReq.getByteArray();
    }

    public static byte[] getListTraceUserReqMsg() {
        // 无参
        return null;
    }
}
