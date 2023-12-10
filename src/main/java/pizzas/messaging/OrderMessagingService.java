package pizzas.messaging;

import pizzas.PizzaOrder;

public interface OrderMessagingService {

    void sendOrder(PizzaOrder order);
}
