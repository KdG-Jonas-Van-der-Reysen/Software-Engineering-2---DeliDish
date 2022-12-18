package be.kdg.delidish;

import be.kdg.delidish.application.DeliveryController;
import be.kdg.delidish.application.OrderController;
import be.kdg.delidish.business.domain.common.*;
import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderEvent;
import be.kdg.delidish.business.domain.order.OrderLine;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.person.*;
import be.kdg.delidish.business.domain.restaurant.Dish;
import be.kdg.delidish.business.domain.restaurant.Restaurant;
import be.kdg.delidish.repositories.memory.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@CucumberContextConfiguration
public class BDDTests {
    // Repositories
    private final CourierMemoryRepository courierRepository;
    private final CityMemoryRepository cityRepository;
    private final RestaurantMemoryRepository restaurantRepository;
    private final CustomerMemoryRepository customerRepository;
    private final DeliveryAddressMemoryRepository deliveryAddressRepository;
    private final DishMemoryRepository dishRepository;
    private final OrderMemoryRepository orderRepository;

    // Controllers
    private final DeliveryController deliveryController;
    private final OrderController orderController;

    // Attributes
    private List<Order> availableDeliveries;

    public BDDTests(
            CourierMemoryRepository courierRepository,
            CityMemoryRepository cityRepository,
            RestaurantMemoryRepository restaurantRepository,
            CustomerMemoryRepository customerRepository,
            DeliveryAddressMemoryRepository deliveryAddressRepository,
            DishMemoryRepository dishRepository, OrderMemoryRepository orderRepository, DeliveryController deliveryController,
            OrderController orderController
    ) {
        this.courierRepository = courierRepository;
        this.cityRepository = cityRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.deliveryController = deliveryController;
        this.orderController = orderController;
    }

    @Given("^cities:$")
    public void cities(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {
            cityRepository.insert(cityRepository.getNextAvailableId(), new City(Integer.parseInt(map.get("postal")), map.get("name"), map.get("country")));
        }
    }

    @Given("^restaurants:$")
    public void restaurants(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {
            Position restaurantPosition = new Position(Double.parseDouble(map.get("adress_long")), Double.parseDouble(map.get("adress_lat")));
            Address restaurantAddress = new Address(
                    cityRepository.findById(Integer.parseInt(map.get("city"))),
                    "",
                    "",
                    restaurantPosition,
                    cityRepository.findById(Integer.parseInt(map.get("city"))).getCountry()
            );

            ContactInfo contactInfo = new ContactInfo(restaurantAddress, "geen@email.com", "geen tel");

            restaurantRepository.insert(restaurantRepository.getNextAvailableId(), new Restaurant(map.get("name"), contactInfo));

        }
    }

    @Given("^customers:$")
    public void customers(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {
            Customer cmr = new Customer(map.get("firstName"), map.get("lastName"));
            cmr.setPersonId(customerRepository.getNextAvailableId());
            customerRepository.insert(cmr.getPersonId(), cmr);
        }
    }

    @Given("^deliveryadresses:$")
    public void deliveryadresses(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {
            // Eerst customer ophalen
            Customer cmr = customerRepository.findById(Integer.parseInt(map.get("customer_id")));

            // Dan positie aanmaken
            Position deliveryPosition = new Position(Double.parseDouble(map.get("lattitude")), Double.parseDouble(map.get("longitude")));

            DeliveryAddress deliveryAddress = new DeliveryAddress(
                    cityRepository.findById(Integer.parseInt(map.get("city_id"))),
                    map.get("street"),
                    map.get("number"),
                    deliveryPosition,
                    cityRepository.findById(Integer.parseInt(map.get("city_id"))).getCountry(),
                    cmr
            );

            deliveryAddressRepository.insert(
                    deliveryAddressRepository.getNextAvailableId(),
                    deliveryAddress
            );

        }
    }

    @Given("^Couriers:$")
    public void couriers(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {
            Partner partner = new Partner("Be4848464");

            Courier courier = new Courier(
                    map.get("FirstName"),
                    map.get("LastName"),
                    partner,
                    new ArrayList<>(),
                    true,
                    new Position(Double.parseDouble(map.get("current_lattitude")), Double.parseDouble(map.get("current_longitude")))
            );

            courier.setPersonId(courierRepository.getNextAvailableId());
            courierRepository.insert(courier.getPersonId(), courier);

        }
    }

    @Given("^Dishes:$")
    public void dishes(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {
            // Get restaurant via restaurant id
            Restaurant restaurant = restaurantRepository.findById(Integer.parseInt(map.get("resto_id")));
            Dish dish = new Dish(
                    Integer.parseInt(map.get("preperationTime")),
                    map.get("name"),
                    restaurant,
                    Integer.parseInt(map.get("maxDeliveryTime"))
            );

            dishRepository.insert(Integer.parseInt(map.get("id")), dish);
        }
    }

    @Given("^An order with description \"([^\"]*)\" for dish with id (\\d+) happened (\\d+) minutes in the past and has state \"([^\"]*)\" placed by customer (\\d+)$")
    public void anOrderWithDescriptionForDishWithIdHappenedMinutesInThePastAndHasStatePlacedByCustomer(String description, int dishId, int minutesAgo, String orderState, int customerId) {

        // Get the dish
        Dish dish = dishRepository.findById(dishId);

        // Get the restaurant via the dish
        Restaurant restaurant = dish.getRestaurant();

        // Get the order state
        OrderState state = OrderState.valueOf(orderState);

        // Get the customer
        Customer cmr = customerRepository.findById(customerId);

        // Create the order line
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);
        OrderLine orderLine = new OrderLine(dishes, 1);

        // Add it to a list
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);

        // Create the order
        Order order = new Order(restaurant, LocalDateTime.now().minusMinutes(minutesAgo), state, orderLines, description);
        order.setOrderId(orderRepository.getNextAvailableId());

        // Add order to repository
        orderRepository.insert(order.getOrderId(), order);
    }

    @And("^Courier (\\d+) is active and has (\\d+) deliveryPoints$")
    public void courierIsActiveAndHasDeliveryPoints(int courierId, int deliveryPoints) {
        // Get courier
        Courier courier = courierRepository.findById(courierId);

        courier.setAvailable(true);

        // Set delivery points
        courier.addPointEvent(EventType.TIMELY_DELIVERY, deliveryPoints);
    }

    @When("^Courier (\\d+) asks for list of available deliveries$")
    public void courierAsksForListOfAvailableDeliveries(int courierId) {
        availableDeliveries = deliveryController.showAvailableOrders(courierId);
    }

    @Then("^Courier gets an empty list$")
    public void courierGetsAnEmptyList() {
        // Check if list is empty
        assertEquals(availableDeliveries.size(), 0);
    }

    @Then("^Courier gets a deliverylist with (\\d+) order$")
    public void courierGetsADeliverylistWithOrder(int orderCount) {
        assertEquals(orderCount, availableDeliveries.size());
    }

    @And("^Courier (\\d+) selects available order (\\d+) to deliver$")
    public void courierSelectsAvailableOrderToDeliver(int courierId, int orderId) {
        deliveryController.selectDelivery(orderId, courierId, LocalDateTime.now());
    }

    @And("^The first delivery of the deliverylist has description \"([^\"]*)\"$")
    public void theFirstDeliveryOfTheDeliverylistHasDescription(String description) {
        assertEquals(availableDeliveries.get(0).getDescription(), description);
    }

    @And("^Courier (\\d+) has position (.+) (.+)$")
    public void courierHasPosition(int courierId, double latitude, double longitude) {
        Courier courier = courierRepository.findById(courierId);
        courier.getCurrentPosition().setLattitude(latitude);
        courier.getCurrentPosition().setLongitude(longitude);
    }

    @And("^Order (\\d+) has courier (\\d+) assigned$")
    public void orderHasCourierAssigned(int orderId, int courierId) {
        Order order = orderRepository.findById(orderId);
        assertEquals(order.getCourierId(), courierId);
    }

    @When("^Courier (\\d+) picks up order (\\d+) (\\d+) minutes after selection$")
    public void courierPicksUpOrderMinutesAfterSelection(int courierId, int orderId, int minutes) {
        orderController.pickupOrder(courierId, orderId, minutes);
    }

    @Given("^An order with description \"([^\"]*)\" for dish with index (\\d+) and dish with index (\\d+) happened (\\d+) minutes in the past and has state \"([^\"]*)\" placed by customer (\\d+)$")
    public void anOrderWithDescriptionForDishWithIndexAndDishWithIndexHappenedMinutesInThePastAndHasStatePlacedByCustomer(String description, int firstDishId, int secondDishId, int minutesAgo, String orderState, int customerId) {

        // Get the dishes
        Dish dish1 = dishRepository.findById(firstDishId);
        Dish dish2 = dishRepository.findById(secondDishId);

        // Get the restaurant via the dish
        Restaurant restaurant = dish1.getRestaurant();

        // Get the order state
        OrderState state = OrderState.valueOf(orderState);

        // Get the customer
        Customer cmr = customerRepository.findById(customerId);

        // Create the order line
        List<Dish> dishes1 = new ArrayList<>();
        dishes1.add(dish1);
        List<Dish> dishes2 = new ArrayList<>();
        dishes2.add(dish2);

        OrderLine orderLine1 = new OrderLine(dishes1, 1);
        OrderLine orderLine2 = new OrderLine(dishes2, 1);

        // Add it to a list
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine1);
        orderLines.add(orderLine2);

        // Create the order
        Order order = new Order(restaurant, LocalDateTime.now().minusMinutes(minutesAgo), state, orderLines, description);
        order.setOrderId(orderRepository.getNextAvailableId());

        // Add order to repository
        orderRepository.insert(order.getOrderId(), order);
    }

    @When("^Courier delivers (\\d+) minutes after order (\\d+) placed$")
    public void courierDeliversMinutesAfterOrderPlaced(int minutes, int orderId) {
        Order order = orderRepository.findById(orderId);
        Courier courier = courierRepository.findById(order.getCourierId());

        /** Delivery points toevoegen **/

        // Calculate when the order has actually been delivered
        LocalDateTime orderActuallyDelivered = order.getTimePlaced().plusMinutes(minutes);
        
        DeliveryPointEvent dpe;

        if(orderActuallyDelivered.isBefore(order.getExpectedDeliveryTime())) {
            // Order has been delivered on time
            System.out.println("Order has been delivered on time");
            dpe = new DeliveryPointEvent(50, EventType.TIMELY_DELIVERY);
        } else {
            // Order has been delivered too late
            System.out.println("Order has been delivered too late");
            dpe = new DeliveryPointEvent(-20, EventType.LATE_DELIVERY);
        }
        courier.addPointEvent(dpe);

        /** Order aanpassen **/
        // Update order state
        order.setState(OrderState.DELIVERED);

        // Add an event to the order, so we know when it's been delivered
        OrderEvent oe = new OrderEvent(
                dpe,
                orderActuallyDelivered,
                dpe.getEventType(),
                "Order has been delivered! Enjoy your meal."
        );

        order.addEvent(oe);

        //Update
        //orderRepository.update(order.getOrderId(), order);
    }

    @Then("^State of order (\\d+) is \"([^\"]*)\"$")
    public void stateOfOrderIs(int orderId, String orderState) {
        assertEquals(
                orderRepository.findById(orderId).getState(),
                OrderState.valueOf(orderState)
        );
    }

    @And("^Courier (\\d+) has an deliveryPoint record with type \"([^\"]*)\" with (-?\\d+) points$")
    public void courierHasAnDeliveryPointRecordWithTypeWithPoints(int courierId, String eventType, int deliveryPoints) {
        assertTrue(
                courierRepository.findById(courierId).getDeliveryPointEvents().contains(
                        new DeliveryPointEvent(deliveryPoints, EventType.valueOf(eventType))
                )
        );
    }
}
