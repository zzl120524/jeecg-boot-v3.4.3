import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '操作',
    align: "center",
    dataIndex: 'operating'
  },
  {
    title: '操作结果',
    align: "center",
    dataIndex: 'operatingResult'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '操作',
    field: 'operating',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入操作!'},
             ];
    },
  },
  {
    label: '操作结果',
    field: 'operatingResult',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入操作结果!'},
             ];
    },
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
