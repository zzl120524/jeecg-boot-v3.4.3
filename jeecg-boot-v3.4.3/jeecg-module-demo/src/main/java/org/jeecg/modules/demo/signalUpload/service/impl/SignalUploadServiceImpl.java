package org.jeecg.modules.demo.signalUpload.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkcoding.http.util.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;
import org.jeecg.modules.demo.signalUpload.entity.SignalUploadVo;
import org.jeecg.modules.demo.signalUpload.mapper.SignalUploadMapper;
import org.jeecg.modules.demo.signalUpload.service.ISignalUploadService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 信令上传
 * @Author: jeecg-boot
 * @Date: 2023-08-21
 * @Version: V1.0
 */
@Service
public class SignalUploadServiceImpl extends ServiceImpl<SignalUploadMapper, SignalUpload> implements ISignalUploadService {

    @Autowired
    private SignalUploadMapper signalUploadMapper;

    @Value("${jeecg.path.upload}")
    private String upLoadPath;

    @Override
    public IPage<SignalUpload> pageList(Page<SignalUpload> page, SignalUploadVo signalUpload) {

        Page<SignalUpload> signalUploadPage = signalUploadMapper.pageList(page, signalUpload);
        return signalUploadPage;
    }

    @Override
    public IPage<SignalUpload> getCdrByRelationId(Page<SignalUpload> page, String id) {
        SignalUploadVo signalUpload = new SignalUploadVo();
        signalUpload.setRelationId(Long.valueOf(id));
        Page<SignalUpload> signalUploadPage = signalUploadMapper.getCdrByRelationId(page, id);
    /*    QueryWrapper<SignalUpload> SignalUploadWrapper = new QueryWrapper<>();
        SignalUploadWrapper.eq("relation_id", id);
        List<SignalUpload> list = this.list(SignalUploadWrapper);*/
        return signalUploadPage;

    }

    @Override
    public IPage<SignalUpload> currentUser(Page<SignalUpload> page, SignalUploadVo signalUpload) {
        Page<SignalUpload> signalUploadPage = signalUploadMapper.currentUser(page, signalUpload);
        return signalUploadPage;
    }

    @Override
    public List<SignalUpload> export(HttpServletRequest request, SignalUploadVo signalUpload, Class<SignalUpload> signalUploadClass, String str) {
        List<SignalUpload> exportList = signalUploadMapper.pageList(signalUpload);


        return exportList;
    }

    @Override
    public XSSFWorkbook exportExcel( SignalUploadVo signalUpload, Class<SignalUpload> signalUploadClass, String title) {
        List<SignalUpload> exportList = signalUploadMapper.pageList(signalUpload);
        XSSFWorkbook xssfSheets = new XSSFWorkbook();

        XSSFSheet userList = xssfSheets.createSheet("上报信息");

        Row titleRow = userList.createRow(0);//创建第一行，起request始为0
        titleRow.createCell(0).setCellValue("FACID");//第一列
        titleRow.createCell(1).setCellValue("上传接口类型");
        titleRow.createCell(2).setCellValue("跟踪用户");
        titleRow.createCell(3).setCellValue("消息类型");
        titleRow.createCell(4).setCellValue("消息名称");
        titleRow.createCell(5).setCellValue("方向");
        titleRow.createCell(6).setCellValue("流方向");
        titleRow.createCell(7).setCellValue("语音编解码类型");
        titleRow.createCell(8).setCellValue("关联ID");
        titleRow.createCell(9).setCellValue("复制报文类型");
        titleRow.createCell(10).setCellValue("异常报文类型");
        titleRow.createCell(11).setCellValue("上报时间");
        titleRow.createCell(12).setCellValue("跟踪信令报文");
        titleRow.createCell(13).setCellValue("RTP报文");
        titleRow.createCell(14).setCellValue("信令复制报文");
        titleRow.createCell(15).setCellValue("异常报文");

        for (int i = 0; i < exportList.size(); i++) {
            Row row = userList.createRow(i + 1);//设置对哪一行操作
            row.createCell(0).setCellValue(exportList.get(i).getFacId());
            row.createCell(1).setCellValue(exportList.get(i).getUploadType());
            if (StrUtil.isNotBlank(exportList.get(i).getTraceUser())){
                row.createCell(2).setCellValue(exportList.get(i).getTraceUser());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getMsgType())){
                row.createCell(3).setCellValue(exportList.get(i).getMsgType());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getMsgName())){
                row.createCell(4).setCellValue(exportList.get(i).getMsgName());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getDirection())){
                row.createCell(5).setCellValue(exportList.get(i).getDirection());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getTrafficeDirection())){
                row.createCell(6).setCellValue(exportList.get(i).getTrafficeDirection());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getCodec())){
                row.createCell(7).setCellValue(exportList.get(i).getCodec());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getRelationId())){
                row.createCell(8).setCellValue(exportList.get(i).getRelationId());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getSignalType())){
                row.createCell(9).setCellValue(exportList.get(i).getSignalType());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getMalformedType())){
                row.createCell(10).setCellValue(exportList.get(i).getMalformedType());
            }
            row.createCell(11).setCellValue(exportList.get(i).getCreateTime());
            if (StrUtil.isNotBlank(exportList.get(i).getSigal())){
                row.createCell(12).setCellValue(exportList.get(i).getSigal());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getRtpPacket())){
                row.createCell(13).setCellValue(exportList.get(i).getRtpPacket());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getSignalPacket())){
                row.createCell(14).setCellValue(exportList.get(i).getSignalPacket());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getPacket())){
                row.createCell(15).setCellValue(exportList.get(i).getPacket());
            }


         /*   if(listBlogVOS.get(i).getNormal()=="0"){
                row.createCell(6).setCellValue("库存正常");
            }else{
                row.createCell(6).setCellValue("库存不正常");
            }*/
        }

        return xssfSheets;
    }

    @Override
    public void exportXls(HttpServletResponse response,HttpServletRequest request, SignalUploadVo signalUpload, Class<SignalUpload> signalUploadClass, String title) throws IOException {


 /*       //判断接收的参数是否为空
        if (!StringUtil.isEmpty(businessNameSimple)){
            //不为空将参数set到对象中去
            businessBaseInfo.setBusinessNameSimple(businessNameSimple);
        }*/


        List<SignalUpload> exportList = signalUploadMapper.pageList(signalUpload);


        //设置导出类型
        response.setContentType("application/x-execl");

        //Content-Disposition就是当用户想把请求所得的内容存为一个文件的时候提供一个默认的文件名
        //设置响应头   attachment表示为附件  并指定文件名称

        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String(("上报信息"+ ".xls").getBytes(), "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream outputStream = response.getOutputStream();

        //导出数据数据生成Excel表格
        exportBusinessBaseInfo(exportList,outputStream);


    }


    public static void exportBusinessBaseInfo(List<SignalUpload> exportList,ServletOutputStream outputStream) throws IOException {
        if (exportList==null){
            return;
        }
        //创建Excel文件
        HSSFWorkbook book=new HSSFWorkbook();
        //创建excel文件中的一个页面
        HSSFSheet sheet = book.createSheet("上报信息");
        //设置默认宽度
        sheet.setDefaultColumnWidth(12);
        //合并单元格
        CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 0, 12);
        //页添加单元格
        sheet.addMergedRegion(rangeAddress);
        //创建第1行
        HSSFRow createRow = sheet.createRow(0);
/*        //创建名称行样式
        HSSFCellStyle style = creatCellStyle(book, (short) 16);
        //创建标题行样式
        HSSFCellStyle style1 = creatCellStyle(book, (short) 11);*/
        //创建第0个单元格
        HSSFCell Cell = createRow.createCell(0);
 /*       //设置单元格的样式
        Cell.setCellStyle(style);*/
        //创建单元格的内容
        if (exportList != null) {
            //for (int i = 0; i < userBaseInfo.size(); i++) {
            Cell.setCellValue("导出上报信息：");
            //}
        }
        //创建第一行单元格
        HSSFRow Row1 = sheet.createRow(1);
        //定义标题行单元格的值
        //例如：
        String[] tille = {"FACID", "上传接口类型", "跟踪用户", "消息类型", "消息名称",
                "方向", "流方向", "语音编解码类型", "关联ID", "复制报文类型",
                "异常报文类型", "上报时间", "跟踪信令报文", "RTP报文", "信令复制报文",
                "异常报文"
        };// 表单字段
        //循环遍历设置题行单元格的值
        for (int i = 0; i < tille.length; i++) {
            HSSFCell createCell = Row1.createCell(i);

            createCell.setCellValue(tille[i]);
        }

        //遍历集合将数据填进表格
        for (int i = 0; i < exportList.size(); i++) {
            SignalUpload signalUpload = exportList.get(i);

            HSSFRow row = sheet.createRow(i + 2); // 标题行和列名 占了两行
            row.createCell(0).setCellValue(signalUpload.getFacId());

            //sql查出的时间需要处理DateFormatUtil.formatToStringTime()
      /*      Row.createCell(1).setCellValue(
                    DateFormatUtil.formatToStringTime(signalUpload.getCreateTime()));*/


            row.createCell(0).setCellValue(exportList.get(i).getFacId());
            row.createCell(1).setCellValue(exportList.get(i).getUploadType());
            if (StrUtil.isNotBlank(exportList.get(i).getTraceUser())){
                row.createCell(2).setCellValue(exportList.get(i).getTraceUser());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getMsgType())){
                row.createCell(3).setCellValue(exportList.get(i).getMsgType());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getMsgName())){
                row.createCell(4).setCellValue(exportList.get(i).getMsgName());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getDirection())){
                row.createCell(5).setCellValue(exportList.get(i).getDirection());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getTrafficeDirection())){
                row.createCell(6).setCellValue(exportList.get(i).getTrafficeDirection());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getCodec())){
                row.createCell(7).setCellValue(exportList.get(i).getCodec());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getRelationId())){
                row.createCell(8).setCellValue(exportList.get(i).getRelationId());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getSignalType())){
                row.createCell(9).setCellValue(exportList.get(i).getSignalType());
            }
            if (ObjectUtil.isNotEmpty(exportList.get(i).getMalformedType())){
                row.createCell(10).setCellValue(exportList.get(i).getMalformedType());
            }
            row.createCell(11).setCellValue(exportList.get(i).getCreateTime());
            if (StrUtil.isNotBlank(exportList.get(i).getSigal())){
                row.createCell(12).setCellValue(exportList.get(i).getSigal());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getRtpPacket())){
                row.createCell(13).setCellValue(exportList.get(i).getRtpPacket());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getSignalPacket())){
                row.createCell(14).setCellValue(exportList.get(i).getSignalPacket());
            }
            if (StrUtil.isNotBlank(exportList.get(i).getPacket())){
                row.createCell(15).setCellValue(exportList.get(i).getPacket());
            }

        }
        try {
            book.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
        }

    }


 /*   @Override
    public ModelAndView export(HttpServletRequest request, SignalUploadVo signalUpload, Class<SignalUpload> clazz, String title) {
*//*        // Step.1 组装查询条件
        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // 过滤选中数据
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id",selectionList);
        }
        // Step.2 获取导出数据
        List<T> exportList = service.list(queryWrapper);*//*
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<SignalUpload> exportList = signalUploadMapper.pageList(signalUpload);


        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.FILE_NAME, title);
        mv.addObject(NormalExcelConstants.CLASS, clazz);
        //update-begin--Author:liusq  Date:20210126 for：图片导出报错，ImageBasePath未设置--------------------
        ExportParams exportParams=new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title);
        exportParams.setImageBasePath(upLoadPath);
        //update-end--Author:liusq  Date:20210126 for：图片导出报错，ImageBasePath未设置----------------------
        mv.addObject(NormalExcelConstants.PARAMS,exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // 过滤选中数据
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id",selectionList);
        }
        // Step.2 获取导出数据
        List<T> exportList = service.list(queryWrapper);*//*
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<SignalUpload> exportList = signalUploadMapper.pageList(signalUpload);


        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.FILE_NAME, title);
        mv.addObject(NormalExcelConstants.CLASS, clazz);
        //update-begin--Author:liusq  Date:20210126 for：图片导出报错，ImageBasePath未设置--------------------
        ExportParams exportParams=new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title);
        exportParams.setImageBasePath(upLoadPath);
        //update-end--Author:liusq  Date:20210126 for：图片导出报错，ImageBasePath未设置----------------------
        mv.addObject(NormalExcelConstants.PARAMS,exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;

    }*/
}

