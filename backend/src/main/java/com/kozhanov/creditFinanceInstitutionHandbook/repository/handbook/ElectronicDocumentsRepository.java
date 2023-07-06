package com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.ElectronicDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ElectronicDocumentsRepository extends JpaRepository<ElectronicDocuments, Integer> {
    public ElectronicDocuments findFirstByOrderByBusinessDay();
    public List<ElectronicDocuments> findAll();
}
