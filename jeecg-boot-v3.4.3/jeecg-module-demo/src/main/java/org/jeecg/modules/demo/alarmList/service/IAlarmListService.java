package org.jeecg.modules.demo.alarmList.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.alarmList.entity.AlarmList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.alarmList.entity.AlarmListVo;

/**
 * @Description: 告警列表
 * @Author: jeecg-boot
 * @Date:   2023-09-10
 * @Version: V1.0
 */
public interface IAlarmListService extends IService<AlarmList> {

    IPage<AlarmList> pagelist(Page<AlarmList> page, AlarmListVo vo);
}
