package com.kozhanov.creditFinanceInstitutionHandbook.repository.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, Integer> {
    public List<BICDirectoryEntry> findAll();
    public Optional<BICDirectoryEntry> findByBIC(int bic);

}
