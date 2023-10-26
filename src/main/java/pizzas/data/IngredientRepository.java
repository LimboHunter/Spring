package pizzas.data;

import org.springframework.data.repository.CrudRepository;
import pizzas.Ingredient;

import javax.persistence.Table;

public interface IngredientRepository extends
        CrudRepository<Ingredient, String> {

}
