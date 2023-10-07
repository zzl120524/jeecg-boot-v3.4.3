package org.jeecg.modules.demo.facAuthKey.entity;

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
 * @Description: facId认证秘钥
 * @Author: jeecg-boot
 * @Date:   2023-08-11
 * @Version: V1.0
 */
@Data
@TableName("fac_auth_key")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="fac_auth_key对象", description="facId认证秘钥")
public class FacAuthKey implements Serializable {
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
	/**facId*/
	@Excel(name = "facId", width = 15)
    @ApiModelProperty(value = "facId")
    private Integer facId;
	/**认证秘钥*/
	@Excel(name = "认证秘钥", width = 15)
    @ApiModelProperty(value = "认证秘钥")
    private String authKey;

    @Excel(name = "是否展示", width = 15)
    @ApiModelProperty(value = "是否展示")
    private Integer flag;

    @Excel(name = "FAC名称", width = 15)
    @ApiModelProperty(value = "FAC名称")
    private String facName;

    @Excel(name = "FAC类型", width = 15)
    @ApiModelProperty(value = "FAC类型")
    private String facType;
}
