package pizzas;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;
@Data
public class PizzaOrder {
    private String deliveryName;
    private String deliverySteet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }
}
