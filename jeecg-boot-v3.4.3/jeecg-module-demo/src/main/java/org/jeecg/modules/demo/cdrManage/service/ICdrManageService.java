package org.jeecg.modules.demo.cdrManage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.cdrManage.entity.CdrManage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.cdrManage.entity.CdrManageVo;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Description: CDR文件管理
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface ICdrManageService extends IService<CdrManage> {

    void analysis(File f) throws Exception;

    void readFile(File f) throws Exception;

    List<CdrManage> getFileById(String id);

    List<CdrManage> readThisFile(File f) throws Exception;

    List<CdrManage> cdrAnalysis(File tempFile)throws Exception;

    IPage<CdrManage> pageList(Page<CdrManage> page, CdrManageVo vo);

    void handReadCdr(int count);

    List<CdrManage>  importCdr(HttpServletRequest request);
}
