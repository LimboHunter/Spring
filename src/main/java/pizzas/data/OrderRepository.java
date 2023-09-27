package pizzas.data;

import pizzas.Pizza;
import pizzas.PizzaOrder;

public interface OrderRepository {

    PizzaOrder save(PizzaOrder order);

}
