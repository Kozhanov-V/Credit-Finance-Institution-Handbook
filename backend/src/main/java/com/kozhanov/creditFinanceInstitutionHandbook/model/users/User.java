package com.kozhanov.creditFinanceInstitutionHandbook.model.users;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.BICDirectoryEntry;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinTable(name = "user_favorites_bic",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bic"))
    private Set<BICDirectoryEntry> bicDirectoryEntries;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(User user) {
        this.username = user.username;
        this.password = user.password;
        this.roles = user.getRoles();
        this.bicDirectoryEntries = user.getBicDirectoryEntries();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role){
        if(roles==null){
            roles = new HashSet<>();
        }
        roles.add(role);
    }
    public void addBicDirectory(BICDirectoryEntry bicDirectoryEntry){
        if(bicDirectoryEntries==null){
            bicDirectoryEntries = new HashSet<>();
        }
        bicDirectoryEntries.add(bicDirectoryEntry);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<BICDirectoryEntry> getBicDirectoryEntries() {
        return bicDirectoryEntries;
    }

    public void setBicDirectoryEntries(Set<BICDirectoryEntry> bicDirectoryEntries) {
        this.bicDirectoryEntries = bicDirectoryEntries;
    }

}