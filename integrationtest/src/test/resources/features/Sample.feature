Feature: Sample

  Background:
    Given Set stub response

  Scenario: Android Sample
    Given Open Android App
    When I fill in "Long Method" to text field
    And I click the button
    Then I should see the text "Long Method"

  Scenario: IOS Sample
    Given Open IOS App
    When I click Count Up Button
    Then I should see the text "4" in IOS screen