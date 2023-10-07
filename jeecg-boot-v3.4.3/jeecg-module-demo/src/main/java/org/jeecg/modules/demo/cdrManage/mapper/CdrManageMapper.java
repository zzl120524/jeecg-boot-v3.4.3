package org.jeecg.modules.demo.cdrManage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.cdrManage.entity.CdrManage;
import org.jeecg.modules.demo.cdrManage.entity.CdrManageVo;

/**
 * @Description: CDR文件管理
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface CdrManageMapper extends BaseMapper<CdrManage> {

    IPage<CdrManage> pageList(Page<CdrManage> page, @Param("param")CdrManageVo param);
}
