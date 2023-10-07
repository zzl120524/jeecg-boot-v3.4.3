package com.sdt.omap.utils;


import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.omg.CORBA.SystemException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;

/**
 * The type Excel utils.
 *
 * @program: ExcelUtils
 * @description:
 */
public class ExcelUtils {

    /**
     * Export excel.
     *
     * @param list           the list
     * @param title          the title
     * @param sheetName      the sheet name
     * @param pojoClass      the pojo class
     * @param fileName       the file name
     * @param isCreateHeader the is create header
     * @param response       the response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,
                                   String fileName, boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }

    /**
     * Export excel.
     *
     * @param list      the list
     * @param title     the title
     * @param sheetName the sheet name
     * @param excelType the excel type
     * @param pojoClass the pojo class
     * @param fileName  the file name
     * @param response  the response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, ExcelType excelType, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response) {
        defaultExport(list,
                pojoClass,
                fileName,
                response,
                new ExportParams(title, sheetName, excelType));
    }

    /**
     * Export excel.
     *
     * @param list     the list
     * @param fileName the file name
     * @param response the response
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName,
                                      HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            //throw new NormalException(e.getMessage());
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * Import excel list.
     *
     * @param <T>        the type parameter
     * @param filePath   the file path
     * @param titleRows  the title rows
     * @param headerRows the header rows
     * @param pojoClass  the pojo class
     * @return the list
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            //throw new NormalException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            //throw new NormalException(e.getMessage());
        }
        return list;
    }

    /**
     * Import excel list.
     *
     * @param <T>        the type parameter
     * @param file       the file
     * @param titleRows  the title rows
     * @param headerRows the header rows
     * @param pojoClass  the pojo class
     * @return the list
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            // throw new NormalException("excel文件不能为空");
        } catch (Exception e) {
            //throw new NormalException(e.getMessage());
            System.out.println(e.getMessage());
        }
        return list;
    }


    /**
     * Gets head rows.
     *
     * @param file    the file
     * @param classes the classes
     * @param maxRow  the max row
     * @return the head rows
     * @description: 获取标题行
     * @param: file
     * @param: classes
     * @param: maxRow 最大行数校验
     * @return: java.lang.Integer
     * @date: 2021 /10/9 16:04
     */
    public static Integer getHeadRows(MultipartFile file, Class<?> classes, int maxRow) throws Exception {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        if (null == sheet) {
            throw new Exception("excel表格未获取到sheet信息");
        }
        int lastRowNum = sheet.getLastRowNum();
        if (lastRowNum <= 0) {
            throw new Exception("未获取到excel表格中的数据");
        }
        //允许有10行以内的误差
        if (lastRowNum > maxRow + 10) {
            throw new Exception("excel表格导入的数据量过大，建议一次性导入的条数不超过" + maxRow + "条。");
        }
        List<String> excelFieldNames = new ArrayList<>();
        Field[] declaredFields = classes.getDeclaredFields();
        for (Field field : declaredFields) {
            Excel annotation = field.getAnnotation(Excel.class);
            if (null != annotation && StringUtils.isNotBlank(annotation.name())) {
                excelFieldNames.add(annotation.name());
            }
        }
        if (CollectionUtil.isEmpty(excelFieldNames)) {
            throw new Exception("传入对象的字段中未获取到带有@Excel注解的字段");
        }
        int start = 0;
        for (int i = 0; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            String cellValue = row.getCell(0).getStringCellValue();
            if (!CollectionUtil.contains(excelFieldNames, cellValue)) {
                continue;
            }
            start = i + 1;
            break;
        }
        if (start == 0) {
            throw new Exception("导入的excel不符合模板规范");
        }
        return start;
    }


    /**
     * Export excel sxssf workbook.
     *
     * @param list          the list
     * @param classes       the classes
     * @param fields        the fields
     * @param updateProcess the update process
     * @return the sxssf workbook
     * @description: excel导出工具类
     * @param: list 要导出的数据
     * @param: classes @Excel注解所在类
     * @param: fields 要导出的字段集合
     * @param: updateProcess 使用Task进行异步导出更新进度使用，可传null
     * @return: org.apache.poi.xssf.streaming.SXSSFWorkbook
     */
    public static SXSSFWorkbook exportExcel(List list, Class<?> classes, List<String> fields, Consumer<Integer> updateProcess) throws Exception {
        if (CollectionUtil.isEmpty(list) || CollectionUtil.isEmpty(fields)) {
            throw new Exception("导出的数据和字段不能为空");
        }
        Field[] declaredFields = classes.getDeclaredFields();
        Map<String, String> fieldKey_fieldNameMap = new HashMap<>();
        Map<String, String> fieldName_fieldKeyMap = new HashMap<>();
        Map<String, Integer> fieldName_RowWidthMap = new HashMap<>();
        for (Field field : declaredFields) {
            Excel annotation = field.getAnnotation(Excel.class);
            if (null == annotation || StringUtils.isBlank(annotation.name())) {
                continue;
            }
            String name = annotation.name();
            String key = field.getName();
            double width = annotation.width();
            if (width == 10) {//默认是10，改完120
                width = 120;
            }
            fieldKey_fieldNameMap.put(key, name);
            fieldName_fieldKeyMap.put(name, key);
            fieldName_RowWidthMap.put(name, (int) width);
        }
        Map<Integer, String> fieldOrderNum_fieldNameMap = new TreeMap<>();
        Integer order = 1;
        for (String field : fields) {
            String fieldName = fieldKey_fieldNameMap.get(field);
            fieldOrderNum_fieldNameMap.put(order++, fieldName);
        }
        if (MapUtils.isEmpty(fieldOrderNum_fieldNameMap)) {
            throw new Exception("传入对象的字段中未获取到要导出的字段");
        }
        SXSSFWorkbook workbook = createExcel(fieldOrderNum_fieldNameMap, fieldName_RowWidthMap);
        Sheet sheet = workbook.getSheetAt(0);
        Font contentFont = getContentFont(workbook);
        CellStyle rowStyle = getRowStyle(workbook, contentFont);
        Integer rowNum = 1;
        for (int i = 0; i < list.size(); i++) {
            try {
                if (null != updateProcess) {
                    updateProcess.accept(i);
                }
                Object obj = list.get(i);
                Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(obj), Map.class);
                Row row = sheet.createRow(rowNum++);
                for (Map.Entry<Integer, String> entry : fieldOrderNum_fieldNameMap.entrySet()) {
                    Integer orderNum = entry.getKey();
                    String fieldName = entry.getValue();
                    String fieldKey = fieldName_fieldKeyMap.get(fieldName);
                    Object value = map.get(fieldKey);
                    if (null == value) {
                        value = "";
                    }
                    setCellValue(row, orderNum - 1, value.toString(), rowStyle);
                }
            } catch (CancellationException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    /**
     * @description: 创建导出excel模板
     * @param: fieldOrderNum_fieldNameMap
     * @param: fieldName_RowWidthMap
     * @return: org.apache.poi.xssf.streaming.SXSSFWorkbook
     */
    private static SXSSFWorkbook createExcel(Map<Integer, String> fieldOrderNum_fieldNameMap, Map<String, Integer> fieldName_RowWidthMap) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        //第一行样式
        Font titleFont = getTitleFont(workbook);
        CellStyle firstRowStyle = getFirstRowStyle(workbook, titleFont);
        Sheet sheet = workbook.createSheet();
        //添加列名
        Row firstRow = sheet.createRow(0);
        for (Map.Entry<Integer, String> entry : fieldOrderNum_fieldNameMap.entrySet()) {
            Integer orderNum = entry.getKey();
            String fieldName = entry.getValue();
            Cell firstRowCell = firstRow.createCell(orderNum - 1);
            firstRowCell.setCellStyle(firstRowStyle);
            Integer width = fieldName_RowWidthMap.get(fieldName);
            if (null == width || width == 0) {
                width = 120;
            }
            //设置列宽，第一列设置即可
            sheet.setColumnWidth(firstRowCell.getColumnIndex(), width * 50);
            firstRowCell.setCellValue(fieldName);
        }
        return workbook;
    }


    /**
     * @description: 获取标题的字体
     * @param: workbook
     * @return: org.apache.poi.ss.usermodel.Font
     */
    private static Font getTitleFont(SXSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("微软雅黑");
        return font;
    }

    /**
     * @description: 获取内容的字体
     * @param: workbook
     * @return: org.apache.poi.ss.usermodel.Font
     */
    private static Font getContentFont(SXSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("宋体");
        return font;
    }


    /**
     * @description: 获取第一行的样式，背景色为黄色，居中，加边框
     * @param: workbook
     * @param: font
     * @return: org.apache.poi.ss.usermodel.CellStyle
     */
    private static CellStyle getFirstRowStyle(SXSSFWorkbook workbook, Font font) {
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        return style;
    }

    /**
     * @description: 获取普通单元格样式，居中，加边框
     * @param: workbook
     * @param: font
     * @return: org.apache.poi.ss.usermodel.CellStyle
     */
    private static CellStyle getRowStyle(SXSSFWorkbook workbook, Font font) {
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setWrapText(true);
        return style;
    }


    /**
     * @description: 设置单元格的值
     * @param: row
     * @param: cellNum
     * @param: cellValue
     * @param: cellStyle
     * @return: void
     */
    private static void setCellValue(Row row, Integer cellNum, String cellValue, CellStyle cellStyle) {
        Cell cell = row.createCell(cellNum);
        cell.setCellStyle(cellStyle);
        if (org.apache.commons.lang.StringUtils.isNotBlank(cellValue)) {
            cell.setCellValue(cellValue);
        }
    }

}

