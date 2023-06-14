package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.ElectronicDocuments;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.xml.ElectronicDocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ElectronicDocumentsService {

    private ElectronicDocuments latestElectronicDocuments;

    @Autowired
    private ElectronicDocumentsRepository electronicDocumentsRepository;

    @PostConstruct
    public void init() {
        this.latestElectronicDocuments = electronicDocumentsRepository.findFirstByOrderByBusinessDay();
    }

    public ElectronicDocuments getLatestElectronicDocuments() {
        if(latestElectronicDocuments == null){
            init();
        }
        return latestElectronicDocuments;
    }

    public void updateLatestElectronicDocuments(ElectronicDocuments electronicDocuments) {
        this.latestElectronicDocuments = electronicDocuments;
        electronicDocumentsRepository.save(electronicDocuments);
    }
    public List<ElectronicDocuments> findAll(){
        List<ElectronicDocuments> electronicDocumentsList = electronicDocumentsRepository.findAll();
        return electronicDocumentsList;
    }
}

