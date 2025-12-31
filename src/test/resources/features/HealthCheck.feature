@smoke
Feature: Health check

  Scenario: Verify the system is up and running
    Given The API base URI is configured
    When I send a GET request to "ping"
    Then the response status code should be 201

  Scenario: Send and verify custom headers and cookies
    Given The API base URI is configured
    And I set the following headers:
      | testheader | testheadervalue |
    And I set the following cookies:
      | testcookiename | testcookievalue |
    When I send a GET request to "ping"
    Then the response status code should be 201
    And the response should contain a "Server" header
    And I log the response headers and cookies