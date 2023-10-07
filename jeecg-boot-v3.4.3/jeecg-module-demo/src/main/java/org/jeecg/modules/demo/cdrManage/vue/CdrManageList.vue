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
      <a-button type="primary" icon="download" @click="handleExportXls('CDR文件管理')">导出</a-button>
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

    <cdr-manage-modal ref="modalForm" @ok="modalFormOk"></cdr-manage-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CdrManageModal from './modules/CdrManageModal'

  export default {
    name: 'CdrManageList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      CdrManageModal
    },
    data () {
      return {
        description: 'CDR文件管理管理页面',
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
            title:'头记录类型',
            align:"center",
            dataIndex: 'headRecType'
          },
          {
            title:'FACID',
            align:"center",
            dataIndex: 'facId'
          },
          {
            title:'CDR文件生成时间',
            align:"center",
            dataIndex: 'startTime'
          },
          {
            title:'CDR文件序列号',
            align:"center",
            dataIndex: 'fileSeq'
          },
          {
            title:'序列号',
            align:"center",
            dataIndex: 'version'
          },
          {
            title:'保留',
            align:"center",
            dataIndex: 'rev'
          },
          {
            title:'CDR类型',
            align:"center",
            dataIndex: 'cdrRecType'
          },
          {
            title:'编号',
            align:"center",
            dataIndex: 'cdrNo'
          },
          {
            title:'关联id',
            align:"center",
            dataIndex: 'relationId'
          },
          {
            title:'主叫号码',
            align:"center",
            dataIndex: 'callingPartyNumber'
          },
          {
            title:'主叫号码类型',
            align:"center",
            dataIndex: 'callingPartyNumberType'
          },
          {
            title:'被叫号码',
            align:"center",
            dataIndex: 'calledPartyNumber'
          },
          {
            title:'被叫号码类型',
            align:"center",
            dataIndex: 'calledPartyNumberType'
          },
          {
            title:'位置有效性',
            align:"center",
            dataIndex: 'paraFlag'
          },
          {
            title:'主叫位置',
            align:"center",
            dataIndex: 'callingLocation'
          },
          {
            title:'主叫位置类型',
            align:"center",
            dataIndex: 'callingLocationType'
          },
          {
            title:'被叫位置',
            align:"center",
            dataIndex: 'calledLocation'
          },
          {
            title:'被叫位置类型',
            align:"center",
            dataIndex: 'calledLocationType'
          },
          {
            title:'前向呼叫历史号码',
            align:"center",
            dataIndex: 'fwHistoryInfoNum'
          },
          {
            title:'前向呼叫历史原因',
            align:"center",
            dataIndex: 'fwHistoryInfoCause'
          },
          {
            title:'后向呼叫历史号码',
            align:"center",
            dataIndex: 'bwHistoryInfoNum'
          },
          {
            title:'后向呼叫历史原因',
            align:"center",
            dataIndex: 'bwHistoryInfoCause'
          },
          {
            title:'呼叫开始时间',
            align:"center",
            dataIndex: 'cdrStartTime'
          },
          {
            title:'呼叫振铃时间',
            align:"center",
            dataIndex: 'alertTime'
          },
          {
            title:'呼叫通话时间',
            align:"center",
            dataIndex: 'answerTime'
          },
          {
            title:'呼叫释放时间',
            align:"center",
            dataIndex: 'releaseTime'
          },
          {
            title:'呼叫释放原因',
            align:"center",
            dataIndex: 'releaseCause'
          },
          {
            title:'前向媒体流中断事件数量',
            align:"center",
            dataIndex: 'fwMediainterrupEventNum'
          },
          {
            title:'后向媒体流中断事件数量',
            align:"center",
            dataIndex: 'bwMediainterrupEventNum'
          },
          {
            title:'前向媒体流丢包率',
            align:"center",
            dataIndex: 'fwMediaFlowLossRate'
          },
          {
            title:'后向媒体流丢包率',
            align:"center",
            dataIndex: 'bwMediaFlowLossRate'
          },
          {
            title:'事件1',
            align:"center",
            dataIndex: 'mediaEventType1Num'
          },
          {
            title:'事件2',
            align:"center",
            dataIndex: 'mediaEventType2Num'
          },
          {
            title:'事件3',
            align:"center",
            dataIndex: 'mediaEventType3Num'
          },
          {
            title:'事件4',
            align:"center",
            dataIndex: 'mediaEventType4Num'
          },
          {
            title:'事件5',
            align:"center",
            dataIndex: 'mediaEventType5Num'
          },
          {
            title:'事件6',
            align:"center",
            dataIndex: 'mediaEventType6Num'
          },
          {
            title:'事件7',
            align:"center",
            dataIndex: 'mediaEventType7Num'
          },
          {
            title:'事件8',
            align:"center",
            dataIndex: 'mediaEventType8Num'
          },
          {
            title:'事件9',
            align:"center",
            dataIndex: 'mediaEventType9Num'
          },
          {
            title:'事件9',
            align:"center",
            dataIndex: 'mediaEventType10Num'
          },
          {
            title:'CDR保留',
            align:"center",
            dataIndex: 'cdrRev'
          },
          {
            title:'tail类型',
            align:"center",
            dataIndex: 'tailRecType'
          },
          {
            title:'tail结束时间',
            align:"center",
            dataIndex: 'tailEndTime'
          },
          {
            title:'CDR数量',
            align:"center",
            dataIndex: 'cdrRecordNum'
          },
          {
            title:'文件关闭原因',
            align:"center",
            dataIndex: 'fileCloseCause'
          },
          {
            title:'tail保留',
            align:"center",
            dataIndex: 'tailRev'
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
          list: "/cdrManage/cdrManage/list",
          delete: "/cdrManage/cdrManage/delete",
          deleteBatch: "/cdrManage/cdrManage/deleteBatch",
          exportXlsUrl: "/cdrManage/cdrManage/exportXls",
          importExcelUrl: "cdrManage/cdrManage/importExcel",
          
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
        fieldList.push({type:'int',value:'headRecType',text:'头记录类型',dictCode:''})
        fieldList.push({type:'int',value:'facId',text:'FACID',dictCode:''})
        fieldList.push({type:'string',value:'startTime',text:'CDR文件生成时间',dictCode:''})
        fieldList.push({type:'string',value:'fileSeq',text:'CDR文件序列号',dictCode:''})
        fieldList.push({type:'int',value:'version',text:'序列号',dictCode:''})
        fieldList.push({type:'string',value:'rev',text:'保留',dictCode:''})
        fieldList.push({type:'int',value:'cdrRecType',text:'CDR类型',dictCode:''})
        fieldList.push({type:'int',value:'cdrNo',text:'编号',dictCode:''})
        fieldList.push({type:'int',value:'relationId',text:'关联id',dictCode:''})
        fieldList.push({type:'string',value:'callingPartyNumber',text:'主叫号码',dictCode:''})
        fieldList.push({type:'int',value:'callingPartyNumberType',text:'主叫号码类型',dictCode:''})
        fieldList.push({type:'string',value:'calledPartyNumber',text:'被叫号码',dictCode:''})
        fieldList.push({type:'int',value:'calledPartyNumberType',text:'被叫号码类型',dictCode:''})
        fieldList.push({type:'string',value:'paraFlag',text:'位置有效性',dictCode:''})
        fieldList.push({type:'string',value:'callingLocation',text:'主叫位置',dictCode:''})
        fieldList.push({type:'int',value:'callingLocationType',text:'主叫位置类型',dictCode:''})
        fieldList.push({type:'string',value:'calledLocation',text:'被叫位置',dictCode:''})
        fieldList.push({type:'int',value:'calledLocationType',text:'被叫位置类型',dictCode:''})
        fieldList.push({type:'string',value:'fwHistoryInfoNum',text:'前向呼叫历史号码',dictCode:''})
        fieldList.push({type:'string',value:'fwHistoryInfoCause',text:'前向呼叫历史原因',dictCode:''})
        fieldList.push({type:'string',value:'bwHistoryInfoNum',text:'后向呼叫历史号码',dictCode:''})
        fieldList.push({type:'string',value:'bwHistoryInfoCause',text:'后向呼叫历史原因',dictCode:''})
        fieldList.push({type:'string',value:'cdrStartTime',text:'呼叫开始时间',dictCode:''})
        fieldList.push({type:'string',value:'alertTime',text:'呼叫振铃时间',dictCode:''})
        fieldList.push({type:'string',value:'answerTime',text:'呼叫通话时间',dictCode:''})
        fieldList.push({type:'string',value:'releaseTime',text:'呼叫释放时间',dictCode:''})
        fieldList.push({type:'string',value:'releaseCause',text:'呼叫释放原因',dictCode:''})
        fieldList.push({type:'int',value:'fwMediainterrupEventNum',text:'前向媒体流中断事件数量',dictCode:''})
        fieldList.push({type:'int',value:'bwMediainterrupEventNum',text:'后向媒体流中断事件数量',dictCode:''})
        fieldList.push({type:'string',value:'fwMediaFlowLossRate',text:'前向媒体流丢包率',dictCode:''})
        fieldList.push({type:'string',value:'bwMediaFlowLossRate',text:'后向媒体流丢包率',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType1Num',text:'事件1',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType2Num',text:'事件2',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType3Num',text:'事件3',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType4Num',text:'事件4',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType5Num',text:'事件5',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType6Num',text:'事件6',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType7Num',text:'事件7',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType8Num',text:'事件8',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType9Num',text:'事件9',dictCode:''})
        fieldList.push({type:'int',value:'mediaEventType10Num',text:'事件9',dictCode:''})
        fieldList.push({type:'string',value:'cdrRev',text:'CDR保留',dictCode:''})
        fieldList.push({type:'int',value:'tailRecType',text:'tail类型',dictCode:''})
        fieldList.push({type:'string',value:'tailEndTime',text:'tail结束时间',dictCode:''})
        fieldList.push({type:'int',value:'cdrRecordNum',text:'CDR数量',dictCode:''})
        fieldList.push({type:'int',value:'fileCloseCause',text:'文件关闭原因',dictCode:''})
        fieldList.push({type:'string',value:'tailRev',text:'tail保留',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>