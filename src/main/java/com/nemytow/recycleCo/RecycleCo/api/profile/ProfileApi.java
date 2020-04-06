package com.nemytow.recycleCo.RecycleCo.api.profile;

import com.nemytow.recycleCo.RecycleCo.dto.ProfileData;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileInput;

public interface ProfileApi {
    ProfileData getProfileInfo();

    Long addNewProfile(ProfileInput profileInput);
}
