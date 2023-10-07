package org.jeecg.modules.demo.workLog.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 业务的日志
 * @Author: jeecg-boot
 * @Date:   2023-08-14
 * @Version: V1.0
 */
@Data
@TableName("work_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="work_log对象", description="业务的日志")
public class WorkLog implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**操作*/
	@Excel(name = "操作", width = 15)
    @ApiModelProperty(value = "操作")
    private String operating;
	/**操作结果*/
	@Excel(name = "操作结果", width = 15)
    @ApiModelProperty(value = "操作结果")
    private String operatingResult;

    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
}
