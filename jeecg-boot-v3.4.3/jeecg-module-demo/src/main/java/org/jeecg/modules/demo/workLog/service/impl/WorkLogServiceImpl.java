package org.jeecg.modules.demo.workLog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.entity.WorkLogVo;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;
import org.jeecg.modules.demo.workLog.service.IWorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 业务的日志
 * @Author: jeecg-boot
 * @Date:   2023-08-14
 * @Version: V1.0
 */
@Service
public class WorkLogServiceImpl extends ServiceImpl<WorkLogMapper, WorkLog> implements IWorkLogService {

    @Autowired
    private WorkLogMapper workLogMapper;

    @Override
    public IPage<WorkLog> pagelist(Page<WorkLog> page, WorkLogVo workLog) {
        Page<WorkLog> pagelist = workLogMapper.pagelist(page, workLog);
        return pagelist;
    }
}
