package com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.AccountStatusDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.ParticipantStatusDeserializer;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "participan_status")
@JsonDeserialize(using = ParticipantStatusDeserializer.class)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class ParticipantStatus {
    @Column(name = "code")
    @Id
    @Size(min = 4, max = 4)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "es_type")
    private String esType;

    public ParticipantStatus(String code, String description, String esType) {
        this.code = code;
        this.description = description;
        this.esType = esType;
    }

    public ParticipantStatus() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
