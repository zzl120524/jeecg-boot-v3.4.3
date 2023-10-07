package com.sdt.omap.server.report;

import com.sdt.omap.server.report.codec.ReportProtocolFactory;
import com.sdt.omap.server.report.handler.ReportHandler;
import com.sdt.omap.utils.Constants;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class ReportServer {

    private IoAcceptor acceptor;
    private InetSocketAddress listenAddr;
    private IoHandler handler = null;
    private Integer readTimeout;
    private Integer idleTimeout;

    public ReportServer() {
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
            chain.addLast("codec", new ProtocolCodecFilter(new ReportProtocolFactory()));
            chain.addLast("threadPool", new ExecutorFilter(new OrderedThreadPoolExecutor(10, 300, 5L, TimeUnit.SECONDS)));
            this.handler = new ReportHandler();
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
            ReportServer server = new ReportServer();
            server.listen(new InetSocketAddress(Constants.REPORT_PORT));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
