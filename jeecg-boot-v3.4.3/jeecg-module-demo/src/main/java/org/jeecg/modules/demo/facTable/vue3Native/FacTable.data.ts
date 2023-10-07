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
    title: '类型',
    align: "center",
    dataIndex: 'facType'
  },
  {
    title: '状态',
    align: "center",
    dataIndex: 'facStatus'
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
    component: 'InputNumber',
  },
  {
    label: '类型',
    field: 'facType',
    component: 'Input',
  },
  {
    label: '状态',
    field: 'facStatus',
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
