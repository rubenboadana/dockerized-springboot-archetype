#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.exceptions;

public class InvalidProductIDException extends RuntimeException {
    private static final String BASE_TEXT = "Product id is not valid: ";

    public InvalidProductIDException(String message) {
        super(BASE_TEXT + message);
    }
}
