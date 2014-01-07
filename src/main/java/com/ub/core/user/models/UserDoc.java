package com.ub.core.user.models;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
public class UserDoc {
    @Id
    protected ObjectId id;

    protected Boolean status;

    @DBRef
    protected List<RoleDoc> roleDocList;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<RoleDoc> getRoleDocList() {
        return roleDocList;
    }

    public void setRoleDocList(List<RoleDoc> roleDocList) {
        this.roleDocList = roleDocList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
