package com.kozhanov.creditFinanceInstitutionHandbook.until;

import org.springframework.context.ApplicationEvent;

public class CurrentUserChangeEvent extends ApplicationEvent {
    private final String username;

    public CurrentUserChangeEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
