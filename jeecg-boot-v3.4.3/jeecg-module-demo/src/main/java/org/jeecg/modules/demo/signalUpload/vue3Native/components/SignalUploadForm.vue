<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="版本号" v-bind="validateInfos.ver">
            <a-input v-model:value="formData.ver" placeholder="请输入版本号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="FACID" v-bind="validateInfos.facId">
	          <a-input-number v-model:value="formData.facId" placeholder="请输入FACID" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="上传接口类型" v-bind="validateInfos.uploadType">
	          <a-input-number v-model:value="formData.uploadType" placeholder="请输入上传接口类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="上传总长度" v-bind="validateInfos.uploadLength">
	          <a-input-number v-model:value="formData.uploadLength" placeholder="请输入上传总长度" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="跟踪用户" v-bind="validateInfos.traceUser">
            <a-input v-model:value="formData.traceUser" placeholder="请输入跟踪用户" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="消息类型" v-bind="validateInfos.msgType">
	          <a-input-number v-model:value="formData.msgType" placeholder="请输入消息类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="信令长度" v-bind="validateInfos.signalLength">
	          <a-input-number v-model:value="formData.signalLength" placeholder="请输入信令长度" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="信号" v-bind="validateInfos.sigal">
            <a-input v-model:value="formData.sigal" placeholder="请输入信号" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="流方向" v-bind="validateInfos.trafficeDirection">
	          <a-input-number v-model:value="formData.trafficeDirection" placeholder="请输入流方向" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="RTP报文CODEC类型" v-bind="validateInfos.codec">
	          <a-input-number v-model:value="formData.codec" placeholder="请输入RTP报文CODEC类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="RTPt报文长度" v-bind="validateInfos.rtpPacketLen">
	          <a-input-number v-model:value="formData.rtpPacketLen" placeholder="请输入RTPt报文长度" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="RTP报文" v-bind="validateInfos.rtpPacket">
            <a-input v-model:value="formData.rtpPacket" placeholder="请输入RTP报文" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="关联ID" v-bind="validateInfos.relationId">
	          <a-input-number v-model:value="formData.relationId" placeholder="请输入关联ID" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="复制报文类型" v-bind="validateInfos.signalType">
	          <a-input-number v-model:value="formData.signalType" placeholder="请输入复制报文类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="信令报文长度" v-bind="validateInfos.signalPacketLen">
	          <a-input-number v-model:value="formData.signalPacketLen" placeholder="请输入信令报文长度" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="信令报文内容" v-bind="validateInfos.signalPacket">
            <a-input v-model:value="formData.signalPacket" placeholder="请输入信令报文内容" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="异常报文类型" v-bind="validateInfos.malformedType">
	          <a-input-number v-model:value="formData.malformedType" placeholder="请输入异常报文类型" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="异常报文长度" v-bind="validateInfos.packetlen">
	          <a-input-number v-model:value="formData.packetlen" placeholder="请输入异常报文长度" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="异常报文" v-bind="validateInfos.packet">
            <a-input v-model:value="formData.packet" placeholder="请输入异常报文" :disabled="disabled"></a-input>
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
  import { saveOrUpdate } from '../SignalUpload.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    ver: '',   
    facId: undefined,
    uploadType: undefined,
    uploadLength: undefined,
    traceUser: '',   
    msgType: undefined,
    signalLength: undefined,
    sigal: '',   
    trafficeDirection: undefined,
    codec: undefined,
    rtpPacketLen: undefined,
    rtpPacket: '',   
    relationId: undefined,
    signalType: undefined,
    signalPacketLen: undefined,
    signalPacket: '',   
    malformedType: undefined,
    packetlen: undefined,
    packet: '',   
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
