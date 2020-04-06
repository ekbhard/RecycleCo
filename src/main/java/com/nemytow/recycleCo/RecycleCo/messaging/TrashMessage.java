package com.nemytow.recycleCo.RecycleCo.messaging;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TrashMessage implements Serializable {

    Long trashId;

    String type;

    boolean right;

    Long userId;

    Long beanId;
}


