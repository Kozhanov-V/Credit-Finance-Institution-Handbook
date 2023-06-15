package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.Accounts;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.xml.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService{
    @Autowired
    AccountsRepository accountsRepository;
    public void save(Accounts accounts){
        accountsRepository.save(accounts);
    }
}
