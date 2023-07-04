package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.ElectronicDocuments;

import java.util.List;

public interface ElectronicDocumentsService {
    public void save(ElectronicDocuments electronicDocuments);
    public List<ElectronicDocuments> findAll();
}
