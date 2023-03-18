#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

    @Query("select p from products p where p.name = :name")
    List<ProductEntity> findByName(String name);

}