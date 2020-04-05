package com.nemytow.recycleCo.RecycleCo.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
