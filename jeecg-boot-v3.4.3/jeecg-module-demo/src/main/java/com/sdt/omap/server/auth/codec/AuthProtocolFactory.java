package com.sdt.omap.server.auth.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class AuthProtocolFactory implements ProtocolCodecFactory {

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return new AuthProtocolEncoder();
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return new AuthProtocolDecoder();
    }
}
