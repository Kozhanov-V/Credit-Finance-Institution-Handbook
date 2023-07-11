package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.AccountsDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.handbook.ElectronicDocumentsDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.ElectronicDocuments;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.ElectronicDocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ElectronicDocumentsServiceImpl implements ElectronicDocumentsService{

    private ElectronicDocuments latestElectronicDocuments;

    @Autowired
    private ElectronicDocumentsRepository electronicDocumentsRepository;

    @Autowired
    private BICDirectoryEntryService bicDirectoryEntryService;
    @Autowired
    private AccountsService accountService;

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
    @Override
    public List<ElectronicDocuments> findAll(){
        List<ElectronicDocuments> electronicDocumentsList = electronicDocumentsRepository.findAll();
        return electronicDocumentsList;
    }

    @Override
    public void save(ElectronicDocuments electronicDocuments) {
        electronicDocumentsRepository.save(electronicDocuments);
    }

    @Override
    public void save(ElectronicDocumentsDeserializer electronicDocumentsDeserializer) {
        ElectronicDocuments electronicDocuments = new ElectronicDocuments(electronicDocumentsDeserializer);
        System.out.println(1);
        // Use a Set to store already added accounts.
        // The Set automatically ensures uniqueness of its elements.
        Set<String> addedAccounts = new HashSet<>();

        List<BICDirectoryEntry> filteredBicDirectoryEntry = electronicDocumentsDeserializer.getBicDirectoryEntryDeserializer().stream()
                .map(bicDirectoryEntry -> {
                    // Filter only new and unique accounts
                    List<AccountsDeserializer> newAccounts = bicDirectoryEntry.getAccountsDeserializer().stream()
                            .filter(account -> {
                                boolean isUnique = !addedAccounts.contains(account.getAccount());
                                if (isUnique) {
                                    addedAccounts.add(account.getAccount());
                                    return true;
                                }
                                return false;
                            })
                            .collect(Collectors.toList());

                    bicDirectoryEntry.setAccountsDeserializer(newAccounts); // update the accounts in the deserializer
                    BICDirectoryEntry bicDirEnt = new BICDirectoryEntry(bicDirectoryEntry);
                    bicDirEnt.setElectronicDocuments(electronicDocuments);
                    return bicDirEnt;
                })
                .collect(Collectors.toList());

        electronicDocuments.setBicDirectoryEntryList(filteredBicDirectoryEntry);
        System.out.println(2);
        electronicDocumentsRepository.save(electronicDocuments);
    }



}

