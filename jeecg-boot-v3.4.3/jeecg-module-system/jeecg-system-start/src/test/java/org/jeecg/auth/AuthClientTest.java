package org.jeecg.auth;

import com.sdt.omap.client.CommClient;
import com.sdt.omap.protocol.auth.AuthMsg;
import com.sdt.omap.protocol.auth.AuthReq;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.session.TransSession;
import com.sdt.omap.utils.Aes128Cmac;
import com.sdt.omap.utils.AesUtil;
import com.sdt.omap.utils.Constants;
import com.sdt.omap.utils.DataTransfer;
import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JeecgSystemApplication.class)
public class AuthClientTest {



    @Test
    public void testAdd() throws Exception{
//            public static void main(String[] args) throws Exception {
        CommClient client = new CommClient();
        TransSession session = client.udpConnect(Constants.AUTH_IP, Constants.AUTH_PORT);

        AuthReq authReq = new AuthReq();
//        authReq.setFacId(new byte[]{0x00, 0x01});
        byte[] authKey = Constants.AUTH_KEY;
        String s = ByteUtil.byte2HexString(authKey, false);
     /*   byte[] bytes1 = {0x00, 0x01};
        int i = ByteUtil.bytes2smallint(bytes1, 0);*/
        authReq.setFacId(ByteUtil.smallint2bytes(6939));
        authReq.setRandFAC(new byte[]{0x00, 0x00, 0x00, 0x01});
        authReq.setMic(null);

        AuthMsg authMsg = new AuthMsg();
        authMsg.setVersion((byte) 0x01);
        authMsg.setMsgType(EMsgType.AUTHREQ);
        authMsg.setContent(authReq.getByteArray());

        byte[] mic = null;
        byte[] paddingData = DataTransfer.padding(authMsg.getByteArray());
        if (paddingData!= null && paddingData.length > 0) {
/*            mic = Aes128Cmac.generateMac(Constants.AUTH_KEY, paddingData);
            mic = Aes128Cmac.generateMac(Constants.AUTH_KEY, paddingData);*/
//            byte[] bytes = Base64Util.DecodeString("yRXLalZxr1FxBKc2a9xKVw==");
//            byte[] bytes = ByteUtil.strToHexByte("01010101010101010101010101010101");
            mic = Aes128Cmac.generateMac(authKey, paddingData);
        }
        authReq.setMic(mic);
        authMsg.setContent(authReq.getByteArray());
        authMsg.setLength((short) (1 + authReq.getByteArray().length));

        System.out.println("发送前的数据");
        System.out.println(ByteUtil.byte2HexString0x(authMsg.getByteArray(), true));

/*        DatagramSocket ds = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        int whileInt = 0;
        while (whileInt <= 3) {
            System.out.println("请输入你要发送的消息");
            //创建数据报包 ,下面数据中的"***.***.***.***"更换成接收消息的主机号,"9999"是由接收方设定好的端口号,
            DatagramPacket dp = new DatagramPacket(authReq.getByteArray(), authReq.getByteArray().length, InetAddress.getByName("127.0.0.1"), 9999);

            //阻塞式方法 此方法在接收到数据报前一直阻塞
//            ds.receive(dp);
            //发送数据
            ds.send(dp);
            ds.close();

        *//*    Boolean checking = facAuthKeyService.checking(authReq);
            if (checking){
                break;
            }
            Thread.sleep(1000);
            break;*//*
            Thread.sleep(1000);
        }*/
//        Boolean checking = facAuthKeyService.checking(authReq);
//        TransSession session = client.udpConnect(Constants.AUTH_IP, Constants.AUTH_PORT);

        session.write(authMsg.getByteArray());

        // 接收响应信息并处理
        try {
            byte[] receiveData = session.read();
            System.out.println(ByteUtil.byte2HexString0x(receiveData, true));
            AuthMsg recvMsg = new AuthMsg();
            recvMsg = recvMsg.parse(receiveData);

            System.out.println(recvMsg.getContent().length);
            byte[] decData = AesUtil.aesDecry(recvMsg.getContent(), Constants.AUTH_KEY);
            System.out.println(recvMsg.toString());
            System.out.println(ByteUtil.byte2HexString0x(decData, true));

            byte[] bytes = AesUtil.aesDecry(receiveData, Constants.AUTH_KEY);
            recvMsg = recvMsg.parse(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
