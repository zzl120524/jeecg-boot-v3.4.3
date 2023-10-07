package org.jeecg.modules.demo.cdrManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdt.omap.protocol.cdr.CDRFile;
import com.sdt.omap.protocol.cdr.CDRRecordSip;
import com.sdt.omap.protocol.cdr.HeadRecord;
import com.sdt.omap.protocol.cdr.TailRecord;
import com.sdt.omap.protocol.enumUtil.ParaFlagType;
import com.sdt.omap.protocol.enumUtil.RecType;
import com.sdt.omap.protocol.enumUtil.ReleaseCauseType;
import com.sdt.omap.utils.BCDUtils;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.common.util.ByteConversionUtils;
import org.jeecg.modules.demo.cdrManage.entity.CdrFile;
import org.jeecg.modules.demo.cdrManage.entity.CdrManage;
import org.jeecg.modules.demo.cdrManage.entity.CdrManageVo;
import org.jeecg.modules.demo.cdrManage.mapper.CdrFileMapper;
import org.jeecg.modules.demo.cdrManage.mapper.CdrManageMapper;
import org.jeecg.modules.demo.cdrManage.service.ICdrManageService;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;
import org.jeecg.modules.demo.workLog.entity.WorkLog;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: CDR文件管理
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class CdrManageServiceImpl extends ServiceImpl<CdrManageMapper, CdrManage> implements ICdrManageService {

    @Autowired
    private CdrFileMapper cdrFileMapper;

    @Value("${ftpPath}")
    private String path;

    @Autowired
    private WorkLogMapper workLogMapper;

    @Override
    public void analysis(File f) throws Exception {
        List<CdrManage> list = readCDRFile(f);
        saveBatch(list);
    }

    public List<CdrManage> readCDRFile(File f) throws Exception {
        InputStream inputStream = new FileInputStream(f);
        //创建字节数组存储数据
        byte[] bytes = new byte[(int)f.length()];
        inputStream.read(bytes);//把文件里的数据 读入 内存数组bytes
        inputStream.close();
        CDRFile cdrFile = new CDRFile();
        cdrFile = cdrFile.parse(bytes);
        ArrayList<CdrManage> list = new ArrayList<>();
        HeadRecord headRecord = cdrFile.getHeadRecord();
        TailRecord tailRecord = cdrFile.getTailRecord();
        List<CDRRecordSip> recordSipList = cdrFile.getRecordSipList();
        for (CDRRecordSip cdrRecordSip : recordSipList) {
            CdrManage cdrFileUpload = new CdrManage();

            cdrFileUpload.setHeadRecType(ByteUtil.byte2tinyint(headRecord.getRecType()));//1b
            cdrFileUpload.setFacId(ByteUtil.bytes2smallint(headRecord.getFacId(), 0));//2b
            String startTime = BCDUtils.formatStr(BCDUtils.byte2str(headRecord.getStartTime()));
            cdrFileUpload.setStartTime(startTime);//7b
            int fileSeq = ByteUtil.bytes2smallint(headRecord.getFileSeq(), 0);
            cdrFileUpload.setFileSeq(fileSeqFormat(fileSeq));//2b
            cdrFileUpload.setVersion(ByteUtil.byte2tinyint(headRecord.getVersion()));//1b
//            cdrFileUpload.setRev(new String(headRecord.getRev(),"UTF-8"));//49b
            cdrFileUpload.setRev(ByteUtil.byteToPin(headRecord.getRev()));//49b


            int cdrRecType = ByteUtil.byte2tinyint(cdrRecordSip.getRecType());
            cdrFileUpload.setCdrRecType(RecType.unknown(cdrRecType));//1b
            cdrFileUpload.setCdrNo(ByteUtil.bytes2int(cdrRecordSip.getCdrNo(),0));//4b
            cdrFileUpload.setRelationId(ByteConversionUtils.bytesToLongBig(cdrRecordSip.getRelationId()));//8b
            byte[] callingPartyNumber = cdrRecordSip.getCallingPartyNumber();
//            byte callingPartyNumberType = callingPartyNumber[0];
//            byte[] callingNum = new byte[7];
//            System.arraycopy(callingPartyNumber,1,callingNum,0,7);
            cdrFileUpload.setCallingPartyNumber(BCDUtils.bcd2str(callingPartyNumber));//7b
//            cdrFileUpload.setCallingPartyNumberType(ByteUtil.byte2tinyint(callingPartyNumberType));//1b
            byte[] calledPartyNumber = cdrRecordSip.getCalledPartyNumber();
//            byte calledPartyNumberType = calledPartyNumber[0];
//            byte[] calledNum = new byte[7];
//            System.arraycopy(calledPartyNumber,1,calledNum,0,7);
            cdrFileUpload.setCalledPartyNumber(BCDUtils.bcd2str(calledPartyNumber));//7b
//            cdrFileUpload.setCalledPartyNumberType(ByteUtil.byte2tinyint(calledPartyNumberType));//1b

            byte paraFlag1 = cdrRecordSip.getParaFlag();
//                System.out.println(ByteUtil.byte2HexString0x(paraFlag1, true));
            int paraFlag = ByteUtil.byte2tinyint(cdrRecordSip.getParaFlag());
            cdrFileUpload.setParaFlag(ParaFlagType.unknown(paraFlag));//1b


            byte[] callingLocation = cdrRecordSip.getCallingLocation();
            byte callingLocationType = callingLocation[0];
            byte[] callingLocationStr = new byte[32];
            System.arraycopy(callingLocation,1,callingLocationStr,0,32);
            cdrFileUpload.setCallingLocationType(ByteUtil.byte2tinyint(callingLocationType));//1b
//            cdrFileUpload.setCallingLocation(new String(callingLocationStr,"UTF-8"));//32b
            String callingLocationSting = ByteUtil.byteToPin(callingLocationStr);
            if(callingLocationSting.startsWith("0000")){
                cdrFileUpload.setCallingLocation("");//32b
            }else {
                cdrFileUpload.setCallingLocation(ByteUtil.byteToPin(callingLocationStr));//32b
            }


            byte[] calledLocation = cdrRecordSip.getCalledLocation();
            byte calledLocationType = calledLocation[0];
            byte[] calledLocationStr = new byte[32];
            System.arraycopy(calledLocation,1,calledLocationStr,0,32);
            cdrFileUpload.setCalledLocationType(ByteUtil.byte2tinyint(calledLocationType));//1b
//            cdrFileUpload.setCalledLocation(new String(calledLocationStr,"UTF-8"));//32b
            String calledLocationString = ByteUtil.byteToPin(calledLocationStr);
            if(calledLocationString.startsWith("0000")){
                cdrFileUpload.setCalledLocation("");//32b
            }else {
                cdrFileUpload.setCalledLocation(ByteUtil.byteToPin(calledLocationStr));//32b
            }

            byte[] fwHistoryInfo = cdrRecordSip.getFwHistoryInfo();
            byte[] fwHistoryInfoNumber = new byte[24];
            byte[] fwHistoryInfoCause = new byte[2];
            System.arraycopy(fwHistoryInfo,0,fwHistoryInfoNumber,0,24);
            System.arraycopy(fwHistoryInfo,24,fwHistoryInfoCause,0,2);
            String fwHistoryInfoNumberStr = BCDUtils.bcd2str(fwHistoryInfoNumber);
            if(fwHistoryInfoNumberStr.startsWith("0000")){
                cdrFileUpload.setFwHistoryInfoNum("");//32b
            }else {
                cdrFileUpload.setFwHistoryInfoNum(BCDUtils.bcd2str(fwHistoryInfoNumber));//7b
            }
//            cdrFileUpload.setFwHistoryInfoCause(new String(fwHistoryInfoCause,"UTF-8"));//2b
            cdrFileUpload.setFwHistoryInfoCause(ByteUtil.byteToPin(fwHistoryInfoCause));//2b

            byte[] bwHistoryInfo = cdrRecordSip.getBwHistoryInfo();
            byte[] bwHistoryInfoNumber = new byte[24];
            byte[] bwHistoryInfoCause = new byte[2];
            System.arraycopy(bwHistoryInfo,0,bwHistoryInfoNumber,0,24);
            System.arraycopy(bwHistoryInfo,24,bwHistoryInfoCause,0,2);
            String bwHistoryInfoNumberStr = BCDUtils.bcd2str(bwHistoryInfoNumber);
            if(bwHistoryInfoNumberStr.startsWith("0000")){
                cdrFileUpload.setBwHistoryInfoNum("");//32b
            }else {
                cdrFileUpload.setBwHistoryInfoNum(BCDUtils.bcd2str(bwHistoryInfoNumber));//8b
            }
//            cdrFileUpload.setBwHistoryInfoCause(new String(bwHistoryInfoCause,"UTF-8"));//2b
            cdrFileUpload.setBwHistoryInfoCause(ByteUtil.byteToPin(bwHistoryInfoCause));//2b

            String cdrStartTime = BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getStartTime()));
            cdrFileUpload.setCdrStartTime(cdrStartTime);//7b
            String alertTime = BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getAlertTime()));
            cdrFileUpload.setAlertTime(alertTime);//7b
            cdrFileUpload.setAnswerTime(BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getAnswerTime())));//7b
            cdrFileUpload.setReleaseTime(BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getReleaseTime())));//7b

            int releaseCause = ByteUtil.bytes2smallint(cdrRecordSip.getReleaseCause(), 0);
            cdrFileUpload.setReleaseCause(ReleaseCauseType.unknown(releaseCause));//2b

            cdrFileUpload.setFwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getFwMediaInterrupEventNum()));//1b
            cdrFileUpload.setBwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getBwMediaInterrupEventNum()));//1b

            int fwMediaFlowLoss = ByteUtil.bytes2smallint(cdrRecordSip.getFwMediaFlowLossRate(), 0);
            int bwMediaFlowLoss = ByteUtil.bytes2smallint(cdrRecordSip.getBwMediaFlowLossRate(), 0);
            cdrFileUpload.setFwMediaFlowLossRate(fwMediaFlowLoss/100.0 + "%");//2b
            cdrFileUpload.setBwMediaFlowLossRate(bwMediaFlowLoss/100.0 + "%");//2b
            cdrFileUpload.setMediaEventType1Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType1Num(),0));//2b
            cdrFileUpload.setMediaEventType2Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType2Num(),0));//2b
            cdrFileUpload.setMediaEventType3Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType3Num(),0));//2b
            cdrFileUpload.setMediaEventType4Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType4Num(),0));//2b
            cdrFileUpload.setMediaEventType5Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType5Num(),0));//2b
            cdrFileUpload.setMediaEventType6Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType6Num(),0));//2b
            cdrFileUpload.setMediaEventType7Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType7Num(),0));//2b
            cdrFileUpload.setMediaEventType8Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType8Num(),0));//2b
            cdrFileUpload.setMediaEventType9Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType9Num(),0));//2b
            cdrFileUpload.setMediaEventType10Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType10Num(),0));//2b
            cdrFileUpload.setCdrRev(ByteUtil.byteToPin(cdrRecordSip.getRev()));//26b


            cdrFileUpload.setTailRecType(ByteUtil.byte2tinyint(tailRecord.getRecType()));//1b

            String endTime = BCDUtils.formatStr(BCDUtils.byte2str(tailRecord.getEndTime()));
            cdrFileUpload.setTailEndTime(endTime);//7b
            cdrFileUpload.setCdrRecordNum(ByteConversionUtils.bytes2IntBig(tailRecord.getCdrRecordNum()));//4b
            cdrFileUpload.setFileCloseCause(ByteUtil.byte2tinyint(tailRecord.getFileCloseCause()));//1b
//            cdrFileUpload.setTailRev(new String(tailRecord.getRev(),"UTF-8"));//49b
            cdrFileUpload.setTailRev(ByteUtil.byteToPin(tailRecord.getRev()));//49b

            list.add(cdrFileUpload);
        }
        return list;
    }

    @Override
    public void readFile(File f) throws Exception {
        CdrFile cdrName = new CdrFile();
        cdrName.setCdrFileName(f.getName());
        cdrFileMapper.insert(cdrName);

        InputStream inputStream = new FileInputStream(f);
        //创建字节数组存储数据
        byte[] bytes = new byte[(int)f.length()];
        inputStream.read(bytes);//把文件里的数据 读入 内存数组bytes
        inputStream.close();
        CDRFile cdrFile = new CDRFile();
        cdrFile = cdrFile.parse(bytes);
        ArrayList<CdrManage> list = new ArrayList<>();
        HeadRecord headRecord = cdrFile.getHeadRecord();
        TailRecord tailRecord = cdrFile.getTailRecord();
        List<CDRRecordSip> recordSipList = cdrFile.getRecordSipList();
        for (CDRRecordSip cdrRecordSip : recordSipList) {
            CdrManage cdrFileUpload = new CdrManage();
            cdrFileUpload.setCdrFileId(cdrName.getId());
            cdrFileUpload.setHeadRecType(ByteUtil.byte2tinyint(headRecord.getRecType()));//1b
            cdrFileUpload.setFacId(ByteUtil.bytes2smallint(headRecord.getFacId(), 0));//2b
            String startTime = BCDUtils.formatStr(BCDUtils.byte2str(headRecord.getStartTime()));
            cdrFileUpload.setStartTime(startTime);//7b
            int fileSeq = ByteUtil.bytes2smallint(headRecord.getFileSeq(), 0);
            cdrFileUpload.setFileSeq(fileSeqFormat(fileSeq));//2b
            cdrFileUpload.setVersion(ByteUtil.byte2tinyint(headRecord.getVersion()));//1b
//            cdrFileUpload.setRev(new String(headRecord.getRev(),"UTF-8"));//49b
            cdrFileUpload.setRev(ByteUtil.byteToPin(headRecord.getRev()));//49b


            int cdrRecType = ByteUtil.byte2tinyint(cdrRecordSip.getRecType());
            cdrFileUpload.setCdrRecType(RecType.unknown(cdrRecType));//1b
            cdrFileUpload.setCdrNo(ByteUtil.bytes2int(cdrRecordSip.getCdrNo(),0));//4b
            cdrFileUpload.setRelationId(ByteConversionUtils.bytesToLongBig(cdrRecordSip.getRelationId()));//8b
            byte[] callingPartyNumber = cdrRecordSip.getCallingPartyNumber();
//            byte callingPartyNumberType = callingPartyNumber[0];
//            byte[] callingNum = new byte[7];
//            System.arraycopy(callingPartyNumber,1,callingNum,0,7);
            cdrFileUpload.setCallingPartyNumber(BCDUtils.bcd2str(callingPartyNumber));//7b
//            cdrFileUpload.setCallingPartyNumberType(ByteUtil.byte2tinyint(callingPartyNumberType));//1b
            byte[] calledPartyNumber = cdrRecordSip.getCalledPartyNumber();
//            byte calledPartyNumberType = calledPartyNumber[0];
//            byte[] calledNum = new byte[7];
//            System.arraycopy(calledPartyNumber,1,calledNum,0,7);
            cdrFileUpload.setCalledPartyNumber(BCDUtils.bcd2str(calledPartyNumber));//7b
//            cdrFileUpload.setCalledPartyNumberType(ByteUtil.byte2tinyint(calledPartyNumberType));//1b

            byte paraFlag1 = cdrRecordSip.getParaFlag();
//                System.out.println(ByteUtil.byte2HexString0x(paraFlag1, true));
            int paraFlag = ByteUtil.byte2tinyint(cdrRecordSip.getParaFlag());
            cdrFileUpload.setParaFlag(ParaFlagType.unknown(paraFlag));//1b

            byte[] callingLocation = cdrRecordSip.getCallingLocation();
            byte callingLocationType = callingLocation[0];
            byte[] callingLocationStr = new byte[32];
            System.arraycopy(callingLocation,1,callingLocationStr,0,32);
            cdrFileUpload.setCallingLocationType(ByteUtil.byte2tinyint(callingLocationType));//1b
//            cdrFileUpload.setCallingLocation(new String(callingLocationStr,"UTF-8"));//32b
            cdrFileUpload.setCallingLocation(ByteUtil.byteToPin(callingLocationStr));//32b

            byte[] calledLocation = cdrRecordSip.getCalledLocation();
            byte calledLocationType = calledLocation[0];
            byte[] calledLocationStr = new byte[32];
            System.arraycopy(calledLocation,1,calledLocationStr,0,32);
            cdrFileUpload.setCalledLocationType(ByteUtil.byte2tinyint(calledLocationType));//1b
//            cdrFileUpload.setCalledLocation(new String(calledLocationStr,"UTF-8"));//32b
            cdrFileUpload.setCalledLocation(ByteUtil.byteToPin(calledLocationStr));//32b

            byte[] fwHistoryInfo = cdrRecordSip.getFwHistoryInfo();
            byte[] fwHistoryInfoNumber = new byte[24];
            byte[] fwHistoryInfoCause = new byte[2];
            System.arraycopy(fwHistoryInfo,0,fwHistoryInfoNumber,0,24);
            System.arraycopy(fwHistoryInfo,24,fwHistoryInfoCause,0,2);
            cdrFileUpload.setFwHistoryInfoNum(BCDUtils.bcd2str(fwHistoryInfoNumber));//7b
//            cdrFileUpload.setFwHistoryInfoCause(new String(fwHistoryInfoCause,"UTF-8"));//2b
            cdrFileUpload.setFwHistoryInfoCause(ByteUtil.byteToPin(fwHistoryInfoCause));//2b

            byte[] bwHistoryInfo = cdrRecordSip.getBwHistoryInfo();
            byte[] bwHistoryInfoNumber = new byte[24];
            byte[] bwHistoryInfoCause = new byte[2];
            System.arraycopy(bwHistoryInfo,0,bwHistoryInfoNumber,0,24);
            System.arraycopy(bwHistoryInfo,24,bwHistoryInfoCause,0,2);
            cdrFileUpload.setBwHistoryInfoNum(BCDUtils.bcd2str(bwHistoryInfoNumber));//8b
//            cdrFileUpload.setBwHistoryInfoCause(new String(bwHistoryInfoCause,"UTF-8"));//2b
            cdrFileUpload.setBwHistoryInfoCause(ByteUtil.byteToPin(bwHistoryInfoCause));//2b

            String cdrStartTime = BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getStartTime()));
            cdrFileUpload.setCdrStartTime(cdrStartTime);//7b
            String alertTime = BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getAlertTime()));
            cdrFileUpload.setAlertTime(alertTime);//7b
            cdrFileUpload.setAnswerTime(BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getAnswerTime())));//7b
            cdrFileUpload.setReleaseTime(BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getReleaseTime())));//7b

            int releaseCause = ByteUtil.bytes2smallint(cdrRecordSip.getReleaseCause(), 0);
            cdrFileUpload.setReleaseCause(ReleaseCauseType.unknown(releaseCause));//2b

            cdrFileUpload.setFwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getFwMediaInterrupEventNum()));//1b
            cdrFileUpload.setBwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getBwMediaInterrupEventNum()));//1b

            int fwMediaFlowLoss = ByteUtil.bytes2smallint(cdrRecordSip.getFwMediaFlowLossRate(), 0);
            int bwMediaFlowLoss = ByteUtil.bytes2smallint(cdrRecordSip.getBwMediaFlowLossRate(), 0);
            cdrFileUpload.setFwMediaFlowLossRate(fwMediaFlowLoss/100.0 + "%");//2b
            cdrFileUpload.setBwMediaFlowLossRate(bwMediaFlowLoss/100.0 + "%");//2b
            cdrFileUpload.setMediaEventType1Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType1Num(),0));//2b
            cdrFileUpload.setMediaEventType2Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType2Num(),0));//2b
            cdrFileUpload.setMediaEventType3Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType3Num(),0));//2b
            cdrFileUpload.setMediaEventType4Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType4Num(),0));//2b
            cdrFileUpload.setMediaEventType5Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType5Num(),0));//2b
            cdrFileUpload.setMediaEventType6Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType6Num(),0));//2b
            cdrFileUpload.setMediaEventType7Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType7Num(),0));//2b
            cdrFileUpload.setMediaEventType8Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType8Num(),0));//2b
            cdrFileUpload.setMediaEventType9Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType9Num(),0));//2b
            cdrFileUpload.setMediaEventType10Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType10Num(),0));//2b
            cdrFileUpload.setCdrRev(ByteUtil.byteToPin(cdrRecordSip.getRev()));//26b


            cdrFileUpload.setTailRecType(ByteUtil.byte2tinyint(tailRecord.getRecType()));//1b

            String endTime = BCDUtils.formatStr(BCDUtils.byte2str(tailRecord.getEndTime()));
            cdrFileUpload.setTailEndTime(endTime);//7b
            cdrFileUpload.setCdrRecordNum(ByteConversionUtils.bytes2IntBig(tailRecord.getCdrRecordNum()));//4b
            cdrFileUpload.setFileCloseCause(ByteUtil.byte2tinyint(tailRecord.getFileCloseCause()));//1b
//            cdrFileUpload.setTailRev(new String(tailRecord.getRev(),"UTF-8"));//49b
            cdrFileUpload.setTailRev(ByteUtil.byteToPin(tailRecord.getRev()));//49b


            list.add(cdrFileUpload);
        }

        saveBatch(list);

    }

    @Override
    public List<CdrManage> getFileById(String id) {
        QueryWrapper<CdrManage> cdrWrapper = new QueryWrapper<>();
        cdrWrapper.eq("cdr_file_id", id);
        List<CdrManage> list = this.list(cdrWrapper);
        return list;
    }

    @Override
    public List<CdrManage> readThisFile(File f) throws Exception {

        return readCDRFile(f);
//        return null;
    }

    @Override
    public List<CdrManage> cdrAnalysis(File tempFile) throws Exception{
        List<CdrManage> list = readCDRFile(tempFile);

        return list;
    }

    @Override
    public IPage<CdrManage> pageList(Page<CdrManage> page, CdrManageVo vo) {

        return this.baseMapper.pageList(page,vo);
    }

    @Override
    public void handReadCdr(int count) {
        WorkLog workLog = new WorkLog();
        workLog.setOperating("一键导入CDR文件列表");
        try {
            File directory = new File(path);
            File[] ftpFiles = directory.listFiles();
            for (int j = 0; j < ftpFiles.length; j++) {
                File ftpFile = ftpFiles[j];
                if (!ftpFile.getName().endsWith("cdr")) {
                    continue;
                }
                System.out.println(ftpFile.getName());


                InputStream inputStream = new FileInputStream(ftpFile);
                //创建字节数组存储数据
                byte[] bytes = new byte[(int)ftpFile.length()];
                inputStream.read(bytes);//把文件里的数据 读入 内存数组bytes
                inputStream.close();
                CDRFile cdrFile = new CDRFile();
                cdrFile = cdrFile.parse(bytes);

                CdrFile cdrName = new CdrFile();
                cdrName.setCdrFileName(ftpFile.getName());
                cdrFileMapper.insert(cdrName);
                List<CdrManage> list = new ArrayList<>();
                HeadRecord headRecord = cdrFile.getHeadRecord();
                TailRecord tailRecord = cdrFile.getTailRecord();
                List<CDRRecordSip> recordSipList = cdrFile.getRecordSipList();
                for (CDRRecordSip cdrRecordSip : recordSipList) {
                    CdrManage cdrFileUpload = new CdrManage();
                    cdrFileUpload.setCdrFileId(cdrName.getId());
                    cdrFileUpload.setHeadRecType(ByteUtil.byte2tinyint(headRecord.getRecType()));//1b
                    cdrFileUpload.setFacId(ByteUtil.bytes2smallint(headRecord.getFacId(), 0));//2b
                    String startTime = BCDUtils.formatStr(BCDUtils.byte2str(headRecord.getStartTime()));
                    cdrFileUpload.setStartTime(startTime);//7b
                    int fileSeq = ByteUtil.bytes2smallint(headRecord.getFileSeq(), 0);
                    cdrFileUpload.setFileSeq(fileSeqFormat(fileSeq));//2b
                    cdrFileUpload.setVersion(ByteUtil.byte2tinyint(headRecord.getVersion()));//1b
                    cdrFileUpload.setRev(ByteUtil.byteToPin(headRecord.getRev()));//49b


                    int cdrRecType = ByteUtil.byte2tinyint(cdrRecordSip.getRecType());
                    cdrFileUpload.setCdrRecType(RecType.unknown(cdrRecType));//1b
                    cdrFileUpload.setCdrNo(ByteUtil.bytes2int(cdrRecordSip.getCdrNo(),0));//4b
                    cdrFileUpload.setRelationId(ByteConversionUtils.bytesToLongBig(cdrRecordSip.getRelationId()));//8b
                    byte[] callingPartyNumber = cdrRecordSip.getCallingPartyNumber();
//            byte callingPartyNumberType = callingPartyNumber[0];
//            byte[] callingNum = new byte[7];
//            System.arraycopy(callingPartyNumber,1,callingNum,0,7);
                    cdrFileUpload.setCallingPartyNumber(BCDUtils.bcd2str(callingPartyNumber));//7b
//            cdrFileUpload.setCallingPartyNumberType(ByteUtil.byte2tinyint(callingPartyNumberType));//1b
                    byte[] calledPartyNumber = cdrRecordSip.getCalledPartyNumber();
//            byte calledPartyNumberType = calledPartyNumber[0];
//            byte[] calledNum = new byte[7];
//            System.arraycopy(calledPartyNumber,1,calledNum,0,7);
                    cdrFileUpload.setCalledPartyNumber(BCDUtils.bcd2str(calledPartyNumber));//7b
//            cdrFileUpload.setCalledPartyNumberType(ByteUtil.byte2tinyint(calledPartyNumberType));//1b

                    byte paraFlag1 = cdrRecordSip.getParaFlag();
//                System.out.println(ByteUtil.byte2HexString0x(paraFlag1, true));
                    int paraFlag = ByteUtil.byte2tinyint(cdrRecordSip.getParaFlag());
                    cdrFileUpload.setParaFlag(ParaFlagType.unknown(paraFlag));//1b

                    byte[] callingLocation = cdrRecordSip.getCallingLocation();
                    byte callingLocationType = callingLocation[0];
                    byte[] callingLocationStr = new byte[32];
                    System.arraycopy(callingLocation,1,callingLocationStr,0,32);
                    cdrFileUpload.setCallingLocationType(ByteUtil.byte2tinyint(callingLocationType));//1b
//            cdrFileUpload.setCallingLocation(new String(callingLocationStr,"UTF-8"));//32b
                    String callingLocationSting = ByteUtil.byteToPin(callingLocationStr);
                    if(callingLocationSting.startsWith("0000")){
                        cdrFileUpload.setCallingLocation("");//32b
                    }else {
                        cdrFileUpload.setCallingLocation(ByteUtil.byteToPin(callingLocationStr));//32b
                    }


                    byte[] calledLocation = cdrRecordSip.getCalledLocation();
                    byte calledLocationType = calledLocation[0];
                    byte[] calledLocationStr = new byte[32];
                    System.arraycopy(calledLocation,1,calledLocationStr,0,32);
                    cdrFileUpload.setCalledLocationType(ByteUtil.byte2tinyint(calledLocationType));//1b
//            cdrFileUpload.setCalledLocation(new String(calledLocationStr,"UTF-8"));//32b
                    String calledLocationString = ByteUtil.byteToPin(calledLocationStr);
                    if(calledLocationString.startsWith("0000")){
                        cdrFileUpload.setCalledLocation("");//32b
                    }else {
                        cdrFileUpload.setCalledLocation(ByteUtil.byteToPin(calledLocationStr));//32b
                    }

                    byte[] fwHistoryInfo = cdrRecordSip.getFwHistoryInfo();
                    byte[] fwHistoryInfoNumber = new byte[24];
                    byte[] fwHistoryInfoCause = new byte[2];
                    System.arraycopy(fwHistoryInfo,0,fwHistoryInfoNumber,0,24);
                    System.arraycopy(fwHistoryInfo,24,fwHistoryInfoCause,0,2);
                    String fwHistoryInfoNumberStr = BCDUtils.bcd2str(fwHistoryInfoNumber);
                    if(fwHistoryInfoNumberStr.startsWith("0000")){
                        cdrFileUpload.setFwHistoryInfoNum("");//32b
                    }else {
                        cdrFileUpload.setFwHistoryInfoNum(BCDUtils.bcd2str(fwHistoryInfoNumber));//7b
                    }
//            cdrFileUpload.setFwHistoryInfoCause(new String(fwHistoryInfoCause,"UTF-8"));//2b
                    cdrFileUpload.setFwHistoryInfoCause(ByteUtil.byteToPin(fwHistoryInfoCause));//2b

                    byte[] bwHistoryInfo = cdrRecordSip.getBwHistoryInfo();
                    byte[] bwHistoryInfoNumber = new byte[24];
                    byte[] bwHistoryInfoCause = new byte[2];
                    System.arraycopy(bwHistoryInfo,0,bwHistoryInfoNumber,0,24);
                    System.arraycopy(bwHistoryInfo,24,bwHistoryInfoCause,0,2);
                    String bwHistoryInfoNumberStr = BCDUtils.bcd2str(bwHistoryInfoNumber);
                    if(bwHistoryInfoNumberStr.startsWith("0000")){
                        cdrFileUpload.setBwHistoryInfoNum("");//32b
                    }else {
                        cdrFileUpload.setBwHistoryInfoNum(BCDUtils.bcd2str(bwHistoryInfoNumber));//8b
                    }
//            cdrFileUpload.setBwHistoryInfoCause(new String(bwHistoryInfoCause,"UTF-8"));//2b
                    cdrFileUpload.setBwHistoryInfoCause(ByteUtil.byteToPin(bwHistoryInfoCause));//2b

                    String cdrStartTime = BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getStartTime()));
                    cdrFileUpload.setCdrStartTime(cdrStartTime);//7b
                    String alertTime = BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getAlertTime()));
                    cdrFileUpload.setAlertTime(alertTime);//7b
                    cdrFileUpload.setAnswerTime(BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getAnswerTime())));//7b
                    cdrFileUpload.setReleaseTime(BCDUtils.formatStr(BCDUtils.byte2str(cdrRecordSip.getReleaseTime())));//7b

                    int releaseCause = ByteUtil.bytes2smallint(cdrRecordSip.getReleaseCause(), 0);
                    cdrFileUpload.setReleaseCause(ReleaseCauseType.unknown(releaseCause));//2b

                    cdrFileUpload.setFwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getFwMediaInterrupEventNum()));//1b
                    cdrFileUpload.setBwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getBwMediaInterrupEventNum()));//1b

                    int fwMediaFlowLoss = ByteUtil.bytes2smallint(cdrRecordSip.getFwMediaFlowLossRate(), 0);
                    int bwMediaFlowLoss = ByteUtil.bytes2smallint(cdrRecordSip.getBwMediaFlowLossRate(), 0);
                    cdrFileUpload.setFwMediaFlowLossRate(fwMediaFlowLoss/100.0 + "%");//2b
                    cdrFileUpload.setBwMediaFlowLossRate(bwMediaFlowLoss/100.0 + "%");//2b
                    cdrFileUpload.setMediaEventType1Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType1Num(),0));//2b
                    cdrFileUpload.setMediaEventType2Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType2Num(),0));//2b
                    cdrFileUpload.setMediaEventType3Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType3Num(),0));//2b
                    cdrFileUpload.setMediaEventType4Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType4Num(),0));//2b
                    cdrFileUpload.setMediaEventType5Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType5Num(),0));//2b
                    cdrFileUpload.setMediaEventType6Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType6Num(),0));//2b
                    cdrFileUpload.setMediaEventType7Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType7Num(),0));//2b
                    cdrFileUpload.setMediaEventType8Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType8Num(),0));//2b
                    cdrFileUpload.setMediaEventType9Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType9Num(),0));//2b
                    cdrFileUpload.setMediaEventType10Num(ByteUtil.bytes2smallint(cdrRecordSip.getMediaEventType10Num(),0));//2b
                    cdrFileUpload.setCdrRev(ByteUtil.byteToPin(cdrRecordSip.getRev()));//26b


                    cdrFileUpload.setTailRecType(ByteUtil.byte2tinyint(tailRecord.getRecType()));//1b

                    String endTime = BCDUtils.formatStr(BCDUtils.byte2str(tailRecord.getEndTime()));
                    cdrFileUpload.setTailEndTime(endTime);//7b
                    cdrFileUpload.setCdrRecordNum(ByteConversionUtils.bytes2IntBig(tailRecord.getCdrRecordNum()));//4b
                    cdrFileUpload.setFileCloseCause(ByteUtil.byte2tinyint(tailRecord.getFileCloseCause()));//1b
//            cdrFileUpload.setTailRev(new String(tailRecord.getRev(),"UTF-8"));//49b
                    cdrFileUpload.setTailRev(ByteUtil.byteToPin(tailRecord.getRev()));//49b

                    list.add(cdrFileUpload);
                }

                saveBatch(list);
                ftpFile.delete();
            }
            workLog.setOperatingResult("1");
        } catch (Exception e) {
            e.printStackTrace();
            workLog.setOperatingResult("0");
        }

        workLogMapper.insert(workLog);
    }

    @Override
    public List<CdrManage> importCdr(HttpServletRequest request) {
        WorkLog workLog = new WorkLog();
        workLog.setOperating("离线查看CDR文件");

        List<CdrManage> cdrManages = new ArrayList();

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            List<File> files = new ArrayList<File>();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 获取上传文件对象
                MultipartFile file = entity.getValue();
                String fileName = file.getOriginalFilename();
                // 获取文件后缀
                String prefix = fileName.substring(fileName.lastIndexOf("."));
                File tempFile = File.createTempFile(fileName, prefix);
                file.transferTo(tempFile);
                files.add(tempFile);

            }
            if (!files.get(0).getName().endsWith("cdr")) {
                throw new RuntimeException("请传入CDR格式文件");
            }
            cdrManages = cdrAnalysis(files.get(0));
            for (CdrManage cdrManage : cdrManages) {
                UUID uuid = UUID.randomUUID();
                String id = uuid.toString();
                cdrManage.setId(id);
            }
            workLog.setOperatingResult("1");
        } catch (Exception e) {
            workLog.setOperatingResult("0");
            throw new RuntimeException(e);
        }
        workLogMapper.insert(workLog);
        return cdrManages;
    }

    public static CDRFile readCDRFileByLine(File f) {
        CDRFile cdrFile = new CDRFile();
        BufferedReader reader = null;
        List<CDRRecordSip> recordSipList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            int i = 1;
            while (line != null) {
                if (i == 1 && line.length() == 124) {
                    byte[] head = hexStringAsByteArray(line);
                    HeadRecord headRecord = new HeadRecord();
                    headRecord = headRecord.parse(head);
                    cdrFile.setHeadRecord(headRecord);
                    i++;
                }
                if (i > 1 && line.length() == 124) {
                    byte[] tail = hexStringAsByteArray(line);
                    TailRecord tailRecord = new TailRecord();
                    tailRecord = tailRecord.parse(tail);
                    cdrFile.setTailRecord(tailRecord);
                } else {
                    byte[] content = hexStringAsByteArray(line);
                    CDRRecordSip cdrRecordSip = new CDRRecordSip();
                    cdrRecordSip = cdrRecordSip.parse(content);
                    recordSipList.add(cdrRecordSip);
                }
                line = reader.readLine();
            }
            cdrFile.setRecordSipList(recordSipList);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cdrFile;
    }

    public static byte[] hexStringAsByteArray(String hexString) {
        if (hexString == null || hexString.length() == 0) {
            return null;
        }
  /*      if (!hexString.matches("[a-fA-F0-9]*") || hexString.length() % 2 != 0) {
            return null;
        }*/
        int mid = hexString.length() / 2;
        byte[] data = new byte[mid];
        for (int i = 0; i < mid; i++) {
            data[i] = Integer.valueOf(hexString.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return data;
    }
    public String fileSeqFormat(int fileSeq) {
        String fileSeqStr = String.valueOf(fileSeq);
        if (fileSeqStr.length() == 1) {
            fileSeqStr = "00" + fileSeqStr;
        } else if (fileSeqStr.length() == 2) {
            fileSeqStr = "0" + fileSeqStr;
        }
        return fileSeqStr;
    }



}
