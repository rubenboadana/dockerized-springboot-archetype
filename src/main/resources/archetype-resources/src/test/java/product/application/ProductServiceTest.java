#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.application;

import ${package}.product.domain.ProductObjectMother;
import ${package}.product.domain.ProductRepository;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.ProductID;
import ${package}.product.domain.dto.Quantity;
import ${package}.product.domain.exceptions.ProductNotFoundException;
import ${package}.product.domain.exceptions.QuantityNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
final class ProductServiceTest {

    private static final Long PRODUCT_ID = 1L;
    private static final int NEW_PRODUCT_QUANTITY = 120;
    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private ProductServiceBean sut;

    @Test
    void should_returnProductId_when_createSucceed() {
        //Given
        ProductDTO product = ProductObjectMother.basic();
        ProductID expectedId = new ProductID(PRODUCT_ID);
        doReturn(expectedId).when(productRepositoryMock).create(product);

        //When
        ProductID newId = sut.create(product);

        //Then
        assertThat(newId).isEqualTo(expectedId);
    }

    @Test
    void should_throwException_when_ProductNotFound() {
        //Given
        doThrow(new ProductNotFoundException(PRODUCT_ID)).when(productRepositoryMock).findById(PRODUCT_ID);

        //When/Then
        assertThrows(ProductNotFoundException.class, () -> sut.delete(PRODUCT_ID));
    }

    @Test
    void should_succeed_when_deleteIsPossible() {
        //Given
        doReturn(ProductObjectMother.basic()).when(productRepositoryMock).findById(PRODUCT_ID);

        //When
        sut.delete(PRODUCT_ID);

        //Then
        verify(productRepositoryMock, times(1)).delete(PRODUCT_ID);
    }

    @Test
    void should_returnUpdatedProduct_when_updateSucceed() {
        //Given
        ProductDTO product = ProductObjectMother.basic();
        ProductDTO expectedProduct = ProductObjectMother.basic();
        expectedProduct.setQuantity(new Quantity(NEW_PRODUCT_QUANTITY));
        doReturn(expectedProduct).when(productRepositoryMock).update(PRODUCT_ID, product);

        //When
        ProductDTO receivedProduct = sut.update(PRODUCT_ID, product);

        //Then
        assertThat(receivedProduct.getQuantity()).isEqualTo(NEW_PRODUCT_QUANTITY);
    }

}
