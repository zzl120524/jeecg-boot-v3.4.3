package org.jeecg.modules.demo.signalUpload.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdt.omap.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.cdrManage.entity.CdrManage;
import org.jeecg.modules.demo.cdrManage.entity.InfoData;
import org.jeecg.modules.demo.cdrManage.service.ICdrManageService;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;
import org.jeecg.modules.demo.signalUpload.entity.SignalUploadVo;
import org.jeecg.modules.demo.signalUpload.service.ISignalUploadService;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description: 信令上传
 * @Author: jeecg-boot
 * @Date: 2023-08-21
 * @Version: V1.0
 */
@Api(tags = "信令上传")
@RestController
@RequestMapping("/signalUpload/signalUpload")
@Slf4j
public class SignalUploadController extends JeecgController<SignalUpload, ISignalUploadService> {
    @Autowired
    private ISignalUploadService signalUploadService;

    @Autowired
    private ICdrManageService cdrManageService;

    /**
     * 分页列表查询
     *
     * @param signalUpload
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "信令上传-分页列表查询")
    @ApiOperation(value = "信令上传-分页列表查询", notes = "信令上传-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<SignalUpload>> queryPageList(SignalUpload signalUpload,
                                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                     HttpServletRequest req) {
        QueryWrapper<SignalUpload> queryWrapper = QueryGenerator.initQueryWrapper(signalUpload, req.getParameterMap());
        Page<SignalUpload> page = new Page<SignalUpload>(pageNo, pageSize);
        IPage<SignalUpload> pageList = signalUploadService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    @ApiOperation(value = "信令上传-条件分页列表查询", notes = "信令上传-条件分页列表查询")
    @PostMapping(value = "/pageList")
    public Result<IPage<SignalUpload>> pageList(@RequestBody SignalUploadVo signalUpload) {

        Page<SignalUpload> page = new Page<SignalUpload>(signalUpload.getPageNo(), signalUpload.getPageSize());
        IPage<SignalUpload> pageList = signalUploadService.pageList(page, signalUpload);
        return Result.OK(pageList);
    }

    @ApiOperation(value = "用户追踪-查看追踪用户的信令消息", notes = "用户追踪-查看追踪用户的信令消息")
    @PostMapping(value = "/currentUser")
    public Result<IPage<SignalUpload>> currentUser(@RequestBody SignalUploadVo signalUpload) {

        Page<SignalUpload> page = new Page<SignalUpload>(signalUpload.getPageNo(), signalUpload.getPageSize());
        IPage<SignalUpload> pageList = signalUploadService.currentUser(page, signalUpload);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param signalUpload
     * @return
     */
    @AutoLog(value = "信令上传-添加")
    @ApiOperation(value = "信令上传-添加", notes = "信令上传-添加")
    //@RequiresPermissions("org.jeecg.modules.demo:signal_upload:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody SignalUpload signalUpload) {
        signalUploadService.save(signalUpload);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param signalUpload
     * @return
     */
    @AutoLog(value = "信令上传-编辑")
    @ApiOperation(value = "信令上传-编辑", notes = "信令上传-编辑")
    //@RequiresPermissions("org.jeecg.modules.demo:signal_upload:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody SignalUpload signalUpload) {
        signalUploadService.updateById(signalUpload);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "信令上传-通过id删除")
    @ApiOperation(value = "信令上传-通过id删除", notes = "信令上传-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.demo:signal_upload:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        signalUploadService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "信令上传-批量删除")
    @ApiOperation(value = "信令上传-批量删除", notes = "信令上传-批量删除")
    //@RequiresPermissions("org.jeecg.modules.demo:signal_upload:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.signalUploadService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "信令上传-通过id查询")
    @ApiOperation(value = "信令上传-通过id查询", notes = "信令上传-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<SignalUpload> queryById(@RequestParam(name = "id", required = true) String id) {
        SignalUpload signalUpload = signalUploadService.getById(id);
        if (signalUpload == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(signalUpload);
    }

    /*    */

/*    **
     * 导出excel
     *
     * @param request
     * @param signalUpload
     **/
    //@RequiresPermissions("org.jeecg.modules.demo:signal_upload:exportXls")
    @RequestMapping(value = "/exportXls")
    public void exportXls(HttpServletResponse response,HttpServletRequest request,@RequestBody SignalUploadVo signalUpload) throws IOException {
        signalUploadService.exportXls(response, request,signalUpload, SignalUpload.class, "信令上传");

    }

    /*
     * 导出excel
     *
     * @param request
     * @param signalUpload*/

    //@RequiresPermissions("org.jeecg.modules.demo:signal_upload:exportXls")
    @PostMapping(value = "/export")
    public List<SignalUpload> export(HttpServletRequest request, @RequestBody(required = false) SignalUploadVo signalUpload) {

        return signalUploadService.export(request, signalUpload, SignalUpload.class, "上报信息");
    }

    /*    */

    /**
     * 导出excel
     *
     * @param
     * @param signalUpload
     **/
    //@RequiresPermissions("org.jeecg.modules.demo:signal_upload:exportXls")
 /*   @PostMapping(value = "/export")
    @ApiOperation(value = "信令上传-导出export", notes = "信令上传-导出export")
    public void export(HttpServletRequest request,HttpServletResponse response,@RequestBody SignalUploadVo signalUpload) {
        List<SignalUpload> list = signalUploadService.export(request, signalUpload, SignalUpload.class, "上报信息");

        String fileName = "上报信息"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xlsx";
        ExcelUtils.exportExcel(list, null, null, ExcelType.XSSF, SignalUpload.class, fileName, response);


    }*/
    @PostMapping("exportExcel")
    @ApiOperation(value = "信令上传-导出exportExcel", notes = "信令上传-导出exportExcel")
    public void exportExccelAddMoney(HttpServletResponse response, @RequestBody SignalUploadVo signalUpload) throws IOException {
        XSSFWorkbook xssfSheets = signalUploadService.exportExcel(signalUpload, SignalUpload.class, "上报信息");

        String fileName = "上报信息.xlsx";
        OutputStream outputStream = null;
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            xssfSheets.write(outputStream);
            System.out.println(xssfSheets);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@RequiresPermissions("signal_upload:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        return super.importExcel(request, response, SignalUpload.class);
    }

    /**
     * 通过cdr导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@RequiresPermissions("signal_upload:importExcel")
    @RequestMapping(value = "/importCdr", method = RequestMethod.POST)
    public Result<List<CdrManage>> importCdr(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CdrManage> cdrManages = cdrManageService.importCdr(request);
        return Result.OK(cdrManages);
    }

    @AutoLog(value = "根据relationId获取信令复制报文记录")
    @ApiOperation(value = "根据relationId获取信令复制报文记录", notes = "根据relationId获取信令复制报文记录")
    @PostMapping(value = "/getCdrByRelationId")
    public Result<IPage<SignalUpload>> getCdrByRelationId(@RequestBody InfoData data) throws Exception {
        Page<SignalUpload> page = new Page<SignalUpload>(data.getPageNo(), data.getPageSize());

        IPage<SignalUpload> SignalUploads = signalUploadService.getCdrByRelationId(page, data.getId());
        return Result.OK(SignalUploads);
    }

}
