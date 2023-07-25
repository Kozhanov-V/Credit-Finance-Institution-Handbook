package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.SWBICSDeserializer;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SWBICS")
@Audited
public class SWBICS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SWBIC")
    private String SWBIC;

    @Column(name = "DefaultSWBIC")
    private boolean defaultSWBIC;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bic")
    @JsonBackReference
    private BICDirectoryEntry bicDirectoryEntry;

    public BICDirectoryEntry getBicDirectoryEntry() {
        return bicDirectoryEntry;
    }

    public void setBicDirectoryEntry(BICDirectoryEntry bicDirectoryEntry) {
        this.bicDirectoryEntry = bicDirectoryEntry;
    }

    public SWBICS(SWBICSDeserializer swbicsDeserializer) {
        this.SWBIC = swbicsDeserializer.getSwbic();
        if(swbicsDeserializer.getDefaultSWBIC()>0){
            defaultSWBIC=true;
        }
        else{
            defaultSWBIC=false;
        }
    }

    public SWBICS() {
    }

    public SWBICS(Long id, String SWBIC, boolean defaultSWBIC) {
        this.id = id;
        this.SWBIC = SWBIC;
        this.defaultSWBIC = defaultSWBIC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSWBIC() {
        return SWBIC;
    }

    public void setSWBIC(String SWBIC) {
        this.SWBIC = SWBIC;
    }

    public boolean isDefaultSWBIC() {
        return defaultSWBIC;
    }

    public void setDefaultSWBIC(boolean defaultSWBIC) {
        this.defaultSWBIC = defaultSWBIC;
    }
}