import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '头记录类型',
    align: "center",
    dataIndex: 'headRecType'
  },
  {
    title: 'FACID',
    align: "center",
    dataIndex: 'facId'
  },
  {
    title: 'CDR文件生成时间',
    align: "center",
    dataIndex: 'startTime'
  },
  {
    title: 'CDR文件序列号',
    align: "center",
    dataIndex: 'fileSeq'
  },
  {
    title: '序列号',
    align: "center",
    dataIndex: 'version'
  },
  {
    title: '保留',
    align: "center",
    dataIndex: 'rev'
  },
  {
    title: 'CDR类型',
    align: "center",
    dataIndex: 'cdrRecType'
  },
  {
    title: '编号',
    align: "center",
    dataIndex: 'cdrNo'
  },
  {
    title: '关联id',
    align: "center",
    dataIndex: 'relationId'
  },
  {
    title: '主叫号码',
    align: "center",
    dataIndex: 'callingPartyNumber'
  },
  {
    title: '主叫号码类型',
    align: "center",
    dataIndex: 'callingPartyNumberType'
  },
  {
    title: '被叫号码',
    align: "center",
    dataIndex: 'calledPartyNumber'
  },
  {
    title: '被叫号码类型',
    align: "center",
    dataIndex: 'calledPartyNumberType'
  },
  {
    title: '位置有效性',
    align: "center",
    dataIndex: 'paraFlag'
  },
  {
    title: '主叫位置',
    align: "center",
    dataIndex: 'callingLocation'
  },
  {
    title: '主叫位置类型',
    align: "center",
    dataIndex: 'callingLocationType'
  },
  {
    title: '被叫位置',
    align: "center",
    dataIndex: 'calledLocation'
  },
  {
    title: '被叫位置类型',
    align: "center",
    dataIndex: 'calledLocationType'
  },
  {
    title: '前向呼叫历史号码',
    align: "center",
    dataIndex: 'fwHistoryInfoNum'
  },
  {
    title: '前向呼叫历史原因',
    align: "center",
    dataIndex: 'fwHistoryInfoCause'
  },
  {
    title: '后向呼叫历史号码',
    align: "center",
    dataIndex: 'bwHistoryInfoNum'
  },
  {
    title: '后向呼叫历史原因',
    align: "center",
    dataIndex: 'bwHistoryInfoCause'
  },
  {
    title: '呼叫开始时间',
    align: "center",
    dataIndex: 'cdrStartTime'
  },
  {
    title: '呼叫振铃时间',
    align: "center",
    dataIndex: 'alertTime'
  },
  {
    title: '呼叫通话时间',
    align: "center",
    dataIndex: 'answerTime'
  },
  {
    title: '呼叫释放时间',
    align: "center",
    dataIndex: 'releaseTime'
  },
  {
    title: '呼叫释放原因',
    align: "center",
    dataIndex: 'releaseCause'
  },
  {
    title: '前向媒体流中断事件数量',
    align: "center",
    dataIndex: 'fwMediainterrupEventNum'
  },
  {
    title: '后向媒体流中断事件数量',
    align: "center",
    dataIndex: 'bwMediainterrupEventNum'
  },
  {
    title: '前向媒体流丢包率',
    align: "center",
    dataIndex: 'fwMediaFlowLossRate'
  },
  {
    title: '后向媒体流丢包率',
    align: "center",
    dataIndex: 'bwMediaFlowLossRate'
  },
  {
    title: '事件1',
    align: "center",
    dataIndex: 'mediaEventType1Num'
  },
  {
    title: '事件2',
    align: "center",
    dataIndex: 'mediaEventType2Num'
  },
  {
    title: '事件3',
    align: "center",
    dataIndex: 'mediaEventType3Num'
  },
  {
    title: '事件4',
    align: "center",
    dataIndex: 'mediaEventType4Num'
  },
  {
    title: '事件5',
    align: "center",
    dataIndex: 'mediaEventType5Num'
  },
  {
    title: '事件6',
    align: "center",
    dataIndex: 'mediaEventType6Num'
  },
  {
    title: '事件7',
    align: "center",
    dataIndex: 'mediaEventType7Num'
  },
  {
    title: '事件8',
    align: "center",
    dataIndex: 'mediaEventType8Num'
  },
  {
    title: '事件9',
    align: "center",
    dataIndex: 'mediaEventType9Num'
  },
  {
    title: '事件9',
    align: "center",
    dataIndex: 'mediaEventType10Num'
  },
  {
    title: 'CDR保留',
    align: "center",
    dataIndex: 'cdrRev'
  },
  {
    title: 'tail类型',
    align: "center",
    dataIndex: 'tailRecType'
  },
  {
    title: 'tail结束时间',
    align: "center",
    dataIndex: 'tailEndTime'
  },
  {
    title: 'CDR数量',
    align: "center",
    dataIndex: 'cdrRecordNum'
  },
  {
    title: '文件关闭原因',
    align: "center",
    dataIndex: 'fileCloseCause'
  },
  {
    title: 'tail保留',
    align: "center",
    dataIndex: 'tailRev'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '头记录类型',
    field: 'headRecType',
    component: 'InputNumber',
  },
  {
    label: 'FACID',
    field: 'facId',
    component: 'InputNumber',
  },
  {
    label: 'CDR文件生成时间',
    field: 'startTime',
    component: 'Input',
  },
  {
    label: 'CDR文件序列号',
    field: 'fileSeq',
    component: 'Input',
  },
  {
    label: '序列号',
    field: 'version',
    component: 'InputNumber',
  },
  {
    label: '保留',
    field: 'rev',
    component: 'Input',
  },
  {
    label: 'CDR类型',
    field: 'cdrRecType',
    component: 'InputNumber',
  },
  {
    label: '编号',
    field: 'cdrNo',
    component: 'InputNumber',
  },
  {
    label: '关联id',
    field: 'relationId',
    component: 'InputNumber',
  },
  {
    label: '主叫号码',
    field: 'callingPartyNumber',
    component: 'Input',
  },
  {
    label: '主叫号码类型',
    field: 'callingPartyNumberType',
    component: 'InputNumber',
  },
  {
    label: '被叫号码',
    field: 'calledPartyNumber',
    component: 'Input',
  },
  {
    label: '被叫号码类型',
    field: 'calledPartyNumberType',
    component: 'InputNumber',
  },
  {
    label: '位置有效性',
    field: 'paraFlag',
    component: 'Input',
  },
  {
    label: '主叫位置',
    field: 'callingLocation',
    component: 'Input',
  },
  {
    label: '主叫位置类型',
    field: 'callingLocationType',
    component: 'InputNumber',
  },
  {
    label: '被叫位置',
    field: 'calledLocation',
    component: 'Input',
  },
  {
    label: '被叫位置类型',
    field: 'calledLocationType',
    component: 'InputNumber',
  },
  {
    label: '前向呼叫历史号码',
    field: 'fwHistoryInfoNum',
    component: 'Input',
  },
  {
    label: '前向呼叫历史原因',
    field: 'fwHistoryInfoCause',
    component: 'Input',
  },
  {
    label: '后向呼叫历史号码',
    field: 'bwHistoryInfoNum',
    component: 'Input',
  },
  {
    label: '后向呼叫历史原因',
    field: 'bwHistoryInfoCause',
    component: 'Input',
  },
  {
    label: '呼叫开始时间',
    field: 'cdrStartTime',
    component: 'Input',
  },
  {
    label: '呼叫振铃时间',
    field: 'alertTime',
    component: 'Input',
  },
  {
    label: '呼叫通话时间',
    field: 'answerTime',
    component: 'Input',
  },
  {
    label: '呼叫释放时间',
    field: 'releaseTime',
    component: 'Input',
  },
  {
    label: '呼叫释放原因',
    field: 'releaseCause',
    component: 'Input',
  },
  {
    label: '前向媒体流中断事件数量',
    field: 'fwMediainterrupEventNum',
    component: 'InputNumber',
  },
  {
    label: '后向媒体流中断事件数量',
    field: 'bwMediainterrupEventNum',
    component: 'InputNumber',
  },
  {
    label: '前向媒体流丢包率',
    field: 'fwMediaFlowLossRate',
    component: 'Input',
  },
  {
    label: '后向媒体流丢包率',
    field: 'bwMediaFlowLossRate',
    component: 'Input',
  },
  {
    label: '事件1',
    field: 'mediaEventType1Num',
    component: 'InputNumber',
  },
  {
    label: '事件2',
    field: 'mediaEventType2Num',
    component: 'InputNumber',
  },
  {
    label: '事件3',
    field: 'mediaEventType3Num',
    component: 'InputNumber',
  },
  {
    label: '事件4',
    field: 'mediaEventType4Num',
    component: 'InputNumber',
  },
  {
    label: '事件5',
    field: 'mediaEventType5Num',
    component: 'InputNumber',
  },
  {
    label: '事件6',
    field: 'mediaEventType6Num',
    component: 'InputNumber',
  },
  {
    label: '事件7',
    field: 'mediaEventType7Num',
    component: 'InputNumber',
  },
  {
    label: '事件8',
    field: 'mediaEventType8Num',
    component: 'InputNumber',
  },
  {
    label: '事件9',
    field: 'mediaEventType9Num',
    component: 'InputNumber',
  },
  {
    label: '事件9',
    field: 'mediaEventType10Num',
    component: 'InputNumber',
  },
  {
    label: 'CDR保留',
    field: 'cdrRev',
    component: 'Input',
  },
  {
    label: 'tail类型',
    field: 'tailRecType',
    component: 'InputNumber',
  },
  {
    label: 'tail结束时间',
    field: 'tailEndTime',
    component: 'Input',
  },
  {
    label: 'CDR数量',
    field: 'cdrRecordNum',
    component: 'InputNumber',
  },
  {
    label: '文件关闭原因',
    field: 'fileCloseCause',
    component: 'InputNumber',
  },
  {
    label: 'tail保留',
    field: 'tailRev',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
