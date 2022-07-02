Feature: Sample

  Background:
    Given Set stub response
    Given Open App

  Scenario: sample1
    When I fill in "Long Method" to text field
    And I click the button
    Then I should see the text "Long Method"