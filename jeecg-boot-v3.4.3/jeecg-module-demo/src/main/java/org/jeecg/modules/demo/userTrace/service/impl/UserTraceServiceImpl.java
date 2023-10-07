package org.jeecg.modules.demo.userTrace.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.sdt.omap.protocol.auth.AuthMsg;
import com.sdt.omap.protocol.comm.*;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.server.control.ControlServer;
import com.sdt.omap.session.TransSession;
import com.sdt.omap.utils.BCDUtils;
import com.sdt.omap.utils.Constants;
import com.sdt.util.standard.ByteUtil;

import org.jeecg.modules.demo.userTrace.entity.StrategyDistribution;
import org.jeecg.modules.demo.userTrace.entity.UserTrace;
import org.jeecg.modules.demo.userTrace.mapper.UserTraceMapper;
import org.jeecg.modules.demo.userTrace.service.IUserTraceService;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

/**
 * @Description: 用户追踪
 * @Author: jeecg-boot
 * @Date: 2023-08-16
 * @Version: V1.0
 */
@Service
public class UserTraceServiceImpl extends ServiceImpl<UserTraceMapper, UserTrace> implements IUserTraceService {

    @Autowired
    private WorkLogMapper workLogMapper;

    @Override
    public JSONObject setTrace(UserTrace userTrace) {
        int code = 1;
        String message = "";
        if (1 == userTrace.getOpType()) {
            message = "开始跟踪";
        } else {
            message = "停止跟踪";
        }
        WorkLog workLog = new WorkLog();
        workLog.setOperating("FACB" + userTrace.getFacId() + "下发用户" + message + "指令");
        try {
            for (Map.Entry<Integer, TransSession> entry : Constants.FACSEESION.entrySet()) {
                System.out.println("TransSessionKey:" + entry.getKey());
            }

            TransSession transSession = Constants.FACSEESION.get(userTrace.getFacId());

            if (transSession != null) {

                CommonMsg commonMsg = new CommonMsg();
                commonMsg.setStartSymbol((byte) 126);
                commonMsg.setVersion((byte) 0x01);
                byte[] setTraceUserReqMsg = getSetTraceUserReqMsg(userTrace);
                commonMsg.setLength((short) (1 + setTraceUserReqMsg.length));
                commonMsg.setMsgType(EMsgType.SETTRACEUSERREQ);
                commonMsg.setSeqNum(1);
                commonMsg.setContent(setTraceUserReqMsg);
                commonMsg.setEndSymbol((byte) 150);

                transSession.write(commonMsg.getByteArray());
                System.out.println("发送SetTraceUserReq数据");
                System.out.println(ByteUtil.byte2HexString0x(commonMsg.getByteArray(), true));
                workLog.setOperatingResult("1");
            } else {
                System.out.println("该FAC设备暂未建立session连接");
                message = "该FAC设备暂未建立session连接";
                code = 2;
                workLog.setOperatingResult("0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        workLogMapper.insert(workLog);
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("message",message);
        return json;
    }

    @Override
    public void listTraceUser() throws Exception {

        TransSession transSession = Constants.FACSEESION.get((int) 0x01);
        if (transSession != null) {
            System.out.println("ListTraceUserReqMsg");
            CommonMsg commonMsg = new CommonMsg();
            commonMsg.setStartSymbol((byte) 126);
            commonMsg.setVersion((byte) 0x01);
            if (getListTraceUserReqMsg() != null) {
                commonMsg.setLength((short) (1 + getListTraceUserReqMsg().length));
            } else {
                commonMsg.setLength((short) 1);
            }

            commonMsg.setMsgType(EMsgType.LISTTRACEUSERREQ);
            commonMsg.setSeqNum(1);
            commonMsg.setContent(getListTraceUserReqMsg());
            commonMsg.setEndSymbol((byte) 150);

            transSession.write(commonMsg.getByteArray());
            System.out.println("ListTraceUserReqMsg");
        }
    }

    @Override
    public JSONObject strategyDistribution(StrategyDistribution strategyDistribution) {
        WorkLog workLog = new WorkLog();
        int code = 1;
        String optype = strategyDistribution.getOpType() == 1 ? "开始下发" : "结束";
        String alarmpolicy = strategyDistribution.getAlarmpolicy() == 1 ? "严重告警" : "一般告警";
        String message = optype + alarmpolicy + "策略";
        workLog.setOperating("FACB" + strategyDistribution.getFacId() + message);
        try {
            for (Map.Entry<Integer, TransSession> entry : Constants.FACSEESION.entrySet()) {
                System.out.println("TransSessionKey:" + entry.getKey());
            }
            TransSession transSession = Constants.FACSEESION.get(strategyDistribution.getFacId());
            if (transSession != null) {

                CommonMsg commonMsg = new CommonMsg();
                commonMsg.setStartSymbol((byte) 126);
                commonMsg.setVersion((byte) 0x01);
                byte[] setAlarmpolicyReq = getSetAlarmpolicyReq(strategyDistribution);
                commonMsg.setLength((short) (1 + setAlarmpolicyReq.length));
                commonMsg.setMsgType(EMsgType.SETALARMPOLICYREQ);
                commonMsg.setSeqNum(1);
                commonMsg.setContent(setAlarmpolicyReq);
                commonMsg.setEndSymbol((byte) 150);

                transSession.write(commonMsg.getByteArray());
                System.out.println("发送SetAlarmpolicyReq数据");
                System.out.println(ByteUtil.byte2HexString0x(commonMsg.getByteArray(), true));
                workLog.setOperatingResult("1");
            } else {
                System.out.println("该FAC设备暂未建立session连接");
                message = "该FAC设备暂未建立session连接";
                code = 2;
                workLog.setOperatingResult("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        workLogMapper.insert(workLog);
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("message",message);
        return json;
    }

    public byte[] getSetTraceUserReqMsg(UserTrace userTrace) throws IOException {

        List<String> userPhone = Arrays.asList(userTrace.getTraceUser().split(","));
        List<byte[]> result = new ArrayList();
        for (String phone : userPhone) {
            byte[] traceUser = new byte[25];

            byte[] bcdphoneNum = BCDUtils.toBCDCode(phone);
            traceUser[0] = (byte) 0x01;
            System.arraycopy(bcdphoneNum, 0, traceUser, 1, bcdphoneNum.length);
            result.add(traceUser);
        }
        byte[] traceUsers = byteMergerAll(result);


      /*  byte[] traceUser = new byte[8];
        byte[] bcdphoneNum = BCDUtils.toBCDCode(userTrace.getTraceUser());
        traceUser[0] = (byte)0x01;
        System.arraycopy(bcdphoneNum,0,traceUser,1,bcdphoneNum.length);*/
        SetTraceUserReq setTraceUserReq = new SetTraceUserReq();
        setTraceUserReq.setOpType(ByteUtil.tinyint2bytes(userTrace.getOpType()));
        setTraceUserReq.setTraceUserNum(ByteUtil.tinyint2bytes(userTrace.getTraceUserNum()));
        setTraceUserReq.setTraceUser(traceUsers);
        byte[] traceDuration = new byte[2];
        traceDuration[0] = (byte) 0x01;
        traceDuration[1] = ByteUtil.tinyint2bytes(userTrace.getTraceDuration());
        setTraceUserReq.setTraceDuration(traceDuration);
        return setTraceUserReq.getByteArray();
    }

    public static byte[] getListTraceUserReqMsg() {
        // 无参
        return null;
    }

    public byte[] byteMergerAll(List<byte[]> args) {
        int length_byte = 0;
        for (byte[] b : args) {
            length_byte += b.length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (byte[] b : args) {
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        System.out.println("发送traceUsers数据:" + ByteUtil.byte2HexString0x(all_byte, true));
        return all_byte;

    }

    public byte[] getSetAlarmpolicyReq(StrategyDistribution strategyDistribution) {
        SetAlarmpolicyReq setAlarmpolicyReq = new SetAlarmpolicyReq();
        setAlarmpolicyReq.setOpType(ByteUtil.tinyint2bytes(strategyDistribution.getOpType()));
        setAlarmpolicyReq.setAlarmpolicy(ByteUtil.tinyint2bytes(strategyDistribution.getAlarmpolicy()));
        return setAlarmpolicyReq.getByteArray();
    }
}
