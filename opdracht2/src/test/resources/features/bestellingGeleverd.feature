Feature: Een bestelling als afgeleverd markeren

    Als koerier
    Wil ik kunnen aangeven dat een bestelling geleverd is
    Zodat ik de bestelling kan afronden en betaald kan worden

    Background: testdata voor bestellingen

     #   Given koeriers
        Given Koeriers
            | koerier_id | naam     | voornaam  | beschikbaar | deliveryPointsSaldo | Tegoed | Locatie |
            | 1          | Janssens | Jan       | false       | 0                   | € 0    | 0.5 0.5 |
            | 2          | Jos      | Vermeulen | true        | 5                   | € 0    | 0.1 0.1 |

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
            | 2                  | 2                | 01-01-2022 13:31 | OP_TIJD_AFGEHAALD |

    Scenario: koerier 1 wilt bestelling 2 afsluiten en heeft op tijd geleverd
        Given koerier 1 geeft aan dat bestelling 2 afgeleverd is
        When koerier 1 klikt op "AFGELEVERD" bij bestelling 2
        Then wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "OP_TIJD_BEZORGD"
        And verandert beschikbaar van koerier 1 naar true

    Scenario: koerier 1 wilt bestelling 2 afsluiten en heeft op tijd geleverd
        Given koerier 1 geeft aan dat bestelling 2 afgeleverd is
        When koerier 1 klikt op "AFGELEVERD" bij bestelling 2
        Then wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "OP_TIJD_BEZORGD"
        And verandert beschikbaar van koerier 1 naar true

    Scenario: koerier 1 wilt bestelling 2 afsluiten en heeft te laat (max 10 min) geleverd
        Given koerier 1 geeft aan dat bestelling 2 afgeleverd is
        When koerier 1 klikt op "AFGELEVERD" bij bestelling 2
        Then wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "BEZORGD"
        And verandert beschikbaar van koerier 1 naar true

    Scenario: koerier 1 wilt bestelling 2 afsluiten en heeft te laat (meer dan 10 min) geleverd
        Given koerier 1 geeft aan dat bestelling 2 afgeleverd is
        When koerier 1 klikt op "AFGELEVERD" bij bestelling 2
        Then wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht 2,  created nu, status "TE_LAAT_BEZORGD"
        And verandert beschikbaar van koerier 1 naar true