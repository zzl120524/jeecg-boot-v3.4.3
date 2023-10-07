package com.sdt.omap.server.auth;

import com.sdt.omap.protocol.auth.AuthReq;
import com.sdt.omap.server.auth.codec.AuthProtocolFactory;
import com.sdt.omap.server.auth.handler.AuthHandler;
import com.sdt.omap.utils.Constants;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.jeecg.modules.demo.facAuthKey.service.IFacAuthKeyService;
import org.jeecg.modules.demo.facAuthKey.service.impl.FacAuthKeyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class AuthServer {


    private IoAcceptor acceptor;
    private InetSocketAddress listenAddr;
    private IoHandler handler = null;
    private Integer readTimeout;
    private Integer idleTimeout;

    public AuthServer() {
        this.readTimeout = 600;
        this.idleTimeout = 600;
    }

    public void listen(InetSocketAddress listenAddr) throws Exception {
        if (listenAddr == null) {
            throw new Exception("监听地址不能为空");
        } else {
            this.listenAddr = listenAddr;
            this.acceptor = new NioDatagramAcceptor();
            DefaultIoFilterChainBuilder chain = this.acceptor.getFilterChain();
            chain.addLast("codec", new ProtocolCodecFilter(new AuthProtocolFactory()));
            chain.addLast("threadPool", new ExecutorFilter(new OrderedThreadPoolExecutor(10, 300, 5L, TimeUnit.SECONDS)));
            this.handler = new AuthHandler();
            this.acceptor.setHandler(this.handler);

            try {
                this.acceptor.bind(listenAddr);
            } catch (Exception e) {
                throw new Exception("监听启动失败，原因：" + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /*DatagramSocket socket = new DatagramSocket(9999);
        byte[] buffer = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        int len = packet.getLength();
        AuthReq authReq = new AuthReq();
        AuthReq parse = authReq.parse(buffer);
        socket.close();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IFacAuthKeyService facAuthKeyService = context.getBean(IFacAuthKeyService.class);*/

//        Boolean checking = facAuthKeyService.checking(parse);

        try {
            AuthServer server = new AuthServer();
            server.listen(new InetSocketAddress(Constants.AUTH_PORT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
