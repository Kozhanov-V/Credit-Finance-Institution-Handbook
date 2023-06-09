package com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

// XchType
@Entity
@Table(name = "exchange_participant")
public class ExchangeParticipant {
    @Column(name = "code")
    @Id
    @Size(min = 1,max = 1)
    private boolean code;

    @Column(name = "description")
    private String description;

    @Column(name = "es_type")
    private String esType;



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
