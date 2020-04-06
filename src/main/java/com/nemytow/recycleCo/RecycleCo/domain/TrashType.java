package com.nemytow.recycleCo.RecycleCo.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "trash_dictionary")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrashType {
    @NonNull
    String type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
