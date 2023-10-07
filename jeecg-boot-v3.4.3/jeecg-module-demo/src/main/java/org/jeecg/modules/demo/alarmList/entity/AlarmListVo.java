package org.jeecg.modules.demo.alarmList.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 告警列表
 * @Author: jeecg-boot
 * @Date:   2023-09-10
 * @Version: V1.0
 */
@Data
@TableName("alarm_list")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="alarm_list对象", description="告警列表")
public class AlarmListVo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**FACID*/
	@Excel(name = "FACID", width = 15)
    @ApiModelProperty(value = "FACID")
    private Integer facId;
	/**上传接口类型*/
	@Excel(name = "上传接口类型", width = 15)
    @ApiModelProperty(value = "上传接口类型")
    private Integer uploadType;
	/**上传总长度*/
	@Excel(name = "上传总长度", width = 15)
    @ApiModelProperty(value = "上传总长度")
    private Integer uploadLength;
	/**异常报文类型*/
	@Excel(name = "异常报文类型", width = 15)
    @ApiModelProperty(value = "异常报文类型")
    private Integer malformedType;
	/**异常报文长度*/
	@Excel(name = "异常报文长度", width = 15)
    @ApiModelProperty(value = "异常报文长度")
    private Integer packetlen;
	/**异常报文*/
	@Excel(name = "异常报文", width = 15)
    @ApiModelProperty(value = "异常报文")
    private String packet;

    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
    @TableField(exist = false)
    @ApiModelProperty(value = "分页")
    private Integer pageNo;
    @TableField(exist = false)
    @ApiModelProperty(value = "分页")
    private Integer pageSize;
}
