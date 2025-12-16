package org.datpham.microservice.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BaseEntity {
    private boolean isDeleted;
    private Instant createdDate;
    private Instant updatedDate;
    private String createdBy;
    private String updatedBy;
}
