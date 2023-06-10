package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ExchangeParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeParticipantRepository extends JpaRepository<ExchangeParticipant,Integer> {
    public Optional<ExchangeParticipant> findByCode(int code);
}
