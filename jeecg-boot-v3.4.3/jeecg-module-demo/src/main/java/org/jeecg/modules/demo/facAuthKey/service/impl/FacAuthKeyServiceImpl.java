package org.jeecg.modules.demo.facAuthKey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdt.omap.protocol.auth.AuthAck;
import com.sdt.omap.protocol.auth.AuthMsg;
import com.sdt.omap.protocol.auth.AuthReq;
import com.sdt.omap.protocol.other.EMsgType;
import com.sdt.omap.utils.Aes128Cmac;
import com.sdt.omap.utils.Constants;
import com.sdt.omap.utils.DataTransfer;
import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.modules.demo.facAuthKey.entity.FacAuthKey;
import org.jeecg.modules.demo.facAuthKey.mapper.FacAuthKeyMapper;
import org.jeecg.modules.demo.facAuthKey.service.IFacAuthKeyService;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

/**
 * @Description: facId认证秘钥
 * @Author: jeecg-boot
 * @Date: 2023-08-11
 * @Version: V1.0
 */
@Service
public class FacAuthKeyServiceImpl extends ServiceImpl<FacAuthKeyMapper, FacAuthKey> implements IFacAuthKeyService {

    @Autowired
    private FacAuthKeyMapper facAuthKeyMapper;
    @Autowired
    private WorkLogMapper workLogMapper;

    @Override
    public void add(FacAuthKey facAuthKey) {
        WorkLog workLog = new WorkLog();
        workLog.setOperating("AuthKey管理新增FACB" + facAuthKey.getFacId());
        try {
            List<Integer> allFacId = facAuthKeyMapper.allFacId();
            if (allFacId.contains(facAuthKey.getFacId())) {
                throw new RuntimeException("该FACID已存在");
            }
            String authKey = getAuthKey();
            facAuthKey.setFacName("FACB" + facAuthKey.getFacId());
            facAuthKey.setAuthKey(authKey);
            facAuthKey.setFlag(0);
            facAuthKeyMapper.insert(facAuthKey);
            workLog.setOperatingResult("1");
        } catch (Exception e) {
            e.printStackTrace();
            workLog.setOperatingResult("0");
        }
        workLogMapper.insert(workLog);

    }

    @Override
    public void updateByFacId(FacAuthKey facAuthKey) {
        FacAuthKey authKeyById = facAuthKeyMapper.selectById(facAuthKey.getId());
        WorkLog workLog = new WorkLog();
        workLog.setOperating("AuthKey管理-FACB" + authKeyById.getFacId()+"重新生成AuthKey");
        try {
            String authKey = getAuthKey();
            facAuthKey.setAuthKey(authKey);
            facAuthKeyMapper.updateById(facAuthKey);
            workLog.setOperatingResult("1");
        } catch (Exception e) {
            e.printStackTrace();
            workLog.setOperatingResult("0");
        }
        workLogMapper.insert(workLog);
    }

    @Override
    public Boolean checking(AuthReq authReq) throws Exception {
        int facId = ByteUtil.bytes2smallint(authReq.getFacId(), 0);
        System.out.println(facId);
        QueryWrapper<FacAuthKey> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fac_id", facId);
        FacAuthKey facAuthKey = facAuthKeyMapper.selectOne(queryWrapper);
        AuthReq auth = new AuthReq();
        BeanUtils.copyProperties(authReq, auth);
        auth.setMic(null);

        AuthMsg authMsg = new AuthMsg();
        authMsg.setVersion((byte) 0x01);
        authMsg.setMsgType(EMsgType.AUTHREQ);
        authMsg.setContent(auth.getByteArray());

        String authKey = facAuthKey.getAuthKey();
        byte[] bytes = Base64Util.DecodeString(authKey);
//        byte[] bytes = ByteUtil.strToHexByte(authKey);
        byte[] mic = null;
        byte[] paddingData = DataTransfer.padding(authMsg.getByteArray());
        if (paddingData != null && paddingData.length > 0) {
            mic = Aes128Cmac.generateMac(bytes, paddingData);
        }
        boolean equalByteArray = ByteUtil.isEqualByteArray(authReq.getMic(), mic);

        if (equalByteArray) {
            AuthAck authAck = new AuthAck();
            authAck.setFacId(authReq.getFacId());
            authAck.setMic(mic);
        }
        return equalByteArray;
    }

    @Override
    public byte[] authKey(AuthReq authReq) throws Exception {
        int facId = ByteUtil.bytes2smallint(authReq.getFacId(), 0);
        System.out.println(facId);
        QueryWrapper<FacAuthKey> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fac_id", facId);
        FacAuthKey facAuthKey = facAuthKeyMapper.selectOne(queryWrapper);
        String authKey = facAuthKey.getAuthKey();
        byte[] bytes = Base64Util.DecodeString(authKey);
        return bytes;
    }

    @Override
    public IPage<FacAuthKey> pagelist(Page<FacAuthKey> page, FacAuthKey facAuthKey) {
        IPage<FacAuthKey> pagelist = facAuthKeyMapper.pagelist(page, facAuthKey);
        return pagelist;
    }

    @Override
    public IPage<FacAuthKey> getList(Page<FacAuthKey> page) {
        IPage<FacAuthKey> pagelist = facAuthKeyMapper.getList(page);

        return pagelist;
    }

    public String getAuthKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[16];
        secureRandom.nextBytes(randomBytes);
        String authKey = Base64Util.EncodeString(randomBytes);
    /*    byte[] encodedBytes = Base64.getEncoder().encode(randomBytes);
        String authKey = new String(encodedBytes, StandardCharsets.UTF_8);*/

        return authKey;
    }
}
