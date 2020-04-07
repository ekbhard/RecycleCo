package com.nemytow.recycleCo.RecycleCo.messaging;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class TrashMessage implements Serializable {

    Long trashId;

    Long typeId;

    boolean right;

    Long userId;

    Long beanId;
}


