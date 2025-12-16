package org.datpham.microservice.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class CreateProductReq {

    @NotNull
    private String name;

    @NotNull
    @Positive
    private int price;

    @NotNull
    private int stock;

    @NotNull
    @Positive
    private String categoryId;
}
