package pizzas.data;

import org.springframework.data.repository.CrudRepository;
import pizzas.IngredientRef;

public interface IngredientRefRepository extends
        CrudRepository<IngredientRef, String> {
}
