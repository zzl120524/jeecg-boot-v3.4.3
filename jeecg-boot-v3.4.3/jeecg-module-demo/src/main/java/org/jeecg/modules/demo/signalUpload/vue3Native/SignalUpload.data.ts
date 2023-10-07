import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '版本号',
    align: "center",
    dataIndex: 'ver'
  },
  {
    title: 'FACID',
    align: "center",
    dataIndex: 'facId'
  },
  {
    title: '上传接口类型',
    align: "center",
    dataIndex: 'uploadType'
  },
  {
    title: '上传总长度',
    align: "center",
    dataIndex: 'uploadLength'
  },
  {
    title: '跟踪用户',
    align: "center",
    dataIndex: 'traceUser'
  },
  {
    title: '消息类型',
    align: "center",
    dataIndex: 'msgType'
  },
  {
    title: '信令长度',
    align: "center",
    dataIndex: 'signalLength'
  },
  {
    title: '信号',
    align: "center",
    dataIndex: 'sigal'
  },
  {
    title: '流方向',
    align: "center",
    dataIndex: 'trafficeDirection'
  },
  {
    title: 'RTP报文CODEC类型',
    align: "center",
    dataIndex: 'codec'
  },
  {
    title: 'RTPt报文长度',
    align: "center",
    dataIndex: 'rtpPacketLen'
  },
  {
    title: 'RTP报文',
    align: "center",
    dataIndex: 'rtpPacket'
  },
  {
    title: '关联ID',
    align: "center",
    dataIndex: 'relationId'
  },
  {
    title: '复制报文类型',
    align: "center",
    dataIndex: 'signalType'
  },
  {
    title: '信令报文长度',
    align: "center",
    dataIndex: 'signalPacketLen'
  },
  {
    title: '信令报文内容',
    align: "center",
    dataIndex: 'signalPacket'
  },
  {
    title: '异常报文类型',
    align: "center",
    dataIndex: 'malformedType'
  },
  {
    title: '异常报文长度',
    align: "center",
    dataIndex: 'packetlen'
  },
  {
    title: '异常报文',
    align: "center",
    dataIndex: 'packet'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "FACID",
    field: 'facId',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "上传接口类型",
    field: 'uploadType',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "跟踪用户",
    field: 'traceUser',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "消息类型",
    field: 'msgType',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "流方向",
    field: 'trafficeDirection',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "RTP报文CODEC类型",
    field: 'codec',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "关联ID",
    field: 'relationId',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "复制报文类型",
    field: 'signalType',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "异常报文类型",
    field: 'malformedType',
    component: 'Input',
    colProps: {span: 6},
  },
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '版本号',
    field: 'ver',
    component: 'Input',
  },
  {
    label: 'FACID',
    field: 'facId',
    component: 'InputNumber',
  },
  {
    label: '上传接口类型',
    field: 'uploadType',
    component: 'InputNumber',
  },
  {
    label: '上传总长度',
    field: 'uploadLength',
    component: 'InputNumber',
  },
  {
    label: '跟踪用户',
    field: 'traceUser',
    component: 'Input',
  },
  {
    label: '消息类型',
    field: 'msgType',
    component: 'InputNumber',
  },
  {
    label: '信令长度',
    field: 'signalLength',
    component: 'InputNumber',
  },
  {
    label: '信号',
    field: 'sigal',
    component: 'Input',
  },
  {
    label: '流方向',
    field: 'trafficeDirection',
    component: 'InputNumber',
  },
  {
    label: 'RTP报文CODEC类型',
    field: 'codec',
    component: 'InputNumber',
  },
  {
    label: 'RTPt报文长度',
    field: 'rtpPacketLen',
    component: 'InputNumber',
  },
  {
    label: 'RTP报文',
    field: 'rtpPacket',
    component: 'Input',
  },
  {
    label: '关联ID',
    field: 'relationId',
    component: 'InputNumber',
  },
  {
    label: '复制报文类型',
    field: 'signalType',
    component: 'InputNumber',
  },
  {
    label: '信令报文长度',
    field: 'signalPacketLen',
    component: 'InputNumber',
  },
  {
    label: '信令报文内容',
    field: 'signalPacket',
    component: 'Input',
  },
  {
    label: '异常报文类型',
    field: 'malformedType',
    component: 'InputNumber',
  },
  {
    label: '异常报文长度',
    field: 'packetlen',
    component: 'InputNumber',
  },
  {
    label: '异常报文',
    field: 'packet',
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
