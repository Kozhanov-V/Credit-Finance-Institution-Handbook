package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "SWBICS")
public class SWBICSDeserializer {

    @JacksonXmlProperty(localName = "SWBIC", isAttribute = true)
    private String swbic;

    @JacksonXmlProperty(localName = "DefaultSWBIC", isAttribute = true)
    private int defaultSWBIC;

    public String getSwbic() {
        return swbic;
    }

    public void setSwbic(String swbic) {
        this.swbic = swbic;
    }

    public int getDefaultSWBIC() {
        return defaultSWBIC;
    }

    public void setDefaultSWBIC(int defaultSWBIC) {
        this.defaultSWBIC = defaultSWBIC;
    }
}
