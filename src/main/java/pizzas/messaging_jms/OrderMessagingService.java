package pizzas.messaging_jms;

import pizzas.PizzaOrder;

public interface OrderMessagingService {

    void sendOrder(PizzaOrder order);
}
