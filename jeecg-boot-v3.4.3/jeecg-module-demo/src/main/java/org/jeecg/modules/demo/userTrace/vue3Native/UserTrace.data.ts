import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '操作类型',
    align: "center",
    dataIndex: 'opType'
  },
  {
    title: '跟踪用户数量',
    align: "center",
    dataIndex: 'traceUserNum'
  },
  {
    title: '跟踪用户号码',
    align: "center",
    dataIndex: 'traceUser'
  },
  {
    title: '跟踪周期',
    align: "center",
    dataIndex: 'traceDuration'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '操作类型',
    field: 'opType',
    component: 'InputNumber',
  },
  {
    label: '跟踪用户数量',
    field: 'traceUserNum',
    component: 'InputNumber',
  },
  {
    label: '跟踪用户号码',
    field: 'traceUser',
    component: 'Input',
  },
  {
    label: '跟踪周期',
    field: 'traceDuration',
    component: 'InputNumber',
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
