Feature: As a Amex customer, user should be able to submit the form for Gold plus

  Scenario: Login into the application
    Given I am on the Login page URL "https://www.americanexpress.com/fr-fr/?inav=NavLogo"
    When I click on the Cartes American Express link
    Then I will select the En savoir plus under Gold
    And I will proceed to the next page by clicking Demandez votre Carte
    Then I will fill in the userDetails
    And Click on continue button

  Scenario: Error message validation
    Given I am on the Login page URL "https://www.americanexpress.com/fr-fr/?inav=NavLogo"
    When I click on the Cartes American Express link
    Then I will select the En savoir plus under Gold
    And I will proceed to the next page by clicking Demandez votre Carte
    And I will click on continue button to evaluate the error messages