package pizzas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pizzas.data.IngredientRepository;
import pizzas.web.api.IngredientController;

@SpringBootTest
public class RestTest { // testing API for interest
    RestTemplate rest = new RestTemplate();

    @Test
    public void createIngredient() { //testing if possible to check ingredient
        rest.postForLocation("http://localhost:8080/ingredients",
                new Ingredient("ThkC", "Thick Crust", Ingredient.Type.CRUST));
    }

}
