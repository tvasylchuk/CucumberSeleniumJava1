Feature: Onliner Product Search Form

  @ProductSearch
  Scenario: Check search feature for tv
    Given Open start page of Onliner
    When I click on Catalog button
    Then Catalog web page is opened
    When I select the following product categories in menu:
      | Department | Электроника |
      | Category   | Телевидение |
      | Product    | Телевизоры  |
    Then TV page is opened
    When I select following filters:
      | Maker        | Samsung   |
      | UpperPrice   | 1500      |
      | DiagonalFrom | 40"       |
      | DiagonalTo   | 50"       |
      | Resolution   | 1920x1080 |
    Then products with following attributes are displayed:
      | Product      | Телевизор |
      | Maker        | Samsung   |
      | UpperPrice   | 1500      |
      | DiagonalFrom | 40        |
      | DiagonalTo   | 50        |
      | Resolution   | 1920x1080 |