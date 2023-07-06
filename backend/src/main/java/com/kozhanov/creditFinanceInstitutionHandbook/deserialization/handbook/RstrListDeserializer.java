package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantRestriction;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.RestrictionEntry;

import java.util.Date;

@JacksonXmlRootElement(localName = "RstrList")
public class RstrListDeserializer {

    @JacksonXmlProperty(localName = "Rstr", isAttribute = true)
    private ParticipantRestriction participantRestriction;

    @JacksonXmlProperty(localName = "RstrDate", isAttribute = true)
    private Date rstrDate;

    public RstrListDeserializer() {
    }

    public ParticipantRestriction getParticipantRestriction() {
        return participantRestriction;
    }

    public void setParticipantRestriction(ParticipantRestriction participantRestriction) {
        this.participantRestriction = participantRestriction;
    }

    public Date getRstrDate() {
        return rstrDate;
    }

    public void setRstrDate(Date rstrDate) {
        this.rstrDate = rstrDate;
    }



}
