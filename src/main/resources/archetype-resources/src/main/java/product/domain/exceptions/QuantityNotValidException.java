#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.exceptions;

public class QuantityNotValidException extends RuntimeException {
    private static final String BASE_TEXT = "Quantity is not valid: ";

    public QuantityNotValidException(String message) {
        super(BASE_TEXT + message);
    }
}

