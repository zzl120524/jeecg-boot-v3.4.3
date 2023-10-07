<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="头记录类型" v-bind="validateInfos.headRecType">
	          <a-input-number v-model:value="formData.headRecType" placeholder="请输入头记录类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="FACID" v-bind="validateInfos.facId">
	          <a-input-number v-model:value="formData.facId" placeholder="请输入FACID" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="CDR文件生成时间" v-bind="validateInfos.startTime">
            <a-input v-model:value="formData.startTime" placeholder="请输入CDR文件生成时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="CDR文件序列号" v-bind="validateInfos.fileSeq">
            <a-input v-model:value="formData.fileSeq" placeholder="请输入CDR文件序列号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="序列号" v-bind="validateInfos.version">
	          <a-input-number v-model:value="formData.version" placeholder="请输入序列号" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="保留" v-bind="validateInfos.rev">
            <a-input v-model:value="formData.rev" placeholder="请输入保留" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="CDR类型" v-bind="validateInfos.cdrRecType">
	          <a-input-number v-model:value="formData.cdrRecType" placeholder="请输入CDR类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="编号" v-bind="validateInfos.cdrNo">
	          <a-input-number v-model:value="formData.cdrNo" placeholder="请输入编号" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="关联id" v-bind="validateInfos.relationId">
	          <a-input-number v-model:value="formData.relationId" placeholder="请输入关联id" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="主叫号码" v-bind="validateInfos.callingPartyNumber">
            <a-input v-model:value="formData.callingPartyNumber" placeholder="请输入主叫号码" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="主叫号码类型" v-bind="validateInfos.callingPartyNumberType">
	          <a-input-number v-model:value="formData.callingPartyNumberType" placeholder="请输入主叫号码类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="被叫号码" v-bind="validateInfos.calledPartyNumber">
            <a-input v-model:value="formData.calledPartyNumber" placeholder="请输入被叫号码" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="被叫号码类型" v-bind="validateInfos.calledPartyNumberType">
	          <a-input-number v-model:value="formData.calledPartyNumberType" placeholder="请输入被叫号码类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="位置有效性" v-bind="validateInfos.paraFlag">
            <a-input v-model:value="formData.paraFlag" placeholder="请输入位置有效性" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="主叫位置" v-bind="validateInfos.callingLocation">
            <a-input v-model:value="formData.callingLocation" placeholder="请输入主叫位置" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="主叫位置类型" v-bind="validateInfos.callingLocationType">
	          <a-input-number v-model:value="formData.callingLocationType" placeholder="请输入主叫位置类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="被叫位置" v-bind="validateInfos.calledLocation">
            <a-input v-model:value="formData.calledLocation" placeholder="请输入被叫位置" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="被叫位置类型" v-bind="validateInfos.calledLocationType">
	          <a-input-number v-model:value="formData.calledLocationType" placeholder="请输入被叫位置类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="前向呼叫历史号码" v-bind="validateInfos.fwHistoryInfoNum">
            <a-input v-model:value="formData.fwHistoryInfoNum" placeholder="请输入前向呼叫历史号码" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="前向呼叫历史原因" v-bind="validateInfos.fwHistoryInfoCause">
            <a-input v-model:value="formData.fwHistoryInfoCause" placeholder="请输入前向呼叫历史原因" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="后向呼叫历史号码" v-bind="validateInfos.bwHistoryInfoNum">
            <a-input v-model:value="formData.bwHistoryInfoNum" placeholder="请输入后向呼叫历史号码" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="后向呼叫历史原因" v-bind="validateInfos.bwHistoryInfoCause">
            <a-input v-model:value="formData.bwHistoryInfoCause" placeholder="请输入后向呼叫历史原因" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="呼叫开始时间" v-bind="validateInfos.cdrStartTime">
            <a-input v-model:value="formData.cdrStartTime" placeholder="请输入呼叫开始时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="呼叫振铃时间" v-bind="validateInfos.alertTime">
            <a-input v-model:value="formData.alertTime" placeholder="请输入呼叫振铃时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="呼叫通话时间" v-bind="validateInfos.answerTime">
            <a-input v-model:value="formData.answerTime" placeholder="请输入呼叫通话时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="呼叫释放时间" v-bind="validateInfos.releaseTime">
            <a-input v-model:value="formData.releaseTime" placeholder="请输入呼叫释放时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="呼叫释放原因" v-bind="validateInfos.releaseCause">
            <a-input v-model:value="formData.releaseCause" placeholder="请输入呼叫释放原因" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="前向媒体流中断事件数量" v-bind="validateInfos.fwMediainterrupEventNum">
	          <a-input-number v-model:value="formData.fwMediainterrupEventNum" placeholder="请输入前向媒体流中断事件数量" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="后向媒体流中断事件数量" v-bind="validateInfos.bwMediainterrupEventNum">
	          <a-input-number v-model:value="formData.bwMediainterrupEventNum" placeholder="请输入后向媒体流中断事件数量" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="前向媒体流丢包率" v-bind="validateInfos.fwMediaFlowLossRate">
            <a-input v-model:value="formData.fwMediaFlowLossRate" placeholder="请输入前向媒体流丢包率" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="后向媒体流丢包率" v-bind="validateInfos.bwMediaFlowLossRate">
            <a-input v-model:value="formData.bwMediaFlowLossRate" placeholder="请输入后向媒体流丢包率" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件1" v-bind="validateInfos.mediaEventType1Num">
	          <a-input-number v-model:value="formData.mediaEventType1Num" placeholder="请输入事件1" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件2" v-bind="validateInfos.mediaEventType2Num">
	          <a-input-number v-model:value="formData.mediaEventType2Num" placeholder="请输入事件2" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件3" v-bind="validateInfos.mediaEventType3Num">
	          <a-input-number v-model:value="formData.mediaEventType3Num" placeholder="请输入事件3" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件4" v-bind="validateInfos.mediaEventType4Num">
	          <a-input-number v-model:value="formData.mediaEventType4Num" placeholder="请输入事件4" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件5" v-bind="validateInfos.mediaEventType5Num">
	          <a-input-number v-model:value="formData.mediaEventType5Num" placeholder="请输入事件5" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件6" v-bind="validateInfos.mediaEventType6Num">
	          <a-input-number v-model:value="formData.mediaEventType6Num" placeholder="请输入事件6" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件7" v-bind="validateInfos.mediaEventType7Num">
	          <a-input-number v-model:value="formData.mediaEventType7Num" placeholder="请输入事件7" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件8" v-bind="validateInfos.mediaEventType8Num">
	          <a-input-number v-model:value="formData.mediaEventType8Num" placeholder="请输入事件8" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件9" v-bind="validateInfos.mediaEventType9Num">
	          <a-input-number v-model:value="formData.mediaEventType9Num" placeholder="请输入事件9" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="事件9" v-bind="validateInfos.mediaEventType10Num">
	          <a-input-number v-model:value="formData.mediaEventType10Num" placeholder="请输入事件9" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="CDR保留" v-bind="validateInfos.cdrRev">
            <a-input v-model:value="formData.cdrRev" placeholder="请输入CDR保留" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="tail类型" v-bind="validateInfos.tailRecType">
	          <a-input-number v-model:value="formData.tailRecType" placeholder="请输入tail类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="tail结束时间" v-bind="validateInfos.tailEndTime">
            <a-input v-model:value="formData.tailEndTime" placeholder="请输入tail结束时间" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="CDR数量" v-bind="validateInfos.cdrRecordNum">
	          <a-input-number v-model:value="formData.cdrRecordNum" placeholder="请输入CDR数量" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="文件关闭原因" v-bind="validateInfos.fileCloseCause">
	          <a-input-number v-model:value="formData.fileCloseCause" placeholder="请输入文件关闭原因" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="tail保留" v-bind="validateInfos.tailRev">
            <a-input v-model:value="formData.tailRev" placeholder="请输入tail保留" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, defineProps, computed } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import moment from 'moment';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../CdrManage.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    headRecType: undefined,
    facId: undefined,
    startTime: '',   
    fileSeq: '',   
    version: undefined,
    rev: '',   
    cdrRecType: undefined,
    cdrNo: undefined,
    relationId: undefined,
    callingPartyNumber: '',   
    callingPartyNumberType: undefined,
    calledPartyNumber: '',   
    calledPartyNumberType: undefined,
    paraFlag: '',   
    callingLocation: '',   
    callingLocationType: undefined,
    calledLocation: '',   
    calledLocationType: undefined,
    fwHistoryInfoNum: '',   
    fwHistoryInfoCause: '',   
    bwHistoryInfoNum: '',   
    bwHistoryInfoCause: '',   
    cdrStartTime: '',   
    alertTime: '',   
    answerTime: '',   
    releaseTime: '',   
    releaseCause: '',   
    fwMediainterrupEventNum: undefined,
    bwMediainterrupEventNum: undefined,
    fwMediaFlowLossRate: '',   
    bwMediaFlowLossRate: '',   
    mediaEventType1Num: undefined,
    mediaEventType2Num: undefined,
    mediaEventType3Num: undefined,
    mediaEventType4Num: undefined,
    mediaEventType5Num: undefined,
    mediaEventType6Num: undefined,
    mediaEventType7Num: undefined,
    mediaEventType8Num: undefined,
    mediaEventType9Num: undefined,
    mediaEventType10Num: undefined,
    cdrRev: '',   
    tailRecType: undefined,
    tailEndTime: '',   
    cdrRecordNum: undefined,
    fileCloseCause: undefined,
    tailRev: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = {
  };
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: true });
  
  /**
   * 新增
   */
  function add() {
    edit({});
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(() => {
      resetFields();
      //赋值
      Object.assign(formData, record);
    });
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    // 触发表单验证
    await validate();
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    let model = formData;
    if (model.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in model) {
      //如果该数据是数组并且是字符串类型
      if (model[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          model[data] = model[data].join(',');
        }
      }
    }
    await saveOrUpdate(model, isUpdate.value)
      .then((res) => {
        if (res.success) {
          createMessage.success(res.message);
          emit('ok');
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    height: 500px !important;
    overflow-y: auto;
    padding: 24px 24px 24px 24px;
  }
</style>
