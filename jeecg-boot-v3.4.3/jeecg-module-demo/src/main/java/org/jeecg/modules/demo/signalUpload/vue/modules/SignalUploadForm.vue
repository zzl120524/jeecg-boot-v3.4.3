<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="版本号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ver">
              <a-input v-model="model.ver" placeholder="请输入版本号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="FACID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="facId">
              <a-input-number v-model="model.facId" placeholder="请输入FACID" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上传接口类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="uploadType">
              <a-input-number v-model="model.uploadType" placeholder="请输入上传接口类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上传总长度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="uploadLength">
              <a-input-number v-model="model.uploadLength" placeholder="请输入上传总长度" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="跟踪用户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="traceUser">
              <a-input v-model="model.traceUser" placeholder="请输入跟踪用户"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="消息类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="msgType">
              <a-input-number v-model="model.msgType" placeholder="请输入消息类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="信令长度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signalLength">
              <a-input-number v-model="model.signalLength" placeholder="请输入信令长度" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="信号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sigal">
              <a-input v-model="model.sigal" placeholder="请输入信号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="流方向" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="trafficeDirection">
              <a-input-number v-model="model.trafficeDirection" placeholder="请输入流方向" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="RTP报文CODEC类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="codec">
              <a-input-number v-model="model.codec" placeholder="请输入RTP报文CODEC类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="RTPt报文长度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rtpPacketLen">
              <a-input-number v-model="model.rtpPacketLen" placeholder="请输入RTPt报文长度" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="RTP报文" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rtpPacket">
              <a-input v-model="model.rtpPacket" placeholder="请输入RTP报文"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="关联ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="relationId">
              <a-input-number v-model="model.relationId" placeholder="请输入关联ID" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="复制报文类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signalType">
              <a-input-number v-model="model.signalType" placeholder="请输入复制报文类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="信令报文长度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signalPacketLen">
              <a-input-number v-model="model.signalPacketLen" placeholder="请输入信令报文长度" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="信令报文内容" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signalPacket">
              <a-input v-model="model.signalPacket" placeholder="请输入信令报文内容"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="异常报文类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="malformedType">
              <a-input-number v-model="model.malformedType" placeholder="请输入异常报文类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="异常报文长度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="packetlen">
              <a-input-number v-model="model.packetlen" placeholder="请输入异常报文长度" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="异常报文" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="packet">
              <a-input v-model="model.packet" placeholder="请输入异常报文"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'SignalUploadForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/signalUpload/signalUpload/add",
          edit: "/signalUpload/signalUpload/edit",
          queryById: "/signalUpload/signalUpload/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>