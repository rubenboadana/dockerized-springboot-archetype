#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain;

import ${package}.product.domain.dto.Price;
import ${package}.product.domain.exceptions.InvalidPriceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PriceTest {

    @Test
    void should_throwException_when_priceEqualOrLowerThanZero() {
        assertThrows(InvalidPriceException.class, () -> new Price(0));
        assertThrows(InvalidPriceException.class, () -> new Price(-1));
    }
}
