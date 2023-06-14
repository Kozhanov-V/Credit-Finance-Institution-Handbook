package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AvailableTransferService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailableTransferServiceRepository extends JpaRepository<AvailableTransferService, Integer> {
    public Optional<AvailableTransferService> findByCode(int code);
}
