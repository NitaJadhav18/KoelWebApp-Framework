Feature: Login feature
#  //Background: I Open Login Page
  Scenario: Login with valid email and valid password
    Given I open Login Page
    When I enter email "Verify12@gmail.com"
    And I enter password "te$t$tudent"
    And I click Login
    Then I am at HomePage

  Scenario: Login with invalid email and invalid password
    Given I open Login Page
    When I enter email "Ve@hhh.com"
    And I enter password "ddmfmm"
    And I click Login
    Then I am at LoginPage

