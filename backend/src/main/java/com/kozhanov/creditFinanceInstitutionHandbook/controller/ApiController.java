package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AvailableTransferService;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ExchangeParticipant;
import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.ElectronicDocuments;
import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.ParticipantInfo;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.*;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.xml.BICDirectoryEntryRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.xml.ParticipantInfoRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.ElectronicDocumentsService;
import com.kozhanov.creditFinanceInstitutionHandbook.until.FilterParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Autowired
    private ParticipantTypeRepository participantTypeRepository;


    @Autowired
    private ParticipantStatusRepository participantStatusRepository;

    @Autowired
    private AvailableTransferServiceRepository availableTransferServiceRepository;

    @Autowired
    private ElectronicDocumentsService electronicDocumentsService;

    @Autowired
    private ParticipantInfoRepository participantInfoRepository;

    @GetMapping("/data")
    public ResponseEntity<?> getAllData() {
        Map<String,Object> response = new HashMap<>();
        response.put("bicDirectoryEntries",bicDirectoryEntryRepository.findAll());
        response.put("participantTypes",participantTypeRepository.findAll());
        response.put("availableTransferServices",availableTransferServiceRepository.findAll());
        response.put("participantStatuses",participantStatusRepository.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody FilterParameters filterParameters) {
        System.out.println(filterParameters);
        return new ResponseEntity<>("Все четка filter", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BICDirectoryEntry bicDirectoryEntry){
        bicDirectoryEntry.setElectronicDocuments(electronicDocumentsService.getLatestElectronicDocuments());
        ParticipantInfo participantInfo = bicDirectoryEntry.getParticipantInfo();
        participantInfoRepository.save(participantInfo);
        bicDirectoryEntryRepository.save(bicDirectoryEntry);
        return new ResponseEntity<>("в", HttpStatus.OK);
    }


}
