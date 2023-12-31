package pizzas.messaging_jms;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import pizzas.PizzaOrder;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter(){
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String , Class<?>> typeIdMapping = new HashMap<String, Class<?>>();
        typeIdMapping.put("order",PizzaOrder.class);
        messageConverter.setTypeIdMappings(typeIdMapping);

        return messageConverter;
    }

    @Bean
    public Destination orderQueue(){
        return new ActiveMQQueue("pizzacloud.order.queue");
    }
}
