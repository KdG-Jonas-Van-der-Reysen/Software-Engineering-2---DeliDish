package be.kdg.delidish;

import be.kdg.delidish.application.DeliveryController;

public class Main {
    public static void main(String[] args) {
        DeliveryController controller = DeliveryController.getInstance();

        controller.showAvailableOrders(0).forEach(System.out::println);
    }
}
