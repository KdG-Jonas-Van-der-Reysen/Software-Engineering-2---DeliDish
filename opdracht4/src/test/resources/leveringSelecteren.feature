Feature: Overzicht leveringen tonen
  Als courier wil ik een overzicht krijgen van de voor mij beschikbare levering zodat ik een levering kan aannemen.


  Background:

    Given cities:
      | postal| name                      | country       |
      | 2000  | Antwerpen                 | BELGIUM       |
      | 2600  | Berchem                   | BELGIUM       |
      | 2040  | BerendrechtZandvlietLillo | BELGIUM       |
      | 2140  | Borgerhout                | BELGIUM       |
      | 2100  | Deurne                    | BELGIUM       |
      | 2180  | Ekeren                    | BELGIUM       |
      | 2660  | Hoboken                   | BELGIUM       |
      | 2170  | Merksem                   | BELGIUM       |
      | 2610  | Wilrijk                   | BELGIUM       |
      | 90210 | Beverly Hills             | UNITED_STATES |

    Given restaurants:
      | Name                                                       | adress_lat| adress_long|city|
      | Resto dat niet zo ver af is                                | 51.210150 | 4.397607   |0   |
      | Resto dat te ver af waardoor dishes niet dicht genoeg zijn | 60        | 10         |9|

    Given customers:
      | mail                   | mobile    | firstName | lastName  |
      | lievefransen@gmail.com | 048562675 | Lieve   | Fransen |

      #city_id is the n-th number in de cities collection
      #customer_id is the n-th number in the customers collection. The adress is added to the delivery addresses.Scenario:
      #isPrimary: The adress is the primary contact adress of the user.
    Given deliveryadresses:
      | street          | number | city_id | lattitude | longitude | customer_id | isPrimary |
      | Nationalestraat | 10   | 0       | 51.214236 | 4.398242  | 0           | 1         |
      | Gravinstraat    | 19   | 3       | 51.215090 | 4.443523  | 0           | 0         |

  #By default couriers are inactive
    Given Couriers:
      | FirstName | LastName     | Street     | Number | city_id | adress_lat | adress_long | mail       | tel       | current_lattitude | current_longitude| bankAccount           |
      | Frits   | Den Dichterbij | Volkstraat | 10   | 0      | 51.211759    | 4.396674   | frits@kdg.be | 032545856 | 51.219090         | 4.399394         | BE11111111111111111 |
      | Frats   | Van UitAmerica | BlaBla     | 10   | 0      | 51.211759    | 4.396674   | frats@kdg.be | 033545856 | 51.219090         | 4.399394         | BE11111111111111111 |

      #resto_id is the n-th number in de cities collectionGiven Dishes
    Given Dishes:
      |id| name              | description                               | price | preperationTime | maxDeliveryTime | resto_id |
      |0| Ravioli Summervides| Ravioli met rucola & Parmezaan            | 17.5  | 30              | 20              | 0        |
      |1| Spaghetti frivol   | Eigenlijk gewoon bolognaise uit een pakje | 20    | 15              | 25              | 1        |

    

  Scenario: A "placed order" invisible 4th minute when deliverypoints Courier below average.
    Given An order with description "4thminute" for dish with id 0 happened 4 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 is active and has 100 deliveryPoints
    And Courier 1 is active and has 300 deliveryPoints
    When Courier 0 asks for list of available deliveries
    Then Courier gets an empty list

  Scenario: A "placed order" visible 4th minute when deliverypoints Courier above average.
    Given An order with description "4thminute" for dish with id 0 happened 2 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 is active and has 300 deliveryPoints
    And Courier 1 is active and has 100 deliveryPoints
    When Courier 0 asks for list of available deliveries
    Then Courier gets a deliverylist with 1 order


  Scenario: A "already accepted order" not visible 4th minute when deliverypoints Courier above average.
    Given An order with description "4thminute" for dish with id 0 happened 4 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 is active and has 300 deliveryPoints
    And Courier 1 is active and has 100 deliveryPoints
    And Courier 1 selects available order 0 to deliver
    When Courier 0 asks for list of available deliveries
    Then Courier gets an empty list

  Scenario: A "placed order" visible 6th minute when deliverypoints Courier below average.
    Given An order with description "6thminute" for dish with id 0 happened 6 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 is active and has 100 deliveryPoints
    And Courier 1 is active and has 300 deliveryPoints
    When Courier 0 asks for list of available deliveries
    Then Courier gets a deliverylist with 1 order
    And The first delivery of the deliverylist has description "6thminute"

  Scenario: A "placed order" too far a away 6th minute when deliverypoints Courier below average.
    Given An order with description "6thminute" for dish with id 0 happened 6 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 is active and has 100 deliveryPoints
    # ong. 12 km verder aan 15 km/u geraak je op 30 minuten voor de plat niet op tijd
    And Courier 0 has position 51.1842807 4.5824398
    And Courier 1 is active and has 300 deliveryPoints
    When Courier 0 asks for list of available deliveries
    Then Courier gets an empty list

    #Onderstaande scenario's horen eigen bij andere user stories

  # The delivery points for selecting a delivery are not defined in th problem description. We assume '50'.
  Scenario: Courier selects order and receives deliveryPoints.
    Given An order with description "6thminute" for dish with id 1 happened 6 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 is active and has 100 deliveryPoints
    And Courier 0 selects available order 0 to deliver
    Then State of order 0 is "ACCEPTED_BY_COURIER"
    And Order 0 has courier 0 assigned
    And Courier 0 has an deliveryPoint record with type "ORDER_ACCEPTED" with 50 points

  Scenario: Courier picks up order in time and receives deliveryPoints.
    Given An order with description "6thminute" for dish with id 0 happened 6 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 is active and has 100 deliveryPoints
    And Courier 0 selects available order 0 to deliver
    When Courier 0 picks up order 0 23 minutes after selection
    Then State of order 0 is "UNDERWAY_FOR_DELIVERY"
    And Courier 0 has an deliveryPoint record with type "TIMELY_PICKUP" with 50 points


      # The delivery points for late restaurant pickup is -20
  Scenario: Courier picks up order not in time and loses deliveryPoints.
    Given An order with description "6thminute" for dish with index 0 and dish with index 1 happened 6 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 selects available order 0 to deliver
    When Courier 0 picks up order 0 100 minutes after selection
    Then State of order 0 is "UNDERWAY_FOR_DELIVERY"
    And Courier 0 has an deliveryPoint record with type "LATE_PICKUP" with -20 points

  Scenario: Courier delivers order in time and receives deliveryPoints.
    Given An order with description "6thminute" for dish with index 0 and dish with index 1 happened 6 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 selects available order 0 to deliver
    And Courier 0 picks up order 0 20 minutes after selection
    When Courier delivers 30 minutes after order 0 placed
    Then State of order 0 is "DELIVERED"
    And Courier 0 has an deliveryPoint record with type "TIMELY_DELIVERY" with 50 points

  Scenario: Courier delivers order to late and loses deliveryPoints
    Given An order with description "6thminute" for dish with index 0 and dish with index 1 happened 6 minutes in the past and has state "ORDER_PLACED" placed by customer 0
    And Courier 0 selects available order 0 to deliver
    And Courier 0 picks up order 0 20 minutes after selection
    When Courier delivers 44 minutes after order 0 placed
    Then State of order 0 is "DELIVERED"
    And Courier 0 has an deliveryPoint record with type "LATE_DELIVERY" with -20 points


    #TODO: Scenario's for alternative algorithms route duration and calculation available deliveries.