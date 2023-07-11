package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface BICDirectoryEntryService {
    public void save(BICDirectoryEntry bicDirectoryEntry);
    public void delete(int bic);
    public List<BICDirectoryEntry> findAll();
    public HashMap<String, Object> findAll(Pageable pageable);
    public BICDirectoryEntry findByBic(int bic);
    public HashMap<String,Object> findByBic(int bic,Pageable pageable);
    public List<BICDirectoryEntry> findByBicLike(int bic);
    public List<BICDirectoryEntry> findByName(String name);
    public HashMap<String,Object>  findByName(String name, Pageable pageable);
    public void update(int bic, BICDirectoryEntry bicDirectoryEntry);
    public HashMap<String, Object> filter(Integer bic, String nameRecord, Integer typeTransfer, Date validFrom, Date validUntil,Pageable pageable);
}
