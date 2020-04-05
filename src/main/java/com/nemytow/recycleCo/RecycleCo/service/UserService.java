package com.nemytow.recycleCo.RecycleCo.service;

import com.nemytow.recycleCo.RecycleCo.domain.Account;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationData;

public interface UserService {
     Account getCurrentAccount();

     Long addNewAccount(RegistrationData user);


}
