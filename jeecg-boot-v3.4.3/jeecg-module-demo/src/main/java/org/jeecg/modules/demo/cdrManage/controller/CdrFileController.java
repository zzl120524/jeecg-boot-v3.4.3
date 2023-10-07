package org.jeecg.modules.demo.cdrManage.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.cdrManage.entity.CdrFile;
import org.jeecg.modules.demo.cdrManage.entity.PageData;
import org.jeecg.modules.demo.cdrManage.service.ICdrFileService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: CDR文件列表
 * @Author: jeecg-boot
 * @Date:   2023-09-01
 * @Version: V1.0
 */
@Api(tags="CDR文件列表")
@RestController
@RequestMapping("/cdrFile/cdrFile")
@Slf4j
public class CdrFileController extends JeecgController<CdrFile, ICdrFileService> {
	@Autowired
	private ICdrFileService cdrFileService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cdrFile
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "CDR文件列表-分页列表查询")
	@ApiOperation(value="CDR文件列表-分页列表查询", notes="CDR文件列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CdrFile>> queryPageList(CdrFile cdrFile,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CdrFile> queryWrapper = QueryGenerator.initQueryWrapper(cdrFile, req.getParameterMap());
		Page<CdrFile> page = new Page<CdrFile>(pageNo, pageSize);
		IPage<CdrFile> pageList = cdrFileService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 @ApiOperation(value="CDR文件列表-分页列表查询", notes="CDR文件列表-分页列表查询")
	 @PostMapping (value = "/pageList")
	 public Result<IPage<CdrFile>> pageList(@RequestBody PageData pageData) {

		 Page<CdrFile> page = new Page<CdrFile>(pageData.getPageNo(), pageData.getPageSize());

		 IPage<CdrFile> pageList = cdrFileService.pageList(page);
		 return Result.OK(pageList);
	 }

	 @ApiOperation(value="CDR文件列表-未导入", notes="CDR文件列表-未导入")
	 @PostMapping (value = "/cdrList")
	 public Result<IPage<CdrFile>> cdrList(@RequestBody PageData pageData) {

		 Page<CdrFile> page = new Page<CdrFile>(pageData.getPageNo(), pageData.getPageSize());

		 IPage<CdrFile> pageList = cdrFileService.cdrList(page);
		 return Result.OK(pageList);
	 }
	
	/**
	 *   添加
	 *
	 * @param cdrFile
	 * @return
	 */
	@AutoLog(value = "CDR文件列表-添加")
	@ApiOperation(value="CDR文件列表-添加", notes="CDR文件列表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_file:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CdrFile cdrFile) {
		cdrFileService.save(cdrFile);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cdrFile
	 * @return
	 */
	@AutoLog(value = "CDR文件列表-编辑")
	@ApiOperation(value="CDR文件列表-编辑", notes="CDR文件列表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_file:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CdrFile cdrFile) {
		cdrFileService.updateById(cdrFile);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "CDR文件列表-通过id删除")
	@ApiOperation(value="CDR文件列表-通过id删除", notes="CDR文件列表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_file:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		cdrFileService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "CDR文件列表-批量删除")
	@ApiOperation(value="CDR文件列表-批量删除", notes="CDR文件列表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_file:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cdrFileService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "CDR文件列表-通过id查询")
	@ApiOperation(value="CDR文件列表-通过id查询", notes="CDR文件列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CdrFile> queryById(@RequestParam(name="id",required=true) String id) {
		CdrFile cdrFile = cdrFileService.getById(id);
		if(cdrFile==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cdrFile);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cdrFile
    */
    //@RequiresPermissions("org.jeecg.modules.demo:cdr_file:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CdrFile cdrFile) {
        return super.exportXls(request, cdrFile, CdrFile.class, "CDR文件列表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("cdr_file:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CdrFile.class);
    }

}
