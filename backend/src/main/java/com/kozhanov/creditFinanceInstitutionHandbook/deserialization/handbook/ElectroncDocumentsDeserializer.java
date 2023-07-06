package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.CreationReason;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.InfoTypeCode;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JacksonXmlRootElement(localName = "ED807")
public class ElectroncDocumentsDeserializer {
    @JacksonXmlProperty(localName = "EDNo", isAttribute = true)
    private int edNo;

    @JacksonXmlProperty(localName = "EDDate", isAttribute = true)
    private Date edDate;

    @JacksonXmlProperty(localName = "EDAuthor", isAttribute = true)
    private long edAuthor;

    @JacksonXmlProperty(localName = "EDReceiver", isAttribute = true)
    private long eDReceiver;

    @JacksonXmlProperty(localName = "CreationReason", isAttribute = true)
    private CreationReason creationReason;

    @JacksonXmlProperty(localName = "CreationDateTime", isAttribute = true)
    private Date creationDateTime;

    @JacksonXmlProperty(localName = "InfoTypeCode", isAttribute = true)
    private InfoTypeCode infoTypeCode;

    @JacksonXmlProperty(localName = "BusinessDay", isAttribute = true)
    private Date businessDay;

    @JacksonXmlProperty(localName = "DirectoryVersion", isAttribute = true)
    private int directoryVersion;

    @JacksonXmlProperty(localName = "BICDirectoryEntry")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<BICDirectoryEntryDeserializer> bicDirectoryEntryDeserializer ;

    public ElectroncDocumentsDeserializer() {
    }

    public int getEdNo() {
        return edNo;
    }

    public void setEdNo(int edNo) {
        this.edNo = edNo;
    }

    public Date getEdDate() {
        return edDate;
    }

    public void setEdDate(Date edDate) {
        this.edDate = edDate;
    }

    public long getEdAuthor() {
        return edAuthor;
    }

    public void setEdAuthor(long edAuthor) {
        this.edAuthor = edAuthor;
    }

    public long geteDReceiver() {
        return eDReceiver;
    }

    public void seteDReceiver(long eDReceiver) {
        this.eDReceiver = eDReceiver;
    }

    public CreationReason getCreationReason() {
        return creationReason;
    }

    public void setCreationReason(CreationReason creationReason) {
        this.creationReason = creationReason;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public InfoTypeCode getInfoTypeCode() {
        return infoTypeCode;
    }

    public void setInfoTypeCode(InfoTypeCode infoTypeCode) {
        this.infoTypeCode = infoTypeCode;
    }

    public Date getBusinessDay() {
        return businessDay;
    }

    public void setBusinessDay(Date businessDay) {
        this.businessDay = businessDay;
    }

    public int getDirectoryVersion() {
        return directoryVersion;
    }

    public void setDirectoryVersion(int directoryVersion) {
        this.directoryVersion = directoryVersion;
    }

    public List<BICDirectoryEntryDeserializer> getBicDirectoryEntryDeserializer() {
        return bicDirectoryEntryDeserializer;
    }

    public void setBicDirectoryEntryDeserializer(List<BICDirectoryEntryDeserializer> bicDirectoryEntryDeserializer) {
        this.bicDirectoryEntryDeserializer = bicDirectoryEntryDeserializer;
    }
}
