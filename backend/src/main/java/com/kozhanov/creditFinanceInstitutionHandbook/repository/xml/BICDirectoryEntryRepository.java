package com.kozhanov.creditFinanceInstitutionHandbook.repository.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry,Integer> {
    public List<BICDirectoryEntry> findAll();
}
