package com.project.delicioso.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data // provides to generates getters and setters for all fields
@Builder // help to build my object in a easy web using the design pattern Builder
@NoArgsConstructor
@AllArgsConstructor // If we talk about design pattern builder we need to have this one
@Entity
@Table(name="users", schema="public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto generated / incremented
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private LocalDate dateOfBirth;

    private String email;

    private String password;

    /*@ManyToOne
    @JoinColumn(name = "role_id")*/
    @Enumerated(EnumType.STRING)
    private Role role;

    private String address;

    private String postalCode;

    private LocalDateTime createdAt;

    private String paymentId;

    @Override // Method should return a list of roles
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername(){
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
