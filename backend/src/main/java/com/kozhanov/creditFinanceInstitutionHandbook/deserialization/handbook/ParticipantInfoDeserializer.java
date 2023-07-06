package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AvailableTransferService;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ExchangeParticipant;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JacksonXmlRootElement(localName = "ParticipantInfo")
public class ParticipantInfoDeserializer {

    @JacksonXmlProperty(localName = "NameP", isAttribute = true)
    private String nameP;

    @JacksonXmlProperty(localName = "EnglName", isAttribute = true)
    private String EnglName;

    @JacksonXmlProperty(localName = "RegN", isAttribute = true)
    private String RegN;

    @JacksonXmlProperty(localName = "CntrCd", isAttribute = true)
    private String cntrCd;

    @JacksonXmlProperty(localName = "Rgn", isAttribute = true)
    private String rgn;

    @JacksonXmlProperty(localName = "Ind", isAttribute = true)
    private String ind;

    @JacksonXmlProperty(localName = "Tnp", isAttribute = true)
    private String tnp;

    @JacksonXmlProperty(localName = "Nnp", isAttribute = true)
    private String nnp;

    @JacksonXmlProperty(localName = "Adr", isAttribute = true)
    private String adr;

    @JacksonXmlProperty(localName = "PrntBIC", isAttribute = true)
    private int PrntBIC;

    @JacksonXmlProperty(localName = "DateIn", isAttribute = true)
    private Date dateIn;

    @JacksonXmlProperty(localName = "DateOut", isAttribute = true)
    private Date dateOut;

    @JacksonXmlProperty(localName = "PtType", isAttribute = true)
    private ParticipantType ptType;

    @JacksonXmlProperty(localName = "Srvcs", isAttribute = true)
    private AvailableTransferService srvcs;

    @JacksonXmlProperty(localName = "XchType", isAttribute = true)
    private ExchangeParticipant xchType;

    @JacksonXmlProperty(localName = "UID", isAttribute = true)
    private long uid;

    @JacksonXmlProperty(localName = "ParticipantStatus", isAttribute = true)
    private ParticipantStatus participantStatus;

    @JacksonXmlProperty(localName = "RstrList")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<RstrListDeserializer> rstrList = new ArrayList<>();

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

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

    public int getPrntBIC() {
        return PrntBIC;
    }

    public void setPrntBIC(int prntBIC) {
        PrntBIC = prntBIC;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public ParticipantType getPtType() {
        return ptType;
    }

    public void setPtType(ParticipantType ptType) {
        this.ptType = ptType;
    }

    public AvailableTransferService getSrvcs() {
        return srvcs;
    }

    public void setSrvcs(AvailableTransferService srvcs) {
        this.srvcs = srvcs;
    }

    public ExchangeParticipant getXchType() {
        return xchType;
    }

    public void setXchType(ExchangeParticipant xchType) {
        this.xchType = xchType;
    }



    public ParticipantStatus getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(ParticipantStatus participantStatus) {
        this.participantStatus = participantStatus;
    }

    public List<RstrListDeserializer> getRstrList() {
        return rstrList;
    }

    public void setRstrList(List<RstrListDeserializer> rstrList) {
        this.rstrList = rstrList;
    }
}
