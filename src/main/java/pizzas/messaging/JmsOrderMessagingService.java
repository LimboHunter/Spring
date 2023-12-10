package pizzas.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import pizzas.PizzaOrder;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class JmsOrderMessagingService implements OrderMessagingService{

    private JmsTemplate jms;


    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms){
        this.jms = jms;
    }

    @Override
    public void sendOrder(PizzaOrder order){
        jms.convertAndSend("pizzacloud.order.queue", order,
        this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException{
        message.setStringProperty("X_ORDER_SOURCE","WEB");
        return message;
    }
}
