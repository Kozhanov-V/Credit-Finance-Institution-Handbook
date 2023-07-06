package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.AccountRestriction;

import java.util.Date;
@JacksonXmlRootElement(localName = "AccRstrList")
public class AccRstrListDeserializer {
    @JacksonXmlProperty(localName = "AccRstr", isAttribute = true)
    private AccountRestriction accRstr;

    @JacksonXmlProperty(localName = "AccRstrDate", isAttribute = true)
    private Date accRstrDate;

    public AccountRestriction getAccRstr() {
        return accRstr;
    }

    public void setAccRstr(AccountRestriction accRstr) {
        this.accRstr = accRstr;
    }

    public Date getAccRstrDate() {
        return accRstrDate;
    }

    public void setAccRstrDate(Date accRstrDate) {
        this.accRstrDate = accRstrDate;
    }
}
