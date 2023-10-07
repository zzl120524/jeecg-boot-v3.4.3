package org.jeecg.modules.demo.signalUpload.entity;

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
 * @Description: 信令上传
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
@Data
@TableName("signal_upload")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="signal_upload对象", description="信令上传")
public class SignalUploadVo implements Serializable {
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
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private String ver;
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
	/**跟踪用户*/
	@Excel(name = "跟踪用户", width = 15)
    @ApiModelProperty(value = "跟踪用户")
    private String traceUser;
	/**消息类型*/
	@Excel(name = "消息类型", width = 15)
    @ApiModelProperty(value = "消息类型")
    private Integer msgType;

    /**消息名称*/
    @Excel(name = "消息名称", width = 15)
    @ApiModelProperty(value = "消息名称")
    private String msgName;

    /**方向*/
    @Excel(name = "方向", width = 15)
    @ApiModelProperty(value = "方向")
    private String direction;

	/**信令长度*/
	@Excel(name = "信令长度", width = 15)
    @ApiModelProperty(value = "信令长度")
    private Integer signalLength;
	/**信号*/
	@Excel(name = "信号", width = 15)
    @ApiModelProperty(value = "信号")
    private String sigal;
	/**流方向*/
	@Excel(name = "流方向", width = 15)
    @ApiModelProperty(value = "流方向")
    private Integer trafficeDirection;
	/**RTP报文CODEC类型*/
	@Excel(name = "RTP报文CODEC类型", width = 15)
    @ApiModelProperty(value = "RTP报文CODEC类型")
    private Integer codec;
	/**RTPt报文长度*/
	@Excel(name = "RTPt报文长度", width = 15)
    @ApiModelProperty(value = "RTPt报文长度")
    private Integer rtpPacketLen;
	/**RTP报文*/
	@Excel(name = "RTP报文", width = 15)
    @ApiModelProperty(value = "RTP报文")
    private String rtpPacket;
	/**关联ID*/
	@Excel(name = "关联ID", width = 15)
    @ApiModelProperty(value = "关联ID")
    private Long relationId;
	/**复制报文类型*/
	@Excel(name = "复制报文类型", width = 15)
    @ApiModelProperty(value = "复制报文类型")
    private Integer signalType;
	/**信令报文长度*/
	@Excel(name = "信令报文长度", width = 15)
    @ApiModelProperty(value = "信令报文长度")
    private Integer signalPacketLen;
	/**信令报文内容*/
	@Excel(name = "信令报文内容", width = 15)
    @ApiModelProperty(value = "信令报文内容")
    private String signalPacket;
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

    @ApiModelProperty(value = "分页")
    private Integer pageNo;

    @ApiModelProperty(value = "分页")
    private Integer pageSize;

    /**操作类型*/
    @ApiModelProperty(value = "操作类型")
    private Integer opType;
    /**跟踪用户数量*/
    @ApiModelProperty(value = "跟踪用户数量")
    private Integer traceUserNum;
    /**跟踪周期*/
    @ApiModelProperty(value = "跟踪周期")
    private Integer traceDuration;


    @ApiModelProperty(value = "开始日期参数")
    private String startTime;

    @ApiModelProperty(value = "开始日期参数")
    private String beginTime;
    @ApiModelProperty(value = "结束日期参数")
    private String endTime;
    @ApiModelProperty(value = "结束日期参数")
    private String value;

    @ApiModelProperty(value = "是否选择跟踪媒体报文")
    private Integer isRtp;
}
