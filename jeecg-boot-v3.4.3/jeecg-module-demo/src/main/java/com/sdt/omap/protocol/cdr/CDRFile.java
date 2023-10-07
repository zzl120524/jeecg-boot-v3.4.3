package com.sdt.omap.protocol.cdr;

import com.sdt.util.standard.ByteUtil;

import java.util.ArrayList;
import java.util.List;

public class CDRFile {

    private HeadRecord headRecord;
    private List<CDRRecordSip> recordSipList;
    private TailRecord tailRecord;

    public CDRFile parse(byte[] data) throws Exception {
        int len = data.length;
        int recordLen = len - 64 - 64;
        int circleNum = recordLen / 264;//3

        CDRFile cdrFile = new CDRFile();

        byte[] bDest = new byte[64];
        System.arraycopy(data, 0, bDest, 0, 64);

        HeadRecord headRecord = new HeadRecord();
        headRecord = headRecord.parse(bDest);
        cdrFile.setHeadRecord(headRecord);

        List<CDRRecordSip> cdrRecordSipList = new ArrayList<>();

        int num = 0;
        for (num = 0; num < circleNum; num++) {
            CDRRecordSip cdrRecordSip = new CDRRecordSip();
            bDest = new byte[264];
            System.arraycopy(data, 64 + num * 264, bDest, 0, 264);
            System.out.println("CDRRecordSip:"+ByteUtil.byte2HexString0x(bDest, true));
            cdrRecordSip = cdrRecordSip.parse(bDest);
            cdrRecordSipList.add(cdrRecordSip);
        }
        cdrFile.setRecordSipList(cdrRecordSipList);


        bDest = new byte[64];
        System.arraycopy(data, 64 + num * 264, bDest, 0, 64);
        TailRecord tailRecord = new TailRecord();
        tailRecord = tailRecord.parse(bDest);
        cdrFile.setTailRecord(tailRecord);

        return cdrFile;
    }

    public HeadRecord getHeadRecord() {
        return headRecord;
    }

    public void setHeadRecord(HeadRecord headRecord) {
        this.headRecord = headRecord;
    }

    public List<CDRRecordSip> getRecordSipList() {
        return recordSipList;
    }

    public void setRecordSipList(List<CDRRecordSip> recordSipList) {
        this.recordSipList = recordSipList;
    }

    public TailRecord getTailRecord() {
        return tailRecord;
    }

    public void setTailRecord(TailRecord tailRecord) {
        this.tailRecord = tailRecord;
    }
}
