package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bic_directory_entry")
public class BICDirectoryEntry {


    public BICDirectoryEntry(int BIC, ChangeType changeType, ElectronicDocuments electronicDocuments, List<Accounts> accounts, ParticipantInfo participantInfo) {
        this.BIC = BIC;
        this.changeType = changeType;
        this.electronicDocuments = electronicDocuments;
        this.accounts = accounts;
        this.participantInfo = participantInfo;
    }

    public BICDirectoryEntry(int BIC, ElectronicDocuments electronicDocuments, List<Accounts> accounts, ParticipantInfo participantInfo) {
        this.BIC = BIC;
        this.electronicDocuments = electronicDocuments;
        this.accounts = accounts;
        this.participantInfo = participantInfo;
    }

    public BICDirectoryEntry(int BIC, ElectronicDocuments electronicDocuments, ParticipantInfo participantInfo) {
        this.BIC = BIC;
        this.electronicDocuments = electronicDocuments;
        this.participantInfo = participantInfo;
    }

    public BICDirectoryEntry(int BIC, ParticipantInfo participantInfo) {
        this.BIC = BIC;
        this.participantInfo = participantInfo;
    }

    public BICDirectoryEntry() {
    }

    @Id
    @Column(name = "bic")
    @Max(999999999)
    @NotNull
    private int BIC;

    @ManyToOne
    @JoinColumn(name = "change_type_code")
    private ChangeType changeType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "electronic_documents_number", nullable = false)
    private ElectronicDocuments electronicDocuments;

    @OneToMany(mappedBy = "bicDirectoryEntry", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JsonManagedReference
    private List<Accounts> accounts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_info_id", nullable = false)
    @JsonManagedReference
    private ParticipantInfo participantInfo;



    public int getBic() {
        return BIC;
    }

    public void setBic(int BIC) {
        this.BIC = BIC;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public ElectronicDocuments getElectronicDocuments() {
        return electronicDocuments;
    }

    public void setElectronicDocuments(ElectronicDocuments electronicDocuments) {
        this.electronicDocuments = electronicDocuments;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void addAccount(Accounts account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setBicDirectoryEntry(this);
    }

    public ParticipantInfo getParticipantInfo() {
        return participantInfo;
    }

    public void setParticipantInfo(ParticipantInfo participantInfo) {
        this.participantInfo = participantInfo;
    }

    @Override
    public String toString() {
        return "BICDirectoryEntry{" +
                "BIC=" + BIC +
                ", changeType=" + changeType +
                ", electronicDocuments=" + electronicDocuments +
                ", accountsId=" + accounts +
                ", participantInfo=" + participantInfo +
                '}';
    }
}