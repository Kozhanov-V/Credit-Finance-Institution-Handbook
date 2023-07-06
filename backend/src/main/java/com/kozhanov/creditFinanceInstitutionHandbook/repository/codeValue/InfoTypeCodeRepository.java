package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.InfoTypeCode;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoTypeCodeRepository extends JpaRepository<InfoTypeCode, String> {
    public Optional<InfoTypeCode> findByCode(String code);
}
