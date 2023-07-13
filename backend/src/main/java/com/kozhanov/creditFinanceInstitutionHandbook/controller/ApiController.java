package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AvailableTransferService;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantType;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.Accounts;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.ParticipantInfo;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.*;
import com.kozhanov.creditFinanceInstitutionHandbook.service.*;
import com.kozhanov.creditFinanceInstitutionHandbook.until.FilterParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
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
    private ChangeTypeRepository changeTypeRepository;

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

   @Autowired
   private AccountsService accountsService;

   @Autowired
   private ImportService importService;

    @GetMapping("/data")
    public ResponseEntity<?> getAllData(Pageable pageable) {
        return new ResponseEntity<>(bicDirectoryEntryService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/participantTypes")
    public ResponseEntity<?> getParticipantTypes(){
        List<ParticipantType> participantTypes =participantTypeRepository.findAll();
        return new ResponseEntity<>(participantTypes, HttpStatus.OK);
    }

    @GetMapping("/availableTransferServices")
    public ResponseEntity<?> getAvailableTransferServices(){
        List<AvailableTransferService> availableTransferServices = availableTransferServiceRepository.findAll();
        return new ResponseEntity<>(availableTransferServices, HttpStatus.OK);
    }

    @GetMapping("/participantStatuses")
    public ResponseEntity<?> getParticipantStatuses(){
        List<ParticipantStatus>  participantStatuses = participantStatusRepository.findAll();
        return new ResponseEntity<>(participantStatuses, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody FilterParameters filterParameters, Pageable pageable) {
       return new ResponseEntity<>( bicDirectoryEntryService.filter(filterParameters.getBic(),filterParameters.getNameRecord(),filterParameters.getTypeTransfer(), filterParameters.getValidFrom(), filterParameters.getValidUntil(),pageable)
               , HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BICDirectoryEntry bicDirectoryEntry){
        bicDirectoryEntryService.save(bicDirectoryEntry);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bic}")
    public ResponseEntity<?> deleteBicDirectoryEntry(@PathVariable int bic) {
        bicDirectoryEntryService.delete(bic);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @PutMapping("/update/{bic}")
    public ResponseEntity<?> updateBicDirectoryEntry(@PathVariable int bic, @RequestBody BICDirectoryEntry bicDirectoryEntry) {
        bicDirectoryEntry.setChangeType(changeTypeRepository.findByCode("CHGD").get());
     bicDirectoryEntryService.update(bic, bicDirectoryEntry);
        System.out.println("1111111");
        return new ResponseEntity<>(bicDirectoryEntry, HttpStatus.OK);
    }

    @GetMapping("/accounts/{bic}")
    public ResponseEntity<?> getAccounts(@PathVariable int bic){
        List<Accounts> accountsList =accountsService.findByBic(bic);
        return new ResponseEntity<>(accountsList,HttpStatus.OK);
    }

    @GetMapping("/findBy/bic/{bic}")
    public ResponseEntity<?> getEntryByBic(@PathVariable int bic, Pageable pageable){
        HashMap<String,Object> bicDirectoryEntries = bicDirectoryEntryService.findByBic(bic, pageable);
        return new ResponseEntity<>(bicDirectoryEntries,HttpStatus.OK);
    }
    @GetMapping("/findBy/name/{name}")
    public ResponseEntity<?> getEntryByName(@PathVariable String name, Pageable pageable){
        HashMap<String,Object> bicDirectoryEntries= bicDirectoryEntryService.findByName(name, pageable);
        return new ResponseEntity<>(bicDirectoryEntries,HttpStatus.OK);
    }

    @PostMapping("/import/update")
    public ResponseEntity<?> updateFromCB(){
        System.out.println("star import");
        importService.importFromCB();
        System.out.println("end import");
        return new ResponseEntity<>("bicDirectoryEntries",HttpStatus.OK);
    }




}
