Feature: Product creation use cases

  Scenario: Creating a product with correct values
    When valid product creation request is sent
    Then response code is "201"
    And response body is:
      """
      {"id":1}
      """