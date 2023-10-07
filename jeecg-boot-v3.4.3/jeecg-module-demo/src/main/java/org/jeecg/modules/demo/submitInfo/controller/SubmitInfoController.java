package org.jeecg.modules.demo.submitInfo.controller;

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
import org.jeecg.modules.demo.submitInfo.entity.SubmitInfo;
import org.jeecg.modules.demo.submitInfo.service.ISubmitInfoService;

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
 * @Description: 上报信息
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Api(tags="上报信息")
@RestController
@RequestMapping("/submitInfo/submitInfo")
@Slf4j
public class SubmitInfoController extends JeecgController<SubmitInfo, ISubmitInfoService> {
	@Autowired
	private ISubmitInfoService submitInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param submitInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "上报信息-分页列表查询")
	@ApiOperation(value="上报信息-分页列表查询", notes="上报信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SubmitInfo>> queryPageList(SubmitInfo submitInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SubmitInfo> queryWrapper = QueryGenerator.initQueryWrapper(submitInfo, req.getParameterMap());
		Page<SubmitInfo> page = new Page<SubmitInfo>(pageNo, pageSize);
		IPage<SubmitInfo> pageList = submitInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param submitInfo
	 * @return
	 */
	@AutoLog(value = "上报信息-添加")
	@ApiOperation(value="上报信息-添加", notes="上报信息-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:submit_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SubmitInfo submitInfo) {
		submitInfoService.save(submitInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param submitInfo
	 * @return
	 */
	@AutoLog(value = "上报信息-编辑")
	@ApiOperation(value="上报信息-编辑", notes="上报信息-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:submit_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SubmitInfo submitInfo) {
		submitInfoService.updateById(submitInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上报信息-通过id删除")
	@ApiOperation(value="上报信息-通过id删除", notes="上报信息-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:submit_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		submitInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "上报信息-批量删除")
	@ApiOperation(value="上报信息-批量删除", notes="上报信息-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:submit_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.submitInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "上报信息-通过id查询")
	@ApiOperation(value="上报信息-通过id查询", notes="上报信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SubmitInfo> queryById(@RequestParam(name="id",required=true) String id) {
		SubmitInfo submitInfo = submitInfoService.getById(id);
		if(submitInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(submitInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param submitInfo
    */
    //@RequiresPermissions("org.jeecg.modules.demo:submit_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SubmitInfo submitInfo) {
        return super.exportXls(request, submitInfo, SubmitInfo.class, "上报信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("submit_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SubmitInfo.class);
    }

}
