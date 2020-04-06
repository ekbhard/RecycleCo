package com.nemytow.recycleCo.RecycleCo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "profile")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user",unique = true)
    private User user;

    @NonNull
    String firstName;

    @NonNull
    String secondName;

    @NonNull
    String serName;

    String userData;

    @NonNull
    String address;

    @NonNull
    String phone;
}
