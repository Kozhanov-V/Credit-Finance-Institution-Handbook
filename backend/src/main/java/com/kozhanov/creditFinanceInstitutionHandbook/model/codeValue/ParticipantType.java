package com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

// PtType - - name in doc about codes

@Entity
@Table(name = "participant_type")
public class ParticipantType {
    @Column(name = "code")
    @Id
    @Size(max = 2)
    private int code;

    @Column(name = "description")
    private String description;

    @Column(name = "es_type")
    private String esType;

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
