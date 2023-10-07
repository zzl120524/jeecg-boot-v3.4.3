package com.sdt.omap.server.report.codec;

import com.sdt.omap.server.control.codec.ControlProtocolDecoder;
import com.sdt.omap.server.control.codec.ControlProtocolEncoder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ReportProtocolFactory implements ProtocolCodecFactory {
    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return new ReportProtocolEncoder();
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return new ReportProtocolDecoder();
    }
}
