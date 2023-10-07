package com.sdt.omap.client;

import com.sdt.omap.server.auth.codec.AuthProtocolFactory;
import com.sdt.omap.server.control.codec.ControlProtocolFactory;
import com.sdt.omap.server.report.codec.ReportProtocolFactory;
import com.sdt.omap.session.TransSession;
import com.sdt.util.logger.ELogLevel;
import com.sdt.util.logger.console.ConsoleLogger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

public class CommClient {

    private IoConnector connector;
    private IoSession session;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer idleTimeout;

    public CommClient() {
        this.connectTimeout = 10;
        this.readTimeout = 600;
        this.idleTimeout = 600;
    }

    public TransSession udpConnect(String serverIP, int serverPort) throws Exception {
        TransSession transSession = null;
        try {
            this.connector = new NioDatagramConnector();
            this.connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new AuthProtocolFactory()));
            this.connector.setHandler(new CommClientHandler());
            this.connector.getSessionConfig().setUseReadOperation(true);
            ConnectFuture connectFuture = this.connector.connect(new InetSocketAddress(serverIP, serverPort));
            connectFuture.awaitUninterruptibly();
            if (!connectFuture.isConnected()) {
                throw new Exception("与" + serverIP + ":" + serverPort + "进行UDP连接时四元组填充失败。");
            } else {
                this.session = connectFuture.getSession();
                transSession = new TransSession(connectFuture.getSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transSession;
    }

    public TransSession tcpConnect(String serverIP, int serverPort) throws Exception {

        try {
            this.connector = new NioSocketConnector();
            this.connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ControlProtocolFactory()));
            this.connector.setHandler(new CommClientHandler());
            this.connector.getSessionConfig().setUseReadOperation(true);

            ConnectFuture connectFuture = this.connector.connect(new InetSocketAddress(serverIP, serverPort));
            connectFuture.awaitUninterruptibly();
            if (!connectFuture.isConnected()) {
                this.connector.dispose();
                throw new Exception("与" + serverIP + ":" + serverPort + "连接建立失败，请确认对端服务是否已启动");
            } else {
                this.session = connectFuture.getSession();

                TransSession transSession = new TransSession(connectFuture.getSession());

                return transSession;
            }
        } catch (Exception e) {
            String msg = "连接建立失败，原因：" + e.getMessage();
            ConsoleLogger.consolePrintLog(ELogLevel.ERROR, msg);
            throw new Exception(msg);
        }
    }

    public TransSession udpReportConnect(String serverIP, int serverPort) throws Exception {
        TransSession transSession = null;
        try {
            this.connector = new NioDatagramConnector();
            this.connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ReportProtocolFactory()));
            this.connector.setHandler(new CommClientHandler());
            this.connector.getSessionConfig().setUseReadOperation(true);
            ConnectFuture connectFuture = this.connector.connect(new InetSocketAddress(serverIP, serverPort));
            connectFuture.awaitUninterruptibly();
            if (!connectFuture.isConnected()) {
                throw new Exception("与" + serverIP + ":" + serverPort + "进行UDP连接时四元组填充失败。");
            } else {
                this.session = connectFuture.getSession();
                transSession = new TransSession(connectFuture.getSession());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transSession;
    }
}
