@wip
Feature: Product Feature

  Background:
    Given Set base URL "baseUrl_product" for "dev"
    And Set authorization with Bearer Token on "dev"

  Scenario: Get Employees
    When Send "GET" request to "get_employee_data" endpoint
    Then Verify that status code is 200


  Scenario: Add Product
    When Send request body with "add product"
    And Send "POST" request to "add_product" endpoint
    Then Verify that status code is 200
    And Verify response body