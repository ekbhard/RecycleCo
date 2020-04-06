package com.nemytow.recycleCo.RecycleCo.api.account;

import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationInput;

public interface AccountApi {
    Long addNewUser(RegistrationInput account);

    User getCurrentUser();
}
