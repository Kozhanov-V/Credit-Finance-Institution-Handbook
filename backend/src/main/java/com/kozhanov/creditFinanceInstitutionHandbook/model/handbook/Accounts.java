package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.AccountStatusDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.RegulationAccountTypeDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.AccountsDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "account")
    @Size(min = 20, max = 20)

    private String account;

    @ManyToOne
    @JoinColumn(name = "regulation_account_type_code")
    private RegulationAccountType regulationAccountType;

    @Column(name = "control_key")
    @Size(min = 2, max = 2)
    private String controlKey;

    @Column(name = "account_cbr_bic")
    @Max(999999999)
    private int accountCBRBIC;

    @Column(name = "date_in")
    @Temporal(TemporalType.DATE)
    private Date dateIn;

    @Column(name = "date_out")
    @Temporal(TemporalType.DATE)
    private Date dateOut;

    @ManyToOne
    @JoinColumn(name = "account_status_code")
    private AccountStatus accountStatus;

    @ManyToOne
    @JoinColumn(name = "bic")
    @JsonBackReference
    private BICDirectoryEntry bicDirectoryEntry;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<AccountRestriction> accountRestrictions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Accounts(AccountsDeserializer accountsDeserializer) {
        this.account = accountsDeserializer.getAccount();
        this.regulationAccountType = accountsDeserializer.getRegulationAccountType();
        this.controlKey = accountsDeserializer.getControlKey();
        this.accountCBRBIC = accountsDeserializer.getAccountCBRBIC();
        this.dateIn = accountsDeserializer.getDateIn();
        this.dateOut = accountsDeserializer.getDateOut();
        this.accountStatus = accountsDeserializer.getAccountStatus();
        if(accountsDeserializer.getAccountRestrictionsDeserializer()!=null)
        this.accountRestrictions = accountsDeserializer.getAccountRestrictionsDeserializer().stream().map(AccountRestriction::new).collect(Collectors.toList());

    }


    public List<AccountRestriction> getAccountRestrictions() {
        return accountRestrictions;
    }

    public void addAccountRestriction(AccountRestriction accountRestriction){
        if(accountRestrictions==null){
            accountRestrictions = new ArrayList<>();
        }
        accountRestrictions.add(accountRestriction);
    }

    public Accounts() {
    }

    public Accounts(String account, RegulationAccountType regulationAccountType, String controlKey, int accountCBRBIC, Date dateIn, Date dateOut, AccountStatus accountStatus, BICDirectoryEntry bicDirectoryEntry) {
        this.account = account;
        this.regulationAccountType = regulationAccountType;
        this.controlKey = controlKey;
        this.accountCBRBIC = accountCBRBIC;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.accountStatus = accountStatus;
        this.bicDirectoryEntry = bicDirectoryEntry;
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

    public BICDirectoryEntry getBicDirectoryEntry() {
        return bicDirectoryEntry;
    }

    public void setBicDirectoryEntry(BICDirectoryEntry bicDirectoryEntry) {
        this.bicDirectoryEntry = bicDirectoryEntry;
    }

    public void setAccountRestrictions(List<AccountRestriction> accountRestrictions) {
        this.accountRestrictions = accountRestrictions;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "account='" + account + '\'' +
                ", regulationAccountType=" + regulationAccountType +
                ", controlKey='" + controlKey + '\'' +
                ", accountCBRBIC=" + accountCBRBIC +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", accountStatus=" + accountStatus +
                '}';
    }
}

