package com.kozhanov.creditFinanceInstitutionHandbook.controller;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountOperationRestriction;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountStatus;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.Accounts;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountOperationRestrictionRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.RegulationAccountTypeRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.AccountsService;
import com.kozhanov.creditFinanceInstitutionHandbook.service.BICDirectoryEntryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "Account Management", description = "Operations related to account in Credit Finance Institution Handbook")
@RestController
@RequestMapping("/api/account")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;


    @Autowired
    private RegulationAccountTypeRepository regulationAccountTypeRepository;

    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Autowired
    private AccountOperationRestrictionRepository accountOperationRestrictionRepository;


    @ApiOperation(value = "Get all accounts belonging to Bic Directory Entry", response = List.class)
    @GetMapping("/{bic}")
    public ResponseEntity<?> getAccounts(@PathVariable int bic){
        List<Accounts> accountsList =accountsService.findByBic(bic);
        return new ResponseEntity<>(accountsList,HttpStatus.OK);
    }

    @ApiOperation(value = "Update an existing account")
    @PutMapping("/update/{accountNumber}")
    public ResponseEntity<?> updateAccount(@PathVariable String accountNumber, @RequestBody Accounts accounts) {
        accountsService.update(accountNumber, accounts);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an account")
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable String accountNumber) {
        accountsService.delete(accountNumber);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }

    @ApiOperation(value = "Save new account")
    @PostMapping("/save")
    public ResponseEntity<?>saveAccount(@RequestParam int bic, @RequestBody Accounts accounts) {
       BICDirectoryEntry bicDirectoryEntry = bicDirectoryEntryService.findByBic(bic);

       accountsService.save(bicDirectoryEntry, accounts);
        return new ResponseEntity<>("All is good", HttpStatus.OK);
    }
    @ApiOperation(value = "Get all regulation account type codes", response = List.class)
    @GetMapping("/regulationAccountTypes")
    public ResponseEntity<?> getRegulationAccountTypes() {
        List<RegulationAccountType> regulationAccountTypeList = regulationAccountTypeRepository.findAll();
        return new ResponseEntity<>(regulationAccountTypeList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all account status codes",response = List.class)
    @GetMapping("/accountStatuses")
    public ResponseEntity<List<AccountStatus>> getAccountStatuses() {
        List<AccountStatus> accountStatusList = accountStatusRepository.findAll();
        return new ResponseEntity<>(accountStatusList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all account restriction codes",response = List.class)
    @GetMapping("/accountRestrictions")
    public ResponseEntity<?> getAccountRestrictions(){
        List<AccountOperationRestriction> accountRestrictionList = accountOperationRestrictionRepository.findAll();
        return new ResponseEntity<>(accountRestrictionList, HttpStatus.OK);
    }

}
