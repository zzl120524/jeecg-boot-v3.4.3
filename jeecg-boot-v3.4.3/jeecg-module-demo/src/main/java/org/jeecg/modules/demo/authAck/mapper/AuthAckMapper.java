package org.jeecg.modules.demo.authAck.mapper;

import org.jeecg.modules.demo.authAck.entity.AuthAckVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 授权确认
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
public interface AuthAckMapper extends BaseMapper<AuthAckVo> {

    AuthAckVo getAuthAckByFacId();

}
