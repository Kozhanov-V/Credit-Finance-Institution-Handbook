package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "ED807")
public class ElectronicDocumentsDeserializer {
    @JacksonXmlProperty(localName = "EDNo", isAttribute = true)
    private String edNo;

    @JacksonXmlProperty(localName = "EDDate", isAttribute = true)
    private String edDate;

    @JacksonXmlProperty(localName = "EDAuthor", isAttribute = true)
    private String edAuthor;

    @JacksonXmlProperty(localName = "CreationReason", isAttribute = true)
    private String creationReason;

    @JacksonXmlProperty(localName = "CreationDateTime", isAttribute = true)
    private String creationDateTime;

    @JacksonXmlProperty(localName = "InfoTypeCode", isAttribute = true)
    private String infoTypeCode;

    @JacksonXmlProperty(localName = "BusinessDay", isAttribute = true)
    private String businessDay;

    @JacksonXmlProperty(localName = "DirectoryVersion", isAttribute = true)
    private String directoryVersion;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "BICDirectoryEntry")
    private List<BICDirectoryEntryDeserializer> bicDirectoryEntryDeserializer;

    public String getEdNo() {
        return edNo;
    }

    public void setEdNo(String edNo) {
        this.edNo = edNo;
    }

    public String getEdDate() {
        return edDate;
    }

    public void setEdDate(String edDate) {
        this.edDate = edDate;
    }

    public String getEdAuthor() {
        return edAuthor;
    }

    public void setEdAuthor(String edAuthor) {
        this.edAuthor = edAuthor;
    }

    public String getCreationReason() {
        return creationReason;
    }

    public void setCreationReason(String creationReason) {
        this.creationReason = creationReason;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getInfoTypeCode() {
        return infoTypeCode;
    }

    public void setInfoTypeCode(String infoTypeCode) {
        this.infoTypeCode = infoTypeCode;
    }

    public String getBusinessDay() {
        return businessDay;
    }

    public void setBusinessDay(String businessDay) {
        this.businessDay = businessDay;
    }

    public String getDirectoryVersion() {
        return directoryVersion;
    }

    public void setDirectoryVersion(String directoryVersion) {
        this.directoryVersion = directoryVersion;
    }

    public List<BICDirectoryEntryDeserializer> getBicDirectoryEntry() {
        return bicDirectoryEntryDeserializer;
    }

    public void setBicDirectoryEntry(List<BICDirectoryEntryDeserializer> bicDirectoryEntryDeserializer) {
        this.bicDirectoryEntryDeserializer = bicDirectoryEntryDeserializer;
    }
}