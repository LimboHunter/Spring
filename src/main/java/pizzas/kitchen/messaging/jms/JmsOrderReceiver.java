package pizzas.kitchen.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pizzas.PizzaOrder;
import pizzas.kitchen.OrderReceiver;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms){
        this.jms = jms;
    }

    public PizzaOrder receiveOrder(){
        return (PizzaOrder) jms.receiveAndConvert("pizzacloud.order.queue");
    }
}
