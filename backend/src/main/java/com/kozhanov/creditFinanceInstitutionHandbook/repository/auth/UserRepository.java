package com.kozhanov.creditFinanceInstitutionHandbook.repository.auth;

import com.kozhanov.creditFinanceInstitutionHandbook.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
}
