package org.jeecg.modules.demo.cdrManage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.cdrManage.entity.CdrFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: CDR文件列表
 * @Author: jeecg-boot
 * @Date:   2023-09-01
 * @Version: V1.0
 */
public interface CdrFileMapper extends BaseMapper<CdrFile> {

    IPage<CdrFile> pageList(Page<CdrFile> page);
}
