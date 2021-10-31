Feature: PcPartPicker.com
  PC Part Picker is my favorite site. It helps you build a PC

  Background:
    Given that the Browser is open
    Then the test can proceed

  @After
  Scenario: Search for pcPartPicker and Basic Landing Page Test Case
    Given that I enter for PC Part picker in the Google search bar
    When I click on the System Builder Link
    Then I should appear on the system Builder page
    And the Original Compatibility banner should appear with the correct appearance
    And the list of components buttons should be available to click
    And I should be able to navigate to each link
    Then I should close the browser
