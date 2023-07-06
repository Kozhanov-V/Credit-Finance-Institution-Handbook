package com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.ParticipantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ParticipantStatusRepository extends JpaRepository<ParticipantStatus, String> {
    public Optional<ParticipantStatus> findByCode(String code);
}
