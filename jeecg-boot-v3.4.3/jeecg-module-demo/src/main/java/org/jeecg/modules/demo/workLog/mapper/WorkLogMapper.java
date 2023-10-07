package org.jeecg.modules.demo.workLog.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.workLog.entity.WorkLogVo;

/**
 * @Description: 业务的日志
 * @Author: jeecg-boot
 * @Date:   2023-08-14
 * @Version: V1.0
 */
public interface WorkLogMapper extends BaseMapper<WorkLog> {

    Page<WorkLog> pagelist(Page<WorkLog> page,@Param("param") WorkLogVo param);

}
