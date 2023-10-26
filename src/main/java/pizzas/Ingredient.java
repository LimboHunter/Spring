package pizzas;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "ingredient")
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient{

    @Id
    private final String id;

    private final String name;
    private final Type type;

    public enum Type{
        CRUST, VEGGIES, MEAT, CHEESE, SAUCE
    }

}
