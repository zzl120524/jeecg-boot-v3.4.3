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
    title: 'FAC类型',
    align: "center",
    dataIndex: 'facType'
  },
  {
    title: '上报类型',
    align: "center",
    dataIndex: 'submitType'
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
    label: 'FAC类型',
    field: 'facType',
    component: 'InputNumber',
  },
  {
    label: '上报类型',
    field: 'submitType',
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
