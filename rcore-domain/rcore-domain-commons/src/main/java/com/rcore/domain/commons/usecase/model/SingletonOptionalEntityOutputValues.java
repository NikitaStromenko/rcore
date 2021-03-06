package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

import java.util.Optional;

@Value(staticConstructor = "of")
public class SingletonOptionalEntityOutputValues<Entity extends BaseEntity> implements UseCase.OutputValues {
    private final Optional<Entity> entity;

    public static SingletonOptionalEntityOutputValues empty(){
        return SingletonOptionalEntityOutputValues.of(Optional.empty());
    }
}
