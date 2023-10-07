package org.jeecg.modules.demo.authAck.entity;

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
 * @Description: 授权确认
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
@Data
@TableName("auth_ack")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="auth_ack对象", description="授权确认")
public class AuthAckVo implements Serializable {
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
	/**facId*/
	@Excel(name = "facId", width = 15)
    @ApiModelProperty(value = "facId")
    private java.lang.String facId;
	/**控制接口tcp链路连接地址*/
	@Excel(name = "控制接口tcp链路连接地址", width = 15)
    @ApiModelProperty(value = "控制接口tcp链路连接地址")
    private java.lang.String controlifIp;
	/**控制接口tcp链路连接port*/
	@Excel(name = "控制接口tcp链路连接port", width = 15)
    @ApiModelProperty(value = "控制接口tcp链路连接port")
    private java.lang.Integer controlifPort;
	/**信令流接受接口UDP连接地址*/
	@Excel(name = "信令流接受接口UDP连接地址", width = 15)
    @ApiModelProperty(value = "信令流接受接口UDP连接地址")
    private java.lang.String signalPacketReportIp;
	/**信令流接受接口UDP连接port*/
	@Excel(name = "信令流接受接口UDP连接port", width = 15)
    @ApiModelProperty(value = "信令流接受接口UDP连接port")
    private java.lang.Integer signalPacketReportPort;
	/**跟踪用户或ip流的UDP连接地址*/
	@Excel(name = "跟踪用户或ip流的UDP连接地址", width = 15)
    @ApiModelProperty(value = "跟踪用户或ip流的UDP连接地址")
    private java.lang.String tracePacketReportIp;
	/**跟踪用户或ip流的UDP连接端口*/
	@Excel(name = "跟踪用户或ip流的UDP连接端口", width = 15)
    @ApiModelProperty(value = "跟踪用户或ip流的UDP连接端口")
    private java.lang.Integer tracePacketReportUdp;
	/**-*/
	@Excel(name = "-", width = 15)
    @ApiModelProperty(value = "-")
    private java.lang.String encryptPacketReportIp;
	/**-*/
	@Excel(name = "-", width = 15)
    @ApiModelProperty(value = "-")
    private java.lang.Integer encryptPacketReportUdp;
	/**-*/
	@Excel(name = "-", width = 15)
    @ApiModelProperty(value = "-")
    private java.lang.String malformedPacketReportIp;
	/**-*/
	@Excel(name = "-", width = 15)
    @ApiModelProperty(value = "-")
    private java.lang.Integer malformedPacketReportUdp;
	/**ftpUser名称*/
	@Excel(name = "ftpUser名称", width = 15)
    @ApiModelProperty(value = "ftpUser名称")
    private java.lang.String cdrFtpUser;
	/**ftp密码*/
	@Excel(name = "ftp密码", width = 15)
    @ApiModelProperty(value = "ftp密码")
    private java.lang.String cdrFtpKey;
	/**ftp文件路径*/
	@Excel(name = "ftp文件路径", width = 15)
    @ApiModelProperty(value = "ftp文件路径")
    private java.lang.String ftpDirectory;
	/**-*/
	@Excel(name = "-", width = 15)
    @ApiModelProperty(value = "-")
    private java.lang.String ftpServerIp;
	/**-*/
	@Excel(name = "-", width = 15)
    @ApiModelProperty(value = "-")
    private java.lang.String mic;
}
