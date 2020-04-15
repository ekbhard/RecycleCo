package com.nemytow.recycleCo.RecycleCo.messaging;

import lombok.*;

import java.io.Serializable;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@ToString
public class PythonMessage implements Serializable {

    @NonNull
    Long userId;

    @NonNull
    Long beanId;
}
