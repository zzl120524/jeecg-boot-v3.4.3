package org.jeecg.modules.demo.authAck.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.modules.demo.authAck.entity.AuthAckVo;
import org.jeecg.modules.demo.authAck.mapper.AuthAckMapper;
import org.jeecg.modules.demo.authAck.service.IAuthAckService;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 授权确认
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
@Service
public class AuthAckServiceImpl extends ServiceImpl<AuthAckMapper, AuthAckVo> implements IAuthAckService {

    @Autowired
    private AuthAckMapper authAckMapper;
    @Autowired
    private WorkLogMapper workLogMapper;

    @Override
    public AuthAckVo getAuthAckByFacId() {
        AuthAckVo authAckByFacId = authAckMapper.getAuthAckByFacId();
        return authAckByFacId;
    }

    @Override
    public List<JSONObject> queryList() {
        List<AuthAckVo> authAcks = authAckMapper.selectList(null);

        List<JSONObject> list = new ArrayList<>();
        for (AuthAckVo authAck : authAcks) {
            JSONObject jsonId = new JSONObject();
            jsonId.put("key","id");
            jsonId.put("text","id");
            jsonId.put("value",authAck.getId());
            list.add(jsonId);

       /*     JSONObject json = new JSONObject();
            json.put("text","FAC_ID");
            json.put("value",authAck.getFacId());
            json.put("key","facId");*/
            JSONObject jsonControlifIp = new JSONObject();
            jsonControlifIp.put("key","controlifIp");
            jsonControlifIp.put("text","控制接口地址");
            jsonControlifIp.put("value",authAck.getControlifIp());
            list.add(jsonControlifIp);
            JSONObject jsonControlifPort = new JSONObject();
            jsonControlifPort.put("key","controlifPort");
            jsonControlifPort.put("text","控制接口端口");
            jsonControlifPort.put("value",authAck.getControlifPort());
            list.add(jsonControlifPort);
            JSONObject jsonSignalPacketReportIp = new JSONObject();
            jsonSignalPacketReportIp.put("key","signalPacketReportIp");
            jsonSignalPacketReportIp.put("text","用户跟踪报文上传地址");
            jsonSignalPacketReportIp.put("value",authAck.getSignalPacketReportIp());
            list.add(jsonSignalPacketReportIp);

            JSONObject jsonSignalPacketReportPort = new JSONObject();
            jsonSignalPacketReportPort.put("key","signalPacketReportPort");
            jsonSignalPacketReportPort.put("text","用户跟踪报文上传端口");
            jsonSignalPacketReportPort.put("value",authAck.getSignalPacketReportPort());
            list.add(jsonSignalPacketReportPort);


            /*JSONObject jsonTracePacketReportIp= new JSONObject();
            jsonTracePacketReportIp.put("key","tracePacketReportIp");
            jsonTracePacketReportIp.put("text","跟踪用户UDP连接Port");
            jsonTracePacketReportIp.put("value",authAck.getTracePacketReportIp());
            list.add(jsonTracePacketReportIp);

            JSONObject jsonTracePacketReportUdp= new JSONObject();
            jsonTracePacketReportUdp.put("key","tracePacketReportUdp");
            jsonTracePacketReportUdp.put("text","跟踪用户UDP连接端口");
            jsonTracePacketReportUdp.put("value",authAck.getTracePacketReportUdp());
            list.add(jsonTracePacketReportUdp);

            JSONObject jsonEncryptPacketReportIp= new JSONObject();
            jsonEncryptPacketReportIp.put("key","encryptPacketReportIp");
            jsonEncryptPacketReportIp.put("text","加密数据连接IP");
            jsonEncryptPacketReportIp.put("value",authAck.getEncryptPacketReportIp());
            list.add(jsonEncryptPacketReportIp);


            JSONObject jsonEncryptPacketReportUdp= new JSONObject();
            jsonEncryptPacketReportUdp.put("key","encryptPacketReportUdp");
            jsonEncryptPacketReportUdp.put("text","加密数据连接端口");
            jsonEncryptPacketReportUdp.put("value",authAck.getEncryptPacketReportUdp());
            list.add(jsonEncryptPacketReportUdp);

            JSONObject jsonMalformedPacketReportIp= new JSONObject();
            jsonMalformedPacketReportIp.put("key","malformedPacketReportIp");
            jsonMalformedPacketReportIp.put("text","异常报文连接IP");
            jsonMalformedPacketReportIp.put("value",authAck.getMalformedPacketReportIp());
            list.add(jsonMalformedPacketReportIp);

            JSONObject jsonMalformedPacketReportUdp= new JSONObject();
            jsonMalformedPacketReportUdp.put("key","malformedPacketReportUdp");
            jsonMalformedPacketReportUdp.put("text","异常报文连接端口");
            jsonMalformedPacketReportUdp.put("value",authAck.getMalformedPacketReportUdp());
            list.add(jsonMalformedPacketReportUdp);
*/
            JSONObject jsonCdrFtpUser= new JSONObject();
            jsonCdrFtpUser.put("key","cdrFtpUser");
            jsonCdrFtpUser.put("text","FTP User名称");
            jsonCdrFtpUser.put("value",authAck.getCdrFtpUser());
            list.add(jsonCdrFtpUser);

            JSONObject jsonCdrFtpKey= new JSONObject();
            jsonCdrFtpKey.put("key","cdrFtpKey");
            jsonCdrFtpKey.put("text","FTP 密码");
            jsonCdrFtpKey.put("value",authAck.getCdrFtpKey());
            list.add(jsonCdrFtpKey);

            JSONObject jsonFtpDirectory= new JSONObject();
            jsonFtpDirectory.put("key","ftpDirectory");
            jsonFtpDirectory.put("text","FTP文件路径");
            jsonFtpDirectory.put("value",authAck.getFtpDirectory());
            list.add(jsonFtpDirectory);

            JSONObject jsonFtpServerIp= new JSONObject();
            jsonFtpServerIp.put("key","ftpServerIp");
            jsonFtpServerIp.put("text","服务IP");
            jsonFtpServerIp.put("value",authAck.getFtpServerIp());
            list.add(jsonFtpServerIp);



        }
        return list;
    }

    @Override
    public void edit(AuthAckVo authAck) {
        WorkLog workLog = new WorkLog();
        workLog.setOperating("修改服务配置");

        try{
            String ip = authAck.getSignalPacketReportIp();
            Integer port = authAck.getSignalPacketReportPort();
            authAck.setTracePacketReportIp(ip);
            authAck.setTracePacketReportUdp(port);
            authAck.setEncryptPacketReportIp(ip);
            authAck.setEncryptPacketReportUdp(port);
            authAck.setMalformedPacketReportIp(ip);
            authAck.setMalformedPacketReportUdp(port);
            authAckMapper.updateById(authAck);
            workLog.setOperatingResult("1");
        }catch (Exception e) {
            e.printStackTrace();
            workLog.setOperatingResult("0");
        }
        workLogMapper.insert(workLog);
    }
}
