package com.sdt.omap.utils;

import com.sdt.util.standard.ByteUtil;

public class LocationUtil {

    public static String getInfoByType(int type,byte[] callingLocation) {
        switch(type) {
            case 1:
                return cgi3gpp(callingLocation);
            case 2:
                return utranCellId3gpp(callingLocation);
            case 3:
                return utranSai3gpp(callingLocation);
            case 4:
                return ci3gpp2(callingLocation);
            case 5:
                return iWlanNodeId(callingLocation);
            case 6:
                return utranCellId3gpp4g(callingLocation);
            case 7:
                return utranCellId3gpp4g5gc(callingLocation);
            case 8:
                return utranCellId3gpp5g(callingLocation);
        }
        return "未知";
    }

    public static String cgi3gpp(byte[] callingLocationStr) {
        byte[] omap = new byte[3];
        byte[] mnc = new byte[2];
        byte[] lac = new byte[4];
        byte[] ci = new byte[4];
        System.arraycopy(callingLocationStr,0,omap,0,3);
        System.arraycopy(callingLocationStr,3,mnc,0,2);
        System.arraycopy(callingLocationStr,5,lac,0,4);
        System.arraycopy(callingLocationStr,9,ci,0,4);
        int omapInt = ByteUtil.bytes2int(omap,0);
        int mncInt = ByteUtil.bytes2smallint(mnc,0);
        int lacInt = ByteUtil.bytes2int(lac,0);
        int ciInt = ByteUtil.bytes2int(ci,0);
        return String.valueOf(omapInt)+String.valueOf(mncInt)+String.valueOf(lacInt)+String.valueOf(ciInt);
    }

    public static String utranCellId3gpp(byte[] callingLocationStr) {
        byte[] omap = new byte[3];
        byte[] mnc = new byte[2];
        byte[] lac = new byte[4];
        byte[] umtsci = new byte[7];
        System.arraycopy(callingLocationStr,0,omap,0,3);
        System.arraycopy(callingLocationStr,3,mnc,0,2);
        System.arraycopy(callingLocationStr,5,lac,0,4);
        System.arraycopy(callingLocationStr,9,umtsci,0,7);
        int omapInt = ByteUtil.bytes2int(omap,0);
        int mncInt = ByteUtil.bytes2smallint(mnc,0);
        int lacInt = ByteUtil.bytes2int(lac,0);
        int umtsciInt = ByteUtil.bytes2int(umtsci,0);
        return String.valueOf(omapInt)+String.valueOf(mncInt)+String.valueOf(lacInt)+String.valueOf(umtsciInt);
    }

    public static String utranSai3gpp(byte[] callingLocationStr) {
        byte[] omap = new byte[3];
        byte[] mnc = new byte[2];
        byte[] lac = new byte[4];
        byte[] sac = new byte[4];
        System.arraycopy(callingLocationStr,0,omap,0,3);
        System.arraycopy(callingLocationStr,3,mnc,0,2);
        System.arraycopy(callingLocationStr,5,lac,0,4);
        System.arraycopy(callingLocationStr,9,sac,0,4);
        int omapInt = ByteUtil.bytes2int(omap,0);
        int mncInt = ByteUtil.bytes2smallint(mnc,0);
        int lacInt = ByteUtil.bytes2int(lac,0);
        int sacInt = ByteUtil.bytes2int(sac,0);
        return String.valueOf(omapInt)+String.valueOf(mncInt)+String.valueOf(lacInt)+String.valueOf(sacInt);
    }

    public static String ci3gpp2(byte[] callingLocationStr) {
        byte[] sid = new byte[2];
        byte[] nid = new byte[2];

        byte[] baseId = new byte[2];
        System.arraycopy(callingLocationStr,0,sid,0,2);
        System.arraycopy(callingLocationStr,2,nid,0,2);
        byte pzid = callingLocationStr[4];
        System.arraycopy(callingLocationStr,5,baseId,0,2);
        int sidInt = ByteUtil.bytes2smallint(sid,0);
        int nidInt = ByteUtil.bytes2smallint(nid,0);
        int pzidInt = ByteUtil.byte2tinyint(pzid);
        int baseIdInt = ByteUtil.bytes2smallint(baseId,0);
        return String.valueOf(sidInt)+String.valueOf(nidInt)+String.valueOf(pzidInt)+String.valueOf(baseIdInt);
    }

    public static String iWlanNodeId(byte[] callingLocationStr) {
        byte[] mac = new byte[12];
        System.arraycopy(callingLocationStr,0,mac,0,12);

        int sidInt = ByteUtil.bytes2smallint(mac,0);
        return String.valueOf(mac);
    }

    public static String utranCellId3gpp4g(byte[] callingLocationStr) {
        byte[] omap = new byte[3];
        byte[] mnc = new byte[2];
        byte[] tac = new byte[4];
        byte[] eci = new byte[7];
        System.arraycopy(callingLocationStr,0,omap,0,3);
        System.arraycopy(callingLocationStr,3,mnc,0,2);
        System.arraycopy(callingLocationStr,5,tac,0,4);
        System.arraycopy(callingLocationStr,9,eci,0,7);
        int omapInt = ByteUtil.bytes2int(omap,0);
        int mncInt = ByteUtil.bytes2smallint(mnc,0);
        int tacInt = ByteUtil.bytes2int(tac,0);
        int eciInt = ByteUtil.bytes2int(eci,0);
        return String.valueOf(omapInt)+String.valueOf(mncInt)+String.valueOf(tacInt)+String.valueOf(eciInt);
    }

    public static String utranCellId3gpp4g5gc(byte[] callingLocationStr) {
        byte[] omap = new byte[3];
        byte[] mnc = new byte[2];
        byte[] tac = new byte[6];
        byte[] eci = new byte[7];
        System.arraycopy(callingLocationStr,0,omap,0,3);
        System.arraycopy(callingLocationStr,3,mnc,0,2);
        System.arraycopy(callingLocationStr,5,tac,0,6);
        System.arraycopy(callingLocationStr,11,eci,0,7);
        int omapInt = ByteUtil.bytes2int(omap,0);
        int mncInt = ByteUtil.bytes2smallint(mnc,0);
        int tacInt = ByteUtil.bytes2int(tac,0);
        int eciInt = ByteUtil.bytes2int(eci,0);
        return String.valueOf(omapInt)+String.valueOf(mncInt)+String.valueOf(tacInt)+String.valueOf(eciInt);
    }

    public static String utranCellId3gpp5g(byte[] callingLocationStr) {
        byte[] omap = new byte[3];
        byte[] mnc = new byte[2];
        byte[] tac = new byte[6];
        byte[] nci = new byte[9];
        byte[] nid = new byte[11];
        System.arraycopy(callingLocationStr,0,omap,0,3);
        System.arraycopy(callingLocationStr,3,mnc,0,2);
        System.arraycopy(callingLocationStr,5,tac,0,6);
        System.arraycopy(callingLocationStr,11,nci,0,9);
        System.arraycopy(callingLocationStr,20,nid,0,11);
        int omapInt = ByteUtil.bytes2int(omap,0);
        int mncInt = ByteUtil.bytes2smallint(mnc,0);
        int tacInt = ByteUtil.bytes2int(tac,0);
        int nciInt = ByteUtil.bytes2int(nci,0);
        int nidInt = ByteUtil.bytes2int(nid,0);

        return String.valueOf(omapInt)+String.valueOf(mncInt)+String.valueOf(tacInt)+String.valueOf(nciInt)+String.valueOf(nidInt);
    }

    public static void main(String[] args) {

//        System.out.println(ByteUtil.byte2HexString(bytes,true));
        System.out.println();
    }
}
