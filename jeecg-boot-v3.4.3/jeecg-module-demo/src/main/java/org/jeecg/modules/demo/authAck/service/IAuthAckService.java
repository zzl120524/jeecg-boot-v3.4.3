package org.jeecg.modules.demo.authAck.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.demo.authAck.entity.AuthAckVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 授权确认
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
public interface IAuthAckService extends IService<AuthAckVo> {

    AuthAckVo getAuthAckByFacId();

    List<JSONObject> queryList();

    void edit(AuthAckVo authAck);
}
