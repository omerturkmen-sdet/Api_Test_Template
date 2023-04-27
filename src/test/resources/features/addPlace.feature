Feature: Add Place Feature

  Background:
    Given Set base URL "baseUrl_map" for "dev"
    And Set authorization with Bearer Token on "dev"

  Scenario: Add Place
    When Send request body with "add place"
    And Send "POST" request to "add_place" endpoint
    Then Verify that status code is 200
    And Verify response body
