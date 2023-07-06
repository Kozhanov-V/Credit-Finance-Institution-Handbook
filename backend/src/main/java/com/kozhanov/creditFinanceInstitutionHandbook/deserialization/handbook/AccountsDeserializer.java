package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;

@JacksonXmlRootElement(localName = "Accounts")
public class AccountsDeserializer {
    @JacksonXmlProperty(localName = "Account", isAttribute = true)
    private String account;

    @JacksonXmlProperty(localName = "RegulationAccountType", isAttribute = true)
    private String regulationAccountType;

    @JacksonXmlProperty(localName = "CK", isAttribute = true)
    private String ck;

    @JacksonXmlProperty(localName = "AccountCBRBIC", isAttribute = true)
    private String accountCBRBIC;

    @JacksonXmlProperty(localName = "DateIn", isAttribute = true)
    private String dateIn;

    @JacksonXmlProperty(localName = "AccountStatus", isAttribute = true)
    private AccountStatus accountStatus;

    @JacksonXmlProperty(localName = "AccRstrList")
    @JacksonXmlElementWrapper(useWrapping = false)
    private AccRstrListDeserializer AccRstrListDeserializer;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRegulationAccountType() {
        return regulationAccountType;
    }

    public void setRegulationAccountType(String regulationAccountType) {
        this.regulationAccountType = regulationAccountType;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getAccountCBRBIC() {
        return accountCBRBIC;
    }

    public void setAccountCBRBIC(String accountCBRBIC) {
        this.accountCBRBIC = accountCBRBIC;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccRstrListDeserializer getAccRstrList() {
        return AccRstrListDeserializer;
    }

    public void setAccRstrList(AccRstrListDeserializer accRstrListDeserializer) {
        AccRstrListDeserializer = accRstrListDeserializer;
    }
}
