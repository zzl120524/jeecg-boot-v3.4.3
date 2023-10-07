package com.sdt.omap.protocol.comm;

public class IpV6Header {

    private int version; //
    private String trafficClass; //
    private String flowLabel; //
    private int payloadLength; //
    private String nextHeader; //
    private int hopLimit; //
    private String sourceAddress; //
    private String destinationAddress; //
    private String udpAddress; //
    private String udpAddres; //

    public IpV6Header parse(String bits) {
        String versionStr = bits.substring(0, 4);
        String trafficClassStr = bits.substring(4,12);
        String flowLabelStr = bits.substring(12,32);
        String payloadLengthStr = bits.substring(32,48);
        String nextHeaderStr = bits.substring(48, 56);
        String HopLimitStr = bits.substring(56, 64);
        String sourceIpStr = bits.substring(64, 192);
        String destIpStr = bits.substring(192,320);

/*        String source_port_str = bits.substring(160,192);
        String dest_port_str = bits.substring(192,224);*/

        int version = Integer.valueOf(versionStr, 2);
        int payloadLength = Integer.valueOf(payloadLengthStr, 2);
        int nextHeader = Integer.valueOf(nextHeaderStr, 2);
        int HopLimit = Integer.valueOf(HopLimitStr, 2);


        IpV6Header ipHeader = new IpV6Header();
        ipHeader.setVersion(version);
        ipHeader.setTrafficClass(trafficClassStr);
        ipHeader.setFlowLabel(flowLabelStr);
        ipHeader.setPayloadLength(payloadLength);
        ipHeader.setNextHeader(getProtocol(nextHeader));
        ipHeader.setHopLimit(HopLimit);
        ipHeader.setSourceAddress(sourceIpStr);
        ipHeader.setDestinationAddress(destIpStr);

    /*    ipHeader.setUdpAddress(getIPAddress(source_port_str));
        ipHeader.setUdpAddres(getIPAddress(dest_port_str));*/
        return ipHeader;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTrafficClass() {
        return trafficClass;
    }

    public void setTrafficClass(String trafficClass) {
        this.trafficClass = trafficClass;
    }

    public String getFlowLabel() {
        return flowLabel;
    }

    public void setFlowLabel(String flowLabel) {
        this.flowLabel = flowLabel;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public void setPayloadLength(int payloadLength) {
        this.payloadLength = payloadLength;
    }


    public String getNextHeader() {
        return nextHeader;
    }

    public void setNextHeader(String nextHeader) {
        this.nextHeader = nextHeader;
    }

    public int getHopLimit() {
        return hopLimit;
    }

    public void setHopLimit(int hopLimit) {
        this.hopLimit = hopLimit;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getUdpAddress() {
        return udpAddress;
    }

    public void setUdpAddress(String udpAddress) {
        this.udpAddress = udpAddress;
    }

    public String getUdpAddres() {
        return udpAddres;
    }

    public void setUdpAddres(String udpAddres) {
        this.udpAddres = udpAddres;
    }

    private static String getIPClass(String ip){
        String firstOctet = ip.substring(0,8);
        if(firstOctet.charAt(0) == '0') return "A";
        if(firstOctet.charAt(1) == '0') return "B";
        if(firstOctet.charAt(2) == '0') return "C";
        if(firstOctet.charAt(3) == '0') return "D";
        return "E";
    }

    private static String getIPAddress(String ip){
        String a = ip.substring(0,8);
        String b = ip.substring(8,16);
        String c = ip.substring(16,24);
        String d = ip.substring(24,32);

        return ""+Integer.parseInt(a, 2)+"."+
                Integer.parseInt(b, 2)+"."+
                Integer.parseInt(c, 2)+"."+
                Integer.parseInt(d, 2);
    }

    private static String getProtocol(int protocol){
        switch(protocol){
            case 0: return "HOPOPT";
            case 1: return "ICMP";
            case 2: return "IGMP";
            case 3: return "GGP";
            case 4: return "IP-in-IP";
            case 5: return "ST";
            case 6: return "TCP";
            case 7: return "CBT";
            case 8: return "EGP";
            case 9: return "IGP";
            case 10: return "BBN-RCC-MON";
            case 11: return "NVP-II";
            case 12: return "PUP";
            case 13: return "ARGUS";
            case 14: return "EMCON";
            case 15: return "XNET";
            case 16: return "CHAOS";
            case 17: return "UDP";
            case 18: return "MUX";
            case 19: return "DCN-MEAS";
            case 20: return "HMP";
            case 21: return "PRM";
            case 22: return "XNS-IDP";
            case 23: return "TRUNK-1";
            case 24: return "TRUNK-2";
            case 25: return "LEAF-1";
            case 26: return "LEAF-2";
            case 27: return "RDP";
            case 28: return "IRTP";
            case 29: return "ISO-TP4";
            case 30: return "NETBLT";
            case 31: return "MFE-NSP";
            case 32: return "MERIT-INP";
            case 33: return "DCCP";
            case 34: return "3PC";
            case 35: return "IDPR";
            case 36: return "XTP";
            case 37: return "DDP";
            case 38: return "IDPR-CMTP";
            case 39: return "TP";
            case 40: return "IL";
            case 41: return "IPv6";
            case 42: return "SDRP";
            case 43: return "IPv6-Route";
            case 44: return "IPv6-Frag";
            case 45: return "IDRP";
            case 46: return "RSVP";
            case 47: return "GRE";
            case 48: return "MHRP";
            case 49: return "BNA";
            case 50: return "ESP";
            case 51: return "AH";
            case 52: return "I-NLSP";
            case 53: return "SWIPE";
            case 54: return "NARP";
            case 55: return "MOBILE";
            case 56: return "TLSP";
            case 57: return "SKIP";
            case 58: return "IPv6-ICMP";
            case 59: return "IPv6-NoNxt";
            case 60: return "IPv6-Opts";
            case 61: return "";
            case 62: return "CFTP";
            case 63: return "";
            case 64: return "SAT-EXPAK";
            case 65: return "KRYPTOLAN";
            case 66: return "RVD";
            case 67: return "IPPC";
            case 68: return "";
            case 69: return "SAT-MON";
            case 70: return "VISA";
            case 71: return "IPCU";
            case 72: return "CPNX";
            case 73: return "CPHB";
            case 74: return "WSN";
            case 75: return "PVP";
            case 76: return "BR-SAT-MON";
            case 77: return "SUN-ND";
            case 78: return "WB-MON";
            case 79: return "WB-EXPAK";
            case 80: return "ISO-IP";
            case 81: return "VMTP";
            case 82: return "SECURE-VMTP";
            case 83: return "VINES";
            case 84: return "TTP/IPTM";
            case 85: return "NSFNET-IGP";
            case 86: return "DGP";
            case 87: return "TCF";
            case 88: return "EIGRP";
            case 89: return "OSPF";
            case 90: return "Sprite-RPC";
            case 91: return "LARP";
            case 92: return "MTP";
            case 93: return "AX";
            case 94: return "IPIP";
            case 95: return "MICP";
            case 96: return "SCC-SP";
            case 97: return "ETHERIP";
            case 98: return "ENCAP";
            case 99: return "";
            case 100: return "GMTP";
            case 101: return "IFMP";
            case 102: return "PNNI";
            case 103: return "PIM";
            case 104: return "ARIS";
            case 105: return "SCPS";
            case 106: return "QNX";
            case 107: return "A";
            case 108: return "IPComp";
            case 109: return "SNP";
            case 110: return "Compaq-Peer";
            case 111: return "IPX-in-IP";
            case 112: return "VRRP";
            case 113: return "PGM";
            case 114: return "";
            case 115: return "L2TP";
            case 116: return "DDX";
            case 117: return "IATP";
            case 118: return "STP";
            case 119: return "SRP";
            case 120: return "UTI";
            case 121: return "SMP";
            case 122: return "SM";
            case 123: return "PTP";
            case 124: return "IS-IS";
            case 125: return "FIRE";
            case 126: return "CRTP";
            case 127: return "CRUDP";
            case 128: return "SSCOPMCE";
            case 129: return "IPLT";
            case 130: return "SPS";
            case 131: return "PIPE";
            case 132: return "SCTP";
            case 133: return "FC";
            case 134: return "RSVP-E2E-IGNORE";
            case 135: return "Mobility Header";
            case 136: return "UDPLite";
            case 137: return "MPLS-in-IP";
            case 138: return "manet";
            case 139: return "HIP";
            case 140: return "Shim6";
            case 141: return "WESP";
            case 142: return "ROHC";
            case 255: return "Reserved";
        }
        if(protocol>=253 && protocol<=254) return "Used for testing and experimenting";
        if(protocol>=143 && protocol<=252) return "Unassigned";
        return "";
    }
}
