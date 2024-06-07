
Feature: Purchase the order from Ecommerce website

  Background:
    Given I landed on Ecommerce Page

  Scenario Outline: Positive Test for submitting the order
    Given Login with valid credentials - <username> and <password>
    When I add a product <productName> to Cart
    And Checkout <productName>
    And I submit the order
    Then Verify <message> is displayed on confirmation page


    Examples:
      | username                    | password     | productName   | message                 |
      | ralitsalisichkova@gmail.com | QAAcademy123 | IPHONE 13 PRO | THANKYOU FOR THE ORDER. |
