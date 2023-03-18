#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure.controller;

import ${package}.product.domain.dto.ErrorResponse;
import ${package}.product.domain.ProductService;
import ${package}.product.domain.exceptions.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Products")
public class DeleteProductController {
    private final ProductService productService;

    @Autowired
    public DeleteProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Product deletion failure",
                    content = @Content)})
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") Long id) {
        try {
            productService.delete(id);
        } catch (ProductNotFoundException productNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message(productNotFoundException.getMessage()).build());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder().message(ex.getMessage()).build());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
