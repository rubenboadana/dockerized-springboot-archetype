#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import ${package}.product.domain.ProductService;
import ${package}.product.domain.dto.Price;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.ProductID;
import ${package}.product.domain.dto.Quantity;
import ${package}.product.infrastructure.controller.CreateProductController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreateProductController.class)
@AutoConfigureMockMvc
class CreateProductControllerTest {

    private static final Long NEW_PRODUCT_ID = 1L;

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
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_responseInternalError_when_internalErrorIsProduced() throws Exception {
        //Given
        doThrow(new RuntimeException()).when(productServiceMock).create(any(ProductDTO.class));

        //When/Then
        ProductDTO product = getProduct();
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void should_returnHTTP200_when_orderCreationSucceed() throws Exception {
        //Given
        doReturn(new ProductID(NEW_PRODUCT_ID)).when(productServiceMock).create(any(ProductDTO.class));

        //When/Then
        ProductDTO product = getProduct();
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("${symbol_dollar}.id", is(NEW_PRODUCT_ID.intValue())));
    }

    private ProductDTO getProduct() {
        return ProductDTO.builder().name("pizza").price(new Price(9.8)).quantity(new Quantity(100)).build();
    }
}
