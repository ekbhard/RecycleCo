package com.nemytow.recycleCo.RecycleCo.messaging;

import lombok.*;

import java.io.Serializable;


@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@ToString
public class TrashMessage implements Serializable {

    Long typeId;

    boolean right;

    Long userId;

    Long beanId;
}


