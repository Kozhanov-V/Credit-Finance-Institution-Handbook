package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SWBICS")
public class SWBICS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bic", nullable = false)
    @JsonBackReference
    private BICDirectoryEntry bicDirectoryEntry;

    @Column(name = "SWBIC")
    private String SWBIC;

    @Column(name = "DefaultSWBIC")
    private Boolean DefaultSWBIC;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BICDirectoryEntry getBicDirectoryEntry() {
        return bicDirectoryEntry;
    }

    public void setBicDirectoryEntry(BICDirectoryEntry bicDirectoryEntry) {
        this.bicDirectoryEntry = bicDirectoryEntry;
    }

    public String getSWBIC() {
        return SWBIC;
    }

    public void setSWBIC(String SWBIC) {
        this.SWBIC = SWBIC;
    }

    public Boolean getDefaultSWBIC() {
        return DefaultSWBIC;
    }

    public void setDefaultSWBIC(Boolean defaultSWBIC) {
        DefaultSWBIC = defaultSWBIC;
    }

    public SWBICS() {
    }

    public SWBICS(BICDirectoryEntry bicDirectoryEntry, String SWBIC, Boolean defaultSWBIC) {
        this.bicDirectoryEntry = bicDirectoryEntry;
        this.SWBIC = SWBIC;
        DefaultSWBIC = defaultSWBIC;
    }
}