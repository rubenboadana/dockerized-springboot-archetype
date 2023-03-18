Feature: Product delete use cases

  Scenario: Deleting a product with correct values
    Given a valid product is available
    When valid product delete request is sent
    Then response code is "200"

  Scenario: Deleting a product not found
    Given a valid product is available
    When invalid product delete request is sent
    Then response code is "404"
    And response body is:
      """
      {"message":"Could not find product with id 40"}
      """