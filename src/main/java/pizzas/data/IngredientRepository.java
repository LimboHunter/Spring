package pizzas.data;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import pizzas.Ingredient;

@Table("Ingredient_Ref")
public interface IngredientRepository extends
        CrudRepository<Ingredient, String> {

}
