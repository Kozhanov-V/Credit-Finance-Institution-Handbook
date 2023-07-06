package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType;

import java.util.List;
@JacksonXmlRootElement(localName = "BICDirectoryEntry")
public class BICDirectoryEntryDeserializer {
    @JacksonXmlProperty(localName = "BIC", isAttribute = true)
    private int bic;

    @JacksonXmlProperty(localName = "ChangeType", isAttribute = true)
    private ChangeType changeType;

    @JacksonXmlProperty(localName = "ParticipantInfo")
    private ParticipantInfoDeserializer participantInfoDeserializer;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Accounts")
    private List<AccountsDeserializer> accounts;

    @JacksonXmlProperty(localName = "SWBICS")
    private SWBICSDeserializer swbics;

    public List<AccountsDeserializer> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountsDeserializer> accounts) {
        this.accounts = accounts;
    }


    public int getBic() {
        return bic;
    }

    public void setBic(int bic) {
        this.bic = bic;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public ParticipantInfoDeserializer getParticipantInfo() {
        return participantInfoDeserializer;
    }

    public void setParticipantInfo(ParticipantInfoDeserializer participantInfoDeserializer) {
        this.participantInfoDeserializer = participantInfoDeserializer;
    }

    public SWBICSDeserializer getSwbics() {
        return swbics;
    }

    public void setSwbics(SWBICSDeserializer swbics) {
        this.swbics = swbics;
    }

    @Override
    public String toString() {
        return "BICDirectoryEntry{" +
                "bic='" + bic + '\'' +
                ", participantInfo=" + participantInfoDeserializer.getNameP() +
                ", swbics=" + swbics +
                '}';
    }
}
