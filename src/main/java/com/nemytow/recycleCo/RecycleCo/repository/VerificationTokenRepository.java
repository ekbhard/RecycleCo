package com.nemytow.recycleCo.RecycleCo.repository;

import com.nemytow.recycleCo.RecycleCo.domain.User;
import com.nemytow.recycleCo.RecycleCo.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}