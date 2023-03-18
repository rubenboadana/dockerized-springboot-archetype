#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.dto;

import ${package}.product.domain.exceptions.QuantityNotValidException;

public class Quantity {

    private final int value;

    public Quantity(int value) {
        if (value < 0) {
            throw new QuantityNotValidException("Quantity cannot be lower than zero");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
