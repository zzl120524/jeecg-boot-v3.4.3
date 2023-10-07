<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="facId" v-bind="validateInfos.facId">
            <a-input v-model:value="formData.facId" placeholder="请输入facId" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="控制接口tcp链路连接地址" v-bind="validateInfos.controlifIp">
            <a-input v-model:value="formData.controlifIp" placeholder="请输入控制接口tcp链路连接地址" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="控制接口tcp链路连接port" v-bind="validateInfos.controlifPort">
            <a-input v-model:value="formData.controlifPort" placeholder="请输入控制接口tcp链路连接port" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="信令流接受接口UDP连接地址" v-bind="validateInfos.signalPacketReportIp">
            <a-input v-model:value="formData.signalPacketReportIp" placeholder="请输入信令流接受接口UDP连接地址" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="信令流接受接口UDP连接port" v-bind="validateInfos.signalPacketReportPort">
            <a-input v-model:value="formData.signalPacketReportPort" placeholder="请输入信令流接受接口UDP连接port" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="跟踪用户或ip流的UDP连接地址" v-bind="validateInfos.tracePacketReportIp">
            <a-input v-model:value="formData.tracePacketReportIp" placeholder="请输入跟踪用户或ip流的UDP连接地址" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="跟踪用户或ip流的UDP连接端口" v-bind="validateInfos.tracePacketReportUdp">
            <a-input v-model:value="formData.tracePacketReportUdp" placeholder="请输入跟踪用户或ip流的UDP连接端口" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="-" v-bind="validateInfos.encryptPacketReportIp">
            <a-input v-model:value="formData.encryptPacketReportIp" placeholder="请输入-" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="-" v-bind="validateInfos.encryptPacketReportUdp">
            <a-input v-model:value="formData.encryptPacketReportUdp" placeholder="请输入-" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="-" v-bind="validateInfos.malformedPacketReportIp">
            <a-input v-model:value="formData.malformedPacketReportIp" placeholder="请输入-" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="-" v-bind="validateInfos.malformedPacketReportUdp">
            <a-input v-model:value="formData.malformedPacketReportUdp" placeholder="请输入-" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="ftpUser名称" v-bind="validateInfos.cdrFtpUser">
            <a-input v-model:value="formData.cdrFtpUser" placeholder="请输入ftpUser名称" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="ftp密码" v-bind="validateInfos.cdrFtpKey">
            <a-input v-model:value="formData.cdrFtpKey" placeholder="请输入ftp密码" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="ftp文件路径" v-bind="validateInfos.ftpDirectory">
            <a-input v-model:value="formData.ftpDirectory" placeholder="请输入ftp文件路径" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="-" v-bind="validateInfos.ftpServerIp">
            <a-input v-model:value="formData.ftpServerIp" placeholder="请输入-" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="-" v-bind="validateInfos.mic">
            <a-input v-model:value="formData.mic" placeholder="请输入-" :disabled="disabled"></a-input>
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
  import { saveOrUpdate } from '../AuthAck.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    facId: '',   
    controlifIp: '',   
    controlifPort: '',   
    signalPacketReportIp: '',   
    signalPacketReportPort: '',   
    tracePacketReportIp: '',   
    tracePacketReportUdp: '',   
    encryptPacketReportIp: '',   
    encryptPacketReportUdp: '',   
    malformedPacketReportIp: '',   
    malformedPacketReportUdp: '',   
    cdrFtpUser: '',   
    cdrFtpKey: '',   
    ftpDirectory: '',   
    ftpServerIp: '',   
    mic: '',   
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
