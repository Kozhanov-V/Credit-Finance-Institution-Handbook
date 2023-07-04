package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.Accounts;

import java.util.List;

public interface AccountsService {
    public void save(Accounts accounts);
    public List<Accounts> findByBic(int bic);
    public void update(String accountNumber, Accounts accounts);
    public void delete(String accountNumber);
}
