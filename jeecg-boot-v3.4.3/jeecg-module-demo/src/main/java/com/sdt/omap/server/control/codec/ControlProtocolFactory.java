package com.sdt.omap.server.control.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ControlProtocolFactory implements ProtocolCodecFactory {

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return new ControlProtocolEncoder();
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return new ControlProtocolDecoder();
    }
}
