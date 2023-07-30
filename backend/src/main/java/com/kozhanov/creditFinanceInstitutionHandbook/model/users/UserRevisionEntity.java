package com.kozhanov.creditFinanceInstitutionHandbook.model.users;

import com.kozhanov.creditFinanceInstitutionHandbook.until.UserRevisionListener;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
import javax.persistence.Entity;

@Entity
@RevisionEntity(UserRevisionListener.class)
public class UserRevisionEntity extends DefaultRevisionEntity {
    private String username;

    public UserRevisionEntity(String username) {
        this.username = username;
    }

    public UserRevisionEntity() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

