#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.application;

import ${package}.product.domain.ProductRepository;
import ${package}.product.domain.ProductService;
import ${package}.product.domain.dto.ProductDTO;
import ${package}.product.domain.dto.ProductID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceBean implements ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductServiceBean(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductID create(ProductDTO product) {
        return repository.create(product);
    }

    @Override
    public void delete(Long id) {
        checkIfExists(id);
        repository.delete(id);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO product) {
        return repository.update(id, product);
    }

    private void checkIfExists(Long id) {
        repository.findById(id);
    }
}
