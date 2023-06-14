package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ChangeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChangeTypeRepository extends JpaRepository<ChangeType, String> {
    public Optional<ChangeType> findByCode(String code);
}
