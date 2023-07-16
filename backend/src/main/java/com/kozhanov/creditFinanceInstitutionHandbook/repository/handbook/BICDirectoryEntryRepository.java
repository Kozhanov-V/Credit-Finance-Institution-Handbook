package com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, Integer> {
    @EntityGraph(attributePaths = {"accounts", "swbicsList"})
    public Optional<BICDirectoryEntry> findByBIC(int bic);
    public Page<BICDirectoryEntry>findByBIC(int bic,Pageable pageable);

    @Query("SELECT b FROM BICDirectoryEntry b INNER JOIN b.participantInfo p WHERE " +
            "(:bic IS NULL OR b.BIC = :bic) AND " +
            "(:nameRecord IS NULL OR LOWER(p.nameParticipant) LIKE LOWER(CONCAT('%', :nameRecord, '%'))) AND " +
            "(:typeTransfer IS NULL OR p.participantType.code = :typeTransfer) AND " +
            "(:validFrom IS NULL OR p.dateIn >= :validFrom) AND " +
            "(:validUntil IS NULL OR p.dateIn <= :validUntil)")
    Page<BICDirectoryEntry> filter(@Param("bic") Integer bic,
                                   @Param("nameRecord") String nameRecord,
                                   @Param("typeTransfer") Integer typeTransfer,
                                   @Param("validFrom") Date validFrom,
                                   @Param("validUntil") Date validUntil,
                                     Pageable pageable);

    @Query("SELECT b FROM BICDirectoryEntry b INNER JOIN b.participantInfo p WHERE " +
            "(:bic IS NULL OR b.BIC = :bic) AND " +
            "(:nameRecord IS NULL OR LOWER(p.nameParticipant) LIKE LOWER(CONCAT('%', :nameRecord, '%'))) AND " +
            "(:typeTransfer IS NULL OR p.participantType.code = :typeTransfer) AND " +
            "(:validFrom IS NULL OR p.dateIn >= :validFrom) AND " +
            "(:validUntil IS NULL OR p.dateIn <= :validUntil)")
    List<BICDirectoryEntry> filter(@Param("bic") Integer bic,
                                   @Param("nameRecord") String nameRecord,
                                   @Param("typeTransfer") Integer typeTransfer,
                                   @Param("validFrom") Date validFrom,
                                   @Param("validUntil") Date validUntil);


    List<BICDirectoryEntry> findByBICLike(int bic);
    Page<BICDirectoryEntry> findByBICLike(int bic,Pageable pageable);
    List<BICDirectoryEntry> findByParticipantInfo_NameParticipantLikeIgnoreCase(String name);
    Page<BICDirectoryEntry> findByParticipantInfo_NameParticipantLikeIgnoreCase(String name,Pageable pageable);

}
