Feature: Navigation main menu test

  @MenuNavigation
  Scenario Outline: : Check navigation on '<page>'
    Given Open start page of Onliner
    When I click on '<menu>' item
    Then '<page>' is opened

    Examples:
    |menu           |page                 |
    |Каталог        |catalog.onliner.by   |
    |Автобарахолка  |ab.onliner.by        |
    |Дома и квартиры|r.onliner.by/pk/     |
    |Услуги         |s.onliner.by/tasks   |
    |Барахолка      |baraholka.onliner.by |
    |Форум          |forum.onliner.by     |
