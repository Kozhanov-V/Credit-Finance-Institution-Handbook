package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue.AccountOperationRestrictionDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.AccountRestrictionDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountOperationRestriction;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;

@Table(name = "account_restriction")
@Entity
@Audited
public class AccountRestriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_restriction_code")

    private AccountOperationRestriction accountRestriction;

    @Column(name = "restriction_date")
    @Temporal(TemporalType.DATE)
    private Date restrictionDate;

    public AccountRestriction() {
    }



    public AccountRestriction(AccountRestrictionDeserializer accountRestrictionDeserializer) {
        this.accountRestriction = accountRestrictionDeserializer.getAccRstr();
        this.restrictionDate = accountRestrictionDeserializer.getAccRstrDate();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
