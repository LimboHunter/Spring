package pizzas;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table
@RequiredArgsConstructor
public class Ingredient{

    @Id
    private final String id;

    private final String name;
    private final Type type;

    public enum Type{
        CRUST, VEGGIES, MEAT, CHEESE, SAUCE
    }

}
