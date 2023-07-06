package com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.AccountStatusDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.ExchangeParticipantDeserializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

// XchType
@Entity
@Table(name = "exchange_participant")
@JsonDeserialize(using = ExchangeParticipantDeserializer.class)
public class ExchangeParticipant {
    @Column(name = "code")
    @Id
    @Size(min = 1, max = 1)
    private int code;


    @Column(name = "description")
    private String description;

    @Column(name = "es_type")
    private String esType;

    public ExchangeParticipant(int code, String description, String esType) {
        this.code = code;
        this.description = description;
        this.esType = esType;
    }

    public ExchangeParticipant() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEsType() {
        return esType;
    }

    public void setEsType(String esType) {
        this.esType = esType;
    }
}
