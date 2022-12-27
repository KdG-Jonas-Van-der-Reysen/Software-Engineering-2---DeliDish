package be.kdg.delidish;

import be.kdg.delidish.application.DeliveryController;
import be.kdg.delidish.application.OrderController;
import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.domain.common.ContactInfo;
import be.kdg.delidish.business.domain.common.DeliveryAddress;
import be.kdg.delidish.business.domain.common.Position;
import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.EventType;
import be.kdg.delidish.business.domain.restaurant.DishIngredient;
import be.kdg.delidish.business.factory.*;
import be.kdg.delidish.repositories.memory.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@CucumberContextConfiguration
public class BDDTests {
    // Repositories
    @Autowired
    private CourierMemoryRepository courierRepository;
    @Autowired
    private CityMemoryRepository cityRepository;
    @Autowired
    private RestaurantMemoryRepository restaurantRepository;
    @Autowired
    private CustomerMemoryRepository customerRepository;
    @Autowired
    private DeliveryAddressMemoryRepository deliveryAddressRepository;
    @Autowired
    private DishMemoryRepository dishRepository;
    @Autowired
    private OrderMemoryRepository orderRepository;

    // Controllers
    @Autowired
    private DeliveryController deliveryController;
    @Autowired
    private OrderController orderController;

    // Attributes
    private List<Order> availableDeliveries;

    // Background Data
    @Given("^cities:$")
    public void cities(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {

            cityRepository.insert(
                    cityRepository.getNextAvailableId(),
                    CityFactory.create(
                            Integer.parseInt(map.get("postal")),
                            map.get("name"),
                            map.get("country")
                    )
            );
        }
    }

    @Given("^restaurants:$")
    public void restaurants(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {

            Position restaurantPosition = PositionFactory.create(
                    Double.parseDouble(map.get("adress_lat")),
                    Double.parseDouble(map.get("adress_long"))
            );

            Address restaurantAddress = AddressFactory.create(
                    cityRepository.findById(Integer.parseInt(map.get("city"))),
                    "UNKNOWN",
                    "UNKNOWN",
                    restaurantPosition,
                    cityRepository.findById(Integer.parseInt(map.get("city"))).getCountry()
            );

            ContactInfo contactInfo = ContactInfoFactory.create(
                    restaurantAddress,
                    "UNKNOWN",
                    "UNKNOWN"
            );

            restaurantRepository.insert(
                    restaurantRepository.getNextAvailableId(),
                    RestaurantFactory.create(
                            map.get("Name"),
                            contactInfo
                    )
            );
        }
    }

    @Given("^customers:$")
    public void customers(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {

            customerRepository.insert(
                    customerRepository.getNextAvailableId(),
                    CustomerFactory.create(
                            map.get("firstName"),
                            map.get("lastName")
                    )
            );
        }
    }

    @Given("^deliveryadresses:$")
    public void deliveryadresses(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {

            Position deliveryPosition = PositionFactory.create(
                    Double.parseDouble(map.get("lattitude")),
                    Double.parseDouble(map.get("longitude"))
            );

            DeliveryAddress deliveryAddress = DeliveryAddressFactory.create(
                    cityRepository.findById(Integer.parseInt(map.get("city_id"))),
                    map.get("street"),
                    map.get("number"),
                    deliveryPosition,
                    cityRepository.findById(Integer.parseInt(map.get("city_id"))).getCountry(),
                    customerRepository.findById(Integer.parseInt(map.get("customer_id")))
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

            Courier courier = CourierFactory.create(
                    map.get("FirstName"),
                    map.get("LastName"),
                    new ArrayList<>(),
                    true,
                    PositionFactory.create(
                            Double.parseDouble(map.get("current_lattitude")),
                            Double.parseDouble(map.get("current_longitude"))
                    )
            );

            courierRepository.insert(courierRepository.getNextAvailableId(), courier);
        }
    }

    @Given("^Dishes:$")
    public void dishes(DataTable dataTable) {
        for (Map<String, String> map : dataTable.asMaps()) {

            DishIngredient dishIngredient = DishFactory.createIngredient(
                    Integer.parseInt(map.get("preperationTime")),
                    map.get("name"),
                    restaurantRepository.findById(Integer.parseInt(map.get("resto_id"))),
                    Integer.parseInt(map.get("maxDeliveryTime"))
            );

            dishRepository.insert(Integer.parseInt(map.get("id")), dishIngredient);
        }
    }

    // Tests
    @Given("^An order with description \"([^\"]*)\" for dish with id (\\d+) happened (\\d+) minutes in the past and has state \"([^\"]*)\" placed by customer (\\d+)$")
    public void anOrderWithDescriptionForDishWithIdHappenedMinutesInThePastAndHasStatePlacedByCustomer(String description, int dishId, int minutesAgo, String orderState, int customerId) {
        // Clear order repository
        orderRepository.clear();

        // Add via manager
        orderController.addOrderWithDishForCustomer(description, dishId, minutesAgo, orderState, customerId);
    }

    @And("^Courier (\\d+) is active and has (\\d+) deliveryPoints$")
    public void courierIsActiveAndHasDeliveryPoints(int courierId, int deliveryPoints) {
        // Check if the deliveryPoints amount is divisible by 50
        if(deliveryPoints % 50 != 0) {
            throw new IllegalArgumentException("DeliveryPoints " + deliveryPoints + " is not divisible by 50.");
        }

        // Get courier
        Courier courier = courierRepository.findById(courierId);

        courier.setAvailable(true);

        // Set delivery points (Add 50 points until the delivery points are all added)
        while(courier.getTotalDeliveryPoints() < deliveryPoints) {
            // For example if we want to add 100 deliveryPoints, we'll create two events from 50 points each
            courier.addPointEvent(EventType.TIMELY_DELIVERY);
        }
    }

    @When("^Courier (\\d+) asks for list of available deliveries$")
    public void courierAsksForListOfAvailableDeliveries(int courierId) {
        availableDeliveries = deliveryController.showAvailableOrders(courierId);
    }

    @Then("^Courier gets an empty list$")
    public void courierGetsAnEmptyList() {
        assertEquals(0, availableDeliveries.size());
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

        Courier courier = courierRepository.findById(courierId);
        assertEquals(order.getCourier(), courier);
    }

    @When("^Courier (\\d+) picks up order (\\d+) (\\d+) minutes after selection$")
    public void courierPicksUpOrderMinutesAfterSelection(int courierId, int orderId, int minutes) {
        orderController.pickupOrder(courierId, orderId, minutes);
    }

    @Given("^An order with description \"([^\"]*)\" for dish with index (\\d+) and dish with index (\\d+) happened (\\d+) minutes in the past and has state \"([^\"]*)\" placed by customer (\\d+)$")
    public void anOrderWithDescriptionForDishWithIndexAndDishWithIndexHappenedMinutesInThePastAndHasStatePlacedByCustomer(String description, int firstDishId, int secondDishId, int minutesAgo, String orderState, int customerId) {
        orderRepository.clear();

        List<Integer> dishes = new ArrayList<>();
        dishes.add(firstDishId, secondDishId);

        orderController.addOrderWithDishesForCustomer(description, dishes, minutesAgo, orderState, customerId);
    }

    @When("^Courier delivers (\\d+) minutes after order (\\d+) placed$")
    public void courierDeliversMinutesAfterOrderPlaced(int minutes, int orderId) {
        orderController.pickupOrderAfterMinutes(minutes, orderId);
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
                courierRepository.findById(courierId).getDeliveryPointEvents().stream().anyMatch(
                        dpe ->
                            dpe.getPoints() == deliveryPoints &&
                            dpe.equals(DeliveryPointEventFactory.create(EventType.valueOf(eventType)))
                )
        );
    }
}
