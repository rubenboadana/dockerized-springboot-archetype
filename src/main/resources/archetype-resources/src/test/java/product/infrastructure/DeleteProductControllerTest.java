#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure;

import ${package}.product.domain.ProductService;
import ${package}.product.domain.exceptions.ProductNotFoundException;
import ${package}.product.infrastructure.controller.DeleteProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeleteProductController.class)
@AutoConfigureMockMvc
class DeleteProductControllerTest {

    private static final Long PRODUCT_ID = 1L;

    @MockBean
    private ProductService productServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_responseProductNotFound_when_productNotExist() throws Exception {
        //Given
        doThrow(new ProductNotFoundException(PRODUCT_ID)).when(productServiceMock).delete(PRODUCT_ID);

        //When/Then
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("${symbol_dollar}.message", is("Could not find product with id 1")));

    }

    @Test
    void should_responseInternalError_when_internalErrorIsProduced() throws Exception {
        //Given
        doThrow(new RuntimeException()).when(productServiceMock).delete(PRODUCT_ID);

        //When/Then
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void should_returnHTTP200_when_productDeletionSucceed() throws Exception {
        //Given
        doNothing().when(productServiceMock).delete(PRODUCT_ID);

        //When/Then
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }

}
