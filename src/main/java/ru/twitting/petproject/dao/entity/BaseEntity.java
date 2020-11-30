package ru.twitting.petproject.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created", updatable = false)
    private OffsetDateTime created;

    @Column(name = "modified")
    private OffsetDateTime modified;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @PrePersist
    private void onCreate() {
        var current = OffsetDateTime.now();
        created = current;
        modified = current;
        active = true;
    }

    @PreUpdate
    private void onUpdate() {
        modified = OffsetDateTime.now();
    }

    @PreRemove
    private void onDelete() {
        modified = OffsetDateTime.now();
        active = false;
    }
}