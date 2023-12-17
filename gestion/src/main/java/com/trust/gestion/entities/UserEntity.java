package com.trust.gestion.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Size(max = 500)
    @Column(name = "email", nullable = false, length = 500)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "account_non_expired", nullable = false)
    private Boolean accountNonExpired = false;

    @NotNull
    @Column(name = "account_non_locked", nullable = false)
    private Boolean accountNonLocked = false;

    @NotNull
    @Column(name = "credentials_non_expired", nullable = false)
    private Boolean credentialsNonExpired = false;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private Instant registrationDate;

    @NotNull
    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

}
