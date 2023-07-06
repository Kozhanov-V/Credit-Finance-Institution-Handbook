package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountOperationRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountOperationRestrictionRepository extends JpaRepository<AccountOperationRestriction, String> {
    public Optional<AccountOperationRestriction> findByCode(String code);

    @Override
    public List<AccountOperationRestriction> findAll();
}
