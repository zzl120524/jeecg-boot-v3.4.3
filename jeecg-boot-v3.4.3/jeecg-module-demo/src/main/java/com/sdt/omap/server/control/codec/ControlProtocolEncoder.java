package com.sdt.omap.server.control.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ControlProtocolEncoder implements ProtocolEncoder {

    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buf = IoBuffer.allocate(32).setAutoExpand(true);
        buf.put((byte[])message);
        buf.flip();
        out.write(buf);
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
