# encoding: utf-8
Feature: Leave feedback form testing
  @base
  Scenario: Captcha error message is displayed when trying to leave feedback

    Given user opens HomePage
    And I navigate to Login page
    And I login as valid user by entering "userrwby" and password "a1234567"
    And I go to Leave feedback page
    And I fill all the obligatory fields in the form with valid data except captcha
    When I click submit button
    Then I see all the obligatory fields not empty and captcha error message displayed
