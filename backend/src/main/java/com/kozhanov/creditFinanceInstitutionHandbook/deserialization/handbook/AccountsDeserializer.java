package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.AccountRestriction;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JacksonXmlRootElement(localName = "Accounts")
public class AccountsDeserializer {

    @JacksonXmlProperty(localName = "Account", isAttribute = true)
    private String account;

    @JacksonXmlProperty(localName = "RegulationAccountType", isAttribute = true)
    private RegulationAccountType regulationAccountType;

    @JacksonXmlProperty(localName = "CK", isAttribute = true)
    private String controlKey;

    @JacksonXmlProperty(localName = "AccountCBRBIC", isAttribute = true)
    private int accountCBRBIC;

    @JacksonXmlProperty(localName = "DateIn", isAttribute = true)
    private Date dateIn;

    @JacksonXmlProperty(localName = "DateOut", isAttribute = true)
    private Date dateOut;

    @JacksonXmlProperty(localName = "AccountStatus", isAttribute = true)
    private AccountStatus accountStatus;


    @JacksonXmlProperty(localName = "AccRstrList", isAttribute = true)
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AccountRestrictionDeserializer> accountRestrictionsDeserializer = new ArrayList<>();

    public List<AccountRestrictionDeserializer> getAccountRestrictionsDeserializer() {
        return accountRestrictionsDeserializer;
    }

    public void setAccountRestrictionsDeserializer(List<AccountRestrictionDeserializer> accountRestrictionsDeserializer) {
        this.accountRestrictionsDeserializer = accountRestrictionsDeserializer;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public RegulationAccountType getRegulationAccountType() {
        return regulationAccountType;
    }

    public void setRegulationAccountType(RegulationAccountType regulationAccountType) {
        this.regulationAccountType = regulationAccountType;
    }

    public String getControlKey() {
        return controlKey;
    }

    public void setControlKey(String controlKey) {
        this.controlKey = controlKey;
    }

    public int getAccountCBRBIC() {
        return accountCBRBIC;
    }

    public void setAccountCBRBIC(int accountCBRBIC) {
        this.accountCBRBIC = accountCBRBIC;
    }

    public AccountsDeserializer() {
    }
}
