package com.kozhanov.creditFinanceInstitutionHandbook.until;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.User;
import com.kozhanov.creditFinanceInstitutionHandbook.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {
    @Autowired
    private CurrentUserService currentUserService;


    @Override
    public Optional<User> getCurrentAuditor() {
        return currentUserService.getCurrentUser();
    }
}
