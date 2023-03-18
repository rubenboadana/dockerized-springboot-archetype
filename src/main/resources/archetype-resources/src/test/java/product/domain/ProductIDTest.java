#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain;

import ${package}.product.domain.dto.ProductID;
import ${package}.product.domain.exceptions.InvalidProductIDException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProductIDTest {

    @Test
    void should_throwException_when_idIsEqualOrLowerThanZero() {
        assertThrows(InvalidProductIDException.class, () -> new ProductID(0L));
        assertThrows(InvalidProductIDException.class, () -> new ProductID(-1L));
    }
}
