<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="facId" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="facId">
              <a-input v-model="model.facId" placeholder="请输入facId"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="控制接口tcp链路连接地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="controlifIp">
              <a-input v-model="model.controlifIp" placeholder="请输入控制接口tcp链路连接地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="控制接口tcp链路连接port" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="controlifPort">
              <a-input v-model="model.controlifPort" placeholder="请输入控制接口tcp链路连接port"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="信令流接受接口UDP连接地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signalPacketReportIp">
              <a-input v-model="model.signalPacketReportIp" placeholder="请输入信令流接受接口UDP连接地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="信令流接受接口UDP连接port" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="signalPacketReportPort">
              <a-input v-model="model.signalPacketReportPort" placeholder="请输入信令流接受接口UDP连接port"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="跟踪用户或ip流的UDP连接地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tracePacketReportIp">
              <a-input v-model="model.tracePacketReportIp" placeholder="请输入跟踪用户或ip流的UDP连接地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="跟踪用户或ip流的UDP连接端口" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tracePacketReportUdp">
              <a-input v-model="model.tracePacketReportUdp" placeholder="请输入跟踪用户或ip流的UDP连接端口"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="-" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="encryptPacketReportIp">
              <a-input v-model="model.encryptPacketReportIp" placeholder="请输入-"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="-" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="encryptPacketReportUdp">
              <a-input v-model="model.encryptPacketReportUdp" placeholder="请输入-"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="-" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="malformedPacketReportIp">
              <a-input v-model="model.malformedPacketReportIp" placeholder="请输入-"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="-" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="malformedPacketReportUdp">
              <a-input v-model="model.malformedPacketReportUdp" placeholder="请输入-"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="ftpUser名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cdrFtpUser">
              <a-input v-model="model.cdrFtpUser" placeholder="请输入ftpUser名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="ftp密码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cdrFtpKey">
              <a-input v-model="model.cdrFtpKey" placeholder="请输入ftp密码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="ftp文件路径" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ftpDirectory">
              <a-input v-model="model.ftpDirectory" placeholder="请输入ftp文件路径"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="-" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ftpServerIp">
              <a-input v-model="model.ftpServerIp" placeholder="请输入-"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="-" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mic">
              <a-input v-model="model.mic" placeholder="请输入-"  ></a-input>
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
    name: 'AuthAckForm',
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
          add: "/authAck/authAck/add",
          edit: "/authAck/authAck/edit",
          queryById: "/authAck/authAck/queryById"
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