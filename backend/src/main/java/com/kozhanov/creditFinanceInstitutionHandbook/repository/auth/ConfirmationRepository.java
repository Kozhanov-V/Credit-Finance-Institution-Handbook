package com.kozhanov.creditFinanceInstitutionHandbook.repository.auth;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation,String> {
    public Optional<Confirmation> findByToken(String token);
}
