<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="头记录类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="headRecType">
              <a-input-number v-model="model.headRecType" placeholder="请输入头记录类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="FACID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="facId">
              <a-input-number v-model="model.facId" placeholder="请输入FACID" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="CDR文件生成时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="startTime">
              <a-input v-model="model.startTime" placeholder="请输入CDR文件生成时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="CDR文件序列号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fileSeq">
              <a-input v-model="model.fileSeq" placeholder="请输入CDR文件序列号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="序列号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="version">
              <a-input-number v-model="model.version" placeholder="请输入序列号" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="保留" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rev">
              <a-input v-model="model.rev" placeholder="请输入保留"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="CDR类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cdrRecType">
              <a-input-number v-model="model.cdrRecType" placeholder="请输入CDR类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cdrNo">
              <a-input-number v-model="model.cdrNo" placeholder="请输入编号" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="关联id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="relationId">
              <a-input-number v-model="model.relationId" placeholder="请输入关联id" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="主叫号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="callingPartyNumber">
              <a-input v-model="model.callingPartyNumber" placeholder="请输入主叫号码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="主叫号码类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="callingPartyNumberType">
              <a-input-number v-model="model.callingPartyNumberType" placeholder="请输入主叫号码类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="被叫号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="calledPartyNumber">
              <a-input v-model="model.calledPartyNumber" placeholder="请输入被叫号码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="被叫号码类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="calledPartyNumberType">
              <a-input-number v-model="model.calledPartyNumberType" placeholder="请输入被叫号码类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="位置有效性" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="paraFlag">
              <a-input v-model="model.paraFlag" placeholder="请输入位置有效性"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="主叫位置" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="callingLocation">
              <a-input v-model="model.callingLocation" placeholder="请输入主叫位置"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="主叫位置类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="callingLocationType">
              <a-input-number v-model="model.callingLocationType" placeholder="请输入主叫位置类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="被叫位置" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="calledLocation">
              <a-input v-model="model.calledLocation" placeholder="请输入被叫位置"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="被叫位置类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="calledLocationType">
              <a-input-number v-model="model.calledLocationType" placeholder="请输入被叫位置类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="前向呼叫历史号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fwHistoryInfoNum">
              <a-input v-model="model.fwHistoryInfoNum" placeholder="请输入前向呼叫历史号码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="前向呼叫历史原因" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fwHistoryInfoCause">
              <a-input v-model="model.fwHistoryInfoCause" placeholder="请输入前向呼叫历史原因"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="后向呼叫历史号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bwHistoryInfoNum">
              <a-input v-model="model.bwHistoryInfoNum" placeholder="请输入后向呼叫历史号码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="后向呼叫历史原因" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bwHistoryInfoCause">
              <a-input v-model="model.bwHistoryInfoCause" placeholder="请输入后向呼叫历史原因"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="呼叫开始时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cdrStartTime">
              <a-input v-model="model.cdrStartTime" placeholder="请输入呼叫开始时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="呼叫振铃时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="alertTime">
              <a-input v-model="model.alertTime" placeholder="请输入呼叫振铃时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="呼叫通话时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="answerTime">
              <a-input v-model="model.answerTime" placeholder="请输入呼叫通话时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="呼叫释放时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="releaseTime">
              <a-input v-model="model.releaseTime" placeholder="请输入呼叫释放时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="呼叫释放原因" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="releaseCause">
              <a-input v-model="model.releaseCause" placeholder="请输入呼叫释放原因"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="前向媒体流中断事件数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fwMediainterrupEventNum">
              <a-input-number v-model="model.fwMediainterrupEventNum" placeholder="请输入前向媒体流中断事件数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="后向媒体流中断事件数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bwMediainterrupEventNum">
              <a-input-number v-model="model.bwMediainterrupEventNum" placeholder="请输入后向媒体流中断事件数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="前向媒体流丢包率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fwMediaFlowLossRate">
              <a-input v-model="model.fwMediaFlowLossRate" placeholder="请输入前向媒体流丢包率"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="后向媒体流丢包率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bwMediaFlowLossRate">
              <a-input v-model="model.bwMediaFlowLossRate" placeholder="请输入后向媒体流丢包率"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件1" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType1Num">
              <a-input-number v-model="model.mediaEventType1Num" placeholder="请输入事件1" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件2" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType2Num">
              <a-input-number v-model="model.mediaEventType2Num" placeholder="请输入事件2" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件3" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType3Num">
              <a-input-number v-model="model.mediaEventType3Num" placeholder="请输入事件3" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件4" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType4Num">
              <a-input-number v-model="model.mediaEventType4Num" placeholder="请输入事件4" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件5" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType5Num">
              <a-input-number v-model="model.mediaEventType5Num" placeholder="请输入事件5" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件6" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType6Num">
              <a-input-number v-model="model.mediaEventType6Num" placeholder="请输入事件6" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件7" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType7Num">
              <a-input-number v-model="model.mediaEventType7Num" placeholder="请输入事件7" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件8" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType8Num">
              <a-input-number v-model="model.mediaEventType8Num" placeholder="请输入事件8" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件9" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType9Num">
              <a-input-number v-model="model.mediaEventType9Num" placeholder="请输入事件9" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="事件9" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mediaEventType10Num">
              <a-input-number v-model="model.mediaEventType10Num" placeholder="请输入事件9" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="CDR保留" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cdrRev">
              <a-input v-model="model.cdrRev" placeholder="请输入CDR保留"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="tail类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tailRecType">
              <a-input-number v-model="model.tailRecType" placeholder="请输入tail类型" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="tail结束时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tailEndTime">
              <a-input v-model="model.tailEndTime" placeholder="请输入tail结束时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="CDR数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cdrRecordNum">
              <a-input-number v-model="model.cdrRecordNum" placeholder="请输入CDR数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="文件关闭原因" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fileCloseCause">
              <a-input-number v-model="model.fileCloseCause" placeholder="请输入文件关闭原因" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="tail保留" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tailRev">
              <a-input v-model="model.tailRev" placeholder="请输入tail保留"  ></a-input>
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
    name: 'CdrManageForm',
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
          add: "/cdrManage/cdrManage/add",
          edit: "/cdrManage/cdrManage/edit",
          queryById: "/cdrManage/cdrManage/queryById"
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