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
    title: '认证秘钥',
    align: "center",
    dataIndex: 'authKey'
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
    label: '认证秘钥',
    field: 'authKey',
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
