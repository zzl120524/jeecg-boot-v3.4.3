package org.jeecg.modules.demo.workLog.controller;

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
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.entity.WorkLogVo;
import org.jeecg.modules.demo.workLog.service.IWorkLogService;

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
 * @Description: 业务的日志
 * @Author: jeecg-boot
 * @Date:   2023-08-14
 * @Version: V1.0
 */
@Api(tags="业务的日志")
@RestController
@RequestMapping("/workLog/workLog")
@Slf4j
public class WorkLogController extends JeecgController<WorkLog, IWorkLogService> {
	@Autowired
	private IWorkLogService workLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param workLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "业务的日志-分页列表查询")
	@ApiOperation(value="业务的日志-分页列表查询", notes="业务的日志-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WorkLog>> queryPageList(WorkLog workLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WorkLog> queryWrapper = QueryGenerator.initQueryWrapper(workLog, req.getParameterMap());
		Page<WorkLog> page = new Page<WorkLog>(pageNo, pageSize);
		IPage<WorkLog> pageList = workLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 @ApiOperation(value="业务的日志-分页条件列表查询", notes="业务的日志-分页条件列表查询")
	 @PostMapping(value = "/pagelist")
	 public Result<IPage<WorkLog>> pagelist(@RequestBody WorkLogVo vo) {

		 Page<WorkLog> page = new Page<WorkLog>(vo.getPageNo(), vo.getPageSize());
		 IPage<WorkLog> pageList = workLogService.pagelist(page, vo);
		 return Result.OK(pageList);
	 }
	
	/**
	 *   添加
	 *
	 * @param workLog
	 * @return
	 */
	@AutoLog(value = "业务的日志-添加")
	@ApiOperation(value="业务的日志-添加", notes="业务的日志-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:work_log:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody WorkLog workLog) {
		workLogService.save(workLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param workLog
	 * @return
	 */
	@AutoLog(value = "业务的日志-编辑")
	@ApiOperation(value="业务的日志-编辑", notes="业务的日志-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:work_log:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody WorkLog workLog) {
		workLogService.updateById(workLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务的日志-通过id删除")
	@ApiOperation(value="业务的日志-通过id删除", notes="业务的日志-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:work_log:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		workLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "业务的日志-批量删除")
	@ApiOperation(value="业务的日志-批量删除", notes="业务的日志-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:work_log:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.workLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "业务的日志-通过id查询")
	@ApiOperation(value="业务的日志-通过id查询", notes="业务的日志-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WorkLog> queryById(@RequestParam(name="id",required=true) String id) {
		WorkLog workLog = workLogService.getById(id);
		if(workLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(workLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param workLog
    */
    //@RequiresPermissions("org.jeecg.modules.demo:work_log:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WorkLog workLog) {
        return super.exportXls(request, workLog, WorkLog.class, "业务的日志");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("work_log:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, WorkLog.class);
    }

}
