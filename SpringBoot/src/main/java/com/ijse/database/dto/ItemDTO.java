package com.ijse.database.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private String name;
    private Double price;
    private Integer qty;
    private Long categoryId;
}
