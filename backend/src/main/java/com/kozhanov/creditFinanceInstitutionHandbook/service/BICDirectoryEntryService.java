package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;

import java.util.Date;
import java.util.List;

public interface BICDirectoryEntryService {
    public void save(BICDirectoryEntry bicDirectoryEntry);
    public void delete(int bic);
    public List<BICDirectoryEntry> findAll();
    public BICDirectoryEntry findByBic(int bic);
    public List<BICDirectoryEntry> findByBicLike(int bic);
    public List<BICDirectoryEntry> findByName(String name);
    public void update(int bic, BICDirectoryEntry bicDirectoryEntry);
    public List<BICDirectoryEntry> filter(Integer bic, String nameRecord, Integer typeTransfer, Date validFrom, Date validUntil);
}
