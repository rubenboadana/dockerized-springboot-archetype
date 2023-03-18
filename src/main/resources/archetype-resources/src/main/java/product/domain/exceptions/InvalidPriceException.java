#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.exceptions;

public class InvalidPriceException extends RuntimeException {
    private static final String BASE_TEXT = "Price is not valid: ";

    public InvalidPriceException(String message) {
        super(BASE_TEXT + message);
    }
}
