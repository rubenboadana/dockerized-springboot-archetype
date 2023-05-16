#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain;

import ${package}.product.domain.dto.Quantity;
import ${package}.product.domain.exceptions.QuantityNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class QuantityTest {

    @Test
    void should_throwException_when_QuantityIsLowerThanZero() {
        assertThrows(QuantityNotValidException.class, () -> new Quantity(-1));
    }
}

