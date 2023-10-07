package com.sdt.omap.session;

import com.sdt.util.logger.ELogLevel;
import com.sdt.util.logger.console.ConsoleLogger;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.session.IoSession;

public class TransSession {

    private IoSession session;

    public TransSession(IoSession session) {
        this.session = session;
    }

    public void closeExpireSession() {
        this.session.close(true);
    }

    public byte[] read() {
        ReadFuture readFuture = this.session.read();

        while(!readFuture.isDone()) {
            try {
                Thread.sleep(0L, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            byte[] msg = (byte[]) readFuture.getMessage();
            if (msg == null) {
                ConsoleLogger.consolePrintLog(ELogLevel.ERROR, "未收到任何业务数据，会话:" + this.session + "，可能连接已超时或会话已关闭。");
            }

            return msg;
        } catch (RuntimeIoException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(byte[] data) throws Exception {
        this.session.write(data);
    }
}