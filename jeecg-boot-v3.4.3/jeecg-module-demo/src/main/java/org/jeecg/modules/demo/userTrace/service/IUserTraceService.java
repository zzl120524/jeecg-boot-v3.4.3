package org.jeecg.modules.demo.userTrace.service;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.demo.userTrace.entity.StrategyDistribution;
import org.jeecg.modules.demo.userTrace.entity.UserTrace;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 用户追踪
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface IUserTraceService extends IService<UserTrace> {

    JSONObject setTrace(UserTrace userTrace);

    void listTraceUser() throws Exception;

    JSONObject strategyDistribution(StrategyDistribution strategyDistribution);
}
