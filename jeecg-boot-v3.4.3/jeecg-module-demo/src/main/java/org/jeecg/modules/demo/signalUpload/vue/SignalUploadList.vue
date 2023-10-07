<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="FACID">
              <a-input placeholder="请输入FACID" v-model="queryParam.facId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="上传接口类型">
              <a-input placeholder="请输入上传接口类型" v-model="queryParam.uploadType"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="跟踪用户">
                <a-input placeholder="请输入跟踪用户" v-model="queryParam.traceUser"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="消息类型">
                <a-input placeholder="请输入消息类型" v-model="queryParam.msgType"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="流方向">
                <a-input placeholder="请输入流方向" v-model="queryParam.trafficeDirection"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="RTP报文CODEC类型">
                <a-input placeholder="请输入RTP报文CODEC类型" v-model="queryParam.codec"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="关联ID">
                <a-input placeholder="请输入关联ID" v-model="queryParam.relationId"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="复制报文类型">
                <a-input placeholder="请输入复制报文类型" v-model="queryParam.signalType"></a-input>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="异常报文类型">
                <a-input placeholder="请输入异常报文类型" v-model="queryParam.malformedType"></a-input>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('信令上传')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text,record">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" :preview="record.id" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <signal-upload-modal ref="modalForm" @ok="modalFormOk"></signal-upload-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import SignalUploadModal from './modules/SignalUploadModal'

  export default {
    name: 'SignalUploadList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      SignalUploadModal
    },
    data () {
      return {
        description: '信令上传管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'版本号',
            align:"center",
            dataIndex: 'ver'
          },
          {
            title:'FACID',
            align:"center",
            dataIndex: 'facId'
          },
          {
            title:'上传接口类型',
            align:"center",
            dataIndex: 'uploadType'
          },
          {
            title:'上传总长度',
            align:"center",
            dataIndex: 'uploadLength'
          },
          {
            title:'跟踪用户',
            align:"center",
            dataIndex: 'traceUser'
          },
          {
            title:'消息类型',
            align:"center",
            dataIndex: 'msgType'
          },
          {
            title:'信令长度',
            align:"center",
            dataIndex: 'signalLength'
          },
          {
            title:'信号',
            align:"center",
            dataIndex: 'sigal'
          },
          {
            title:'流方向',
            align:"center",
            dataIndex: 'trafficeDirection'
          },
          {
            title:'RTP报文CODEC类型',
            align:"center",
            dataIndex: 'codec'
          },
          {
            title:'RTPt报文长度',
            align:"center",
            dataIndex: 'rtpPacketLen'
          },
          {
            title:'RTP报文',
            align:"center",
            dataIndex: 'rtpPacket'
          },
          {
            title:'关联ID',
            align:"center",
            dataIndex: 'relationId'
          },
          {
            title:'复制报文类型',
            align:"center",
            dataIndex: 'signalType'
          },
          {
            title:'信令报文长度',
            align:"center",
            dataIndex: 'signalPacketLen'
          },
          {
            title:'信令报文内容',
            align:"center",
            dataIndex: 'signalPacket'
          },
          {
            title:'异常报文类型',
            align:"center",
            dataIndex: 'malformedType'
          },
          {
            title:'异常报文长度',
            align:"center",
            dataIndex: 'packetlen'
          },
          {
            title:'异常报文',
            align:"center",
            dataIndex: 'packet'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/signalUpload/signalUpload/list",
          delete: "/signalUpload/signalUpload/delete",
          deleteBatch: "/signalUpload/signalUpload/deleteBatch",
          exportXlsUrl: "/signalUpload/signalUpload/exportXls",
          importExcelUrl: "signalUpload/signalUpload/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'ver',text:'版本号',dictCode:''})
        fieldList.push({type:'int',value:'facId',text:'FACID',dictCode:''})
        fieldList.push({type:'int',value:'uploadType',text:'上传接口类型',dictCode:''})
        fieldList.push({type:'int',value:'uploadLength',text:'上传总长度',dictCode:''})
        fieldList.push({type:'string',value:'traceUser',text:'跟踪用户',dictCode:''})
        fieldList.push({type:'int',value:'msgType',text:'消息类型',dictCode:''})
        fieldList.push({type:'int',value:'signalLength',text:'信令长度',dictCode:''})
        fieldList.push({type:'string',value:'sigal',text:'信号',dictCode:''})
        fieldList.push({type:'int',value:'trafficeDirection',text:'流方向',dictCode:''})
        fieldList.push({type:'int',value:'codec',text:'RTP报文CODEC类型',dictCode:''})
        fieldList.push({type:'int',value:'rtpPacketLen',text:'RTPt报文长度',dictCode:''})
        fieldList.push({type:'string',value:'rtpPacket',text:'RTP报文',dictCode:''})
        fieldList.push({type:'int',value:'relationId',text:'关联ID',dictCode:''})
        fieldList.push({type:'int',value:'signalType',text:'复制报文类型',dictCode:''})
        fieldList.push({type:'int',value:'signalPacketLen',text:'信令报文长度',dictCode:''})
        fieldList.push({type:'string',value:'signalPacket',text:'信令报文内容',dictCode:''})
        fieldList.push({type:'int',value:'malformedType',text:'异常报文类型',dictCode:''})
        fieldList.push({type:'int',value:'packetlen',text:'异常报文长度',dictCode:''})
        fieldList.push({type:'string',value:'packet',text:'异常报文',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>