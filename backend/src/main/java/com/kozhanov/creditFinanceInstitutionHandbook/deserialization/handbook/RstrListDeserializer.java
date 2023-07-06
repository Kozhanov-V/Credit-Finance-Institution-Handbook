package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "RstrList")
public class RstrListDeserializer {
    @JacksonXmlProperty(localName = "Rstr", isAttribute = true)
    private String rstr;

    @JacksonXmlProperty(localName = "RstrDate", isAttribute = true)
    private String rstrDate;

    public String getRstr() {
        return rstr;
    }

    public void setRstr(String rstr) {
        this.rstr = rstr;
    }

    public String getRstrDate() {
        return rstrDate;
    }

    public void setRstrDate(String rstrDate) {
        this.rstrDate = rstrDate;
    }
}
