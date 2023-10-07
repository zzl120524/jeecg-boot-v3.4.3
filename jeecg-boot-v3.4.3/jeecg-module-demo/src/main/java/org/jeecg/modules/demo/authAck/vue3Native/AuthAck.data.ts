import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: 'facId',
    align: "center",
    dataIndex: 'facId'
  },
  {
    title: '控制接口tcp链路连接地址',
    align: "center",
    dataIndex: 'controlifIp'
  },
  {
    title: '控制接口tcp链路连接port',
    align: "center",
    dataIndex: 'controlifPort'
  },
  {
    title: '信令流接受接口UDP连接地址',
    align: "center",
    dataIndex: 'signalPacketReportIp'
  },
  {
    title: '信令流接受接口UDP连接port',
    align: "center",
    dataIndex: 'signalPacketReportPort'
  },
  {
    title: '跟踪用户或ip流的UDP连接地址',
    align: "center",
    dataIndex: 'tracePacketReportIp'
  },
  {
    title: '跟踪用户或ip流的UDP连接端口',
    align: "center",
    dataIndex: 'tracePacketReportUdp'
  },
  {
    title: '-',
    align: "center",
    dataIndex: 'encryptPacketReportIp'
  },
  {
    title: '-',
    align: "center",
    dataIndex: 'encryptPacketReportUdp'
  },
  {
    title: '-',
    align: "center",
    dataIndex: 'malformedPacketReportIp'
  },
  {
    title: '-',
    align: "center",
    dataIndex: 'malformedPacketReportUdp'
  },
  {
    title: 'ftpUser名称',
    align: "center",
    dataIndex: 'cdrFtpUser'
  },
  {
    title: 'ftp密码',
    align: "center",
    dataIndex: 'cdrFtpKey'
  },
  {
    title: 'ftp文件路径',
    align: "center",
    dataIndex: 'ftpDirectory'
  },
  {
    title: '-',
    align: "center",
    dataIndex: 'ftpServerIp'
  },
  {
    title: '-',
    align: "center",
    dataIndex: 'mic'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'facId',
    field: 'facId',
    component: 'Input',
  },
  {
    label: '控制接口tcp链路连接地址',
    field: 'controlifIp',
    component: 'Input',
  },
  {
    label: '控制接口tcp链路连接port',
    field: 'controlifPort',
    component: 'Input',
  },
  {
    label: '信令流接受接口UDP连接地址',
    field: 'signalPacketReportIp',
    component: 'Input',
  },
  {
    label: '信令流接受接口UDP连接port',
    field: 'signalPacketReportPort',
    component: 'Input',
  },
  {
    label: '跟踪用户或ip流的UDP连接地址',
    field: 'tracePacketReportIp',
    component: 'Input',
  },
  {
    label: '跟踪用户或ip流的UDP连接端口',
    field: 'tracePacketReportUdp',
    component: 'Input',
  },
  {
    label: '-',
    field: 'encryptPacketReportIp',
    component: 'Input',
  },
  {
    label: '-',
    field: 'encryptPacketReportUdp',
    component: 'Input',
  },
  {
    label: '-',
    field: 'malformedPacketReportIp',
    component: 'Input',
  },
  {
    label: '-',
    field: 'malformedPacketReportUdp',
    component: 'Input',
  },
  {
    label: 'ftpUser名称',
    field: 'cdrFtpUser',
    component: 'Input',
  },
  {
    label: 'ftp密码',
    field: 'cdrFtpKey',
    component: 'Input',
  },
  {
    label: 'ftp文件路径',
    field: 'ftpDirectory',
    component: 'Input',
  },
  {
    label: '-',
    field: 'ftpServerIp',
    component: 'Input',
  },
  {
    label: '-',
    field: 'mic',
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
