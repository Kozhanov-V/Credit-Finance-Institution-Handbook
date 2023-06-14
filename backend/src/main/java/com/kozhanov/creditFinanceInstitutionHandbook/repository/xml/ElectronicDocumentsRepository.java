package com.kozhanov.creditFinanceInstitutionHandbook.repository.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.ElectronicDocuments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectronicDocumentsRepository extends JpaRepository<ElectronicDocuments, Integer> {
    public ElectronicDocuments findFirstByOrderByBusinessDay();
    public List<ElectronicDocuments> findAll();
}
