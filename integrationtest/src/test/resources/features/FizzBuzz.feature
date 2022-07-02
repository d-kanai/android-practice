Feature: FizzBuzz

  Background:
    Given Open App

  Scenario: sample1
    When I fill in "15" to text field
    And I click the button
    Then I should see the text "FizzBuzz"