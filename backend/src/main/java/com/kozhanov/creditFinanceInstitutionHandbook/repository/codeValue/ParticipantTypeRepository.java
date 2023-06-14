package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantTypeRepository extends JpaRepository<ParticipantType, Integer> {
    public Optional<ParticipantType> findByCode(int code);
}
