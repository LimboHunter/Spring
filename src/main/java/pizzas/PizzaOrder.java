package pizzas;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table()
public class PizzaOrder implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long user;

    @Id
    private long id;

    private Date placedAt;

    @NotBlank(message = "Необходимо Имя")
    private String deliveryName;

    @NotBlank(message = "Необходима Улица")
    private String deliveryStreet;

    @NotBlank(message = "Необходим Город")
    private String deliveryCity;

    @NotBlank(message = "Необходим Округ")
    private String deliveryState;

    @NotBlank(message = "Необходим Zip")
    private String deliveryZip;

    @CreditCardNumber(message = "Неверный номер карты")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message = "Неверный формат (ММ/ГГ)")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Неверный CVV")
    private String ccCVV;

    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza){
        this.placedAt();
        this.pizzas.add(pizza);
    }

    void placedAt(){
        this.placedAt = new Date();
    }
}
