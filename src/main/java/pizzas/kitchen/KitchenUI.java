package pizzas.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pizzas.PizzaOrder;

@Component
@Slf4j
public class KitchenUI {

    public void displayOrder(PizzaOrder order){
      log.info("RECEIVED ORDER: " + order);
    }
}
