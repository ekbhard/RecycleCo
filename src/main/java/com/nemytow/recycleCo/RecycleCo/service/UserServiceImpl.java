package com.nemytow.recycleCo.RecycleCo.service;

import com.nemytow.recycleCo.RecycleCo.domain.Account;
import com.nemytow.recycleCo.RecycleCo.dto.RegistrationData;
import com.nemytow.recycleCo.RecycleCo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Account getCurrentAccount() throws UsernameNotFoundException {
        String username = getLogin();
        return accountRepository.findByUsername(username);
    }

    public String getLoggedInUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth==null){
            return "nosession";
        }
        return auth.getName();
    }


    public Long addNewAccount(RegistrationData account) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        Account newAccount = Account.builder()
                .password(bCryptPasswordEncoder.encode(account.getPassword()))
                .username(account.getUsername())
                .active(true)
                .roles(roles)
                .build();
        accountRepository.save(newAccount);
        return newAccount.getId();
    }

    private String getLogin(){
        return ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }
}
