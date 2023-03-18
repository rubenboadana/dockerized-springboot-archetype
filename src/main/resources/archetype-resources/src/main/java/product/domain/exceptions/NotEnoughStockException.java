#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.exceptions;

public class NotEnoughStockException extends RuntimeException {
    private static final String BASE_TEXT = "Not enough stock";

    public NotEnoughStockException() {
        super(BASE_TEXT);
    }
}
