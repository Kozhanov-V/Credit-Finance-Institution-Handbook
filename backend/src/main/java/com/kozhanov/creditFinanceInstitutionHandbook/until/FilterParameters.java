package com.kozhanov.creditFinanceInstitutionHandbook.until;

import java.time.LocalDate;
import java.util.Date;

public class FilterParameters {

    private Integer bic;
    private String nameRecord;
    private Integer typeTransfer;
    private Date validFrom;
    private Date validUntil;


    public FilterParameters() {
    }

    public Integer getBic() {
        return bic;
    }


    public FilterParameters( Integer bic, String nameRecord, Integer typeTransfer, Date validFrom, Date validUntil) {
        this.bic = bic;
        this.nameRecord = nameRecord;
        this.typeTransfer = typeTransfer;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }


    public String getNameRecord() {
        return nameRecord;
    }

    public void setNameRecord(String nameRecord) {
        this.nameRecord = nameRecord;
    }

    public void setBic(Integer bic) {
        this.bic = bic;
    }

    public Integer getTypeTransfer() {
        return typeTransfer;
    }

    public void setTypeTransfer(Integer typeTransfer) {
        this.typeTransfer = typeTransfer;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public String toString() {
        return "FilterParameters{" +
                "bic='" + bic + '\'' +
                ", nameRecord='" + nameRecord + '\'' +
                ", typeTransfer='" + typeTransfer + '\'' +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                '}';
    }
}

