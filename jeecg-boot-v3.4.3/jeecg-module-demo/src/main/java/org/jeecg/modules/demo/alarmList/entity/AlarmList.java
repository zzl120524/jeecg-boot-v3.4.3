package org.jeecg.modules.demo.alarmList.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class AlarmList implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**FACID*/
	@Excel(name = "FACID", width = 15)
    @ApiModelProperty(value = "FACID")
    private java.lang.Integer facId;
	/**上传接口类型*/
	@Excel(name = "上传接口类型", width = 15)
    @ApiModelProperty(value = "上传接口类型")
    private java.lang.Integer uploadType;
	/**上传总长度*/
	@Excel(name = "上传总长度", width = 15)
    @ApiModelProperty(value = "上传总长度")
    private java.lang.Integer uploadLength;
	/**异常报文类型*/
	@Excel(name = "异常报文类型", width = 15)
    @ApiModelProperty(value = "异常报文类型")
    private java.lang.Integer malformedType;
	/**异常报文长度*/
	@Excel(name = "异常报文长度", width = 15)
    @ApiModelProperty(value = "异常报文长度")
    private java.lang.Integer packetlen;
	/**异常报文*/
	@Excel(name = "异常报文", width = 15)
    @ApiModelProperty(value = "异常报文")
    private java.lang.String packet;
}
