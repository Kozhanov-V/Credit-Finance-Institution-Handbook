package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.CreationReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreationReasonRepository extends JpaRepository<CreationReason, String> {
    public Optional<CreationReason> findByCode(String code);
}
