package com.nemytow.recycleCo.RecycleCo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "trash")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trash {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user",unique = true)
    private User user;

    boolean checked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private TrashType type;

    @NonNull
    Long binId;
}
