package org.jeecg.modules.demo.cdrManage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.modules.demo.cdrManage.entity.CdrFile;
import org.jeecg.modules.demo.cdrManage.mapper.CdrFileMapper;
import org.jeecg.modules.demo.cdrManage.service.ICdrFileService;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description: CDR文件列表
 * @Author: jeecg-boot
 * @Date: 2023-09-01
 * @Version: V1.0
 */
@Service
public class CdrFileServiceImpl extends ServiceImpl<CdrFileMapper, CdrFile> implements ICdrFileService {
    @Value("${ftpPath}")
    private String path;

    @Autowired
    private WorkLogMapper workLogMapper;

    @Override
    public IPage<CdrFile> pageList(Page<CdrFile> page) {

        return this.baseMapper.pageList(page);
    }

    @Override
    public IPage<CdrFile> cdrList(Page<CdrFile> page) {
        WorkLog workLog = new WorkLog();
        workLog.setOperating("查询未导入CDR文件列表");
        System.out.println("ftpPath:"+path);
        List<CdrFile> cdrFiles = new ArrayList<>();
        try {
            File directory = new File(path);
            File[] ftpFiles = directory.listFiles();
            if (ftpFiles.length > 0 ){
                for (int j = 0; j < ftpFiles.length; j++) {
                    File ftpFile = ftpFiles[j];
                    if (!ftpFile.getName().endsWith("cdr")) {
                        continue;
                    }
                    CdrFile cdrFile = new CdrFile();
                    cdrFile.setId(UUID.randomUUID().toString());
                    cdrFile.setCdrFileName(ftpFile.getName());
                    cdrFile.setStatus("未导入");
                    cdrFiles.add(cdrFile);
                }
            }
            workLog.setOperatingResult("1");
        } catch (Exception e) {
            e.printStackTrace();
            workLog.setOperatingResult("0");
        }
        page.setTotal(cdrFiles.size());
        workLogMapper.insert(workLog);
        return page.setRecords(cdrFiles);
    }
}
