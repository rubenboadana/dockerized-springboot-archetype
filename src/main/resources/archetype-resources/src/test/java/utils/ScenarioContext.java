#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@ScenarioScope
@Component
@Data
public class ScenarioContext {
    private ResponseEntity<String> response;
}
