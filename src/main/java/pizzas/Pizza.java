package pizzas;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
@Data
public class Pizza {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 letters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "Choose at least one element")
    private List<Ingredient> ingredients;
    int a = -1;

}
