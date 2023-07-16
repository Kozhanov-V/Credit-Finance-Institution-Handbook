package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.Confirmation;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.ConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfirmationServiceImpl implements ConfirmationService{
    @Autowired
    private ConfirmationRepository confirmationRepository;
    @Override
    public void save(Confirmation confirmation) {
        confirmationRepository.save(confirmation);
    }

    @Override
    public void delete(Confirmation confirmation) {
        confirmationRepository.delete(confirmation);
    }

    @Override
    public Optional<Confirmation> findByToken(String token) {
        return confirmationRepository.findByToken(token);
    }
}
