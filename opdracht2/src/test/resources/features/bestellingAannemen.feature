Feature: Een bestelling aannemen

  Als koerier
  wil ik een bestelling (opdracht) kunnen aannemen
  zodat ik geld en punten kan verdienen

  Background: testdata voor bestellingen

    Given Koeriers
      | koerier_id | naam     | voornaam  | beschikbaar | locatie |
      | 1          | Janssens | Jan       | true        | 0.5 0.5 |
      | 2          | Jos      | Vermeulen | true        | 0.1 0.1 |

    Given Bestellingen
      | bestelling_id | restaurant_id | leverOpdracht_id |
      | 1             | 1             | null             |
      | 2             | 1             | null             |

    Given LeverOpdrachten
      | leverOpdracht_id | afleverAdres                         | koerier_id | bestelling_id |
      | 1                | Boomstesteenweg 5, Merksem, België   | null       | 1             |
      | 2                | Struiksezandlaan 7, Brandsem, België | null       | 2             |

    Given StatusWijziging
      | statusWijziging_id | leverOpdracht_id | created          | status |
      | 1                  | 1                | 01-01-2022 12:00 | OPEN   |
      | 2                  | 2                | 01-01-2022 13:00 | OPEN   |

  Scenario: koerier wilt een leverOpdracht aannemen
    Given koerier 1 wilt leverOpdracht 2 aannemen
    When koerier 1 klikt op aannemen bij leverOpdracht 2
    Then verandert koerier_id van leverOpdracht 2 naar 1
    And wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "AF_TE_HALEN"
    And verandert beschikbaar van koerier 1 naar false
    And het systeem geeft de gegevens van leverOpdracht 2 weer

  Scenario: koerier wilt een tweede leverOpdracht aannemen
    Given koerier 1 wilt leverOpdracht 1 aannemen
    When de beschikbaarheid van koerier 1 staat op "false"
    Then nieuwe leverOpdracht 1 wordt niet aangenomen
