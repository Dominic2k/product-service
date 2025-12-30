package org.datpham.microservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LockProductReq {
    @NotEmpty(message = "Items cannot be empty")
    private List<LockProductItem> items;
}
