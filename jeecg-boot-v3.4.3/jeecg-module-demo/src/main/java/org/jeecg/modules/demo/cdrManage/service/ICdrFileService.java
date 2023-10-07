package org.jeecg.modules.demo.cdrManage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.cdrManage.entity.CdrFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: CDR文件列表
 * @Author: jeecg-boot
 * @Date:   2023-09-01
 * @Version: V1.0
 */
public interface ICdrFileService extends IService<CdrFile> {

    IPage<CdrFile> pageList(Page<CdrFile> page);

    IPage<CdrFile> cdrList(Page<CdrFile> page);
}
