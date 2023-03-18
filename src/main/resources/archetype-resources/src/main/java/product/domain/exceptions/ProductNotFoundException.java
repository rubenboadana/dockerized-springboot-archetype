#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private static final String BASE_TEXT = "Could not find product with id ";

    public ProductNotFoundException(Long id) {
        super(BASE_TEXT + id);
    }
}
