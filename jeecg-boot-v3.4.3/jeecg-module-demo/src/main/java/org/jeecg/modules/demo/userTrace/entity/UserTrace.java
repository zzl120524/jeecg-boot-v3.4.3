package org.jeecg.modules.demo.userTrace.entity;

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
 * @Description: 用户追踪
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Data
@TableName("user_trace")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="user_trace对象", description="用户追踪")
public class UserTrace implements Serializable {
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
	/**操作类型*/
	@Excel(name = "操作类型", width = 15)
    @ApiModelProperty(value = "操作类型")
    private java.lang.Integer opType;
	/**跟踪用户数量*/
	@Excel(name = "跟踪用户数量", width = 15)
    @ApiModelProperty(value = "跟踪用户数量")
    private java.lang.Integer traceUserNum;
	/**跟踪用户号码*/
	@Excel(name = "跟踪用户号码", width = 15)
    @ApiModelProperty(value = "跟踪用户号码")
    private java.lang.String traceUser;
	/**跟踪周期*/
	@Excel(name = "跟踪周期", width = 15)
    @ApiModelProperty(value = "跟踪周期")
    private java.lang.Integer traceDuration;

	@Excel(name = "facId", width = 15)
    @ApiModelProperty(value = "facId")
    private java.lang.Integer facId;
}
