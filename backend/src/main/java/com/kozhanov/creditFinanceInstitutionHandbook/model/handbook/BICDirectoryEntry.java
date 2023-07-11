package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.ChangeTypeDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.BICDirectoryEntryDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.ParticipantInfoDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.AccountsService;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "bic_directory_entry")
public class BICDirectoryEntry {


    @Id
    @Column(name = "bic")
    @Max(999999999)
    @NotNull
    private int BIC;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_info_id", nullable = false)
    @JsonManagedReference
    private ParticipantInfo participantInfo;



    @ManyToOne
    @JoinColumn(name = "change_type_code")
    private ChangeType changeType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "electronic_documents_number")
    @JsonBackReference
    private ElectronicDocuments electronicDocuments;

    @OneToMany(mappedBy = "bicDirectoryEntry", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Accounts> accounts = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<SWBICS> swbicsList = new ArrayList<>();



    public BICDirectoryEntry(BICDirectoryEntryDeserializer bicDirectoryEntryDeserializer) {
        ParticipantInfoDeserializer participantInfoDes = bicDirectoryEntryDeserializer.getParticipantInfoDeserializer();
        this.BIC = bicDirectoryEntryDeserializer.getBic();
        this.participantInfo = new ParticipantInfo.Builder()
                .nameParticipant(participantInfoDes.getNameP())
                .engNameParticipant(participantInfoDes.getEnglName())
                .registrationNumber(participantInfoDes.getRegN())
                .countryCode(participantInfoDes.getCntrCd())
                .regionCode(participantInfoDes.getRgn())
                .index(participantInfoDes.getInd())
                .typeLocation(participantInfoDes.getTnp())
                .nameLocation(participantInfoDes.getNnp())
                .address(participantInfoDes.getAdr())
                .parentBIC(participantInfoDes.getPrntBIC())
                .dateIn(participantInfoDes.getDateIn())
                .dateOut(participantInfoDes.getDateOut())
                .participantType(participantInfoDes.getPtType())
                .availableTransferService(participantInfoDes.getSrvcs())
                .exchangeParticipant(participantInfoDes.getXchType())
                .UID(participantInfoDes.getUid())
                .participantStatus(participantInfoDes.getParticipantStatus())
                .build();
        this.changeType = bicDirectoryEntryDeserializer.getChangeType();
        System.out.println(accounts);
        this.accounts = bicDirectoryEntryDeserializer.getAccountsDeserializer().stream().map(account->{
            Accounts acc = new Accounts(account);
            acc.setBicDirectoryEntry(this);
            return acc;
        }).collect(Collectors.toList());
        this.swbicsList = bicDirectoryEntryDeserializer.getSwbicsDeserializer().stream().map(swbic->{
            SWBICS SWBIC = new SWBICS(swbic);
            SWBIC.setBicDirectoryEntry(this);
            return SWBIC;
        }).collect(Collectors.toList());
    }

    public int getBIC() {
        return BIC;
    }



    public void setBIC(int BIC) {
        this.BIC = BIC;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public List<SWBICS> getSwbicsList() {
        return swbicsList;
    }

    public void setSwbicsList(List<SWBICS> swbicsList) {
        this.swbicsList = swbicsList;
    }



    public BICDirectoryEntry() {
    }

    public BICDirectoryEntry(int BIC, ElectronicDocuments electronicDocuments, ParticipantInfo participantInfo) {
        this.BIC = BIC;
        this.electronicDocuments = electronicDocuments;
        this.participantInfo = participantInfo;
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
                ", participantInfo=" + participantInfo +
                ", changeType=" + changeType +
                ", electronicDocuments=" + electronicDocuments +
                ", accounts=" + accounts +
                ", swbicsList=" + swbicsList +
                ", accounts=" + accounts +
                '}';
    }
}
