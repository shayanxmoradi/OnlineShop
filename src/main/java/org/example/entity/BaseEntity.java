package org.example.entity;

public abstract class BaseEntity<ID>{
    private ID id;
    public BaseEntity() {}

    public BaseEntity(ID id) {
        this.id = id;
    }
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }

}
