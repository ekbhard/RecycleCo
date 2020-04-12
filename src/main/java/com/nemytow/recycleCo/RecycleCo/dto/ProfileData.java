package com.nemytow.recycleCo.RecycleCo.dto;

import com.nemytow.recycleCo.RecycleCo.domain.Profile;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ProfileData {

    @NonNull
    String firstName;

    @NonNull
    String secondName;

    String serName;

    String userData;

    @NonNull
    String phone;

    @NonNull
    String address;

    public static ProfileData of(Profile profile){
        return ProfileData.builder()
                .firstName(profile.getFirstName())
                .secondName(profile.getSecondName())
                .serName(profile.getSerName())
                .userData(profile.getUserData())
                .phone(profile.getPhone())
                .address(profile.getAddress())
                .build();
    }
}
