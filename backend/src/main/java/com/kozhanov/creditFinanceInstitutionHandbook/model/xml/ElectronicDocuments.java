package com.kozhanov.creditFinanceInstitutionHandbook.model.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.CreationReason;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.InfoTypeCode;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "electronic_documents")
public class ElectronicDocuments {

    @Id
    @Column(name = "number")
    @Digits(integer = 9, fraction = 0)
    private Integer number;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;


    @Column(name = "author")
    @Min(1000000000L)
    @Max(9999999999L)
    private long author;

    @Column(name = "receiver")
    @Min(1000000000L)
    @Max(9999999999L)
    private long receiver;

    @ManyToOne
    @JoinColumn(name = "creation_reason", nullable = false)
    private CreationReason creationReason;

    @Column(name = "creation_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @ManyToOne
    @JoinColumn(name = "info_type_code", nullable = false)
    private InfoTypeCode infoTypeCode;

    @Column(name = "business_day")
    @Temporal(TemporalType.DATE)
    private Date businessDay;

    @Column(name = "directory_version")
    @Max(99)
    private int directoryVersion;

    public ElectronicDocuments(Integer number, Date date, long author, long receiver, CreationReason creationReason, Date creationDateTime, InfoTypeCode infoTypeCode, Date businessDay, int directoryVersion) {
        this.number = number;
        this.date = date;
        this.author = author;
        this.receiver = receiver;
        this.creationReason = creationReason;
        this.creationDateTime = creationDateTime;
        this.infoTypeCode = infoTypeCode;
        this.businessDay = businessDay;
        this.directoryVersion = directoryVersion;
    }

    public ElectronicDocuments() {
    }

    public ElectronicDocuments(Integer number, Date date, long author, CreationReason creationReason, Date creationDateTime, InfoTypeCode infoTypeCode, Date businessDay, int directoryVersion) {
        this.number = number;
        this.date = date;
        this.author = author;
        this.creationReason = creationReason;
        this.creationDateTime = creationDateTime;
        this.infoTypeCode = infoTypeCode;
        this.businessDay = businessDay;
        this.directoryVersion = directoryVersion;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
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

    @Override
    public String toString() {
        return "ElectronicDocuments{" +
                "number=" + number +
                ", date=" + date +
                ", author=" + author +
                ", receiver=" + receiver +
                ", creationReason=" + creationReason +
                ", creationDateTime=" + creationDateTime +
                ", infoTypeCode=" + infoTypeCode +
                ", businessDay=" + businessDay +
                ", directoryVersion=" + directoryVersion +
                '}';
    }
}
