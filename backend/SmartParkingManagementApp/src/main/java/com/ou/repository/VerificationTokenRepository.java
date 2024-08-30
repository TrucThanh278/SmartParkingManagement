package com.ou.repository;

import com.ou.pojo.VerificationToken;

public interface VerificationTokenRepository {
    VerificationToken findByToken(String token);
    VerificationToken save(VerificationToken verificationToken);
    void delete(VerificationToken verificationToken);
    
}

