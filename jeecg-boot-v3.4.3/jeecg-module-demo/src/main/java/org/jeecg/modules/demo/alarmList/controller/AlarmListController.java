package org.jeecg.modules.demo.alarmList.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.alarmList.entity.AlarmList;
import org.jeecg.modules.demo.alarmList.entity.AlarmListVo;
import org.jeecg.modules.demo.alarmList.service.IAlarmListService;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 告警列表
 * @Author: jeecg-boot
 * @Date:   2023-09-10
 * @Version: V1.0
 */
@Api(tags="告警列表")
@RestController
@RequestMapping("/alarmList/alarmList")
@Slf4j
public class AlarmListController extends JeecgController<AlarmList, IAlarmListService> {
	@Autowired
	private IAlarmListService alarmListService;
	
	/**
	 * 分页列表查询
	 *
	 * @param alarmList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "告警列表-分页列表查询")
	@ApiOperation(value="告警列表-分页列表查询", notes="告警列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AlarmList>> queryPageList(AlarmList alarmList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AlarmList> queryWrapper = QueryGenerator.initQueryWrapper(alarmList, req.getParameterMap());
		Page<AlarmList> page = new Page<AlarmList>(pageNo, pageSize);
		IPage<AlarmList> pageList = alarmListService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 @ApiOperation(value="告警列表-分页条件列表查询", notes="告警列表-分页条件列表查询")
	 @PostMapping(value = "/pagelist")
	 public Result<IPage<AlarmList>> pagelist(@RequestBody AlarmListVo vo) {

		 Page<AlarmList> page = new Page<AlarmList>(vo.getPageNo(), vo.getPageSize());
		 IPage<AlarmList> pageList = alarmListService.pagelist(page, vo);
		 return Result.OK(pageList);
	 }
	

    /**
    * 导出excel
    *
    * @param request
    * @param alarmList
    */
    //@RequiresPermissions("org.jeecg.modules.demo:alarm_list:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AlarmList alarmList) {
        return super.exportXls(request, alarmList, AlarmList.class, "告警列表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("alarm_list:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AlarmList.class);
    }

}
