package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.Accounts;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService{
    @Autowired
    AccountsRepository accountsRepository;
    public void save(Accounts accounts){
        accountsRepository.save(accounts);
    }

    @Override
    public List<Accounts> findByBic(int bic) {
        return accountsRepository.findByBicDirectoryEntry_BIC(bic);
    }

    @Override
    public void update(String accountNumber, Accounts accounts) {
        Accounts existingAccounts = accountsRepository.findByAccount(accountNumber)
                .orElseThrow(() -> new RuntimeException("No entry found with id: " + accountNumber));
        existingAccounts.setAccount(accounts.getAccount());
        existingAccounts.setAccountCBRBIC(accounts.getAccountCBRBIC());
        existingAccounts.setAccountStatus(accounts.getAccountStatus());
        existingAccounts.setRegulationAccountType(accounts.getRegulationAccountType());
        existingAccounts.setControlKey(accounts.getControlKey());
        existingAccounts.setDateIn(accounts.getDateIn());
        existingAccounts.setDateOut(accounts.getDateOut());
        accountsRepository.save(existingAccounts);
    }

    @Override
    public void delete(String accountNumber) {
        accountsRepository.deleteById(accountNumber);
    }


}
