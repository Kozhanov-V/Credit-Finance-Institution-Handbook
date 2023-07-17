package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ChangeTypeRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.BICDirectoryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService {

    @Autowired
  private   BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Autowired
  private  ParticipantInfoService participantInfoService;

    @Autowired
   private ChangeTypeRepository changeTypeRepository;

    @Override
    public void save(BICDirectoryEntry bicDirectoryEntry) {
        try {
            bicDirectoryEntry.setChangeType(changeTypeRepository.findByCode("ADDD").get());
            bicDirectoryEntryRepository.save(bicDirectoryEntry);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid arguments provided: ", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation: ", e);
        }
    }

    @Override
    public void delete(int bic) {
        try {
            bicDirectoryEntryRepository.deleteById(bic);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("BIC is null: ", e);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No entry found with BIC: " + bic, e);
        }
    }

    @Override
    public List<BICDirectoryEntry> findAll() {
        try {
            return bicDirectoryEntryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching all BIC directory entries: ", e);
        }
    }

    @Override
    public HashMap<String, Object> findAll(Pageable pageable) {
        try {
            Page<BICDirectoryEntry> page = bicDirectoryEntryRepository.findAll(pageable);
            HashMap<String, Object> response = new HashMap<>();
            response.put("bicDirectoryEntries", page.toList());
            response.put("totalPage", page.getTotalPages());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching paged BIC directory entries: ", e);
        }
    }

    @Override
    public BICDirectoryEntry findByBic(int bic) {

            return bicDirectoryEntryRepository.findByBICLike(bic).orElseThrow(() -> new EntityNotFoundException("No entry found with BIC: " + bic));

    }

    @Override
    public HashMap<String, Object> findByBic(int bic, Pageable pageable) {
        try {
            Page<BICDirectoryEntry> page = bicDirectoryEntryRepository.findByBICLike(bic, pageable);
            HashMap<String, Object> response = new HashMap<>();
            response.put("bicDirectoryEntries", page.toList());
            response.put("totalPage", page.getTotalPages());
            return response;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid BIC or pageable provided: ", e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("No entry found with BIC: " + bic, e);
        }
    }


    @Override
    public List<BICDirectoryEntry> findByName(String name) {
        try {
            return bicDirectoryEntryRepository.findByParticipantInfo_NameParticipantLikeIgnoreCase("%" + name + "%");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid name provided: ", e);
        }
    }

    @Override
    public HashMap<String, Object> findByName(String name, Pageable pageable) {
        try {
            Page<BICDirectoryEntry> page = bicDirectoryEntryRepository.findByParticipantInfo_NameParticipantLikeIgnoreCase("%" + name + "%", pageable);
            HashMap<String, Object> response = new HashMap<>();
            response.put("bicDirectoryEntries", page.toList());
            response.put("totalPage", page.getTotalPages());
            return response;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid name or pageable provided: ", e);
        }
    }

    @Override
    public void update(int bic, BICDirectoryEntry bicDirectoryEntry) {
        try {
            BICDirectoryEntry existingEntry = bicDirectoryEntryRepository.findByBICLike(bic)
                    .orElseThrow(() -> new EntityNotFoundException("No entry found with BIC: " + bic));
            bicDirectoryEntry.setChangeType(changeTypeRepository.findByCode("CHGD").get());
            bicDirectoryEntry.setElectronicDocuments(existingEntry.getElectronicDocuments());
            bicDirectoryEntryRepository.save(bicDirectoryEntry);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid arguments provided: ", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation: ", e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("No entry found with BIC: " + bic, e);
        }

    }

    @Override
    public HashMap<String, Object> filter(Integer bic, String nameRecord, Integer typeTransfer, Date validFrom, Date validUntil, Pageable pageable) {
        try {
            Page<BICDirectoryEntry> page = bicDirectoryEntryRepository.filter(bic, nameRecord, typeTransfer, validFrom, validUntil, pageable);
            HashMap<String, Object> response = new HashMap<>();
            response.put("bicDirectoryEntries", page.toList());
            response.put("totalPage", page.getTotalPages());
            return response;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid filter parameters provided: ", e);
        }
    }

    @Override
    public List<BICDirectoryEntry> filter(Integer bic, String nameRecord, Integer typeTransfer, Date validFrom, Date validUntil) {
        try {
            return bicDirectoryEntryRepository.filter(bic, nameRecord, typeTransfer, validFrom, validUntil);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid filter parameters provided: ", e);
        }
    }
}
