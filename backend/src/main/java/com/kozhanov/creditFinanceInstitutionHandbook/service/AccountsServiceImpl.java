package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.Accounts;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.AccountsRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.BICDirectoryEntryRepository;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountsServiceImpl implements AccountsService{
    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Autowired
    AccountStatusRepository accountStatusRepository;

    public void save(Accounts accounts){

            accountsRepository.save(accounts);

    }

    @Override
    public void save(BICDirectoryEntry bicDirectoryEntry, Accounts accounts) {

        accounts = setAccountStatus(accounts);

        bicDirectoryEntry.addAccount(accounts);
        bicDirectoryEntryRepository.save(bicDirectoryEntry);

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
        existingAccounts.setRegulationAccountType(accounts.getRegulationAccountType());
        existingAccounts.setControlKey(accounts.getControlKey());
        existingAccounts.setDateIn(accounts.getDateIn());
        existingAccounts.setDateOut(accounts.getDateOut());
        existingAccounts = setAccountStatus(existingAccounts);
      //  existingAccounts.setAccountRestrictions(accounts.getAccountRestrictions());
        accountsRepository.save(existingAccounts);
    }

    @Override
    public void delete(String accountNumber) {
        accountsRepository.deleteByAccount(accountNumber);
    }

    private Accounts setAccountStatus(Accounts accounts){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date today = sdf.parse(sdf.format(new Date()));
            Date dateOut = accounts.getDateOut() != null ? sdf.parse(sdf.format(accounts.getDateOut())) : today;
            Date dateIn = sdf.parse(sdf.format(accounts.getDateIn()));

            if (dateOut.before(today) || dateIn.after(today)) {
                System.out.println("ACDL");
                accounts.setAccountStatus(accountStatusRepository.findByCode("ACDL").get());
            } else {
                System.out.println("ACAC");
                accounts.setAccountStatus(accountStatusRepository.findByCode("ACAC").get());
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return accounts;
    }

}
