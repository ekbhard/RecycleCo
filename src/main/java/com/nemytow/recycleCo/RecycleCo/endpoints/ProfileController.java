package com.nemytow.recycleCo.RecycleCo.endpoints;

import com.nemytow.recycleCo.RecycleCo.api.account.AccountApi;
import com.nemytow.recycleCo.RecycleCo.api.profile.ProfileApi;
import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.dto.OperationResponse;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileData;
import com.nemytow.recycleCo.RecycleCo.dto.ProfileInput;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationInput;
import com.nemytow.recycleCo.RecycleCo.service.UserPrincipalDetailsServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/profile")
@Valid
public class ProfileController {

    @Autowired
    @NonNull ProfileApi profileApi;

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {"application/json"})
    public ProfileData getUserProfile() {
        return profileApi.getProfileInfo();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse addUserProfile(@RequestBody ProfileInput profileInput, HttpServletRequest req) {
        Long idCreated = profileApi.addNewProfile(profileInput);
        OperationResponse resp = new OperationResponse();
        if (idCreated != null){
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.SUCCESS);
            resp.setOperationMessage("Profile Added , id : " + idCreated);
        }
        else{
            resp.setOperationStatus(OperationResponse.ResponseStatusEnum.ERROR);
            resp.setOperationMessage("Profile already exist");
        }
        return resp;
    }

}
