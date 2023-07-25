package com.kozhanov.creditFinanceInstitutionHandbook.config;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.User;
import com.kozhanov.creditFinanceInstitutionHandbook.until.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class EnversConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
