#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure.controller;

import ${package}.product.domain.dto.ErrorResponse;
import ${package}.product.domain.ProductService;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.ProductID;
import ${package}.product.domain.exceptions.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Products")
public class UpdateProductController {
    private final ProductService productService;

    @Autowired
    public UpdateProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Update the product information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductID.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid product information supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Product update failure",
                    content = @Content)})
    @PutMapping(value = "/products/{id}")
    public ResponseEntity updateProduct(@PathVariable(value = "id") Long id, @Valid @RequestBody ProductDTO product) {
        ProductDTO updatedProduct;
        try {
            updatedProduct = productService.update(id, product);
        } catch (ProductNotFoundException productNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message(productNotFoundException.getMessage()).build());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder().message(ex.getMessage()).build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }
}
