package com.nemytow.recycleCo.RecycleCo.service.email;

import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.domain.VerificationToken;
import com.nemytow.recycleCo.RecycleCo.repository.UserRepository;
import com.nemytow.recycleCo.RecycleCo.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}
