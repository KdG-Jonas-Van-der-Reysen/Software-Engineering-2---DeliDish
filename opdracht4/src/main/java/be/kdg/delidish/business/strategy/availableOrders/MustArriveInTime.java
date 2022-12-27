package be.kdg.delidish.business.strategy.availableOrders;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.repositories.memory.CourierMemoryRepository;
import be.kdg.delidish.repositories.memory.OrderMemoryRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component("MustArriveInTime")
public class MustArriveInTime implements AvailableOrdersStrategy {

    private final OrderMemoryRepository orderRepository;
    private final CourierMemoryRepository courierRepository;

    public MustArriveInTime(OrderMemoryRepository orderRepository, CourierMemoryRepository courierRepository) {
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
    }

        @Override
        public List<Order> getAvailableOrders(int courierId) {
            // Eerst alle orders opvragen en filteren op status ORDER_PLACED --> Dat zijn alle orders die nog geleverd moeten worden
            List<Order> orders = orderRepository.getAll().stream().filter(order -> order.getState() == OrderState.ORDER_PLACED).toList();

            Courier courier = courierRepository.findById(courierId);
            if (courier == null) {   //if the courier is invalid return empty list
                return orders;
            }

            // Per order
            // Lijst ophalen van in aanmerking komende koeriers per order (getApplicableCouriersForOrder)
            return orders.stream().filter(order -> {
                List<Courier> applicableCouriers = getApplicableCouriersForOrder(order);
                // --> Zit onze koerier erbij?
                if (applicableCouriers.contains(courier)) {
                    // Hoe lang geleden is het order geplaatst?
                    // Langer dan 5 min geleden --> order is available voor deze koerier
                    if (order.getTimePlaced().isBefore(LocalDateTime.now().minusMinutes(5))) {
                        return true;
                    } else {
                        // gemiddelde berekenen van alle in aanmerking komende koeriers
                        // Hoger dan gemiddelde? --> order is available voor deze koerier
                        return courier.getTotalDeliveryPoints() >= order.getAverageDeliveryPoints(applicableCouriers);
                    }
                }
                return false;
            }).toList();
        }

    // Lijst maken van koeriers die in aanmerking komen voor een order
    private List<Courier> getApplicableCouriersForOrder(Order order) {
        // Alle koeriers ophalen en filteren op het volgende
        return courierRepository.getAll().stream().filter(courier -> courier.willArriveInTimeForOrder(order)).toList();
    }
}
