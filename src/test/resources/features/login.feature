@login
Feature: Instagram login

  Scenario Outline: Login as Different Users
    When I tap on Login button
    Then I type "<username>" into username field
    And I type "<password>" into password field
    Then I tap on Login button
    And I verify user is logged in
  Examples:
  |username  | password |
  |igor      |password  |
  |alex      |password  |