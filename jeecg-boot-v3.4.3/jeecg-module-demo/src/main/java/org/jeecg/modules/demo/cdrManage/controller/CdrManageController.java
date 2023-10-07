package org.jeecg.modules.demo.cdrManage.controller;

import java.io.File;
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
import org.jeecg.modules.demo.cdrManage.entity.CdrManage;
import org.jeecg.modules.demo.cdrManage.entity.CdrManageVo;
import org.jeecg.modules.demo.cdrManage.entity.InfoData;
import org.jeecg.modules.demo.cdrManage.service.ICdrManageService;

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
 * @Description: CDR文件管理
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Api(tags="CDR文件管理")
@RestController
@RequestMapping("/cdrManage/cdrManage")
@Slf4j
public class CdrManageController extends JeecgController<CdrManage, ICdrManageService> {
	@Autowired
	private ICdrManageService cdrManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cdrManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "CDR文件管理-分页列表查询")
	@ApiOperation(value="CDR文件管理-分页列表查询", notes="CDR文件管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CdrManage>> queryPageList(CdrManage cdrManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CdrManage> queryWrapper = QueryGenerator.initQueryWrapper(cdrManage, req.getParameterMap());
		Page<CdrManage> page = new Page<CdrManage>(pageNo, pageSize);
		IPage<CdrManage> pageList = cdrManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 @ApiOperation(value="CDR文件管理-分页条件列表查询", notes="CDR文件管理-分页条件列表查询")
	 @PostMapping(value = "/pageList")
	 public Result<IPage<CdrManage>> pageList(@RequestBody CdrManageVo vo) {

		 Page<CdrManage> page = new Page<CdrManage>(vo.getPageNo(), vo.getPageSize());
		 IPage<CdrManage> pageList = cdrManageService.pageList(page, vo);
		 return Result.OK(pageList);
	 }
	
	/**
	 *   添加
	 *
	 * @param cdrManage
	 * @return
	 */
	@AutoLog(value = "CDR文件管理-添加")
	@ApiOperation(value="CDR文件管理-添加", notes="CDR文件管理-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_manage:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CdrManage cdrManage) {
		cdrManageService.save(cdrManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cdrManage
	 * @return
	 */
	@AutoLog(value = "CDR文件管理-编辑")
	@ApiOperation(value="CDR文件管理-编辑", notes="CDR文件管理-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_manage:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CdrManage cdrManage) {
		cdrManageService.updateById(cdrManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "CDR文件管理-通过id删除")
	@ApiOperation(value="CDR文件管理-通过id删除", notes="CDR文件管理-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_manage:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		cdrManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "CDR文件管理-批量删除")
	@ApiOperation(value="CDR文件管理-批量删除", notes="CDR文件管理-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:cdr_manage:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cdrManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "CDR文件管理-通过id查询")
	@ApiOperation(value="CDR文件管理-通过id查询", notes="CDR文件管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CdrManage> queryById(@RequestParam(name="id",required=true) String id) {
		CdrManage cdrManage = cdrManageService.getById(id);
		if(cdrManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cdrManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cdrManage
    */
    //@RequiresPermissions("org.jeecg.modules.demo:cdr_manage:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CdrManage cdrManage) {
        return super.exportXls(request, cdrManage, CdrManage.class, "CDR文件管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("cdr_manage:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {

        return super.importExcel(request, response, CdrManage.class);
    }

	 @AutoLog(value = "CDR文件上传-读取路径下单个文件")
	 @ApiOperation(value="CDR文件上传-读取路径下单个文件", notes="CDR文件上传-读取路径下单个文件")
	 @PostMapping(value = "/read")
	 public Result<String> read(String filePath) throws Exception {
		 File f = new File(filePath);

		 cdrManageService.analysis(f);
		 return Result.OK("添加成功");
	 }

	 @AutoLog(value = "CDR文件上传-文件名为列表")
	 @ApiOperation(value="CDR文件上传-文件名为列表", notes="CDR文件上传-文件名为列表")
	 @PostMapping(value = "/readFile")
	 public Result<String> readFile(String filePath) throws Exception {
		 File f = new File(filePath);

		 cdrManageService.readFile(f);
		 return Result.OK("添加成功");
	 }

	 @AutoLog(value = "根据CDR文件主键ID获取CDR记录")
	 @ApiOperation(value="根据CDR文件主键ID获取CDR记录", notes="根据CDR文件主键ID获取CDR记录")
	 @PostMapping (value = "/getFileById")
	 public Result<List<CdrManage>> getFileById(@RequestBody InfoData data) throws Exception {
		 List<CdrManage> files = cdrManageService.getFileById(data.getId());
		 return Result.OK(files);
	 }

	 @AutoLog(value = "读取当前文件")
	 @ApiOperation(value="读取当前文件", notes="读取当前文件")
	 @RequestMapping(value="/readThisFile", method= RequestMethod.POST)
	 public Result<List<CdrManage>> readThisFile(@RequestParam(value = "file", required = true)MultipartFile  file,HttpServletRequest request) throws Exception {
		 String originalFilename = file.getOriginalFilename();
		 File tempFile = new File(originalFilename);

		 List<CdrManage> cdrManages = cdrManageService.readThisFile(tempFile);
		 return Result.OK(cdrManages);
	 }

	 @AutoLog(value = "读取CDR文件-手动触发")
	 @ApiOperation(value="读取CDR文件-手动触发", notes="读取CDR文件-手动触发")
	 @GetMapping(value="/handReadCdr")
	 public Result<String> handReadCdr() throws Exception {
		 cdrManageService.handReadCdr(1);
		 return Result.OK("导入成功");
	 }

}
