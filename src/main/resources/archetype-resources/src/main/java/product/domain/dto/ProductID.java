#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ${package}.product.domain.exceptions.InvalidProductIDException;

public class ProductID {

    private static final String IDENTIFIER_LOWER_THAN_ONE = "Product id can't be lower than 1";

    @JsonProperty("id")
    private final Long value;

    public ProductID(Long value) {
        if (value <= 0) {
            throw new InvalidProductIDException(IDENTIFIER_LOWER_THAN_ONE);
        }

        this.value = value;
    }
}