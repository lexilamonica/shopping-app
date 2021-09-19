* All rights reserved
* Lexi LaMonica
Feature: Shopping App Bill Calculation
  Scenario: Shopper buys an items of price 48.00 in a state that taxes and chooses next day shipping
    Given Shopper buys a total of 48.00
    And Shopper is shopping in CA, NY, OR IL in which a state that taxes
    And Shopper wants "NEXT_DAY" shipping
    Then Bill with tax is 50.6 dollars and 25 dollars shipping
