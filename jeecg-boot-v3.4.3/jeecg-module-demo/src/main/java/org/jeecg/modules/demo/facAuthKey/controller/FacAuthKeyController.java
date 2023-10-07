package org.jeecg.modules.demo.facAuthKey.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdt.omap.protocol.auth.AuthReq;
import com.sdt.util.security.Base64Util;
import com.sdt.util.standard.ByteUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.facAuthKey.entity.FacAuthKey;
import org.jeecg.modules.demo.facAuthKey.service.IFacAuthKeyService;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: facId认证秘钥
 * @Author: jeecg-boot
 * @Date:   2023-08-11
 * @Version: V1.0
 */
@Api(tags="facId认证秘钥")
@RestController
@RequestMapping("/facAuthKey/facAuthKey")
@Slf4j
public class FacAuthKeyController extends JeecgController<FacAuthKey, IFacAuthKeyService> {
	@Autowired
	private IFacAuthKeyService facAuthKeyService;

	 @Value("${jeecg.path.text}")
	 private String textPath;
	
	/**
	 * 分页列表查询
	 *
	 * @param facAuthKey
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "facId认证秘钥-分页列表查询")
	@ApiOperation(value="facId认证秘钥-分页列表查询", notes="facId认证秘钥-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FacAuthKey>> queryPageList(FacAuthKey facAuthKey,
													 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													 HttpServletRequest req) throws IOException {
		QueryWrapper<FacAuthKey> queryWrapper = QueryGenerator.initQueryWrapper(facAuthKey, req.getParameterMap());
		Page<FacAuthKey> page = new Page<FacAuthKey>(pageNo, pageSize);
		IPage<FacAuthKey> pageList = facAuthKeyService.page(page, queryWrapper);

		List<FacAuthKey> records = pageList.getRecords();
		for (FacAuthKey record : records) {
			String authKey = ByteUtil.byte2HexString(Base64Util.DecodeString(record.getAuthKey()), true);
			record.setAuthKey(authKey);
		}
		return Result.OK(pageList);
	}

	@ApiOperation(value="facId认证秘钥-分页条件列表查询", notes="facId认证秘钥-分页条件列表查询")
	@PostMapping(value = "/pagelist")
	public Result<IPage<FacAuthKey>> pagelist(@RequestBody FacAuthKey facAuthKey,
										   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) throws IOException {

		Page<FacAuthKey> page = new Page<FacAuthKey>(pageNo, pageSize);
		IPage<FacAuthKey> pageList = facAuthKeyService.pagelist(page, facAuthKey);
		List<FacAuthKey> records = pageList.getRecords();
		for (FacAuthKey record : records) {
			String authKey = ByteUtil.byte2HexString(Base64Util.DecodeString(record.getAuthKey()), true);
			record.setAuthKey(authKey);
		}
		return Result.OK(pageList);
	}

	@ApiOperation(value="facId认证秘钥-分页条件列表查询", notes="facId认证秘钥-分页条件列表查询")
	@GetMapping (value = "/getList")
	public Result<IPage<FacAuthKey>> getList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) throws IOException {

		Page<FacAuthKey> page = new Page<FacAuthKey>(pageNo, pageSize);
		IPage<FacAuthKey> pageList = facAuthKeyService.getList(page);
		List<FacAuthKey> records = pageList.getRecords();
		for (FacAuthKey record : records) {
			String authKey = ByteUtil.byte2HexString(Base64Util.DecodeString(record.getAuthKey()), true);
			record.setAuthKey(authKey);
		}
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param facAuthKey
	 * @return
	 */
	@AutoLog(value = "facId认证秘钥-添加")
	@ApiOperation(value="facId认证秘钥-添加", notes="facId认证秘钥-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FacAuthKey facAuthKey) {
		facAuthKeyService.add(facAuthKey);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param facAuthKey
	 * @return
	 */
	@AutoLog(value = "facId认证秘钥-编辑")
	@ApiOperation(value="facId认证秘钥-编辑", notes="facId认证秘钥-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FacAuthKey facAuthKey) {
		facAuthKeyService.updateByFacId(facAuthKey);
		return Result.OK("编辑成功!");
	}

	 /**
	  *  编辑
	  *
	  * @param facAuthKey
	  * @return
	  */
	 @AutoLog(value = "facId认证秘钥-重新生成")
	 @ApiOperation(value="facId认证秘钥-重新生成", notes="facId认证秘钥-重新生成")
	 //@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:edit")
	 @PostMapping(value = "/modify")
	 public Result<String> modify(@RequestBody FacAuthKey facAuthKey) {
		 facAuthKeyService.updateByFacId(facAuthKey);
		 return Result.OK("编辑成功!");
	 }
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "facId认证秘钥-通过id删除")
	@ApiOperation(value="facId认证秘钥-通过id删除", notes="facId认证秘钥-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		facAuthKeyService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "facId认证秘钥-批量删除")
	@ApiOperation(value="facId认证秘钥-批量删除", notes="facId认证秘钥-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.facAuthKeyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "facId认证秘钥-通过id查询")
	@ApiOperation(value="facId认证秘钥-通过id查询", notes="facId认证秘钥-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FacAuthKey> queryById(@RequestParam(name="id",required=true) String id) {
		FacAuthKey facAuthKey = facAuthKeyService.getById(id);
		if(facAuthKey==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(facAuthKey);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param facAuthKey
    */
    //@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FacAuthKey facAuthKey) {
        return super.exportXls(request, facAuthKey, FacAuthKey.class, "facId认证秘钥");
    }

	 /**
	  * 导出text
	  *
	  * @param request
	  * @param facAuthKey
	  */
	 @ApiOperation(value="facId认证秘钥-导出text", notes="facId认证秘钥-导出text")
	 //@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:exportXls")
	 @PostMapping(value = "/textById")
	 public Result<JSONObject> textById(HttpServletRequest request, HttpServletResponse response, FacAuthKey facAuthKey) throws IOException {
		 FacAuthKey byId = facAuthKeyService.getById(facAuthKey.getId());
		 Integer facId = byId.getFacId();

		 JSONObject jsonObject = new JSONObject();
		 String authKey = ByteUtil.byte2HexString(Base64Util.DecodeString(byId.getAuthKey()), false);
		 jsonObject.put("fileName",String.valueOf(facId));
		 jsonObject.put("authKey",authKey);
		 return Result.OK(jsonObject);
	 }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("fac_auth_key:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FacAuthKey.class);
    }

	 @AutoLog(value = "facId认证秘钥-秘钥验证")
	 @ApiOperation(value="facId认证秘钥-编辑", notes="facId认证秘钥-编辑")
	 //@RequiresPermissions("org.jeecg.modules.demo:fac_auth_key:edit")
	 @RequestMapping(value = "/checking", method = {RequestMethod.PUT,RequestMethod.POST})
	 public Result<Boolean> checking(@RequestBody AuthReq authReq) throws Exception {
		 Boolean checking = facAuthKeyService.checking(authReq);
		 return Result.OK(checking);
	 }
}
