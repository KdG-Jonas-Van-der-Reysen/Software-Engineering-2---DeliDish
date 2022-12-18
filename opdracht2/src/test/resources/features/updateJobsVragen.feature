Feature: Update bestellingen vragen

  Als koerier
  Wil ik om een update van bestellingen kunnen vragen
  Zodat ik nieuwe bestellingen kan aannemen

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

  Scenario: koerier drukt op refresh knop in de app
    Given Er zijn 2 bestellingen waarvoor de koerier in aanmerking komt
    When de koerier drukt op de refresh knop van de bestellingen
    Then toont het systeem de 2 bestellingen waarvan de status "OPEN" is
