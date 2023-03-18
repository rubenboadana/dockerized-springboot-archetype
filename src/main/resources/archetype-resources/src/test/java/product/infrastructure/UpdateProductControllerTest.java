#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import ${package}.product.domain.ProductService;
import ${package}.product.domain.dto.Price;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.Quantity;
import ${package}.product.domain.exceptions.ProductNotFoundException;
import ${package}.product.infrastructure.controller.UpdateProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UpdateProductController.class)
@AutoConfigureMockMvc
class UpdateProductControllerTest {

    private static final Long PRODUCT_ID = 1L;
    private static final int NEW_PRODUCT_QUANTITY = 120;

    @MockBean
    private ProductService productServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_responseBadRequest_when_productPriceIsNegative() throws Exception {
        //Given
        String json = "{${symbol_escape}"name${symbol_escape}": ${symbol_escape}"string${symbol_escape}",${symbol_escape}"quantity${symbol_escape}": 0,${symbol_escape}"price${symbol_escape}": -0.1}";

        //When/Then
        mockMvc.perform(put("/products/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_responseProductNotFound_when_invalidProductIdProvided() throws Exception {
        //Given
        doThrow(new ProductNotFoundException(PRODUCT_ID)).when(productServiceMock).update(any(), any(ProductDTO.class));

        //When/Then
        ProductDTO product = getProduct();
        mockMvc.perform(put("/products/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("${symbol_dollar}.message", is("Could not find product with id 1")));

    }


    @Test
    void should_responseInternalError_when_internalErrorIsProduced() throws Exception {
        //Given
        doThrow(new RuntimeException()).when(productServiceMock).update(any(), any(ProductDTO.class));

        //When/Then
        ProductDTO product = getProduct();
        mockMvc.perform(put("/products/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void should_returnHTTP200_when_updateProductSucceed() throws Exception {
        //Given
        ProductDTO expectedProduct = getProduct();
        expectedProduct.setQuantity(new Quantity(NEW_PRODUCT_QUANTITY));
        doReturn(expectedProduct).when(productServiceMock).update(any(), any(ProductDTO.class));

        //When/Then
        ProductDTO product = getProduct();
        mockMvc.perform(put("/products/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("${symbol_dollar}.quantity", is(NEW_PRODUCT_QUANTITY)));
    }

    private ProductDTO getProduct() {
        return ProductDTO.builder().name("pizza").price(new Price(9.8)).quantity(new Quantity(100)).build();
    }
}
