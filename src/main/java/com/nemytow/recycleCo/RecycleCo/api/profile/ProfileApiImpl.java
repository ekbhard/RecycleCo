package com.nemytow.recycleCo.RecycleCo.api.profile;


import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.domain.Profile;
import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.dto.OperationResponse;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileData;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileInput;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationInput;
import com.nemytow.recycleCo.RecycleCo.repository.ProfileRepository;
import com.nemytow.recycleCo.RecycleCo.service.Role;
import com.nemytow.recycleCo.RecycleCo.service.UserPrincipalDetailsServiceImpl;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
@Log4j2
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class ProfileApiImpl implements ProfileApi {

    @Autowired
    AccountApi accountApi;

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public ProfileData getProfileInfo(){
       return ProfileData.of(accountApi.getCurrentUser().getProfile());
    }

    @Override
    public Long addNewProfile(ProfileInput profileInput) {
        if (accountApi.getCurrentUser().getProfile() != null){
            return null;
        }
        Profile profile = Profile.builder()
                .firstName(profileInput.getFirstName())
                .secondName(profileInput.getSecondName())
                .serName(profileInput.getSerName())
                .user(accountApi.getCurrentUser())
                .userData(profileInput.getUserData())
                .address(profileInput.getAddress())
                .phone(profileInput.getPhone())
                .build();
        accountApi.getCurrentUser().setProfile(profile);
        profileRepository.save(profile);
        return profile.getId();
    }

}
