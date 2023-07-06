package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountOperationRestriction;

import java.util.Date;

@JacksonXmlRootElement(localName = "AccRstrList")
public class AccountRestrictionDeserializer {

    @JacksonXmlProperty(localName = "AccRstr", isAttribute = true)
    private AccountOperationRestriction accRstr;

    @JacksonXmlProperty(localName = "AccRstrDate", isAttribute = true)
    private Date accRstrDate;

    @JacksonXmlProperty(localName = "SuccessorBIC", isAttribute = true)
    private int bic;

    public Date getAccRstrDate() {
        return accRstrDate;
    }

    public void setAccRstrDate(Date accRstrDate) {
        this.accRstrDate = accRstrDate;
    }

    public int getBic() {
        return bic;
    }

    public void setBic(int bic) {
        this.bic = bic;
    }

    public AccountOperationRestriction getAccRstr() {
        return accRstr;
    }

    public void setAccRstr(AccountOperationRestriction accRstr) {
        this.accRstr = accRstr;
    }

    public AccountRestrictionDeserializer() {
    }
}
