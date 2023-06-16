package com.kozhanov.creditFinanceInstitutionHandbook.repository.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, Integer> {
    public List<BICDirectoryEntry> findAll();
    public Optional<BICDirectoryEntry> findByBIC(int bic);

    @Query("SELECT b FROM BICDirectoryEntry b INNER JOIN b.participantInfo p WHERE " +
            "(:bic =0 OR b.BIC = :bic) AND " +
            "(:nameRecord IS NULL OR p.nameParticipant LIKE %:nameRecord%) AND " +
            "(:typeTransfer IS NULL OR p.participantType.code = :typeTransfer) AND " +
            "(:validFrom IS NULL OR p.dateIn >= :validFrom) AND " +
            "(:validUntil IS NULL OR p.dateIn <= :validUntil)")
    List<BICDirectoryEntry> filter(@Param("bic") Integer bic,
                                   @Param("nameRecord") String nameRecord,
                                   @Param("typeTransfer") Integer typeTransfer,
                                   @Param("validFrom") Date validFrom,
                                   @Param("validUntil") Date validUntil);
}
