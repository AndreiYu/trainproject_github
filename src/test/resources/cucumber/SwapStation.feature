# encoding: utf-8
Feature: This feature deals with swapping stations functionality of the application

  @base
  Scenario Outline: Swapping departure point and arrival point
    Given User opens HomePage
    And User navigates to the Info About Places Available And Cost Page
    When User enters the departure point "<point_from>" and destination point "<point_to>" and presses swap-button
    Then User sees that departure point became "<point_to>" and destination point became "<point_from>"

    Examples:
      | point_from | point_to |
      | МИНСК      | ГРОДНО   |
      | БРЕСТ      | МИНСК    |
