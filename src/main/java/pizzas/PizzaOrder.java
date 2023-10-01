package pizzas;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
@Table
public class PizzaOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private Date placedAt;

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street name required")
    private String deliveryStreet;

    @NotBlank(message = "City required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip required")
    private String deliveryZip;

    @CreditCardNumber(message = "Credit card number is invalid")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message = "Wrong format (MM/YY)")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }
}
