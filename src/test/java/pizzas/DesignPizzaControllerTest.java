package pizzas;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import pizzas.Ingredient.Type;
import pizzas.data.IngredientRepository;
import pizzas.data.OrderRepository;
import pizzas.data.PizzaRepository;
import pizzas.data.UserRepository;
import pizzas.web.DesignPizzaController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DesignPizzaController.class)
public class DesignPizzaControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private List<Ingredient> ingredients;

  private Pizza design;

  @MockBean
  private IngredientRepository ingredientRepository;

  @MockBean
  private PizzaRepository designRepository;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private UserRepository userRepository;

  @BeforeEach
  public void setup() {
    ingredients = Arrays.asList(
             new Ingredient("ThnC", "Thin Crust", Type.CRUST),
     new Ingredient("ThkC", "Thick Crust", Type.CRUST),
     new Ingredient("WFC", "Wood-Fired Crust", Type.CRUST),
     new Ingredient("TS", "Tomato Sause", Type.SAUCE),
     new Ingredient("WS", "White Sause", Type.SAUCE),
     new Ingredient("SLM", "Salami", Type.MEAT),
     new Ingredient("COCK", "Chicken", Type.MEAT),
     new Ingredient("BCN", "Bacon", Type.MEAT),
     new Ingredient("ONS", "Onions", Type.VEGGIES),
     new Ingredient("SWPS", "Sweet Papers", Type.VEGGIES),
     new Ingredient("PIAP", "Pinespple", Type.VEGGIES),
     new Ingredient("TMS", "Tomatoes", Type.VEGGIES),
     new Ingredient("MSRM", "Mushroom", Type.VEGGIES),
     new Ingredient("MOZA", "Mozarella", Type.CHEESE),
     new Ingredient("Prov", "Provolone", Type.CHEESE),
     new Ingredient("GC", "Goat Cheese", Type.CHEESE),
     new Ingredient("RC", "Ricotta Cheese", Type.CHEESE)

    );

    when(ingredientRepository.findAll())
        .thenReturn(ingredients);

    when(ingredientRepository.findById("ThkC")).thenReturn(Optional.of(new Ingredient("ThkC", "Thick Crust", Type.CRUST)));
    when(ingredientRepository.findById("COCK")).thenReturn(Optional.of(new Ingredient("COCK", "Chicken", Type.MEAT)));
    when(ingredientRepository.findById("RC")).thenReturn(Optional.of(new Ingredient("RC", "Ricotta Cheese", Type.CHEESE)));

    design = new Pizza();
    design.setName("Test Pizza");
    List<Ingredient> tempIngredient = (Arrays.asList(
            new Ingredient("ThkC", "Thick Crust", Type.CRUST),
            new Ingredient("COCK", "Chicken", Type.MEAT),
            new Ingredient("RC", "Ricotta Cheese", Type.CHEESE)
    ));

    when(userRepository.findByUsername("test"))
    		.thenReturn(new User("test", "123", "test", "test", "test", "test", "test", "test"));
  }

  @Test
  @WithMockUser(username="test", password="123")
  public void testShowDesignForm() throws Exception {
	mockMvc.perform(get("/design"))
        .andExpect(status().isOk())
        .andExpect(view().name("design"))
        .andExpect(model().attribute("crust", ingredients.subList(0, 3)))
        .andExpect(model().attribute("sauce", ingredients.subList(3, 5)))
        .andExpect(model().attribute("meat", ingredients.subList(5, 8)))
        .andExpect(model().attribute("veggies", ingredients.subList(8, 13)))
        .andExpect(model().attribute("cheese", ingredients.subList(13, 17)));
  }

  @Test
  @WithMockUser(username="test", password="123", authorities="ROLE_USER")
  public void processPizza() throws Exception {
    when(designRepository.save(design))
        .thenReturn(design);

    when(userRepository.findByUsername("test"))
	.thenReturn(new User("test", "123", "test", "test", "test", "test", "test", "test"));

    mockMvc.perform(post("/design").with(csrf())
        .content("name=Test+Pizza&ingredients=ThkC,COCK,RC")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().stringValues("Location", "/orders/current"));
  }

}
