package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.AccountOperationRestrictionDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountOperationRestriction;

import javax.persistence.*;
import java.util.Date;

@Table(name = "account_restriction")
@Entity
public class AccountRestriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Accounts account;

    @ManyToOne
    @JoinColumn(name = "account_restriction_code")
    private AccountOperationRestriction accountRestriction;

    @Column(name = "restriction_date")
    @Temporal(TemporalType.DATE)
    private Date restrictionDate;

    public AccountRestriction() {
    }

    public AccountRestriction(Accounts account, AccountOperationRestriction accountRestriction, Date restrictionDate) {
        this.account = account;
        this.accountRestriction = accountRestriction;
        this.restrictionDate = restrictionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public AccountOperationRestriction getAccountRestriction() {
        return accountRestriction;
    }

    public void setAccountRestriction(AccountOperationRestriction accountRestriction) {
        this.accountRestriction = accountRestriction;
    }

    public Date getRestrictionDate() {
        return restrictionDate;
    }

    public void setRestrictionDate(Date restrictionDate) {
        this.restrictionDate = restrictionDate;
    }
}
