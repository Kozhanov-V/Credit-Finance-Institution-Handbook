package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;

import java.util.Date;
import java.util.List;

public interface BICDirectoryEntryService {
    public void save(BICDirectoryEntry bicDirectoryEntry);
    public void delete(int bic);
    public List<BICDirectoryEntry> findAll();
    public BICDirectoryEntry findByBic(int bic);
    public void update(int bic, BICDirectoryEntry bicDirectoryEntry);
    public List<BICDirectoryEntry> filter(Integer bic, String nameRecord, Integer typeTransfer, Date validFrom, Date validUntil);
}
