package org.jeecg.modules.demo.userTrace.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Description: 用户追踪
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Data
@ApiModel(value="StrategyDistribution", description="策略下发")
public class StrategyDistribution implements Serializable {
    private static final long serialVersionUID = 1L;


	/**操作类型*/
    @ApiModelProperty(value = "操作类型")
    private Integer opType;
    @ApiModelProperty(value = "告警策略类型 :1：严重告警 2：一般告警")
    private Integer alarmpolicy;
    @ApiModelProperty(value = "facId")
    private Integer facId;
}
