package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.RegulationAccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegulationAccountTypeRepository extends JpaRepository<RegulationAccountType,String> {
    public Optional<RegulationAccountType> findByCode(String code);
}
