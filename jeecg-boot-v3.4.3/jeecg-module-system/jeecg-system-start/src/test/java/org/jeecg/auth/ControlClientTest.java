package org.jeecg.auth;

import com.sdt.omap.client.CommClient;
import com.sdt.omap.protocol.comm.CommonMsg;
import com.sdt.omap.protocol.comm.FACNotify;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.session.TransSession;
import com.sdt.omap.utils.Constants;
import com.sdt.util.standard.ByteUtil;

public class ControlClientTest {

    public static void main(String[] args) throws Exception {
        CommClient client = new CommClient();

        TransSession session = client.tcpConnect(Constants.CONTROL_IP, Constants.CONTROL_PORT);

        FACNotify notify = new FACNotify();

        byte[] b = ByteUtil.smallint2bytes(6939);
        notify.setFacId(b);

        CommonMsg commonMsg = new CommonMsg();
        commonMsg.setStartSymbol((byte) 126);
        commonMsg.setVersion((byte) 0x01);
        commonMsg.setLength((short) (1 + notify.getByteArray().length));
        commonMsg.setMsgType(EMsgType.FACNOTIFY);
        commonMsg.setSeqNum(1);
        commonMsg.setContent(notify.getByteArray());
        commonMsg.setEndSymbol((byte) 150);

        System.out.println("发送前的数据");
        System.out.println(ByteUtil.byte2HexString0x(commonMsg.getByteArray(), true));

        session.write(commonMsg.getByteArray());




    }


}
