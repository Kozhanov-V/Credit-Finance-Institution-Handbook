package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.InfoTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfoTypeCodeRepository extends JpaRepository<InfoTypeCode, String> {
    public Optional<InfoTypeCode> findByCode(String code);
}
