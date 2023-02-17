Feature: Navigation on site
  Scenario: : Check navigation on action game page
    Given Open start page on Steam
    When I switch the site language to 'english'
    And I select the 'Categories' and 'Action'
    Then The 'Action' page is opened
    When I select the cheapest game from:
    |Carousel|Special Offers|
    Then The page of the game is opened
