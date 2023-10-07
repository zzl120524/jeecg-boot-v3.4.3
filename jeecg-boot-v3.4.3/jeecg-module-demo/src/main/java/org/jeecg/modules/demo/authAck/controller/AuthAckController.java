package org.jeecg.modules.demo.authAck.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.authAck.entity.AuthAckVo;
import org.jeecg.modules.demo.authAck.service.IAuthAckService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 授权确认
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
@Api(tags="授权确认")
@RestController
@RequestMapping("/authAck/authAck")
@Slf4j
public class AuthAckController extends JeecgController<AuthAckVo, IAuthAckService> {
	@Autowired
	private IAuthAckService authAckService;
	
	/**
	 * 分页列表查询
	 *
	 * @param authAck
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "授权确认-分页列表查询")
	@ApiOperation(value="授权确认-分页列表查询", notes="授权确认-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AuthAckVo>> queryPageList(AuthAckVo authAck,
												  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												  HttpServletRequest req) {
		QueryWrapper<AuthAckVo> queryWrapper = QueryGenerator.initQueryWrapper(authAck, req.getParameterMap());
		Page<AuthAckVo> page = new Page<AuthAckVo>(pageNo, pageSize);
		IPage<AuthAckVo> pageList = authAckService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 @ApiOperation(value="授权确认-列表查询", notes="授权确认-列表查询")
	 @GetMapping(value = "/queryList")
	 public Result<List<JSONObject>> queryList(){
		 List<JSONObject> jsons = authAckService.queryList();
		 return Result.OK(jsons);
	 }
	
	/**
	 *   添加
	 *
	 * @param authAck
	 * @return
	 */
	@AutoLog(value = "授权确认-添加")
	@ApiOperation(value="授权确认-添加", notes="授权确认-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:auth_ack:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AuthAckVo authAck) {
		authAckService.save(authAck);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param authAck
	 * @return
	 */
	@AutoLog(value = "授权确认-编辑")
	@ApiOperation(value="授权确认-编辑", notes="授权确认-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:auth_ack:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AuthAckVo authAck) {
		authAckService.edit(authAck);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "授权确认-通过id删除")
	@ApiOperation(value="授权确认-通过id删除", notes="授权确认-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:auth_ack:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		authAckService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "授权确认-批量删除")
	@ApiOperation(value="授权确认-批量删除", notes="授权确认-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:auth_ack:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.authAckService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "授权确认-通过id查询")
	@ApiOperation(value="授权确认-通过id查询", notes="授权确认-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AuthAckVo> queryById(@RequestParam(name="id",required=true) String id) {
		AuthAckVo authAck = authAckService.getById(id);
		if(authAck==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(authAck);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param authAck
    */
    //@RequiresPermissions("org.jeecg.modules.demo:auth_ack:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AuthAckVo authAck) {
        return super.exportXls(request, authAck, AuthAckVo.class, "授权确认");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("auth_ack:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AuthAckVo.class);
    }

}
