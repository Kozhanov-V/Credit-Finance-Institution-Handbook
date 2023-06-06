package com.kozhanov.creditFinanceInstitutionHandbook.until;

import java.time.LocalDate;

public class FilterParameters {

    private boolean deletedRecord;
    private String orderBy;
    private String cod;
    private String nameRecord;
    private String typeOrganization;
    private String typePayer;
    private LocalDate validFrom;
    private LocalDate validUntil;

    // Конструкторы, геттеры и сеттеры здесь

    public FilterParameters() {
    }

    public FilterParameters(boolean deletedRecord, String orderBy, String cod, String nameRecord, String typeOrganization, String typePayer, LocalDate validFrom, LocalDate validUntil) {
        this.deletedRecord = deletedRecord;
        this.orderBy = orderBy;
        this.cod = cod;
        this.nameRecord = nameRecord;
        this.typeOrganization = typeOrganization;
        this.typePayer = typePayer;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public boolean isDeletedRecord() {
        return deletedRecord;
    }

    public void setDeletedRecord(boolean deletedRecord) {
        this.deletedRecord = deletedRecord;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNameRecord() {
        return nameRecord;
    }

    public void setNameRecord(String nameRecord) {
        this.nameRecord = nameRecord;
    }

    public String getTypeOrganization() {
        return typeOrganization;
    }

    public void setTypeOrganization(String typeOrganization) {
        this.typeOrganization = typeOrganization;
    }

    public String getTypePayer() {
        return typePayer;
    }

    public void setTypePayer(String typePayer) {
        this.typePayer = typePayer;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public String toString() {
        return "FilterParameters{" +
                "deletedRecord=" + deletedRecord +
                ", orderBy='" + orderBy + '\'' +
                ", cod='" + cod + '\'' +
                ", nameRecord='" + nameRecord + '\'' +
                ", typeOrganization='" + typeOrganization + '\'' +
                ", typePayer='" + typePayer + '\'' +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                '}';
    }
}

