package org.jeecg.modules.demo.workLog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.workLog.entity.WorkLogVo;

/**
 * @Description: 业务的日志
 * @Author: jeecg-boot
 * @Date:   2023-08-14
 * @Version: V1.0
 */
public interface IWorkLogService extends IService<WorkLog> {

    IPage<WorkLog> pagelist(Page<WorkLog> page, WorkLogVo workLog);
}
