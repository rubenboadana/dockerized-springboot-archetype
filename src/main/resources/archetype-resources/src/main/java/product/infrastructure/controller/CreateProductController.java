#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure.controller;

import ${package}.product.domain.dto.ErrorResponse;
import ${package}.product.domain.ProductService;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.ProductID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Products")
public class CreateProductController {
    private final ProductService productService;

    @Autowired
    public CreateProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductID.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid product information supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Product creation failure",
                    content = @Content)})
    @PostMapping(value = "/products")
    public ResponseEntity createProduct(@Valid @RequestBody ProductDTO product) {
        ProductID id;
        try {
            id = productService.create(product);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder().message(ex.getMessage()).build());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
