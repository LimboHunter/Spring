package pizzas.kitchen.messaging.jms.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pizzas.PizzaOrder;
import pizzas.kitchen.KitchenUI;

@Profile("jms-listener")
@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui){
        this.ui = ui;
    }

    @JmsListener(destination = "pizzacloud.order.queue")
    public void receiveOrder(PizzaOrder order){
        ui.displayOrder(order);
    }
}
