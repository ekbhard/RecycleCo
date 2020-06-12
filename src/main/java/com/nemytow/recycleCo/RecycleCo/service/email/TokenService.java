package com.nemytow.recycleCo.RecycleCo.service.email;

import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.domain.VerificationToken;

public interface TokenService {

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
