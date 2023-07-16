package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.Confirmation;

import java.util.Optional;

public interface ConfirmationService {
    public void save(Confirmation confirmation);
    public void delete(Confirmation confirmation);
    public Optional<Confirmation> findByToken(String token);
}
