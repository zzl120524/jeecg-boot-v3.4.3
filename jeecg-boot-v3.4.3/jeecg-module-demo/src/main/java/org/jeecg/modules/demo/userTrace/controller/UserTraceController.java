package org.jeecg.modules.demo.userTrace.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.userTrace.entity.StrategyDistribution;
import org.jeecg.modules.demo.userTrace.entity.UserTrace;
import org.jeecg.modules.demo.userTrace.service.IUserTraceService;

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
 * @Description: 用户追踪
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Api(tags="用户追踪")
@RestController
@RequestMapping("/userTrace/userTrace")
@Slf4j
public class UserTraceController extends JeecgController<UserTrace, IUserTraceService> {
	@Autowired
	private IUserTraceService userTraceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param userTrace
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "用户追踪-分页列表查询")
	@ApiOperation(value="用户追踪-分页列表查询", notes="用户追踪-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<UserTrace>> queryPageList(UserTrace userTrace,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<UserTrace> queryWrapper = QueryGenerator.initQueryWrapper(userTrace, req.getParameterMap());
		Page<UserTrace> page = new Page<UserTrace>(pageNo, pageSize);
		IPage<UserTrace> pageList = userTraceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param userTrace
	 * @return
	 */
	@AutoLog(value = "用户追踪-添加")
	@ApiOperation(value="用户追踪-添加", notes="用户追踪-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:user_trace:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody UserTrace userTrace) {
		userTraceService.save(userTrace);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param userTrace
	 * @return
	 */
	@AutoLog(value = "用户追踪-编辑")
	@ApiOperation(value="用户追踪-编辑", notes="用户追踪-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:user_trace:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody UserTrace userTrace) {
		userTraceService.updateById(userTrace);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户追踪-通过id删除")
	@ApiOperation(value="用户追踪-通过id删除", notes="用户追踪-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:user_trace:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		userTraceService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户追踪-批量删除")
	@ApiOperation(value="用户追踪-批量删除", notes="用户追踪-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:user_trace:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.userTraceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "用户追踪-通过id查询")
	@ApiOperation(value="用户追踪-通过id查询", notes="用户追踪-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<UserTrace> queryById(@RequestParam(name="id",required=true) String id) {
		UserTrace userTrace = userTraceService.getById(id);
		if(userTrace==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(userTrace);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param userTrace
    */
    //@RequiresPermissions("org.jeecg.modules.demo:user_trace:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, UserTrace userTrace) {
        return super.exportXls(request, userTrace, UserTrace.class, "用户追踪");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("user_trace:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, UserTrace.class);
    }

	 @AutoLog(value = "用户追踪-设置用户")
	 @ApiOperation(value="用户追踪-设置用户", notes="用户追踪-设置用户")
	 //@RequiresPermissions("org.jeecg.modules.demo:user_trace:add")
	 @PostMapping(value = "/setTraceUser")
	 public Result<String> setTrace(@RequestBody UserTrace userTrace) {
		 JSONObject json = userTraceService.setTrace(userTrace);

		 String message = json.getString("message");
		 if (2 == json.getIntValue("code")){
			 return Result.error(888,message);
		 }
		 return Result.OK(message);
	 }

	 @AutoLog(value = "用户追踪-开始跟踪")
	 @ApiOperation(value="用户追踪-开始跟踪", notes="用户追踪-开始跟踪")
	 //@RequiresPermissions("org.jeecg.modules.demo:user_trace:add")
	 @PostMapping(value = "/listTraceUser")
	 public Result<String> listTraceUser() throws Exception {
		 userTraceService.listTraceUser();

		 return Result.OK("开始跟踪！");
	 }

	 @AutoLog(value = "用户追踪-策略下发")
	 @ApiOperation(value="用户追踪-策略下发", notes="用户追踪-策略下发")
	 @PostMapping(value = "/strategyDistribution")
	 public Result<String> strategyDistribution(@RequestBody StrategyDistribution strategyDistribution) {
		 JSONObject json = userTraceService.strategyDistribution(strategyDistribution);
		 String message = json.getString("message");
		 if (2 == json.getIntValue("code")){
			 return Result.error(888,message);
		 }
		 return Result.OK(message);
	 }

}
