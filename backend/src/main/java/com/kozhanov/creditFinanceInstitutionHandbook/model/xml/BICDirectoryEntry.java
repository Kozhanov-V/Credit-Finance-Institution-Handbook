package com.kozhanov.creditFinanceInstitutionHandbook.model.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "bic_directory_entry")
public class BICDirectoryEntry {
    @Id
    @Column(name = "bic")
    @Min(100000000)
    @Max(999999999)
    private int BIC;

    @ManyToOne
    @JoinColumn(name = "change_type_code")
    private ChangeType changeType;

    @ManyToOne
    @JoinColumn(name = "EDNo",nullable = false)
    private ElectronicDocuments electronicDocuments;

    @OneToMany(mappedBy = "bicDirectoryEntry")
    private List<Accounts> accounts;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private ParticipantInfo participantInfo;


}
