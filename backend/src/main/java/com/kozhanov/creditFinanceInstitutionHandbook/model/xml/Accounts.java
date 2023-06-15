package com.kozhanov.creditFinanceInstitutionHandbook.model.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Accounts {



    @Column(name = "account")
    @Size(min = 20, max = 20)
    @Id
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
    @JoinColumn(name = "BIC")
    private BICDirectoryEntry bicDirectoryEntry;

    public Accounts() {
    }

    public Accounts(String account, RegulationAccountType regulationAccountType, String controlKey, int accountCBRBIC, Date dateIn, AccountStatus accountStatus) {
        this.account = account;
        this.regulationAccountType = regulationAccountType;
        this.controlKey = controlKey;
        this.dateIn = dateIn;
        this.accountStatus = accountStatus;
        this.accountCBRBIC=accountCBRBIC;
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

    @Override
    public String toString() {
        return "Accounts{" +
                "account='" + account + '\'' +
                ", regulationAccountType=" + regulationAccountType +
                ", controlKey='" + controlKey + '\'' +
                ", accountCBRBIC='" + accountCBRBIC + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", accountStatus=" + accountStatus +
                ", bicDirectoryEntry=" + bicDirectoryEntry +
                '}';
    }
}

