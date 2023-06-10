package com.kozhanov.creditFinanceInstitutionHandbook.repository.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.xml.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts,String> {
}
