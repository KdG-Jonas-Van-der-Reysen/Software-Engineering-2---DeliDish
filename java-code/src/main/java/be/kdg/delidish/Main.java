package be.kdg.delidish;

import be.kdg.delidish.application.DeliveryController;
import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.domain.common.ContactInfo;
import be.kdg.delidish.business.domain.common.Position;
import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderLine;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.Partner;
import be.kdg.delidish.business.domain.restaurant.Dish;
import be.kdg.delidish.business.domain.restaurant.Restaurant;
import be.kdg.delidish.persistence.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Eerst efkes een controller maken
        DeliveryController controller = DeliveryController.getInstance();

        // Repository seeden
        controller.addCourier(new Courier(
                "Frits",
                "Zonder ballekes",
                new Partner("zdzdf965"),
                new ArrayList<DeliveryPointEvent>(),
                true,
                new Position(1, 2)
        ));


        List<Dish> ballekesDishes = new ArrayList<>();
        List<OrderLine> orderlinesBallekes = new ArrayList<>();
        ballekesDishes.add(new Dish(30, "ballekes in witte saus"));
        orderlinesBallekes.add(new OrderLine(ballekesDishes, 5));

        controller.addOrder(new Order(
                new Restaurant(
                        "Balls & Glory",
                        new ContactInfo(
                                new Address(
                                        null,
                                        null,
                                        "1",
                                        new Position(1, 2),
                                        "",
                                        0
                                ),
                                "info@ballsnglory.com",
                                "+32 475 37 92 10"
                        )
                ),
                LocalDateTime.now().minusMinutes(6),
                OrderState.ORDER_PLACED,
                orderlinesBallekes
        ));

        Courier frits = controller.getCourier(0);

        // Koerier
        System.out.println("Koerier " + frits.getFullName() + " vraagt een lijst met de beschikbare orders op. Hij krijgt deze orders");
        controller.showAvailableOrders(frits.getPersonId()).forEach(System.out::println);

        // Asign frits to ballekes
        controller.selectDelivery(controller.showAvailableOrders(frits.getPersonId()).get(0).getOrderId(), frits.getPersonId());
        System.out.println("courier assigned to: " + OrderRepository.INSTANCE.get(0).getCourierId());
    }
}
