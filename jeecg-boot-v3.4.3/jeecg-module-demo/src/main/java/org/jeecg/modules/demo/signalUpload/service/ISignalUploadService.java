package org.jeecg.modules.demo.signalUpload.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.signalUpload.entity.SignalUploadVo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 信令上传
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
public interface ISignalUploadService extends IService<SignalUpload> {

    IPage<SignalUpload> pageList(Page<SignalUpload> page, SignalUploadVo signalUpload);

    IPage<SignalUpload> getCdrByRelationId(Page<SignalUpload> page, String id);

    IPage<SignalUpload> currentUser(Page<SignalUpload> page, SignalUploadVo signalUpload);

//    ModelAndView export(HttpServletRequest request, SignalUploadVo signalUpload, Class<SignalUpload> signalUploadClass, String title);

    List<SignalUpload> export(HttpServletRequest request, SignalUploadVo signalUpload, Class<SignalUpload> signalUploadClass, String title);

    XSSFWorkbook exportExcel( SignalUploadVo signalUpload, Class<SignalUpload> signalUploadClass, String title);

    void exportXls(HttpServletResponse response,HttpServletRequest request, SignalUploadVo signalUpload, Class<SignalUpload> signalUploadClass, String title) throws IOException;
}
