package com.rcore.database.mongo.domain.user.port.query;

import com.rcore.database.mongo.common.query.ExampleQuery;
import com.rcore.database.mongo.domain.user.port.model.UserDoc;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class FindByPhoneNumberQuery implements ExampleQuery<UserDoc> {

    private final Long phoneNumber;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("phoneNumber").is(phoneNumber);
    }
}