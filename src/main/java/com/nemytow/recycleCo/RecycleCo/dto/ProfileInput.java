package com.nemytow.recycleCo.RecycleCo.dto;


import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
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
