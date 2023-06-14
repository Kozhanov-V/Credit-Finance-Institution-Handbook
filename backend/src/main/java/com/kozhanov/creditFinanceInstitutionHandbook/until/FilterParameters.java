package com.kozhanov.creditFinanceInstitutionHandbook.until;

import java.time.LocalDate;

public class FilterParameters {

    private String orderBy;
    private String bic;
    private String nameRecord;
    private String typeTransfer;
    private LocalDate validFrom;
    private LocalDate validUntil;


    public FilterParameters() {
    }

    public FilterParameters(String orderBy, String bic, String nameRecord, String typeTransfer, LocalDate validFrom, LocalDate validUntil) {

        this.orderBy = orderBy;
        this.bic = bic;
        this.nameRecord = nameRecord;
        this.typeTransfer = typeTransfer;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }


    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getNameRecord() {
        return nameRecord;
    }

    public void setNameRecord(String nameRecord) {
        this.nameRecord = nameRecord;
    }

    public String getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(String typeTransfer) {
        this.typeTransfer = typeTransfer;
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
                ", orderBy='" + orderBy + '\'' +
                ", bic='" + bic + '\'' +
                ", nameRecord='" + nameRecord + '\'' +
                ", typeTransfer='" + typeTransfer + '\'' +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                '}';
    }
}

