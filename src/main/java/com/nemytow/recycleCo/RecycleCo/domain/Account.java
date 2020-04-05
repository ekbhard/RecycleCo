package com.nemytow.recycleCo.RecycleCo.domain;

import com.nemytow.recycleCo.RecycleCo.service.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 40,unique = true)
    private String username;

    private boolean active;

    @NotNull
    @Column(nullable = false, length = 60)
    private String password;

    @ElementCollection(targetClass = Role.class , fetch = FetchType.EAGER)
    @CollectionTable(name = "account_role" , joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<Role> roles;
    @Transient
    private String passwordConfirm;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
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
}
