package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = " ParticipantInfo")
public class ParticipantInfoDeserializer {
    @JacksonXmlProperty(localName = "NameP", isAttribute = true)
    private String nameP;

    @JacksonXmlProperty(localName = "EnglName", isAttribute = true)
    private String EnglName;

    @JacksonXmlProperty(localName = "CntrCd", isAttribute = true)
    private String cntrCd;

    @JacksonXmlProperty(localName = "Rgn", isAttribute = true)
    private String rgn;

    @JacksonXmlProperty(localName = "Ind", isAttribute = true)
    private String ind;

    @JacksonXmlProperty(localName = "RegN", isAttribute = true)
    private String RegN;

    @JacksonXmlProperty(localName = "Tnp", isAttribute = true)
    private String tnp;

    @JacksonXmlProperty(localName = "Nnp", isAttribute = true)
    private String nnp;

    @JacksonXmlProperty(localName = "Adr", isAttribute = true)
    private String adr;

    @JacksonXmlProperty(localName = "DateIn", isAttribute = true)
    private String dateIn;

    @JacksonXmlProperty(localName = "PtType", isAttribute = true)
    private String ptType;

    @JacksonXmlProperty(localName = "Srvcs", isAttribute = true)
    private String srvcs;

    @JacksonXmlProperty(localName = "XchType", isAttribute = true)
    private String xchType;

    @JacksonXmlProperty(localName = "UID", isAttribute = true)
    private String uid;

    @JacksonXmlProperty(localName = "ParticipantStatus", isAttribute = true)
    private String participantStatus;

    @JacksonXmlProperty(localName = "PrntBIC", isAttribute = true)
    private String PrntBIC;

    @JacksonXmlProperty(localName = "RstrList")
    private RstrListDeserializer rstrListDeserializer;

    public String getEnglName() {
        return EnglName;
    }

    public void setEnglName(String englName) {
        EnglName = englName;
    }

    public String getRegN() {
        return RegN;
    }

    public void setRegN(String regN) {
        RegN = regN;
    }

    public String getPrntBIC() {
        return PrntBIC;
    }

    public void setPrntBIC(String prntBIC) {
        PrntBIC = prntBIC;
    }



    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getCntrCd() {
        return cntrCd;
    }

    public void setCntrCd(String cntrCd) {
        this.cntrCd = cntrCd;
    }

    public String getRgn() {
        return rgn;
    }

    public void setRgn(String rgn) {
        this.rgn = rgn;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public String getTnp() {
        return tnp;
    }

    public void setTnp(String tnp) {
        this.tnp = tnp;
    }

    public String getNnp() {
        return nnp;
    }

    public void setNnp(String nnp) {
        this.nnp = nnp;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getPtType() {
        return ptType;
    }

    public void setPtType(String ptType) {
        this.ptType = ptType;
    }

    public String getSrvcs() {
        return srvcs;
    }

    public void setSrvcs(String srvcs) {
        this.srvcs = srvcs;
    }

    public String getXchType() {
        return xchType;
    }

    public void setXchType(String xchType) {
        this.xchType = xchType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(String participantStatus) {
        this.participantStatus = participantStatus;
    }

    public RstrListDeserializer getRstrList() {
        return rstrListDeserializer;
    }

    public void setRstrList(RstrListDeserializer rstrListDeserializer) {
        this.rstrListDeserializer = rstrListDeserializer;
    }
}
