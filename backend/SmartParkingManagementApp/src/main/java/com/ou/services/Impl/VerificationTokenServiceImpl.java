package com.ou.service.impl;

import com.ou.pojo.VerificationToken;
import com.ou.repository.VerificationTokenRepository;
import com.ou.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        return tokenRepository.findByToken(token); // Phương thức findByToken không trả về Optional
    }

    @Override
    public void save(VerificationToken token) {
        tokenRepository.save(token);
    }
}
