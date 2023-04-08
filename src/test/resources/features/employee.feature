# You can reach out apis from https://dummy.restapiexample.com

  @wip
Feature: Employee Feature

  Background:
    Given Set base URL "baseUrl_employee" for "dev"
    And Set authorization with Bearer Token on "dev"

  Scenario: Get Employees
    When Send "GET" request to "get_employee_data" endpoint
    Then Verify that status code is 200


    Scenario: Create Employee
      When Send request body with "valid employee creation"
      And Send "POST" request to "create_employee" endpoint
      Then Verify that status code is 200
      And Verify response body