package com.nemytow.recycleCo.RecycleCo.domain;

import com.nemytow.recycleCo.RecycleCo.messaging.TrashMessage;
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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trash_type_id")
    private TrashType type;

    @NonNull
    Long binId;

    public static Trash from (User user,TrashType trashType){
        return Trash.builder()
                .user(user)
                .binId(1L)
                .type(trashType)
                .build();

    }
}
