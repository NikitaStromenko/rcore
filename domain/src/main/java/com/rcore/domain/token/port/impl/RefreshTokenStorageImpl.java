package com.rcore.domain.token.port.impl;

import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RefreshTokenStorageImpl implements RefreshTokenStorage {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void put(RefreshTokenEntity refreshTokenEntity) {
        refreshTokenRepository.save(refreshTokenEntity);
    }

    @Override
    public Optional<RefreshTokenEntity> findById(String id) {
            return refreshTokenRepository.findById(id);
    }
}