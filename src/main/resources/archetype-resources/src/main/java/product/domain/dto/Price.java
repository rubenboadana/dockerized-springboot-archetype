#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.dto;

import ${package}.product.domain.exceptions.InvalidPriceException;

public class Price {

    private static final double PRICE_INVALID_VALUE = 0;
    private final double value;

    public Price(double value) {
        if (isEqualOrLowerThanZero(value)) {
            throw new InvalidPriceException("Price cannot be equal or lower than zero");
        }
        this.value = value;
    }

    private boolean isEqualOrLowerThanZero(double value) {
        int comparisonValue = Double.compare(value, PRICE_INVALID_VALUE);
        return comparisonValue == 0 || comparisonValue < 0;
    }

    public double getValue() {
        return value;
    }
}
