package com.kozhanov.creditFinanceInstitutionHandbook.model.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity
public class Confirmation {
    @Id
    private String token;
    @OneToOne
    private User user;


    public Confirmation(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public Confirmation() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




}
