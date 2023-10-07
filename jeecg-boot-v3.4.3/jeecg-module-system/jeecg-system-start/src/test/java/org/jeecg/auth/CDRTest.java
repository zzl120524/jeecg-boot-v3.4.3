package org.jeecg.auth;

import com.sdt.omap.protocol.cdr.CDRFile;
import com.sdt.omap.protocol.cdr.CDRRecordSip;
import com.sdt.omap.protocol.cdr.HeadRecord;
import com.sdt.omap.protocol.cdr.TailRecord;
import com.sdt.omap.utils.BCDUtils;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.common.util.ByteConversionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CDRTest {

    public static void main(String[] args) throws Exception {

        File f = new File("D:/SC/FAC6939-20000104134331001.cdr");
        CDRFile cdrFile = readCDRFileByLine(f);



        HeadRecord headRecord = cdrFile.getHeadRecord();
        int fileSeq = ByteUtil.bytes2smallint(headRecord.getFileSeq(), 0);
        String fileSeqStr = String.valueOf(fileSeq);
        if (fileSeqStr.length() == 1){
            fileSeqStr = "00"+fileSeqStr;
        }else if (fileSeqStr.length() == 2){
            fileSeqStr = "0"+fileSeqStr;
        }
        System.out.println("FileSeq 原文:"+ ByteUtil.byte2HexString(headRecord.getFileSeq(),true));
        System.out.println("FileSeq 转:"+ fileSeqStr);

        TailRecord tailRecord = cdrFile.getTailRecord();
        System.out.println("EndTime 原文:"+ ByteUtil.byte2HexString(tailRecord.getEndTime(),true));
        System.out.println("EndTime 转:"+ BCDUtils.byte2str(tailRecord.getEndTime()));
        System.out.println("EndTime 格式化:"+ BCDUtils.formatStr(BCDUtils.byte2str(tailRecord.getEndTime())));



        List<CDRRecordSip> recordSipList = cdrFile.getRecordSipList();
        for (int i=0; i<recordSipList.size() ; i++) {
            System.out.println("第"+ (i+1) +"条记录");
            CDRRecordSip cdrRecordSip = recordSipList.get(i);

            System.out.println("recType:"+ cdrRecordSip.getRecType());
            System.out.println("cdrNo 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getCdrNo(), true));
//            System.out.println("cdrNo 转:"+ ByteUtil.byte2HexString(cdrRecordSip.getCdrNo(), true));
            System.out.println("RelationId 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getRelationId(), true));
            System.out.println("RelationId 转:"+ ByteConversionUtils.bytesToLongBig(cdrRecordSip.getRelationId()));
            byte[] callingPartyNumber = cdrRecordSip.getCallingPartyNumber();
            byte callingPartyNumberType = callingPartyNumber[0];
            byte[] callingNum = new byte[7];
            System.arraycopy(callingPartyNumber,1,callingNum,0,7);
            System.out.println("CallingPartyNumber 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getCallingPartyNumber(), true));
            System.out.println("CallingPartyNumber 转:"+ BCDUtils.bcd2str(callingNum));
            byte[] calledPartyNumber = cdrRecordSip.getCallingPartyNumber();
            byte calledPartyNumberType = calledPartyNumber[0];
            byte[] calledNum = new byte[7];
            System.arraycopy(calledPartyNumber,1,calledNum,0,7);
            System.out.println("CalledPartyNumber 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getCalledPartyNumber(), true));
            System.out.println("CalledPartyNumber 转:"+ BCDUtils.bcd2str(calledNum));
            System.out.println("ParaFlag 原文:"+ (Byte)(cdrRecordSip.getParaFlag()));
            System.out.println("ParaFlag 转:"+ (Byte.toString(cdrRecordSip.getParaFlag())));

            byte[] callingLocation = cdrRecordSip.getCallingLocation();
            byte callingLocationType = callingLocation[0];
            byte[] callingLocationStr = new byte[32];
            System.arraycopy(callingLocation,1,callingLocationStr,0,32);
            System.out.println("callingLocationType 原文:"+ callingLocationType);
            System.out.println("callingLocationType 转:"+ ByteUtil.byte2tinyint(callingLocationType));
            System.out.println("callingLocation 原文:"+ ByteUtil.byte2HexString(callingLocationStr, true));
            System.out.println("callingLocation 转:"+ ByteUtil.byteToPin(callingLocationStr));

            byte[] calledLocation = cdrRecordSip.getCalledLocation();
            byte calledLocationType = calledLocation[0];
            byte[] calledLocationStr = new byte[32];
            System.arraycopy(calledLocation,1,calledLocationStr,0,32);
            System.out.println("calledLocationType 原文:"+ calledLocationType);
            System.out.println("calledLocationType 转:"+ ByteUtil.byte2tinyint(calledLocationType));
            System.out.println("calledLocation 原文:"+ ByteUtil.byte2HexString(calledLocation, true));
            System.out.println("calledLocation 转:"+ ByteUtil.byteToPin(calledLocation));

            byte[] fwHistoryInfo = cdrRecordSip.getFwHistoryInfo();
            byte[] fwHistoryInfoNumber = new byte[8];
            byte[] fwHistoryInfoCause = new byte[32];
            System.arraycopy(fwHistoryInfo,0,fwHistoryInfoNumber,0,8);
            System.arraycopy(fwHistoryInfo,8,fwHistoryInfoCause,0,2);
            System.out.println("fwHistoryInfoNumber 原文:"+ ByteUtil.byte2HexString(fwHistoryInfoNumber, true));
            System.out.println("fwHistoryInfoNumber 转:"+ ByteUtil.byteToPin(fwHistoryInfoNumber));
            System.out.println("fwHistoryInfoCause 原文:"+ ByteUtil.byte2HexString(fwHistoryInfoCause, true));
            System.out.println("fwHistoryInfoCause 转:"+ ByteUtil.byteToPin(fwHistoryInfoCause));

            byte[] bwHistoryInfo = cdrRecordSip.getBwHistoryInfo();
            byte[] bwHistoryInfoNumber = new byte[8];
            byte[] bwHistoryInfoCause = new byte[32];
            System.arraycopy(bwHistoryInfo,0,bwHistoryInfoNumber,0,8);
            System.arraycopy(bwHistoryInfo,8,bwHistoryInfoCause,0,2);
            System.out.println("bwHistoryInfoNumber 原文:"+ ByteUtil.byte2HexString(bwHistoryInfoNumber, true));
            System.out.println("bwHistoryInfoNumber 转:"+ ByteUtil.byteToPin(bwHistoryInfoNumber));
            System.out.println("bwHistoryInfoCause 原文:"+ ByteUtil.byte2HexString(bwHistoryInfoCause, true));
            System.out.println("bwHistoryInfoCause 转:"+ ByteUtil.byteToPin(bwHistoryInfoCause));
            System.out.println("StartTime 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getStartTime(), true));
            byte[] startTime = cdrRecordSip.getStartTime();
            System.out.println("StartTime 转:"+ BCDUtils.byte2str(startTime));
            System.out.println("AlertTime 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getAlertTime(), true));
            System.out.println("AlertTime 转:"+ BCDUtils.byte2str(cdrRecordSip.getAlertTime()));
            System.out.println("AnswerTime 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getAnswerTime(), true));
            System.out.println("AnswerTime 转:"+ BCDUtils.byte2str(cdrRecordSip.getAnswerTime()));
            System.out.println("ReleaseTime 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getReleaseTime(), true));
            System.out.println("ReleaseTime 转:"+ BCDUtils.byte2str(cdrRecordSip.getReleaseTime()));
            System.out.println("ReleaseCause 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getReleaseCause(), true));
            System.out.println("ReleaseCause 转:"+ ByteUtil.byteToPin(cdrRecordSip.getReleaseCause()));
            System.out.println("FwMediainterrupEventNum 原文:"+ (Byte)(cdrRecordSip.getFwMediaInterrupEventNum()));
            System.out.println("FwMediainterrupEventNum 转:"+ ByteUtil.byte2tinyint(cdrRecordSip.getFwMediaInterrupEventNum()));
            System.out.println("BwMediainterrupEventNum 原文:"+ (Byte)(cdrRecordSip.getBwMediaInterrupEventNum()));
            System.out.println("BwMediainterrupEventNum 转:"+ ByteUtil.byte2tinyint(cdrRecordSip.getBwMediaInterrupEventNum()));
            System.out.println("FwMediaFlowLossRate 原文:"+ ByteUtil.byte2HexString(cdrRecordSip.getFwMediaFlowLossRate(),true));

            //            System.out.println("FwMediaFlowLossRate 转:"+ ByteUtil.byte2tinyint(cdrRecordSip.getFwMediaFlowLossRate()));

           /* CdrFileUpload cdrFileUpload = new CdrFileUpload();

            cdrFileUpload.setHeadRecType(ByteUtil.byte2tinyint(headRecord.getRecType()));//1b
//            System.out.println("CdrRecType==>原数据"+ByteUtil.byte2HexString0x(cdrRecordSip.getRecType(), true));
            cdrFileUpload.setFacId(ByteUtil.bytes2smallint(headRecord.getFacId(),0));//2b

            cdrFileUpload.setStartTime(ByteUtil.byteToPin(headRecord.getStartTime()));//7b
            cdrFileUpload.setFileSeq(ByteUtil.byteToPin(headRecord.getFileSeq()));//2b
            cdrFileUpload.setVersion(ByteUtil.byte2tinyint(headRecord.getVersion()));//1b
            cdrFileUpload.setRev(ByteUtil.byteToPin(headRecord.getRev()));//49b

//            System.out.println("CdrRecType==>原数据"+ByteUtil.byte2HexString0x(cdrRecordSip.getRecType(), true));
            cdrFileUpload.setCdrRecType(ByteUtil.byte2tinyint(cdrRecordSip.getRecType()));//1b
            cdrFileUpload.setCdrNo(ByteUtil.bytes2smallint(cdrRecordSip.getCdrNo(),0));//4b
            cdrFileUpload.setRelationId(ByteConversionUtils.bytesToLongBig(cdrRecordSip.getRelationId()));//8b
            cdrFileUpload.setCallingPartyNumber(ByteUtil.byteToPin(cdrRecordSip.getCallingPartyNumber()));//8b
            cdrFileUpload.setCalledPartyNumber(ByteUtil.byteToPin(cdrRecordSip.getCalledPartyNumber()));//8b
            cdrFileUpload.setParaFlag(Byte.toString(cdrRecordSip.getParaFlag()));//1b

            byte[] callingLocation = cdrRecordSip.getCallingLocation();
            byte callingLocationType = callingLocation[0];
            byte[] callingLocationStr = new byte[32];
            System.arraycopy(callingLocation,1,callingLocationStr,0,32);
            cdrFileUpload.setCallingLocationType(ByteUtil.byte2tinyint(callingLocationType));//1b
            cdrFileUpload.setCallingLocation(ByteUtil.byteToPin(callingLocationStr));//32b

            byte[] calledLocation = cdrRecordSip.getCalledLocation();
            byte calledLocationType = calledLocation[0];
            byte[] calledLocationStr = new byte[32];
            System.arraycopy(calledLocation,1,calledLocationStr,0,32);
            cdrFileUpload.setCalledLocationType(ByteUtil.byte2tinyint(calledLocationType));//1b
            cdrFileUpload.setCalledLocation(ByteUtil.byteToPin(calledLocationStr));//32b

            byte[] fwHistoryInfo = cdrRecordSip.getFwHistoryInfo();
            byte[] fwHistoryInfoNumber = new byte[8];
            byte[] fwHistoryInfoCause = new byte[32];
            System.arraycopy(fwHistoryInfo,0,fwHistoryInfoNumber,0,8);
            System.arraycopy(fwHistoryInfo,8,fwHistoryInfoCause,0,2);
            cdrFileUpload.setFwHistoryInfoNum(ByteUtil.byteToPin(fwHistoryInfoNumber));//8b
            cdrFileUpload.setFwHistoryInfoCause(ByteUtil.byteToPin(fwHistoryInfoCause));//2b

            byte[] bwHistoryInfo = cdrRecordSip.getBwHistoryInfo();
            byte[] bwHistoryInfoNumber = new byte[8];
            byte[] bwHistoryInfoCause = new byte[32];
            System.arraycopy(bwHistoryInfo,0,bwHistoryInfoNumber,0,8);
            System.arraycopy(bwHistoryInfo,8,bwHistoryInfoCause,0,2);
            cdrFileUpload.setBwHistoryInfoNum(ByteUtil.byteToPin(bwHistoryInfoNumber));//8b
            cdrFileUpload.setBwHistoryInfoCause(ByteUtil.byteToPin(bwHistoryInfoCause));//2b

            cdrFileUpload.setCdrStartTime(ByteUtil.byteToPin(cdrRecordSip.getCdrStartTime()));//7b
            cdrFileUpload.setAlertTime(ByteUtil.byteToPin(cdrRecordSip.getAlertTime()));//7b
            cdrFileUpload.setAnswerTime(ByteUtil.byteToPin(cdrRecordSip.getAnswerTime()));//7b
            cdrFileUpload.setReleaseTime(ByteUtil.byteToPin(cdrRecordSip.getReleaseTime()));//7b
            cdrFileUpload.setReleaseCause(ByteUtil.byteToPin(cdrRecordSip.getReleaseCause()));//2b
            cdrFileUpload.setFwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getFwMediaInterrupEventNum()));//1b
            cdrFileUpload.setBwMediainterrupEventNum(ByteUtil.byte2tinyint(cdrRecordSip.getBwMediaInterrupEventNum()));//1b
            cdrFileUpload.setFwMediaFlowLossRate(ByteUtil.byteToPin(cdrRecordSip.getFwMediaFlowLossRate()));//2b
            cdrFileUpload.setBwMediaFlowLossRate(ByteUtil.byteToPin(cdrRecordSip.getBwMediaFlowLossRate()));//2b
            cdrFileUpload.setMediaEventType1Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType1Num()));//2b
            cdrFileUpload.setMediaEventType2Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType2Num()));//2b
            cdrFileUpload.setMediaEventType3Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType3Num()));//2b
            cdrFileUpload.setMediaEventType4Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType4Num()));//2b
            cdrFileUpload.setMediaEventType5Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType5Num()));//2b
            cdrFileUpload.setMediaEventType6Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType6Num()));//2b
            cdrFileUpload.setMediaEventType7Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType7Num()));//2b
            cdrFileUpload.setMediaEventType8Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType8Num()));//2b
            cdrFileUpload.setMediaEventType9Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType9Num()));//2b
            cdrFileUpload.setMediaEventType10Num(ByteUtil.byteToPin(cdrRecordSip.getMediaEventType10Num()));//2b
            cdrFileUpload.setCdrRev(ByteUtil.byteToPin(cdrRecordSip.getRev()));//26b


            cdrFileUpload.setTailRecType(ByteUtil.byte2tinyint(tailRecord.getRecType()));//1b
            cdrFileUpload.setTailEndTime(ByteUtil.byteToPin(tailRecord.getEndTime()));//7b
            cdrFileUpload.setCdrRecordNum(ByteConversionUtils.bytes2IntBig(tailRecord.getCdrRecordNum()));//4b
            cdrFileUpload.setFileCloseCause(Byte.toString(tailRecord.getFileCloseCause()));//1b
            cdrFileUpload.setTailRev(ByteUtil.byteToPin(tailRecord.getRev()));//49b


            list.add(cdrFileUpload);*/
        }

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



}
