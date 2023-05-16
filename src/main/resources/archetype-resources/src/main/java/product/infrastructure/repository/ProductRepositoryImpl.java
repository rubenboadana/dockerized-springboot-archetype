#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure.repository;

import ${package}.product.domain.ProductRepository;
import ${package}.product.domain.dto.Price;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.ProductID;
import ${package}.product.domain.dto.Quantity;
import ${package}.product.domain.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductJPARepository productJPARepository;

    @Override
    public ProductID create(ProductDTO product) {
        ProductEntity entity = getEntityFrom(product);
        ProductEntity response = productJPARepository.save(entity);

        return new ProductID(response.getId());
    }

    @Override
    public void delete(Long id) {
        productJPARepository.deleteById(id);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO product) {
        ProductEntity oldEntity = productJPARepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        oldEntity.setName(product.getName());
        oldEntity.setQuantity(product.getQuantity());
        oldEntity.setPrice(product.getPrice());

        ProductEntity newEntity = productJPARepository.save(oldEntity);

        return getDTOFrom(newEntity);
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductEntity productEntity = productJPARepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return getDTOFrom(productEntity);
    }
    private ProductEntity getEntityFrom(ProductDTO product) {
        return ProductEntity.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    private ProductDTO getDTOFrom(ProductEntity productEntity) {
        return new ProductDTO(productEntity.getId(), productEntity.getName(), new Quantity(productEntity.getQuantity()), new Price(productEntity.getPrice()));
    }
}
