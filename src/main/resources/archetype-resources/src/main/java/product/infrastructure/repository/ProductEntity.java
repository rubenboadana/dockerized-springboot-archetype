#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "products")
@Table(name = "products")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private int quantity;
    @Column
    private double price;

}
