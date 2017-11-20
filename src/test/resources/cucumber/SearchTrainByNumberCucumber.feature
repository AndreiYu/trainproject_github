# encoding: utf-8

Feature: Search train by it's number
  @base
  Scenario Outline: Get train route on any date by train number provided

    Given User opens HomePage
    And User navigates to Ticket booking page
    And User navigates to Information about train page
    When User types train number as "<train_number>", selects "on any date" and presses "get" button
    Then User sees train route for train number provided

    Examples:
      | train_number |
      | 222Б         |
      | 111А         |
      | 605Б         |