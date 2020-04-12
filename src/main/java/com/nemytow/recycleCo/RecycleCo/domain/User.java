package com.nemytow.recycleCo.RecycleCo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

@Entity
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Setter
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Profile profile;

    @Setter
    @ElementCollection(targetClass = Role.class , fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<Role> roles;

    @Builder.Default
    boolean notBlocked = true;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_profile", unique = true)
    private Profile user;

    private boolean active;

    @Builder.Default
    @Column(updatable = false)
    Instant created = Instant.now();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private void block(){ this.notBlocked = false;}

}
