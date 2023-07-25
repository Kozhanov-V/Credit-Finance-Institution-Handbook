package com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.AccountStatusDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.ParticipantTypeDeserializer;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

// PtType - - name in doc about codes

@Entity
@Table(name = "participant_type")
@JsonDeserialize(using = ParticipantTypeDeserializer.class)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ParticipantType {
    @Column(name = "code")
    @Id
    @Size(max = 2)
    private int code;

    @Column(name = "description")
    private String description;

    @Column(name = "es_type")
    private String esType;

    public ParticipantType() {
    }

    public ParticipantType(int code, String description, String esType) {
        this.code = code;
        this.description = description;
        this.esType = esType;
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
