package com.nemytow.recycleCo.RecycleCo.domain;

import com.nemytow.recycleCo.RecycleCo.service.Role;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity(name = "usr")
@AllArgsConstructor
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

    @Setter
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Profile profile;

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

    private void block(){ this.notBlocked = false;}

}
