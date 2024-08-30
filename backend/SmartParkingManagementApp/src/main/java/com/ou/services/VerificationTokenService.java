package com.ou.service;

import com.ou.pojo.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);
    void save(VerificationToken token);
}
