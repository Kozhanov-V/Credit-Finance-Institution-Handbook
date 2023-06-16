package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ChangeTypeRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.xml.BICDirectoryEntryRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.xml.ParticipantInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService{

    @Autowired
    BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Autowired
    ParticipantInfoService participantInfoService;

    @Autowired
    ChangeTypeRepository changeTypeRepository;

    @Override
    public void save(BICDirectoryEntry bicDirectoryEntry) {
        bicDirectoryEntryRepository.save(bicDirectoryEntry);
    }

    @Override
    public List<BICDirectoryEntry> findAll() {
        return bicDirectoryEntryRepository.findAll();
    }

    @Override
    public BICDirectoryEntry findByBic(int bic) {
        return bicDirectoryEntryRepository.findByBIC(bic).get();
    }

    @Override
    public void update(int bic, BICDirectoryEntry bicDirectoryEntry) {
        BICDirectoryEntry existingEntry = bicDirectoryEntryRepository.findByBIC(bic)
                .orElseThrow(() -> new RuntimeException("No entry found with id: " + bic));


        bicDirectoryEntryRepository.delete(existingEntry);
        bicDirectoryEntry.setChangeType(changeTypeRepository.findByCode("CHGD").get());

        participantInfoService.save(bicDirectoryEntry.getParticipantInfo());
        bicDirectoryEntry.setElectronicDocuments(existingEntry.getElectronicDocuments());
        bicDirectoryEntryRepository.save(bicDirectoryEntry);
    }

    @Override
    public List<BICDirectoryEntry> filter(Integer bic, String nameRecord, Integer typeTransfer, Date validFrom, Date validUntil) {
        System.out.println(bic + " " + nameRecord + " " + typeTransfer + " " + validFrom + " " + validUntil);
        return bicDirectoryEntryRepository.filter(bic, nameRecord, typeTransfer, validFrom, validUntil);
    }
}
