package org.jeecg.modules.demo.alarmList.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.alarmList.entity.AlarmList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.alarmList.entity.AlarmListVo;

/**
 * @Description: 告警列表
 * @Author: jeecg-boot
 * @Date:   2023-09-10
 * @Version: V1.0
 */
public interface AlarmListMapper extends BaseMapper<AlarmList> {

    Page<AlarmList> pagelist(Page<AlarmList> page, @Param("param")AlarmListVo vo);
}
