#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.product.steps;

import ${package}.utils.ScenarioContext;
import ${package}.utils.http.HttpClient;
import ${package}.utils.http.HttpMethod;
import ${package}.utils.http.HttpRequest;
import ${package}.product.domain.ProductObjectMother;
import ${package}.product.domain.dto.ProductDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSteps {
    private static final String VALID_PRODUCT = "valid";
    private static final int DEFAULT_PRODUCT_ID = 1;
    private static final int NOT_EXISTING_PRODUCT_ID = 40;
    @Autowired
    private HttpClient httpClient;

    @Autowired
    private ScenarioContext context;

    @Given("a valid product is available")
    public void productIsAlreadyCreated() {
        ResponseEntity<String> response = doCreateRequest();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @When("^valid product creation request is sent${symbol_dollar}")
    public void productCreationIsRequested() {
        ResponseEntity<String> response = doCreateRequest();
        context.setResponse(response);
    }

    @When("^(valid|invalid) product delete request is sent${symbol_dollar}")
    public void productDeleteIsRequested(String requestType) {
        int productID = VALID_PRODUCT.equals(requestType) ? DEFAULT_PRODUCT_ID : NOT_EXISTING_PRODUCT_ID;
        ResponseEntity<String> response = doDeleteRequest(productID);
        context.setResponse(response);
    }

    @Then("^response code is \"([^\"]*)\"$")
    public void checkResponseCode(int expectedCode) {
        ResponseEntity<String> response = context.getResponse();
        assertThat(response.getStatusCode().value()).isEqualTo(expectedCode);
    }

    @Then("^response body is:$")
    public void checkResponseBody(String expectedBody) {
        ResponseEntity<String> response = context.getResponse();
        assertThat(response.getBody()).isEqualTo(expectedBody);
    }

    private ResponseEntity<String> doCreateRequest() {
        ProductDTO product = ProductObjectMother.basic();

        final HttpRequest<ProductDTO> httpRequest = HttpClient.createHttpRequest(HttpMethod.POST, "/products", product);
        return httpClient.doRequest(httpRequest);

    }

    private ResponseEntity<String> doDeleteRequest(int productId) {
        ProductDTO product = ProductObjectMother.basic();

        final HttpRequest<ProductDTO> httpRequest = HttpClient.createHttpRequest(HttpMethod.DELETE, "/products/" + productId, product);
        return httpClient.doRequest(httpRequest);
    }

}
