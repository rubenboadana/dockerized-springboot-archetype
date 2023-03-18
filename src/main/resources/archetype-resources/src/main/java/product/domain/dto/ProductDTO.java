#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.domain.dto;

import ${package}.product.domain.exceptions.NotEnoughStockException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {

    @JsonIgnore
    private Long id;

    private String name;
    private Quantity quantity;
    private Price price;

    public double getPrice() {
        return price.getValue();
    }

    public int getQuantity() {
        return quantity.getValue();
    }

    public boolean hasEnoughStock(int requestedQuantity) {
        return getQuantity() >= requestedQuantity;
    }

    public void checkIfHasEnoughStock(int requestedQuantity) {
        if (!hasEnoughStock(requestedQuantity)) {
            throw new NotEnoughStockException();
        }
    }

}
