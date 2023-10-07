package com.sdt.omap.server.report.codec;

import com.sdt.util.standard.ByteUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ReportProtocolDecoder extends CumulativeProtocolDecoder {

    public IoSession session;

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        this.session = session;
        int start = in.position();

        if (in.hasRemaining()) {
            if (in.remaining() < 6) {
                Thread.sleep(10L);
            } else {
                byte[] header = new byte[6];
                in.get(header);
                byte[] lenData = new byte[2];
                System.arraycopy(header, 4, lenData, 0, 2);
                int datalen = ByteUtil.bytes2smallint(lenData, 0);
//                System.out.println("datalen="+datalen);
                if (in.remaining() >= datalen-6) {
                    byte[] pkt = new byte[datalen];
                    in.position(start);
                    in.get(pkt);
                    out.write(pkt);
                    return true;
                }
            }
        }
        in.position(start);
        return false;
    }
}
