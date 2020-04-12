package com.nemytow.recycleCo.RecycleCo.dto;


import lombok.*;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ProfileInput {

    @NonNull
    String firstName;

    @NonNull
    String secondName;

    @NonNull
    String serName;

    String userData;

    @NonNull
    String phone;

    @NonNull
    String address;
}
