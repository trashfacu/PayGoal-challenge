package com.facundom.payGoal.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductDTO {
    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer productPrice;
    private String productStock;
}
