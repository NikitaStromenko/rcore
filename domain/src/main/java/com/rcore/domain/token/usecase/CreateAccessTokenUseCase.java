package com.rcore.domain.token.usecase;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.token.port.AccessTokenIdGenerator;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.user.entity.UserEntity;

public class CreateAccessTokenUseCase {
    private final AccessTokenIdGenerator idGenerator;
    private final AccessTokenStorage accessTokenStorage;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;

    public CreateAccessTokenUseCase(AccessTokenIdGenerator idGenerator, AccessTokenStorage accessTokenStorage, CreateRefreshTokenUseCase createRefreshTokenUseCase) {
        this.idGenerator = idGenerator;
        this.accessTokenStorage = accessTokenStorage;
        this.createRefreshTokenUseCase = createRefreshTokenUseCase;
    }

    public AccessTokenEntity create(UserEntity userEntity) throws RefreshTokenCreationException {
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(userEntity);
        AccessTokenEntity accessTokenEntity = create(userEntity, refreshTokenEntity);
        accessTokenStorage.put(accessTokenEntity);
        return accessTokenEntity;
    }

    public AccessTokenEntity create(UserEntity userEntity, RefreshTokenEntity refreshTokenEntity) {
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setId(idGenerator.generate());
        accessTokenEntity.setUserId(refreshTokenEntity.getUserId());
        accessTokenEntity.setAccesses(userEntity.getAccesses());
        accessTokenEntity.setExpireAt(DateTimeUtils.fromMillis(DateTimeUtils.getNowMillis() + refreshTokenEntity.getExpireTimeAccessToken()));
        accessTokenEntity.setCreateFromRefreshTokenId(refreshTokenEntity.getId());

        accessTokenEntity.setSign(AccessTokenEntity.sign(accessTokenEntity.getId(), DateTimeUtils.getMillis(accessTokenEntity.getExpireAt()), refreshTokenEntity));

        accessTokenStorage.put(accessTokenEntity);
        return accessTokenEntity;
    }
}
