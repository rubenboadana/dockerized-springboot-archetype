#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain;

import ${package}.product.domain.dto.Price;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.Quantity;

public class ProductObjectMother {

    public static final String DEFAULT_PRODUCT_NAME = "pizza";
    public static final double DEFAULT_PRODUCT_PRICE = 6.70;
    public static final int DEFAULT_PRODUCT_QUANTITY = 10;

    public static ProductDTO basic() {
        return ProductDTO.builder()
                .name(DEFAULT_PRODUCT_NAME)
                .price(new Price(DEFAULT_PRODUCT_PRICE))
                .quantity(new Quantity(DEFAULT_PRODUCT_QUANTITY))
                .build();
    }


}
