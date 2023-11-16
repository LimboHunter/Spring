package pizzas;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Table
@RestResource(rel = "pizzas", path = "pizzas")
@EqualsAndHashCode(exclude = "createdAt")
public class Pizza {

    @Id
    private long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 letters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "Choose at least one element")
    private List<IngredientRef> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(new IngredientRef(ingredient.getId()));
    }
}
