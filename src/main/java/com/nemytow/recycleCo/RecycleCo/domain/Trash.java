package com.nemytow.recycleCo.RecycleCo.domain;

import com.nemytow.recycleCo.RecycleCo.messaging.TrashMessage;
import lombok.*;
import org.springframework.lang.Nullable;

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

    @Setter
    boolean resolved = false;

    @Nullable
    String uri;

    @NonNull
    Long binId;

    public static Trash from (User user, TrashType trashType, TrashMessage message){
        return Trash.builder()
                .uri(message.getUri())
                .user(user)
                .binId(message.getBeanId())
                .type(trashType)
                .build();

    }
}
