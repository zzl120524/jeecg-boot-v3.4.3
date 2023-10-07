package org.jeecg.modules.demo.facTable.controller;

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
import org.jeecg.modules.demo.facTable.entity.FacTable;
import org.jeecg.modules.demo.facTable.service.IFacTableService;

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
 * @Description: fac列表
 * @Author: jeecg-boot
 * @Date:   2023-08-15
 * @Version: V1.0
 */
@Api(tags="fac列表")
@RestController
@RequestMapping("/facTable/facTable")
@Slf4j
public class FacTableController extends JeecgController<FacTable, IFacTableService> {
	@Autowired
	private IFacTableService facTableService;
	
	/**
	 * 分页列表查询
	 *
	 * @param facTable
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "fac列表-分页列表查询")
	@ApiOperation(value="fac列表-分页列表查询", notes="fac列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FacTable>> queryPageList(FacTable facTable,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FacTable> queryWrapper = QueryGenerator.initQueryWrapper(facTable, req.getParameterMap());
		Page<FacTable> page = new Page<FacTable>(pageNo, pageSize);
		IPage<FacTable> pageList = facTableService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param facTable
	 * @return
	 */
	@AutoLog(value = "fac列表-添加")
	@ApiOperation(value="fac列表-添加", notes="fac列表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_table:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FacTable facTable) {
		facTableService.save(facTable);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param facTable
	 * @return
	 */
	@AutoLog(value = "fac列表-编辑")
	@ApiOperation(value="fac列表-编辑", notes="fac列表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_table:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FacTable facTable) {
		facTableService.updateById(facTable);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "fac列表-通过id删除")
	@ApiOperation(value="fac列表-通过id删除", notes="fac列表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_table:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		facTableService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "fac列表-批量删除")
	@ApiOperation(value="fac列表-批量删除", notes="fac列表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_table:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.facTableService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "fac列表-通过id查询")
	@ApiOperation(value="fac列表-通过id查询", notes="fac列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FacTable> queryById(@RequestParam(name="id",required=true) String id) {
		FacTable facTable = facTableService.getById(id);
		if(facTable==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(facTable);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param facTable
    */
    //@RequiresPermissions("org.jeecg.modules.demo:fac_table:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FacTable facTable) {
        return super.exportXls(request, facTable, FacTable.class, "fac列表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("fac_table:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FacTable.class);
    }

}
