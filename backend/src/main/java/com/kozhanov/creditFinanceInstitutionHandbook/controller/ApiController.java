package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.ParticipantInfo;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.*;
import com.kozhanov.creditFinanceInstitutionHandbook.service.BICDirectoryEntryService;
import com.kozhanov.creditFinanceInstitutionHandbook.service.ElectronicDocumentsServiceImpl;
import com.kozhanov.creditFinanceInstitutionHandbook.service.ParticipantInfoService;
import com.kozhanov.creditFinanceInstitutionHandbook.until.FilterParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {



    @Autowired
    private ParticipantTypeRepository participantTypeRepository;

    @Autowired
    private ParticipantStatusRepository participantStatusRepository;

    @Autowired
    private AvailableTransferServiceRepository availableTransferServiceRepository;

    @Autowired
    private ElectronicDocumentsServiceImpl electronicDocumentsService;

   @Autowired
   private BICDirectoryEntryService bicDirectoryEntryService;

   @Autowired
   private ParticipantInfoService participantInfoService;


    @GetMapping("/data")
    public ResponseEntity<?> getAllData() {
        Map<String,Object> response = new HashMap<>();
        response.put("bicDirectoryEntries",bicDirectoryEntryService.findAll());
        response.put("participantTypes",participantTypeRepository.findAll());
        response.put("availableTransferServices",availableTransferServiceRepository.findAll());
        response.put("participantStatuses",participantStatusRepository.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dat")
    public ResponseEntity<?> getlDat() {
        Map<String,Object> response = new HashMap<>();
        response.put("bicDirectoryEntries",bicDirectoryEntryService.findAll());
        response.put("participantTypes",participantTypeRepository.findAll());
        response.put("availableTransferServices",availableTransferServiceRepository.findAll());
        response.put("participantStatuses",participantStatusRepository.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody FilterParameters filterParameters) {
        List<BICDirectoryEntry> bicDirectoryEntries = bicDirectoryEntryService.filter(filterParameters.getBic(),filterParameters.getNameRecord(),filterParameters.getTypeTransfer(), filterParameters.getValidFrom(), filterParameters.getValidUntil());
        for (BICDirectoryEntry item: bicDirectoryEntries) {
            System.out.println(item);
        }
        return new ResponseEntity<>(bicDirectoryEntries, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BICDirectoryEntry bicDirectoryEntry){
        bicDirectoryEntry.setElectronicDocuments(electronicDocumentsService.getLatestElectronicDocuments());
        ParticipantInfo participantInfo = bicDirectoryEntry.getParticipantInfo();
        participantInfoService.save(participantInfo);
        bicDirectoryEntryService.save(bicDirectoryEntry);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bic}")
    public ResponseEntity<?> deleteBicDirectoryEntry(@PathVariable int bic) {
        System.out.println("hui");
        bicDirectoryEntryService.delete(bic);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete() {
        System.out.println("hui2222");
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @PutMapping("/update/{bic}")
    public ResponseEntity<?> updateBicDirectoryEntry(@PathVariable int bic, @RequestBody BICDirectoryEntry bicDirectoryEntry) {
     bicDirectoryEntryService.update(bic, bicDirectoryEntry);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }










}
