package org.jeecg.modules.demo.cdrManage.entity;

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
 * @Description: CDR文件管理
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Data
@TableName("cdr_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cdr_manage对象", description="CDR文件管理")
public class CdrManage implements Serializable {
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

    @ApiModelProperty(value = "CDR文件主键ID")
    private java.lang.String cdrFileId;
	/**头记录类型*/
	@Excel(name = "头记录类型", width = 15)
    @ApiModelProperty(value = "头记录类型")
    private java.lang.Integer headRecType;
	/**FACID*/
	@Excel(name = "FACID", width = 15)
    @ApiModelProperty(value = "FACID")
    private java.lang.Integer facId;
	/**CDR文件生成时间*/
	@Excel(name = "CDR文件生成时间", width = 15)
    @ApiModelProperty(value = "CDR文件生成时间")
    private java.lang.String startTime;
	/**CDR文件序列号*/
	@Excel(name = "CDR文件序列号", width = 15)
    @ApiModelProperty(value = "CDR文件序列号")
    private java.lang.String fileSeq;
	/**序列号*/
	@Excel(name = "序列号", width = 15)
    @ApiModelProperty(value = "序列号")
    private java.lang.Integer version;
	/**保留*/
	@Excel(name = "保留", width = 15)
    @ApiModelProperty(value = "保留")
    private java.lang.String rev;
	/**CDR类型*/
	@Excel(name = "CDR类型", width = 15)
    @ApiModelProperty(value = "CDR类型")
    private java.lang.Integer cdrRecType;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private java.lang.Integer cdrNo;
	/**关联id*/
	@Excel(name = "关联id", width = 15)
    @ApiModelProperty(value = "关联id")
    private java.lang.Long relationId;
	/**主叫号码*/
	@Excel(name = "主叫号码", width = 15)
    @ApiModelProperty(value = "主叫号码")
    private java.lang.String callingPartyNumber;
	/**主叫号码类型*/
	@Excel(name = "主叫号码类型", width = 15)
    @ApiModelProperty(value = "主叫号码类型")
    private java.lang.Integer callingPartyNumberType;
	/**被叫号码*/
	@Excel(name = "被叫号码", width = 15)
    @ApiModelProperty(value = "被叫号码")
    private java.lang.String calledPartyNumber;
	/**被叫号码类型*/
	@Excel(name = "被叫号码类型", width = 15)
    @ApiModelProperty(value = "被叫号码类型")
    private java.lang.Integer calledPartyNumberType;
	/**位置有效性*/
	@Excel(name = "位置有效性", width = 15)
    @ApiModelProperty(value = "位置有效性")
    private java.lang.Integer paraFlag;
	/**主叫位置*/
	@Excel(name = "主叫位置", width = 15)
    @ApiModelProperty(value = "主叫位置")
    private java.lang.String callingLocation;
	/**主叫位置类型*/
	@Excel(name = "主叫位置类型", width = 15)
    @ApiModelProperty(value = "主叫位置类型")
    private java.lang.Integer callingLocationType;
	/**被叫位置*/
	@Excel(name = "被叫位置", width = 15)
    @ApiModelProperty(value = "被叫位置")
    private java.lang.String calledLocation;
	/**被叫位置类型*/
	@Excel(name = "被叫位置类型", width = 15)
    @ApiModelProperty(value = "被叫位置类型")
    private java.lang.Integer calledLocationType;
	/**前向呼叫历史号码*/
	@Excel(name = "前向呼叫历史号码", width = 15)
    @ApiModelProperty(value = "前向呼叫历史号码")
    private java.lang.String fwHistoryInfoNum;
	/**前向呼叫历史原因*/
	@Excel(name = "前向呼叫历史原因", width = 15)
    @ApiModelProperty(value = "前向呼叫历史原因")
    private java.lang.String fwHistoryInfoCause;
	/**后向呼叫历史号码*/
	@Excel(name = "后向呼叫历史号码", width = 15)
    @ApiModelProperty(value = "后向呼叫历史号码")
    private java.lang.String bwHistoryInfoNum;
	/**后向呼叫历史原因*/
	@Excel(name = "后向呼叫历史原因", width = 15)
    @ApiModelProperty(value = "后向呼叫历史原因")
    private java.lang.String bwHistoryInfoCause;
	/**呼叫开始时间*/
	@Excel(name = "呼叫开始时间", width = 15)
    @ApiModelProperty(value = "呼叫开始时间")
    private java.lang.String cdrStartTime;
	/**呼叫振铃时间*/
	@Excel(name = "呼叫振铃时间", width = 15)
    @ApiModelProperty(value = "呼叫振铃时间")
    private java.lang.String alertTime;
	/**呼叫通话时间*/
	@Excel(name = "呼叫通话时间", width = 15)
    @ApiModelProperty(value = "呼叫通话时间")
    private java.lang.String answerTime;
	/**呼叫释放时间*/
	@Excel(name = "呼叫释放时间", width = 15)
    @ApiModelProperty(value = "呼叫释放时间")
    private java.lang.String releaseTime;
	/**呼叫释放原因*/
	@Excel(name = "呼叫释放原因", width = 15)
    @ApiModelProperty(value = "呼叫释放原因")
    private java.lang.Integer releaseCause;
	/**前向媒体流中断事件数量*/
	@Excel(name = "前向媒体流中断事件数量", width = 15)
    @ApiModelProperty(value = "前向媒体流中断事件数量")
    private java.lang.Integer fwMediainterrupEventNum;
	/**后向媒体流中断事件数量*/
	@Excel(name = "后向媒体流中断事件数量", width = 15)
    @ApiModelProperty(value = "后向媒体流中断事件数量")
    private java.lang.Integer bwMediainterrupEventNum;
	/**前向媒体流丢包率*/
	@Excel(name = "前向媒体流丢包率", width = 15)
    @ApiModelProperty(value = "前向媒体流丢包率")
    private java.lang.String fwMediaFlowLossRate;
	/**后向媒体流丢包率*/
	@Excel(name = "后向媒体流丢包率", width = 15)
    @ApiModelProperty(value = "后向媒体流丢包率")
    private java.lang.String bwMediaFlowLossRate;
	/**事件1*/
	@Excel(name = "事件1", width = 15)
    @ApiModelProperty(value = "事件1")
    private java.lang.Integer mediaEventType1Num;
	/**事件2*/
	@Excel(name = "事件2", width = 15)
    @ApiModelProperty(value = "事件2")
    private java.lang.Integer mediaEventType2Num;
	/**事件3*/
	@Excel(name = "事件3", width = 15)
    @ApiModelProperty(value = "事件3")
    private java.lang.Integer mediaEventType3Num;
	/**事件4*/
	@Excel(name = "事件4", width = 15)
    @ApiModelProperty(value = "事件4")
    private java.lang.Integer mediaEventType4Num;
	/**事件5*/
	@Excel(name = "事件5", width = 15)
    @ApiModelProperty(value = "事件5")
    private java.lang.Integer mediaEventType5Num;
	/**事件6*/
	@Excel(name = "事件6", width = 15)
    @ApiModelProperty(value = "事件6")
    private java.lang.Integer mediaEventType6Num;
	/**事件7*/
	@Excel(name = "事件7", width = 15)
    @ApiModelProperty(value = "事件7")
    private java.lang.Integer mediaEventType7Num;
	/**事件8*/
	@Excel(name = "事件8", width = 15)
    @ApiModelProperty(value = "事件8")
    private java.lang.Integer mediaEventType8Num;
	/**事件9*/
	@Excel(name = "事件9", width = 15)
    @ApiModelProperty(value = "事件9")
    private java.lang.Integer mediaEventType9Num;
	/**事件9*/
	@Excel(name = "事件9", width = 15)
    @ApiModelProperty(value = "事件9")
    private java.lang.Integer mediaEventType10Num;
	/**CDR保留*/
	@Excel(name = "CDR保留", width = 15)
    @ApiModelProperty(value = "CDR保留")
    private java.lang.String cdrRev;
	/**tail类型*/
	@Excel(name = "tail类型", width = 15)
    @ApiModelProperty(value = "tail类型")
    private java.lang.Integer tailRecType;
	/**tail结束时间*/
	@Excel(name = "tail结束时间", width = 15)
    @ApiModelProperty(value = "tail结束时间")
    private java.lang.String tailEndTime;
	/**CDR数量*/
	@Excel(name = "CDR数量", width = 15)
    @ApiModelProperty(value = "CDR数量")
    private java.lang.Integer cdrRecordNum;
	/**文件关闭原因*/
	@Excel(name = "文件关闭原因", width = 15)
    @ApiModelProperty(value = "文件关闭原因")
    private java.lang.Integer fileCloseCause;
	/**tail保留*/
	@Excel(name = "tail保留", width = 15)
    @ApiModelProperty(value = "tail保留")
    private java.lang.String tailRev;

}
