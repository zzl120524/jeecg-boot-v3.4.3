<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('授权确认')">导出</a-button>
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

    <auth-ack-modal ref="modalForm" @ok="modalFormOk"></auth-ack-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import AuthAckModal from './modules/AuthAckModal'

  export default {
    name: 'AuthAckList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      AuthAckModal
    },
    data () {
      return {
        description: '授权确认管理页面',
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
            title:'facId',
            align:"center",
            dataIndex: 'facId'
          },
          {
            title:'控制接口tcp链路连接地址',
            align:"center",
            dataIndex: 'controlifIp'
          },
          {
            title:'控制接口tcp链路连接port',
            align:"center",
            dataIndex: 'controlifPort'
          },
          {
            title:'信令流接受接口UDP连接地址',
            align:"center",
            dataIndex: 'signalPacketReportIp'
          },
          {
            title:'信令流接受接口UDP连接port',
            align:"center",
            dataIndex: 'signalPacketReportPort'
          },
          {
            title:'跟踪用户或ip流的UDP连接地址',
            align:"center",
            dataIndex: 'tracePacketReportIp'
          },
          {
            title:'跟踪用户或ip流的UDP连接端口',
            align:"center",
            dataIndex: 'tracePacketReportUdp'
          },
          {
            title:'-',
            align:"center",
            dataIndex: 'encryptPacketReportIp'
          },
          {
            title:'-',
            align:"center",
            dataIndex: 'encryptPacketReportUdp'
          },
          {
            title:'-',
            align:"center",
            dataIndex: 'malformedPacketReportIp'
          },
          {
            title:'-',
            align:"center",
            dataIndex: 'malformedPacketReportUdp'
          },
          {
            title:'ftpUser名称',
            align:"center",
            dataIndex: 'cdrFtpUser'
          },
          {
            title:'ftp密码',
            align:"center",
            dataIndex: 'cdrFtpKey'
          },
          {
            title:'ftp文件路径',
            align:"center",
            dataIndex: 'ftpDirectory'
          },
          {
            title:'-',
            align:"center",
            dataIndex: 'ftpServerIp'
          },
          {
            title:'-',
            align:"center",
            dataIndex: 'mic'
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
          list: "/authAck/authAck/list",
          delete: "/authAck/authAck/delete",
          deleteBatch: "/authAck/authAck/deleteBatch",
          exportXlsUrl: "/authAck/authAck/exportXls",
          importExcelUrl: "authAck/authAck/importExcel",
          
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
        fieldList.push({type:'string',value:'facId',text:'facId',dictCode:''})
        fieldList.push({type:'string',value:'controlifIp',text:'控制接口tcp链路连接地址',dictCode:''})
        fieldList.push({type:'string',value:'controlifPort',text:'控制接口tcp链路连接port',dictCode:''})
        fieldList.push({type:'string',value:'signalPacketReportIp',text:'信令流接受接口UDP连接地址',dictCode:''})
        fieldList.push({type:'string',value:'signalPacketReportPort',text:'信令流接受接口UDP连接port',dictCode:''})
        fieldList.push({type:'string',value:'tracePacketReportIp',text:'跟踪用户或ip流的UDP连接地址',dictCode:''})
        fieldList.push({type:'string',value:'tracePacketReportUdp',text:'跟踪用户或ip流的UDP连接端口',dictCode:''})
        fieldList.push({type:'string',value:'encryptPacketReportIp',text:'-',dictCode:''})
        fieldList.push({type:'string',value:'encryptPacketReportUdp',text:'-',dictCode:''})
        fieldList.push({type:'string',value:'malformedPacketReportIp',text:'-',dictCode:''})
        fieldList.push({type:'string',value:'malformedPacketReportUdp',text:'-',dictCode:''})
        fieldList.push({type:'string',value:'cdrFtpUser',text:'ftpUser名称',dictCode:''})
        fieldList.push({type:'string',value:'cdrFtpKey',text:'ftp密码',dictCode:''})
        fieldList.push({type:'string',value:'ftpDirectory',text:'ftp文件路径',dictCode:''})
        fieldList.push({type:'string',value:'ftpServerIp',text:'-',dictCode:''})
        fieldList.push({type:'string',value:'mic',text:'-',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>