package com.nemytow.recycleCo.RecycleCo.api.account;

import com.nemytow.recycleCo.RecycleCo.domain.Account;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationData;
import com.nemytow.recycleCo.RecycleCo.repository.AccountRepository;
import com.nemytow.recycleCo.RecycleCo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.transaction.Transactional;

@Transactional
public class AccountApiImpl implements AccountApi {

    @Autowired
    UserService userService;

    @Override
    public Account getCurrentAccount(){
        return userService.getCurrentAccount();
    }

    private String getLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
