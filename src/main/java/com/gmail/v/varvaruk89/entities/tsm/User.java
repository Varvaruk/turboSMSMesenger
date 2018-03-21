package com.gmail.v.varvaruk89.entities.tsm;


import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)

    @Enumerated(EnumType.STRING)

    public List<Role> authorities;
    private String password;
    @Transient
    private String confirmPassword;
    private String username;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public User() {
    }

    public User(List<Role> authorities, String password, String username, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    @Override
    public List<Role> getAuthorities() {
        return authorities;
    }

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", authorities=" + authorities +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", username='" + username + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return accountNonExpired == user.accountNonExpired &&
                accountNonLocked == user.accountNonLocked &&
                credentialsNonExpired == user.credentialsNonExpired &&
                enabled == user.enabled &&
                Objects.equals(id, user.id) &&
                Objects.equals(authorities, user.authorities) &&
                Objects.equals(password, user.password) &&
                Objects.equals(confirmPassword, user.confirmPassword) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, authorities, password, confirmPassword, username, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }
}
