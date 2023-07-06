package com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.ParticipantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfo, Integer> {
}
