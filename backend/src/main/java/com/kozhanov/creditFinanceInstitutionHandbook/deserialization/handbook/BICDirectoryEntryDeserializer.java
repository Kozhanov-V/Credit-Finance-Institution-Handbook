package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "BICDirectoryEntry")
public class BICDirectoryEntryDeserializer {

    @JacksonXmlProperty(localName = "BIC", isAttribute = true)
    private int bic;

    @JacksonXmlProperty(localName = "ChangeType", isAttribute = true)
    private ChangeType ChangeType;

    @JacksonXmlProperty(localName = "ParticipantInfo")
    private ParticipantInfoDeserializer participantInfoDeserializer;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Accounts")
    private List<AccountsDeserializer> accountsDeserializer = new ArrayList<>();

    @JacksonXmlProperty(localName = "SWBICS")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<SWBICSDeserializer> swbicsDeserializer = new ArrayList<>();


    public int getBic() {
        return bic;
    }

    public void setBic(int bic) {
        this.bic = bic;
    }

    public com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType getChangeType() {
        return ChangeType;
    }

    public void setChangeType(com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType changeType) {
        ChangeType = changeType;
    }

    public ParticipantInfoDeserializer getParticipantInfoDeserializer() {
        return participantInfoDeserializer;
    }

    public void setParticipantInfoDeserializer(ParticipantInfoDeserializer participantInfoDeserializer) {
        this.participantInfoDeserializer = participantInfoDeserializer;
    }

    public List<SWBICSDeserializer> getSwbicsDeserializer() {
        return swbicsDeserializer;
    }

    public void setSwbicsDeserializer(List<SWBICSDeserializer> swbicsDeserializer) {
        this.swbicsDeserializer = swbicsDeserializer;
    }

    public List<AccountsDeserializer> getAccountsDeserializer() {
        return accountsDeserializer;
    }

    public void setAccountsDeserializer(List<AccountsDeserializer> accountsDeserializer) {
        this.accountsDeserializer = accountsDeserializer;
    }

    public BICDirectoryEntryDeserializer() {
    }

}
