package com.rcore.database.mongo.auth.token.model;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class AccessTokenDoc extends AccessTokenEntity {
}
