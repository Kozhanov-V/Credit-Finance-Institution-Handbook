package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.User;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CurrentUserService {
    private Long currentUserID = 0L;

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getCurrentUser() {
        return userRepository.findById(currentUserID);
    }

    public void setCurrentUser(Long id) {
        currentUserID = id;
    }
}
