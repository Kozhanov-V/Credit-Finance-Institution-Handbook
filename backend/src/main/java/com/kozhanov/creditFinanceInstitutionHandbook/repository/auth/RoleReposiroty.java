package com.kozhanov.creditFinanceInstitutionHandbook.repository.auth;

import com.kozhanov.creditFinanceInstitutionHandbook.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleReposiroty extends JpaRepository<Role,Long> {
}
