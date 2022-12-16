package be.kdg.delidish.bddtests;

import be.kdg.delidish.application.OrderController;
import be.kdg.delidish.repositories.CityRepository;
import be.kdg.delidish.repositories.CourierRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@CucumberContextConfiguration
public class BDDTests {

    private final CourierRepository courierRepository;
    private final OrderController orderController;
    private final CityRepository cityRepository;

    public BDDTests(CourierRepository courierRepository, OrderController orderController, CityRepository cityRepository) {
        this.courierRepository = courierRepository;
        this.orderController = orderController;
        this.cityRepository = cityRepository;
    }

    @Given("cities")
    public void cities(DataTable dataTable) {
        for (Map<String, String> map: dataTable.asMaps()) {
            //cityRepository.save(new City(map.get("postal"), map.get("name"), map.get("country")));
        }
    }

    @Given("^restaurants:$")
    public void restaurants() {
    }

    @Given("^customers:$")
    public void customers() {
    }

    @Given("^deliveryadresses:$")
    public void deliveryadresses() {
    }

    @Given("^Couriers:$")
    public void couriers() {
    }

    @Given("^Dishes:$")
    public void dishes() {
    }

    @Given("^an order with description \"([^\"]*)\" for dish with id (\\d+) happened (\\d+) minutes in the past and has state \"([^\"]*)\" placed by customer (\\d+)$")
    public void anOrderWithDescriptionForDishWithIdHappenedMinutesInThePastAndHasStatePlacedByCustomer(String arg0, int arg1, int arg2, String arg3, int arg4) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Courier (\\d+) is active and has (\\d+) deliveryPoints$")
    public void courierIsActiveAndHasDeliveryPoints(int arg0, int arg1) {
    }

    @When("^Courier (\\d+) asks for list of available deliveries$")
    public void courierAsksForListOfAvailableDeliveries(int arg0) {
    }

    @Then("^Courier gets an empty list$")
    public void courierGetsAnEmptyList() {
    }

    @Then("^Courier gets a deliverylist with (\\d+) order$")
    public void courierGetsADeliverylistWithOrder(int arg0) {
    }

    @And("^Courier (\\d+) selects available order (\\d+) to deliver$")
    public void courierSelectsAvailableOrderToDeliver(int arg0, int arg1) {
    }

    @And("^The first delivery of the deliverylist has description \"([^\"]*)\"$")
    public void theFirstDeliveryOfTheDeliverylistHasDescription(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Courier (\\d+) has position (\\d+)\\.(\\d+) (\\d+)\\.(\\d+)$")
    public void courierHasPosition(int arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    @Then("^state of order (\\d+) is 'ACCEPTED_BY_COURIER'$")
    public void stateOfOrderIsACCEPTED_BY_COURIER(int arg0) {
    }

    @And("^Order (\\d+) has courier (\\d+) assigned$")
    public void orderHasCourierAssigned(int arg0, int arg1) {
    }

    @And("^Courier (\\d+) has an deliveryPoint record with type 'MISSION_ACCEPTED' with (\\d+) points$")
    public void courierHasAnDeliveryPointRecordWithTypeMISSION_ACCEPTEDWithPoints(int arg0, int arg1) {
    }

    @When("^Courier (\\d+) picks up order (\\d+) (\\d+) minutes after selection$")
    public void courierPicksUpOrderMinutesAfterSelection(int arg0, int arg1, int arg2) {
    }

    @Then("^state of order (\\d+) is 'UNDERWAY_FOR_DELIVERY'$")
    public void stateOfOrderIsUNDERWAY_FOR_DELIVERY(int arg0) {
    }

    @And("^Courier (\\d+) has an deliveryPoint record with type 'ORDER_PICKUP_ONTIME' with (\\d+) points$")
    public void courierHasAnDeliveryPointRecordWithTypeORDER_PICKUP_ONTIMEWithPoints(int arg0, int arg1) {
    }

    @Given("^an order with description \"([^\"]*)\" for dish with index (\\d+) and dish with index (\\d+) happened (\\d+) minutes in the past and has state \"([^\"]*)\" placed by customer (\\d+)$")
    public void anOrderWithDescriptionForDishWithIndexAndDishWithIndexHappenedMinutesInThePastAndHasStatePlacedByCustomer(String arg0, int arg1, int arg2, int arg3, String arg4, int arg5) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Courier (\\d+) has an deliveryPoint record with type 'ORDER_PICKUP_LATE' with -(\\d+) points$")
    public void courierHasAnDeliveryPointRecordWithTypeORDER_PICKUP_LATEWithPoints(int arg0, int arg1) {
    }

    @When("^Courier delivers (\\d+) minutes after order (\\d+) placed$")
    public void courierDeliversMinutesAfterOrderPlaced(int arg0, int arg1) {
    }

    @Then("^state of order (\\d+) is 'DELIVERED'$")
    public void stateOfOrderIsDELIVERED(int arg0) {
    }

    @And("^Courier (\\d+) has an deliveryPoint record with type 'ORDER_DELIVERY_ONTIME' with (\\d+) points$")
    public void courierHasAnDeliveryPointRecordWithTypeORDER_DELIVERY_ONTIMEWithPoints(int arg0, int arg1) {
    }

    @And("^Courier (\\d+) has an deliveryPoint record with type 'ORDER_DELIVERY_LATE' with -(\\d+) points$")
    public void courierHasAnDeliveryPointRecordWithTypeORDER_DELIVERY_LATEWithPoints(int arg0, int arg1) {
    }
}
