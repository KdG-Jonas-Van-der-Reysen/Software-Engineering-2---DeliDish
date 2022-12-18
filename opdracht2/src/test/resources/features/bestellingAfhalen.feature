Feature: Afhalen Bestelling

  Als koerier
  Wil ik kunnen aangeven dat een bestelling afgehaald is
  Zodat de Klant weet dat de bestelling onderweg is

  Background: testdata voor bestellingen

    Given Koeriers
      | koerier_id | naam     | voornaam  | beschikbaar | locatie |
      | 1          | Janssens | Jan       | false       | 0.5 0.5 |
      | 2          | Jos      | Vermeulen | true        | 0.1 0.1 |

    Given Bestellingen
      | bestelling_id | restaurant_id | leverOpdracht_id |
      | 1             | 1             | null             |
      | 2             | 1             | null             |

    Given LeverOpdrachten
      | leverOpdracht_id | afleverAdres                         | koerier_id | bestelling_id |
      | 1                | Boomstesteenweg 5, Merksem, België   | null       | 1             |
      | 2                | Struiksezandlaan 7, Brandsem, België | 1          | 2             |

    Given StatusWijziging
      | statusWijziging_id | leverOpdracht_id | created          | status            |
      | 1                  | 1                | 01-01-2022 12:00 | OPEN              |
      | 2                  | 2                | 01-01-2022 13:00 | OPEN              |
      | 2                  | 2                | 01-01-2022 13:10 | AF_TE_HALEN       |
      | 2                  | 2                | 01-01-2022 13:30 | KLAAR             |

  Scenario: Afhalen bestelling voor dat die klaar is
    Given koerier 1 is in het restaurant
    When status leverOpdracht 2 is "KLAAR"
    And de koerier 1 geeft aan dat hij de maaltijd heeft afgehaald
    Then wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "OP_TIJD_AFGEHAALD"
    # todo dit implemeteren

  Scenario: Afhalen bestelling na max afhaaltijd
    Given koerier 1 is in het restaurant
    When status leverOpdracht 2 is "KLAAR"
    And de koerier 1 geeft aan dat hij de maaltijd heeft afgehaald
    Then wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "AFGEHAALD"

  Scenario: Afhalen bestelling 10 of meer min na max afhaaltijd
    Given koerier 1 is in het restaurant
    When status leverOpdracht 2 is "KLAAR"
    And de koerier 1 geeft aan dat hij de maaltijd heeft afgehaald
    Then wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "TE_LAAT_AFGEHAALd"