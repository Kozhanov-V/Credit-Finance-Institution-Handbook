package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.ParticipantRestrictionDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantRestriction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "restriction_entry")
public class RestrictionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_restriction_code")
    private ParticipantRestriction Rstr;

    public ParticipantRestriction getRstr() {
        return Rstr;
    }

    public void setRstr(ParticipantRestriction rstr) {
        Rstr = rstr;
    }

    @Column(name = "RstrDate")
    @Temporal(TemporalType.DATE)
    private Date RstrDate;

    public RestrictionEntry() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRstrDate() {
        return RstrDate;
    }

    public void setRstrDate(Date rstrDate) {
        RstrDate = rstrDate;
    }
}
