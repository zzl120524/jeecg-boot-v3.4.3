package org.jeecg.modules.demo.alarmList.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.alarmList.entity.AlarmList;
import org.jeecg.modules.demo.alarmList.entity.AlarmListVo;
import org.jeecg.modules.demo.alarmList.mapper.AlarmListMapper;
import org.jeecg.modules.demo.alarmList.service.IAlarmListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 告警列表
 * @Author: jeecg-boot
 * @Date:   2023-09-10
 * @Version: V1.0
 */
@Service
public class AlarmListServiceImpl extends ServiceImpl<AlarmListMapper, AlarmList> implements IAlarmListService {

    @Autowired
    private AlarmListMapper alarmListMapper;
    @Override
    public IPage<AlarmList> pagelist(Page<AlarmList> page, AlarmListVo vo) {

        return alarmListMapper.pagelist(page, vo);
    }
}
