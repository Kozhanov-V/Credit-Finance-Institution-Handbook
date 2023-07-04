package com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, String> {
    public List<Accounts> findByBicDirectoryEntry_BIC(int bic);
    public Optional<Accounts> findByAccount(String account);
}
