#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain;

import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.ProductID;

public interface ProductRepository {

    ProductID create(ProductDTO product);

    void delete(Long id);

    ProductDTO update(Long id, ProductDTO product);

    ProductDTO findById(Long id);

}
