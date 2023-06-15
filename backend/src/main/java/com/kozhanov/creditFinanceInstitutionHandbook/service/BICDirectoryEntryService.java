package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;

import java.util.List;

public interface BICDirectoryEntryService {
    public void save(BICDirectoryEntry bicDirectoryEntry);
    public List<BICDirectoryEntry> findAll();
    public BICDirectoryEntry findByBic(int bic);
    public void update(int bic, BICDirectoryEntry bicDirectoryEntry);
}
