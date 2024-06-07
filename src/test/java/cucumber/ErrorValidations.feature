Feature: Error validation

  Scenario Outline: Login with invalid credentials and error message is displayed
    Given I landed on Ecommerce Page
    When Login with invalid credentials - <username> and <password>
    Then Verify error <message> is displayed




    Examples:
      | username                    | password     | message                      |
      | ralitsalisichkova@gmail     | 214235436    | Incorrect email or password. |
      | ralitsa@gmail.com           | QAAcademy123 | Incorrect email or password. |
      | ralitsalisichkova@gmail.com | 35434t366    | Incorrect email or password. |