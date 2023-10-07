package com.sdt.omap.protocol.comm;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class  FacIdServiceImpl {

    public static Map<Integer,String> facIdMap = new HashMap<Integer, String>();

    public Map<Integer,String> getAllFacIdMap(){
        return getFacIdMap();
    }

    public static Map<Integer, String> getFacIdMap() {
        return facIdMap;
    }

    public static void setFacIdMap(Map<Integer, String> facIdMap) {
        FacIdServiceImpl.facIdMap = facIdMap;
    }
}
