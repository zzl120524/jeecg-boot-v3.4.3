import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
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
];

//表单数据
export const formSchema: FormSchema[] = [
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
