package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.User;
import com.kozhanov.creditFinanceInstitutionHandbook.model.users.UserRevisionEntity;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.UserRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.until.CurrentUserChangeEvent;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CurrentUserService implements ApplicationListener<CurrentUserChangeEvent> {

    private UserRepository userRepository;
    private Long currentUserID = 0L;

    @Autowired
    private ApplicationContext applicationContext;

    public CurrentUserService() {
    }

    public UserRepository getUserRepository() {
        if(userRepository == null) {
            userRepository = (UserRepository) applicationContext.getBean(UserRepository.class);
        }
        return userRepository;
    }


    public Optional<User> getCurrentUser() {
        return getUserRepository().findById(currentUserID);
    }

    public void setCurrentUser(Long id) {
        currentUserID = id;
    }


    @SneakyThrows
    @Override
    public void onApplicationEvent(CurrentUserChangeEvent event) {
        User user = getUserRepository().findByUsername(event.getUsername()).orElseThrow(() -> new EntityNotFoundException("No entry found with BIC: " + event.getUsername()));
        setCurrentUser(user.getId());
    }
}

